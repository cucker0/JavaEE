package com.java.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// @Order(优先级) ： 注解指定切面执行的优先级，优先级值越小越优先执行，优先级值可以为负数。未设置优先级的最后执行
@Order(1)
@Aspect
@Component
public class LogAspect4 {
    @Before("execution(* com.java.aspect.api2.Calc.*(..))")
    public void aroundMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LogAspect4 前置通知# method [" + methodName + "], 参数：" + Arrays.asList(joinPoint.getArgs()));
    }
}
