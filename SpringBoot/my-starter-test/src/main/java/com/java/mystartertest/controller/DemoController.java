package com.java.mystartertest.controller;

import com.java.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    TalkService talkService;

    @GetMapping("/")
    public String talk() {
        return "柳宗元: " + talkService.say();
    }
}
