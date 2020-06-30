package com.java.aspect;

import com.java.aspect.api.Calculator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    @Test
    public void test1() {
        // 创建spring的IOC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aspect.xml");
        // 从IOC容器中获取bean实例
        // Calculator calc = (Calculator) ctx.getBean("calculatorImpl");
        Calculator calc = ctx.getBean(Calculator.class);
        // 使用bean
        int ret = calc.add(99, 10);
        System.out.println("ret: " + ret);

        calc.mul(2, 3);
    }
}
