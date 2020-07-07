package com.java.aspect;

import com.java.aspect.api.Calculator;
import com.java.aspect.api2.Calc;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    // 创建spring的IOC容器
    private ApplicationContext ctx = new ClassPathXmlApplicationContext("aspect.xml");

    @Test
    public void test1() {
        // 从IOC容器中获取bean实例
        Calculator calc = (Calculator) ctx.getBean("calculator");
        // Calculator calc = ctx.getBean(Calculator.class);
        // 使用bean
        int ret = calc.add(99, 10);
        System.out.println("ret: " + ret);

        calc.mul(2, 3);
        calc.div(2, 0);
        /*
The method [add] begins with [99, 10]
The method [add] ends
The method [add], ends withs result: 109
ret: 109
The method [mul] begins with [2, 3]
The method [mul] ends
The method [mul], ends withs result: 6
The method [div] ends
The method [div], 异常： java.lang.ArithmeticException: / by zero
         */
    }

    @Test
    public void tesetAroundAdvice() {
        Calc c = (Calc) ctx.getBean("calc");
        c.mul(2, 3);
        c.div(10, 0);
        /*
LogAspect4 前置通知# method [mul], 参数：[2, 3]
LogAspect3 前置通知# method [mul], 参数：[2, 3]
前置通知# method [mul], 参数：[2, 3]
2 * 3 = 6
返回通知# method [mul], 执行结果：6
后置通知# method [mul] 执行结束
LogAspect3 后置通知# The method [mul] ends
LogAspect4 后置通知# The method [mul] ends
LogAspect4 返回通知# The method [mul], ends withs result: 6
LogAspect4 前置通知# method [div], 参数：[10, 0]
LogAspect3 前置通知# method [div], 参数：[10, 0]
前置通知# method [div], 参数：[10, 0]
异常通知# method [div], 出现异常：java.lang.ArithmeticException: / by zero
LogAspect3 后置通知# The method [div] ends
LogAspect4 后置通知# The method [div] ends
LogAspect4 异常通知# The method [div], 异常： java.lang.RuntimeException: java.lang.ArithmeticException: / by zero

         */
    }
}
