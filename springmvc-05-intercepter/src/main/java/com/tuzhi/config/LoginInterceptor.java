package com.tuzhi.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: SpringMVC-study
 * @description:
 * @author: 兔子
 * @create: 2021-12-08 18:36
 **/

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.err.println("======" + request.getRequestURI() + "======");
        if(request.getRequestURI().contains("ogin")) {
            return true;
        }
        if(request.getSession().getAttribute("username") != null) {
            return true;
        }
        response.sendRedirect("/user/goLogin");
        return false;
    }
}
