package com.java.springbootconfig4.service;

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
