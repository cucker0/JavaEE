package com.java.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserServlet {
    @Autowired
    private UserService userService;

    public void add() {
        System.out.println("UserServlet: 接受用户注册请求...");
        userService.addUser();
    }
}
