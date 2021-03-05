package com.java.springbootprofiles2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class HomeIndex {
    @GetMapping({"/", "/index"})
    public String show() {
        return "Hi, Spring Boot.";
    }
}
