# 1、Hello SpringMVC

## 1.1、导入maven依赖

~~~xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.3.9</version>
</dependency>
<!-- https://mvnrepository.com/artifact/javax.servlet/servlet-api -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>servlet-api</artifactId>
    <version>2.5</version>
</dependency>
<dependency>
    <groupId>javax.servlet.jsp</groupId>
    <artifactId>jsp-api</artifactId>
    <version>2.1</version>
</dependency>
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <version>1.2</version>
</dependency>
~~~

## 1.2、配置web.xml文件

~~~xml
<!--    注册servlet-->
    <servlet>
        <servlet-name>SpringMVC</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
<!--        通过初始化参数指定SpringMVC配置文件的位置，进行关联-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc-servlet.xml</param-value>
        </init-param>
<!--        启动顺序，数字越小启动越早-->
        <load-on-startup>1</load-on-startup>
    </servlet>
<!--    所有请求都会被servlet拦截：其中/不包括jsp，/*包括jsp-->
    <servlet-mapping>
        <servlet-name>SpringMVC</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
~~~

## 1.3、创建springmvc-servlet.xml文件

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!-- 自动扫描包，让指定包下的注解生效,由IOC容器统一管理 -->
    <context:component-scan base-package="com.tuzhi.controller"/>
    <!-- 让Spring MVC不处理静态资源 -->
    <mvc:default-servlet-handler />
    <!--
    支持mvc注解驱动
        在spring中一般采用@RequestMapping注解来完成映射关系
        要想使@RequestMapping注解生效
        必须向上下文中注册DefaultAnnotationHandlerMapping
        和一个AnnotationMethodHandlerAdapter实例
        这两个实例分别在类级别和方法级别处理。
        而annotation-driven配置帮助我们自动完成上述两个实例的注入。
     -->
    <mvc:annotation-driven />

    <!-- 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
          id="internalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/WEB-INF/jsp/" />
        <!-- 后缀 -->
        <property name="suffix" value=".jsp" />
    </bean>

</beans>
~~~

## 1.4、在web/WEB-INF文件下创建jsp>hello.jsp

~~~jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${msg}
</body>
</html>

~~~

## 1.5、创建HelloController.java文件

~~~java
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
public class HelloController {
//    真实访问地址/HelloController/hello
    @RequestMapping("hello")
    public String hello(Model model) {
        model.addAttribute("msg","helloSpringMVC");
        //web-inf/jsp/hello.jsp
        return "hello";
    }
}
~~~

# 2、接收前端传来的参数

## 2.1、固定参数

1. 后端代码

   ~~~java
    @RequestMapping("Param/p1")
       public String p1(int a, int b, Model model) {
           int result = a + b;
           model.addAttribute("msg","结果是："+result);
           return "hello";
       }
   ~~~

2. 测试:http://127.0.0.1:8080/Param/p1?a=1&b=2

## 2.2、不同method

~~~java
@GetMapping("Param/p1")
    public String p1(int a, int b, Model model) {
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
~~~

## 2.3、RestFul风格

```java
@GetMapping("Param/p1/{a}/{b}")
public String p1(@PathVariable int a, @PathVariable int b, Model model) {
    int result = a + b;
    model.addAttribute("msg","结果是："+result);
    return "hello";
}
```

* 测试：http://127.0.0.1:8080/Param/p1/1/2