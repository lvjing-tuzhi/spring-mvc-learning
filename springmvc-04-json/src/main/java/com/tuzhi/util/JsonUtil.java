package com.tuzhi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

/**
 * @program: SpringMVC-study
 * @description:JSON工具类
 * @author: 兔子
 * @create: 2021-12-05 22:11
 **/

public class JsonUtil {
    public static String setJson(Object o) {
        return setJson(o,"yyyy-MM-dd HH:mm:ss");
    }
    public static String setJson(Object o,String form) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(simpleDateFormat);
        try {
            String s = mapper.writeValueAsString(o);
            return s;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
