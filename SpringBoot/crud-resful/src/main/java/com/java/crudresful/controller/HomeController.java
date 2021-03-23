package com.java.crudresful.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value = {"/", "index"})
    public String index() {
        return "signin";
    }

    @RequestMapping(value = {"/list"})
    public String list() {
        return "emp/dashboard";
    }
}
