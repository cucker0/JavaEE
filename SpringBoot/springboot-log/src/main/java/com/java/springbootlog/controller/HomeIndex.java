package com.java.springbootlog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeIndex {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping({"/", "/index"})
    public String index() {
        logger.trace("trace日志：===, index");
        logger.debug("debug日志：===, index");
        logger.info("info日志：===, index");
        logger.warn("warn日志：===, index");
        logger.error("error：===, index");
        return "Hello, SpringBoot loggin";
    }
}
