package com.example.psychology.web;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

    private final String[] whiteList = new String[]{
            "/student/login",
            "/student/doLogin",
            "/student/register",
            "/student/doRegister",
            "/student/doLogout",
            "/teacher/login",
            "/teacher/doLogin",
            "/teacher/register",
            "/teacher/doRegister",
            "/teacher/doLogout"
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        for (String s : whiteList) {
            if (uri.equals(s)) {
                return true;
            }
        }
        if (uri.contains("/student")) {
            if (request.getSession().getAttribute("currentStudent") != null) {
                return true;
            } else {
                response.sendRedirect("/student/login");
                return false;
            }
        }
        if (uri.contains("/teacher")) {
            if (request.getSession().getAttribute("currentTeacher") != null) {
                return true;
            } else {
                response.sendRedirect("/teacher/login");
                return false;
            }
        }
        response.sendRedirect("/student/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
