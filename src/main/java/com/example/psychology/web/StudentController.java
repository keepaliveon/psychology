package com.example.psychology.web;

import com.example.psychology.Comman.ChatSession;
import com.example.psychology.Comman.PageInfo;
import com.example.psychology.entity.Student;
import com.example.psychology.entity.Subscribe;
import com.example.psychology.entity.Teacher;
import com.example.psychology.service.StudentService;
import com.example.psychology.service.SubscribeService;
import com.example.psychology.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @Resource
    private TeacherService teacherService;

    @Resource
    private SubscribeService subscribeService;

    @GetMapping("login")
    public String login() {
        return "/student/login";
    }

    @GetMapping("register")
    public String register() {
        return "/student/register";
    }

    @GetMapping("password")
    public ModelAndView password(HttpSession session) {
        Student student = (Student) session.getAttribute("currentStudent");
        ModelAndView modelAndView = new ModelAndView("/student/password");
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @GetMapping("home")
    public ModelAndView home(HttpSession session) {
        Student student = (Student) session.getAttribute("currentStudent");
        ModelAndView modelAndView = new ModelAndView("/student/home");
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @GetMapping("chat")
    public ModelAndView session(HttpSession session) {
        Student student = (Student) session.getAttribute("currentStudent");
        ModelAndView modelAndView = new ModelAndView("/student/chat");
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @GetMapping("consult")
    public ModelAndView consult(HttpSession session) {
        Student student = (Student) session.getAttribute("currentStudent");
        ModelAndView modelAndView = new ModelAndView("/student/consult");
        modelAndView.addObject("student", student);
        List<Teacher> teacherList = teacherService.list();
        for (Teacher item : teacherList) {
            List<Subscribe> subscribeList = item.getSubscribeList();
            for (Subscribe subscribe : subscribeList) {
                if (subscribe.getStudent().getId().equals(student.getId())) {
                    item.setSubscribed(true);
                }
            }
        }
        modelAndView.addObject("teachers", teacherList);
        return modelAndView;
    }

    @PostMapping("doLogin")
    public String signIn(@RequestParam String id, @RequestParam String password, HttpSession session, RedirectAttributes attributes) {
        Student student = studentService.signIn(id, password);
        if (student == null) {
            attributes.addFlashAttribute("info", new PageInfo("error", "登陆失败，请检查用户名和密码"));
            return "redirect:/student/login";
        } else {
            session.setAttribute("currentStudent", student);
            return "redirect:/student/home";
        }
    }

    @PostMapping("doRegister")
    public String doRegister(Student student, RedirectAttributes attributes) {
        studentService.save(student);
        attributes.addFlashAttribute("info", new PageInfo("success", "注册成功"));
        return "redirect:/student/login";
    }

    @PostMapping("doUpdate")
    public String doUpdate(Student student, HttpSession session, RedirectAttributes attributes) {
        session.setAttribute("currentStudent", studentService.save(student));
        attributes.addFlashAttribute("info", new PageInfo("success", "信息修改成功"));
        return "redirect:/student/home";
    }

    @GetMapping("doLogout")
    public String doLogout(HttpSession session, RedirectAttributes attributes) {
        session.invalidate();
        attributes.addFlashAttribute("info", new PageInfo("success", "注销登陆成功"));
        return "redirect:/student/login";
    }

    @GetMapping("doSubscribe")
    public String doSubscribe(@RequestParam String id, RedirectAttributes attributes, HttpSession session) {
        Student student = (Student) session.getAttribute("currentStudent");
        subscribeService.create(student.getId(), id);
        ChatSession chatSession = new ChatSession();
        chatSession.setFromId(student.getId());
        chatSession.setToId(id);
        attributes.addFlashAttribute("chatSession", chatSession);
        return "redirect:/student/chat";
    }

    @PostMapping("doPassword")
    public String doPassword(@RequestParam String password, @RequestParam String oldPassword, RedirectAttributes attributes, HttpSession session) {
        Student current = (Student) session.getAttribute("currentStudent");
        String id = current.getId();
        Student old = studentService.findById(id);
        if (old.getPassword().equals(oldPassword)) {
            old.setPassword(password);
            studentService.save(old);
            session.invalidate();
            attributes.addFlashAttribute("info", new PageInfo("success", "密码修改成功，请重新登陆"));
            return "redirect:/student/login";
        }
        attributes.addFlashAttribute("info", new PageInfo("error", "密码修改失败"));
        return "redirect:/student/password";
    }
}
