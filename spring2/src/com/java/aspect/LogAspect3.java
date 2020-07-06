package com.java.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Order(2)
@Aspect
@Component
public class LogAspect3 {
    @Before("execution(* com.java.aspect.api2.Calc.*(..))")
    public void aroundMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LogAspect3 前置通知# method [" + methodName + "], 参数：" + Arrays.asList(joinPoint.getArgs()));
    }
}
