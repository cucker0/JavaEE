package com.java.aop;

import com.java.aop.api.Calculator;
import com.java.aop.impl.CalculatorImpl;
import com.java.aop.impl.CalculatorLogImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    @Test
    public void test1() {
        Calculator calc = new CalculatorImpl();
        int ret = calc.add(6, 10);
        System.out.println("calc.add(6, 10);");
        System.out.println(ret);
        int ret2 = calc.sub(20, 2);
        System.out.println("calc.sub(20, 2)");
        System.out.println(ret2);
    }

    // 憋足日志版
    @Test
    public void logCalc() {
        Calculator calc = new CalculatorLogImpl();
        calc.add(10, 20);
        calc.mul(3, 7);
    }

    @Test
    public void testAopProxy() {
        Calculator calc = new CalculatorImpl();
        Calculator proxy = (Calculator) CommonProxy.getProxyInstance(calc);
        proxy.add(11, 22);
        proxy.div(99, 2);
    }
}
