package com.java.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {

    /**
     * 使用@RequestMapping注解来映射请求的URL
     * 返回值会通过视图解析器解析为实际的视图资源路径，
     *   对于InternalResourceViewResolver 视图解析器，解析方法：
     *   prefix + returnVal + suffix
     *   如 /WEB-INF/view/success.jsp
     *
     *
     * @return
     */
    @RequestMapping("/helloWorld")
    public String hello() {
        System.out.println("hello world Controller...");
        return "success";
    }

}
