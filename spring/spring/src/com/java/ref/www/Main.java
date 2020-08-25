package com.java.ref.www;

import com.java.first.bean.User;
import com.java.ref.bean.Dao;
import com.java.ref.bean.Service;
import com.java.ref.bean.Web;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config2.xml");

    // 是否为同一个实例对象
    @Test
    public void testDao() {
        Dao dao1 = (Dao) ctx.getBean("dao");
        Dao dao2 = (Dao) ctx.getBean("dao");
        System.out.println(dao1 == dao2);  // true
    }

    @Test
    public void test2() {
        Service service = (Service) ctx.getBean("service");
        service.getDao().update();
        System.out.println("----------");

        Service service2 = (Service) ctx.getBean("service2");
        service2.getDao().update();
    }

    @Test
    public void testWeb() {
        Web web = (Web) ctx.getBean("web");
        web.addUser();
        ctx.close();
    }

    // 测试集体属性值
    @Test
    public void test3() {
        System.out.println("使用list标签来设置集合属性值");
        User user = (User) ctx.getBean("user0");
        System.out.println(user);

        System.out.println("\n--- 属性值引用外部值 ---");
        User user2 = (User) ctx.getBean("user00");
        System.out.println(user2);

        System.out.println("\n--- p 命名空间设置属性值，这样更方便 ---");
        User user3 = (User) ctx.getBean("user3");
        System.out.println(user3);

        System.out.println("\n--- paren属性指定实例之间的继续关系 ---");
        User user4 = (User) ctx.getBean("user4");
        System.out.println(user4);

        System.out.println("\n--- depents-on ---");
        User user5 = (User) ctx.getBean("user5");
        System.out.println(user5);
    }
}
