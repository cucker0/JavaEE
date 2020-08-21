package com.java.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 默认拦截所有的Handler路径，直接访问jsp页面的不会被拦截
 *
 */
/*
多个拦截器执行顺序

Interceptor1 preHandle...
Interceptor2 preHandle...
显示login页面
Interceptor2 postHandle...
Interceptor1 postHandle...
Interceptor2 afterCompletion...
Interceptor1 afterCompletion...
*/
public class Interceptor1 implements HandlerInterceptor {
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
        System.out.println("Interceptor1 preHandle...");
        return true;
    }

    /*
    * 该方法在调用目标方法之后，在渲染视图之前
    *   可以对请求域中的属性或视图做修改
    * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Interceptor1 postHandle...");
    }

    /*
    * 渲染视图之后被调用
    *   释放资源
    * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Interceptor1 afterCompletion...");
    }
}
