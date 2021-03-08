package com.java.springbootautoconfig.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeIndex {
    @GetMapping({"/", "index"})
    public String index() {
        return "Spring Boot autoconfig";
    }
}
