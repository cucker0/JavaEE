package com.java.ref.www;

import com.java.ref.bean.Dao;
import com.java.ref.bean.Web;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeansAutoTest {
    private ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans-auto.xml");

    @Test
    public void test1() {
        Web web = ctx.getBean(Web.class);
        web.addUser();
    }

    @Test
    public void test2() {
        Dao dao1 = (Dao) ctx.getBean("dao2");
        Dao dao2 = (Dao) ctx.getBean("dao2");
        System.out.println(dao1 == dao2);  // false
    }
}
