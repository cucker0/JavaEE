spring2
==

### Spring AOP
* jar包
    ```text
    aspectjweaver-1.9.5.jar
    
    aopalliance-1.0.jar
    commons-logging-1.2.jar
    spring-aop-5.2.3.RELEASE.jar
    spring-aspects-5.2.3.RELEASE.jar
    spring-beans-5.2.3.RELEASE.jar
    spring-context-5.2.3.RELEASE.jar
    spring-context-support-5.2.3.RELEASE.jar
    spring-core-5.2.3.RELEASE.jar
    spring-expression-5.2.3.RELEASE.jar
    spring-instrument-5.2.3.RELEASE.jar
    spring-jdbc-5.2.3.RELEASE.jar
    spring-jms-5.2.3.RELEASE.jar
    spring-messaging-5.2.3.RELEASE.jar
    spring-orm-5.2.3.RELEASE.jar
    spring-oxm-5.2.3.RELEASE.jar
    spring-test-5.2.3.RELEASE.jar
    spring-tx-5.2.3.RELEASE.jar
    ```

* xml配置文件
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:context="http://www.springframework.org/schema/context"
           xmlns:aop="http://www.springframework.org/schema/aop"
           xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">
    
        <!-- 配置自动扫描的包 -->
        <context:component-scan base-package="com.java.aspect"></context:component-scan>
    
        <!-- 使用 AspectJ 注解起作用：自动为匹配的类生成代理对象 -->
        <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
    </beans>
    ```

* bean类，基于注解自动注入
    ```text
    @Component("calculator")
    public class CalculatorImpl implements Calculator {
    
    }
    ```

* 把横切关注点的代码抽象到切面类中
    ```java
    @Aspect
    @Component
    public class LogAspect {
        @Before("execution(public int com.java.aspect.api.Calculator.*(int, int))")
        public void beforeMethod(JoinPoint joinp) {
            String methodName = joinp.getSignature().getName();
            Object[] args = joinp.getArgs();
            System.out.println("The method " + methodName + " begins with " + Arrays.asList(args));
        }
    
        @After("execution(* com.java.aspect.api.*.*(..))")
        public void afterMethod(JoinPoint joinp) {
            String methodName = joinp.getSignature().getName();
            System.out.println("The method " + methodName + " ends");
        }
    
    }
    ```
    * 通知类型，写在切面类的方法上的注解
        ```text
        @Before: 前置通知, 在方法执行之前执行
        @After: 后置通知, 在方法执行之后执行 
        @AfterRunning: 返回通知, 在方法返回结果之后执行
        @AfterThrowing: 异常通知, 在方法抛出异常之后
        @Around: 环绕通知, 围绕着方法执行
        ```