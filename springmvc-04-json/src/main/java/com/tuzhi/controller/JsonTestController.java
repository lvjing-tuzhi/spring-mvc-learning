package com.tuzhi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuzhi.pojo.User;
import com.tuzhi.util.JsonUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: SpringMVC-study
 * @description:
 * @author: 兔子
 * @create: 2021-12-05 20:39
 **/

//使类里面的方法都不经过视图解析器
@RestController
public class JsonTestController {


    @RequestMapping(value = "/j1")
//    使方法不经过视图解析器
//    @RequestBody
    public String json1() {
        User user = new User("吕竟", "男");
        return JsonUtil.setJson(user);
    }

    @RequestMapping("/j2")
    public String json2() {
        List list = new ArrayList();
        User user = new User("吕竟", "nv");
        User user1 = new User("吕竟", "nv");
        User user2 = new User("吕竟", "nv");
        list.add(user);
        list.add(user1);
        list.add(user2);
        return JsonUtil.setJson(list);
    }

    @RequestMapping("/j3")
    public String json3() {
        Date date = new Date();
        return JsonUtil.setJson(date);
    }

}
