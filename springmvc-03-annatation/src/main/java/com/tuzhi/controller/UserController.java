package com.tuzhi.controller;

import com.tuzhi.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @program: SpringMVC-study
 * @description:
 * @author: 兔子
 * @create: 2021-12-03 09:31
 **/
@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("add")
    public String add(@RequestParam("username") String name , Model model) {
        model.addAttribute("msg",name);
        return "test02";
    }

    @GetMapping("update")
    public String update(User user) {
        System.out.println(user);
        return "test02";
    }

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public String testEcoding(@RequestParam("name") String name,Model model) {
        model.addAttribute("msg",name);
        System.out.println(name);
        return "test02";
    }

}
