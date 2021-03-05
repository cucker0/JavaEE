package com.java.springbootprofiles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class HomeIndex {
    @GetMapping("/")
    public String showIndex() {
        return "Hi, Spring Boot";
    }
}
