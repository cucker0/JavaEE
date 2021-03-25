package com.java.crudresful.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    /*
    @GetMapping
    @PostMapping
    @PutMapping
    @DeleteMapping

    是 @RequestMapping(value = "/user/login", method = RequestMethod.POST) 的简便写法
     */

    // 显示 登录页
    @GetMapping("/user/login")
    public String login() {
        return "/user/signin";
    }

    /**
     * 用户登录验证
     *
     * 模拟用户登录，有一个用:admin, 密码:ad123456
     * @param username
     * @param password
     * @param map save error messages
     * @return
     */
    @PostMapping("/user/login")
    public String loginPost(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            Map<String, Object> map,
                            HttpSession session) {
        // 登陆成功
        if (username.equals("admin") && password.equals("ad123456")) {
            session.setAttribute("isLogin", true);
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        }
        map.put("msg", "用户或密码错误!");
        // 登录失败
        return "user/signin";
    }
}
