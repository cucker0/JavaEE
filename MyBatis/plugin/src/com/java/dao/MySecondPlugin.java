package com.java.dao;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = java.sql.Statement.class)
})
public class MySecondPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MySecondPlugin.intercept(Invocation invocation)调用的方法: " + invocation.getMethod());
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("MySecondPlugin.plugin(Object target) ... target" + target);
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
