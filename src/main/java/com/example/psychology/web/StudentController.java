package com.example.psychology.web;

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
    public String signIn(@RequestParam String id, @RequestParam String password, HttpSession session) {
        Student student = studentService.signIn(id, password);
        if (student == null) {
            return "redirect:/student/login";
        } else {
            session.setAttribute("currentStudent", student);
            return "redirect:/student/home";
        }
    }

    @PostMapping("doRegister")
    public String doRegister(Student student) {
        studentService.save(student);
        return "redirect:/student/login";
    }

    @PostMapping("doUpdate")
    public String doUpdate(Student student, HttpSession session) {
        session.setAttribute("currentStudent", studentService.save(student));
        return "redirect:/student/home";
    }

    @GetMapping("doLogout")
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/student/login";
    }

    @GetMapping("doSubscribe")
    public String doSubscribe(@RequestParam String id, HttpSession session) {
        Student student = (Student) session.getAttribute("currentStudent");
        subscribeService.create(student.getId(), id);
        return "redirect:/student/chat";
    }

    @GetMapping("doConsult")
    public String doConsult(@RequestParam String id, HttpSession session) {
        return "redirect:/student/login";
    }

    @PostMapping("doPassword")
    public String doPassword(@RequestParam String password, @RequestParam String oldPassword, HttpSession session) {
        Student current = (Student) session.getAttribute("currentStudent");
        String id = current.getId();
        Student old = studentService.findById(id);
        if (old.getPassword().equals(oldPassword)) {
            old.setPassword(password);
            studentService.save(old);
            session.invalidate();
            return "redirect:/student/login";
        }
        return "redirect:/student/password";
    }
}
