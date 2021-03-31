package com.java.jettyundertow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletContext;

@Controller
public class IndexController {
    @Autowired
    private ServletContext servletContext;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("serverInfo", servletContext.getServerInfo());
        return "index";
    }
}
