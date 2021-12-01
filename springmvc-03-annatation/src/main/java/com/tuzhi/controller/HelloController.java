package com.tuzhi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: SpringMVC-study
 * @description:
 * @author: 兔子
 * @create: 2021-12-01 19:24
 **/

@Controller
@RequestMapping("HelloController")
public class HelloController{
//    真实访问地址/HelloController/hello
    @RequestMapping("hello")
    public String hello(Model model) {
        model.addAttribute("msg","helloSpringMVC");
        //web-inf/jsp/hello.jsp
        return "hello";
    }
    @RequestMapping("hello02")
    public String hello02(Model model) {
        model.addAttribute("msg","hello02");
        return "hello";
    }
}
