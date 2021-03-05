package com.java.springbootlocation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeIndex {
    @GetMapping("/index")
    public String index() {
        return "Hi, Spring Boot.";
    }
}
