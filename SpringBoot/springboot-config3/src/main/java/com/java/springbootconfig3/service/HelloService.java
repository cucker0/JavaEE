package com.java.springbootconfig3.service;

import org.springframework.stereotype.Component;

public class HelloService {

    public HelloService() {
        System.out.println("这是HelloService...");
    }

    public String sayHello() {
        System.out.println("=== sayHello ===");
        return "Hello Gays";
    }

    @Override
    public String toString() {
        return "HelloService{hi ...}";
    }
}
