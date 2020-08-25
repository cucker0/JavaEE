package com.java.aspectxml;

import com.java.aspectxml.api.Calculator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// 基本xml配置文件来配置AOP
public class Main {
    // 创建spring的IOC容器
    private ApplicationContext ctx = new ClassPathXmlApplicationContext("aspect_xml.xml");

    @Test
    public void test1() {
        // 从IOC容器中获取bean实例
        Calculator calc = (Calculator) ctx.getBean("calculator");
        // Calculator calc = ctx.getBean(Calculator.class);
        // 使用bean
        int ret = calc.add(100, 10);
        System.out.println("ret: " + ret);

        calc.mul(2, 10);
        calc.div(2, 0);
        /*
LogAspect2 前置通知# The method [add] begins with [100, 10]
The method [add] begins with [100, 10]
The method [add] ends
The method [add], ends withs result: 110
LogAspect2 后置通知# The method [add] ends
ret: 110
LogAspect2 前置通知# The method [mul] begins with [2, 10]
The method [mul] begins with [2, 10]
The method [mul] ends
The method [mul], ends withs result: 20
LogAspect2 后置通知# The method [mul] ends
LogAspect2 前置通知# The method [div] begins with [2, 0]
The method [div] begins with [2, 0]
The method [div] ends
The method [div], 异常： java.lang.ArithmeticException: / by zero
LogAspect2 后置通知# The method [div] ends
         */
    }

    // xml配置aspect 环绕通知
    @Test
    public void aroudXml() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aspect_xml2.xml");
        Calculator c = (Calculator) ctx.getBean("calculator");
        c.div(20, 3);
    }
}
