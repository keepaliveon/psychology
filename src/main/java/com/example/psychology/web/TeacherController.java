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

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @Resource
    private StudentService studentService;

    @Resource
    private SubscribeService subscribeService;

    @GetMapping("login")
    public String login() {
        return "/teacher/login";
    }

    @GetMapping("register")
    public String register() {
        return "/teacher/register";
    }

    @GetMapping("password")
    public ModelAndView password(HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("currentTeacher");
        ModelAndView modelAndView = new ModelAndView("/teacher/password");
        modelAndView.addObject("teacher", teacher);
        return modelAndView;
    }

    @GetMapping("home")
    public ModelAndView home(HttpSession session) {
        Teacher currentTeacher = (Teacher) session.getAttribute("currentTeacher");
        Teacher teacher = teacherService.findById(currentTeacher.getId());
        ModelAndView modelAndView = new ModelAndView("/teacher/home");
        modelAndView.addObject("teacher", teacher);
        modelAndView.addObject("subscribeList", teacher.getSubscribeList());
        return modelAndView;
    }

    @GetMapping("chat")
    public ModelAndView session(HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("currentTeacher");
        ModelAndView modelAndView = new ModelAndView("/teacher/chat");
        modelAndView.addObject("teacher", teacher);
        return modelAndView;
    }

    @GetMapping("profile")
    public ModelAndView profile(HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("currentTeacher");
        ModelAndView modelAndView = new ModelAndView("/teacher/profile");
        modelAndView.addObject("teacher", teacher);
        return modelAndView;
    }

    @GetMapping("student")
    public ModelAndView student(HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("currentTeacher");
        ModelAndView modelAndView = new ModelAndView("/teacher/student");
        modelAndView.addObject("teacher", teacher);
        modelAndView.addObject("students", studentService.list());
        return modelAndView;
    }

    @GetMapping("document")
    public ModelAndView document(@RequestParam String id, HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("currentTeacher");
        ModelAndView modelAndView = new ModelAndView("/teacher/document");
        modelAndView.addObject("teacher", teacher);
        modelAndView.addObject("student", studentService.findById(id));
        return modelAndView;
    }

    @PostMapping("doLogin")
    public String signIn(@RequestParam String id, @RequestParam String password, HttpSession session, RedirectAttributes attributes) {
        Teacher teacher = teacherService.signIn(id, password);
        if (teacher == null) {
            attributes.addFlashAttribute("info", new PageInfo("error", "登陆失败，请检查用户名和密码"));
            return "redirect:/teacher/login";
        } else {
            session.setAttribute("currentTeacher", teacher);
            return "redirect:/teacher/home";
        }
    }

    @PostMapping("doRegister")
    public String doRegister(Teacher teacher, RedirectAttributes attributes) {
        teacherService.save(teacher);
        attributes.addFlashAttribute("info", new PageInfo("success", "注册成功"));
        return "redirect:/teacher/login";
    }

    @PostMapping("doUpdate")
    public String doUpdate(Teacher teacher, HttpSession session, RedirectAttributes attributes) {
        session.setAttribute("currentTeacher", teacherService.save(teacher));
        attributes.addFlashAttribute("info", new PageInfo("success", "信息修改成功"));
        return "redirect:/teacher/profile";
    }

    @PostMapping("doDocument")
    public String doDocument(Student student, HttpSession session) {
        studentService.save(student);
        return "redirect:/teacher/student";
    }

    @GetMapping("doLogout")
    public String doLogout(HttpSession session, RedirectAttributes attributes) {
        session.invalidate();
        attributes.addFlashAttribute("info", new PageInfo("success", "注销登陆成功"));
        return "redirect:/teacher/login";
    }

    @GetMapping("doSubscribe")
    public String doSubscribe(@RequestParam String id, RedirectAttributes attributes) {
        Subscribe subscribe = subscribeService.find(id);
        subscribeService.remove(id);
        ChatSession chatSession = new ChatSession();
        chatSession.setFromName(subscribe.getTeacher().getName());
        chatSession.setFromId(subscribe.getTeacher().getId());
        chatSession.setToName(subscribe.getTeacher().getName());
        chatSession.setToId(subscribe.getStudent().getId());
        attributes.addAttribute("chatSession", chatSession);
        return "redirect:/teacher/chat";
    }

    @PostMapping("doPassword")
    public String doPassword(@RequestParam String password, @RequestParam String oldPassword, HttpSession session, RedirectAttributes attributes) {
        Teacher current = (Teacher) session.getAttribute("currentTeacher");
        String id = current.getId();
        Teacher old = teacherService.findById(id);
        if (old.getPassword().equals(oldPassword)) {
            old.setPassword(password);
            teacherService.save(old);
            session.invalidate();
            attributes.addFlashAttribute("info", new PageInfo("success", "密码修改成功，请重新登陆"));
            return "redirect:/teacher/login";
        }
        attributes.addFlashAttribute("info", new PageInfo("error", "密码修改失败"));
        return "redirect:/teacher/password";
    }
}
