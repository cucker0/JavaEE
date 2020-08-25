package com.java.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interceptor2 implements HandlerInterceptor {
    /**
     * 该方法在目标方法之前被执行
     *  可以做权限控制、日志输出、事务管理等
     *
     *
     * @param request
     * @param response
     * @param handler
     * @return
     *      true: 继续调用后续的拦截器和目标方法
     *      false: 则不会执行后面的拦截器和目标方法
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Interceptor2 preHandle...");
        return true;
    }

    /*
    * 该方法在调用目标方法之后，在渲染视图之前
    *   可以对请求域中的属性或视图做修改
    * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Interceptor2 postHandle...");
    }

    /*
    * 渲染视图之后被调用
    *   释放资源
    * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Interceptor2 afterCompletion...");
    }
}
