package com.tuzhi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @program: SpringMVC-study
 * @description:固定参数
 * @author: 兔子
 * @create: 2021-12-01 21:15
 **/

@Controller
public class ParamController {

    @GetMapping("Param/p1/{a}/{b}")
    public String p1(@PathVariable int a, @PathVariable int b, Model model) {
        int result = a + b;
        model.addAttribute("msg","结果是："+result);
        return "hello";
    }
    @PostMapping("Param/p1")
    public String p2(int a, int b, Model model) {
        int result = a + b;
        model.addAttribute("msg","结果是："+result);
        return "hello";
    }

}
