package com.tuzhi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringMVC-study
 * @description:
 * @author: 兔子
 * @create: 2021-12-08 17:22
 **/

@RestController
public class TestController {
    @RequestMapping("t1")
    public String test() {
        System.out.println("执行test");
        return "testOk";
    }
}
