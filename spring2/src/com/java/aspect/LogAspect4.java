package com.java.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 复用切点表达式
 *
 * @Order(优先值) ： 注解指定切面执行的优先级，优先级值可以为负数。
 * 优先级值越小时，前置通知先执行，未设置优先级的最后执行，后置通知、返回通知、异常通知后执行，未设置优先级的最先执行
 */
@Order(1)
@Aspect
@Component
public class LogAspect4 {
    /**
     * 用于声明切入点表达式，一般该方法中不需要添加其他代码
     * 使用@Pointcut 来声明切入点表达式
     *
     * 此切入点表达式方法还可以被其他类所引用，写完整路径的全方法即可
     */
    @Pointcut("execution(* com.java.aspect.api2.Calc.*(..))")
    public void joinPointExpression() {
    }

    // @Before("execution(* com.java.aspect.api2.Calc.*(..))")
    @Before("joinPointExpression()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LogAspect4 前置通知# method [" + methodName + "], 参数：" + Arrays.asList(joinPoint.getArgs()));
    }

    // 后置通知，在方法执行后执行，不管是否异常都会执行
    // @After("execution(* com.java.aspect.api2.Calc.*(..))")
    @After("joinPointExpression()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LogAspect4 后置通知# The method [" + methodName + "] ends");
    }

    /**
     * 返回通知
     * 在方法正常结束后执行的通知，可访问到方法的返回值
     *
     * @param joinPoint 切点
     * @param result    此形参名与@AfterReturning定义的returning相对应
     */
    // @AfterReturning(value = "execution(* com.java.aspect.api2.Calc.*(..))", returning = "result")
    @AfterReturning(value = "joinPointExpression()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LogAspect4 返回通知# The method [" + methodName + "], ends withs result: " + result);
    }


    /**
     * 异常通知
     * 在目标方法执行出现异常时执行的方法
     * 可以访问到异常代码，且可以指定异常类型，当指定的特定类型的异常发生时才执行此方法
     * @param joinPoint 切面的切点
     * @param e         与形参名要与@AfterThrowing中定义的throwing值相同，并在此限定异常类型
     */
    // @AfterThrowing(value = "execution(* com.java.aspect.api2.Calc.*(..))", throwing = "e")
    @AfterThrowing(value = "joinPointExpression()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LogAspect4 异常通知# The method [" + methodName + "], 异常： " + e);
    }
}
