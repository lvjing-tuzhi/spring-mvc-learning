package com.tuzhi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @program: SpringMVC-study
 * @description:
 * @author: 兔子
 * @create: 2021-12-08 18:11
 **/

@Controller
@RequestMapping("user")
public class LoginController {

    @RequestMapping("goLogin")
    public String login() {
        return "login";
    }

    @RequestMapping("login")
    public String login(String username, String password, HttpSession httpSession) {
        httpSession.setAttribute("username",username);
        return "home";
    }
    @RequestMapping("loginout")
    public String loginout(HttpSession httpSession) {
        httpSession.removeAttribute("username");
        return "redirect:/";
    }
    @RequestMapping("home")
    public String home() {
        return "home";
    }
}
