package com.java.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * 把这个类声明为一个切面
 *
 * @Component: 需要把该类放到IOC容器中
 * @@Aspect: 声明为切面
 *
 */
@Aspect
@Component
public class LogAspect {
    // 前置通知，方法执行前执行
    @Before("execution(public int com.java.aspect.api.Calculator.*(int, int))")
    public void beforeMethod(JoinPoint joinp) {
        String methodName = joinp.getSignature().getName();
        Object[] args = joinp.getArgs();
        System.out.println("The method " + methodName + " begins with " + Arrays.asList(args));
    }

    // 后置通知，在方法执行后执行，不管是否异常都会执行
    @After("execution(* com.java.aspect.api.*.*(..))")
    public void afterMethod(JoinPoint joinp) {
        String methodName = joinp.getSignature().getName();
        System.out.println("The method " + methodName + " ends");
    }

}
