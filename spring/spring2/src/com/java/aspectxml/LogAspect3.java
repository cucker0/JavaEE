package com.java.aspectxml;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

public class LogAspect3 {
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        String methodName = proceedingJoinPoint.getSignature().getName();
        try {
            // 前置通知
            System.out.println("前置通知# method [" + methodName + "], 参数：" + Arrays.asList(proceedingJoinPoint.getArgs()));
            // 执行目标方法
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            // 异常通知
            // throwable.printStackTrace();
            System.out.println("异常通知# method [" + methodName + "], 出现异常：" + throwable);
            throw new RuntimeException(throwable);
        }
        // 后置通知
        System.out.println("后置通知# method [" + methodName + "] 执行结束");

        // 返回通知
        System.out.println("返回通知# method [" + methodName + "], 执行结果：" + result);
        return result;
    }
}
