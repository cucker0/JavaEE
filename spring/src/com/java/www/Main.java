package com.java.www;

import com.java.bean.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    @Test
    public void testUser() {
        User user = new User("马不易", 30, 1);
        user.info();
    }

    @Test
    public void testIoc() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
        User u = (User) ctx.getBean("user");
        u.info();
        System.out.println(u);
    }
}
