package com.tuzhi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: SpringMVC-study
 * @description:
 * @author: 兔子
 * @create: 2021-12-03 09:30
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String sex;
    private int age;
}
