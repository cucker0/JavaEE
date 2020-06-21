package com.java.annotation;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private ApplicationContext ctx = new ClassPathXmlApplicationContext("annotation.xml");

    @Test
    public void test() {
        UserServlet userServlet = ctx.getBean(UserServlet.class);
        userServlet.add();
        /*
UserServlet: 接受用户注册请求...
UserService: 添加新用户
UserDao: 保存新用户...
        * */
    }
}
