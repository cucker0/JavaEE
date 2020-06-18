package com.java.first.www;

import com.java.first.bean.Car;
import com.java.first.bean.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");

    @Test
    public void testUser() {
        User user = new User("马不易", 30, 1);
        user.info();
    }

    @Test
    public void testIoc() {
        // 从IOC容器中获取bean实例
        User u = (User) ctx.getBean("user");
        // 通过Clazz获取bean，只有当该类只配置了一个bean时，才有效，
        // 否则报异常：org.springframework.beans.factory.NoUniqueBeanDefinitionException，实例不是唯一的
        // User u = ctx.getBean(User.class);
        u.info();
        System.out.println(u);
    }

    @Test
    public void testCar() {
        Car car = (Car) ctx.getBean("car");
        System.out.println("car: " + car);

        Car car2 = (Car) ctx.getBean("car2");
        System.out.println("car2: ");
        System.out.println(car2);

        Car car3 = (Car) ctx.getBean("car3");
        System.out.println("car3: ");
        System.out.println(car3);

        Car car4 = (Car) ctx.getBean("car4");
        System.out.println("car4: " + car4);
    }
}
