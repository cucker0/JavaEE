package com.java.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * 把这个类声明为一个切面
 *
 * @Component: 需要把该类放到IOC容器中
 * @@Aspect: 声明为切面
 */
@Aspect
@Component
public class LogAspect {
    // 前置通知，方法执行前执行
    @Before("execution(public int com.java.aspect.api.Calculator.*(int, int))")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("The method [" + methodName + "] begins with " + Arrays.asList(args));
    }

    // 后置通知，在方法执行后执行，不管是否异常都会执行
    @After("execution(* com.java.aspect.api.*.*(..))")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method [" + methodName + "] ends");
    }

    /**
     * 返回通知
     * 在方法正常结束后执行的通知，可访问到方法的返回值
     *
     * @param joinPoint 切点
     * @param result    此形参名与@AfterReturning定义的returning相对应
     */
    @AfterReturning(value = "execution(public int com.java.aspect.api.Calculator.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method [" + methodName + "], ends withs result: " + result);
    }


    /**
     * 异常通知
     * 在目标方法执行出现异常时执行的方法
     * 可以访问到异常代码，且可以指定异常类型，当指定的特定类型的异常发生时才执行此方法
     * @param joinPoint 切面的切点
     * @param e         与形参名要与@AfterThrowing中定义的throwing值相同，并在此限定异常类型
     */
    @AfterThrowing(value = "execution(public * com.java.aspect.api.Calculator.*(..))", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method [" + methodName + "], 异常： " + e);
    }

}
