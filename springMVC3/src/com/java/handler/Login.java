package com.java.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Login {
    private final String loginView = "login";

    @RequestMapping("/login")
    public String showLoginPage() {
        System.out.println("显示login页面");
        return loginView;
    }
}
