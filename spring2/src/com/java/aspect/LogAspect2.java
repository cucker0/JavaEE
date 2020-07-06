package com.java.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 环绕通知
 * 环绕通知类似于动态代理的全过程
 */

@Aspect
@Component
public class LogAspect2 {
    /**
     * 环绕通知类似于动态代理的全过程
     * @param proceedingJoinPoint 继续连接点
     * @return
     */
    @Around("execution(* com.java.aspect.api2.Calc.*(..))")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        String methodName = proceedingJoinPoint.getSignature().getName();
        try {
            // 前置通知
            System.out.println("前置通知# method [" + methodName + "], 参数：" + Arrays.asList(proceedingJoinPoint.getArgs()));
            // 执行目标方法
            result = proceedingJoinPoint.proceed();
            // 返回通知
            System.out.println("返回通知# method [" + methodName + "], 执行结果：" + result);
        } catch (Throwable throwable) {
            // 异常通知
            // throwable.printStackTrace();
            System.out.println("异常通知# method [" + methodName + "], 出现异常：" + throwable);
            throw new RuntimeException(throwable);
        }
        // 后置通知
        System.out.println("后置通知# method [" + methodName + "] 执行结束");
        return result;
    }
}
