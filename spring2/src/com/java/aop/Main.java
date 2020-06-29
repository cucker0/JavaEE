package com.java.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    @Test
    public void test1() {
        Calculator calc = new CalculatorImpl();
        System.out.println(calc.getClass().getName());

        int ret = calc.add(6, 10);
        System.out.println(ret);
    }
}
