package com.java.dao;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = java.sql.Statement.class)
})
public class MyFirstPlugin implements Interceptor {
    /**
     * 拦截: 拦截目标对象的目标方法的执行
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyFirstPlugin.intercept(Invocation invocation)调用的方法: " + invocation.getMethod());

        Object target = invocation.getTarget();
        System.out.println("当前拦截器拦截到的对象：" + target);
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("SQL语句参数：" + value);
        // 修改sql语句要用的参数
        metaObject.setValue("parameterHandler.parameterObject", 3L);
        // 执行目标方法，并获取方法执行的结果
        Object proceed = invocation.proceed();
        // 返回执行目标方法的结果
        return proceed;
    }

    // 生成动态代理对象，可以使用MyBatis提 供的Plugin类的wrap方法
    @Override
    public Object plugin(Object target) {
        System.out.println("MyFirstPlugin.plugin(Object target) ... target" + target);
        // 通过Plugin.wrap()方法，使用当前interceptor来包装目标对象target
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }

    // 将插件注册时的 property 属性设置进来，位于mybatis配置文件中
    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件的配置信息：" + properties);
    }
}
