package com.java.war.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/notice")
    public String ad(Model model) {
        model.addAttribute("tips", "喜讯：今年全体员工加薪!!!");
        return "notice";
    }
}
