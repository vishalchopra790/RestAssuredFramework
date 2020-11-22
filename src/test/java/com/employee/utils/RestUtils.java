package com.employee.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

    public static String ename(){
        String name= RandomStringUtils.randomAlphanumeric(2);
        return "John"+name;
    }
    public static String esal(){
        String sal= RandomStringUtils.randomNumeric(5);
        return sal;
    }
    public static String eage(){
        String age= RandomStringUtils.randomAlphanumeric(2);
        return age;
    }
}
