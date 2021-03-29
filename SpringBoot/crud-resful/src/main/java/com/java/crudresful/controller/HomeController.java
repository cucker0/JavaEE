package com.java.crudresful.controller;

import com.java.crudresful.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    // @RequestMapping(value = {"/", "index"})
    // public String index() {
    //     return "index";
    // }

    @RequestMapping(value = {"/list"})
    public String list() {
        return "emp/dashboard";
    }

    // 从请求uri中获取用户名
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "user", defaultValue = "张丽") String user) {
        if (user.equalsIgnoreCase("zhangsan")) {
            throw new UserNotExistException();
        }
        return "Hello gay: " + user;
    }
}
