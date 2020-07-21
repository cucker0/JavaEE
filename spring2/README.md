spring2
==

## Spring AOP
```text
AOP（Aspect-Oriented Programming），面向切面编程。是一种新的方法论, 是对传统 OOP(Object-Oriented Programming, 面向对象编程) 的补充.
扩展原来的方法，而不用改动原来的方法

在应用 AOP 编程时, 仍然需要定义公共功能, 但可以明确的定义这个功能在哪里, 以什么方式应用, 并且不必修改受影响的类. 这样一来横切关注点就被模块化到特殊的对象(切面)里.

AOP 的好处:
每个事物逻辑位于一个位置, 代码不分散, 便于维护和升级
业务模块更简洁, 只包含核心业务代码.
```

## 普通版AOP动态代理
[testAopProxy AOP动态代理](src/com/java/aop/Main.java)  
[CommonProxy](src/com/java/aop/CommonProxy.java)  
[CalculatorImpl](src/com/java/aop/impl/CalculatorImpl.java)  

## AOP相关术语
* 切面(Aspect)
    >横切关注点(跨越应用程序多个模块的功能)被模块化的特殊对象
* 通知(Advice)
    >切面必须要完成的工作
* 目标(Target)
    >被通知的对象
* 代理(Proxy)
    >向目标对象应用通知之后创建的对象
* 连接点(JoinPoint)
    >程序执行的某个特定位置：如类某个方法调用前、调用后、方法抛出异常后等
* 切点(pointcut)
    >每个类都拥有多个连接点,所有方法实际上都是连接点


## AspectJ
Java 社区里最完整最流行的 AOP 框架
 
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

### 启用AspectJ注解支持
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
    
        <!-- 使用 AspectJ 注解起作用：会自动为与 AspectJ 切面匹配的 Bean 创建代理,自动为匹配的类生成代理对象 -->
        <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
    </beans>
    ```

* bean类，基于注解自动注入
    ```text
    @Component("calculator")
    public class CalculatorImpl implements Calculator {
    
    }
    ```

### 用AspectJ注解声明切面
* 把横切关注点的代码抽象到切面类中

    在 AspectJ 注解中, 切面只是一个带有 @Aspect 注解的 Java 类. 
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
    ```text
    @After("execution(* com.java.aspect.api.*.*(..))") 含意：
    第一个*：任何类型的返回值
    第二个*：该目录下的任意名的类
    第三个*：任意名的方法
    .. ：匹配任意数量和任意类型的的参数
    ```

* 支持的注解通知类型
    ```text
    @Before: 前置通知, 在方法执行之前执行
  
    @After: 后置通知, 在方法执行之后执行 
  
    @AfterRunning: 返回通知, 在方法返回结果之后执行
  
    @AfterThrowing: 异常通知, 在方法抛出异常之后
  
    @Around: 环绕通知, 围绕着方法执行，与其他类型的相排斥，
          环绕通知类似于动态代理的全过程，可以用此方法实现以上4种通知的效果
          环绕通知是所有通知类型中功能最为强大的, 能够全面地控制连接点
          环绕通知不能与其他类型的通知一起使用
    ```
    * [前置通知,后置通知,异常通知,返回通知 示例](src/com/java/aspect/LogAspect.java)  
    * [环绕通知 示例](src/com/java/aspect/LogAspect2.java)

## AspectJ切入点表达式
* 切入点表达式表示方法
    ```text
    execution * com.atguigu.spring.ArithmeticCalculator.*(..)
        匹配 ArithmeticCalculator 中声明的所有方法,
        第一个 * 代表任意修饰符及任意返回值. 
        第二个 * 代表任意方法. 
        .. 匹配任意数量的参数. 
        若目标类与接口与该切面在同一个包中, 可以省略包名.
    
    execution public * ArithmeticCalculator.*(..)
        匹配 ArithmeticCalculator 接口的所有公有方法.
    
    execution public double ArithmeticCalculator.*(..)
        匹配 ArithmeticCalculator 中返回 double 类型数值的方法
    
    execution public double ArithmeticCalculator.*(double, ..)
        匹配第一个参数为 double 类型的方法, 
        .. 匹配任意数量任意类型的参数
    
    execution public double ArithmeticCalculator.*(double, double)
        匹配参数类型为 double, 
        double 类型的方法.
    ```

* 合并切入点表达式  

    在 AspectJ 中, 切入点表达式可以通过操作符 &&, ||, ! 结合起来.
    ```text
    @Before("execution(* *.add(int, ..)) || execution(* *.sub(int, ..))")
    ```

* 复用切点表达式
    1. 先定义@Pointcut方法
    2. 在需要的通知类型里引用此方法  
    [复用切点表达式LogAspect4](src/com/java/aspect/LogAspect4.java)  


## 指定切面的优先级
```text
@Order(优先值)
    注解指定切面执行的优先级，优先级值可以为负数。
    优先级值越小时，前置通知先执行，未设置优先级的最后执行，
    后置通知、返回通知、异常通知后执行，未设置优先级的最先执行
```

## 基于xml配置声明切面
[aspect_xml配置](src/aspect_xml.xml)  
[前置通知、后置通知、异常通知、返回通知 Main test1()](src/com/java/aspectxml/Main.java)

[aspect_xml2配置](src/aspect_xml2.xml)  
[环绕通知 Main aroudXml()](src/com/java/aspectxml/Main.java)

