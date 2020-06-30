package com.java.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

// 通过AOP动态代理，实现方法执行前、后打印日志
class goInvocationHandler implements InvocationHandler {
    // 要代理的对象
    private Object obj;

    // 方法
    public void setObj(Object obj) {
        this.obj = obj;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        Object ret = null;
        try {
            // 前置通知，方法执行前
            System.out.println("[before] the method: " + methodName + " begins with " + Arrays.asList(args));
            ret = method.invoke(obj, args);
            // 返回通知，方法执行后
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // 异常通知
            System.out.println("出现异常了");
            e.printStackTrace();
        }
        // 后置通知
        System.out.println("[after] the method: " + methodName + " ends with " + ret);
        return ret;
    }


}

public class CommonProxy {
    /**
     * 获取代理实例
     *
     * @param obj 要代理的对象
     * @return Proxy实例
     */
    public static Object getProxyInstance(Object obj) {
        goInvocationHandler hander = new goInvocationHandler();
        hander.setObj(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), hander);
    }
}
