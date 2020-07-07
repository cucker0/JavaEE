package com.java.aspectxml;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;

import java.util.Arrays;


public class LogAspect2 {
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("LogAspect2 前置通知# The method [" + methodName + "] begins with " + Arrays.asList(args));
    }

    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LogAspect2 后置通知# The method [" + methodName + "] ends");
    }

    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LogAspect2 返回通知# The method [" + methodName + "], ends withs result: " + result);
    }

    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LogAspect2 异常通知# The method [" + methodName + "], 异常： " + e);
    }
}
