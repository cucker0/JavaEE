package com.java.crudresful.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 检查登录
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isLogin = request.getSession().getAttribute("isLogin") != null;
        // 登录成功，放行请求
        if (isLogin) {
            return true;
        }
        // 未登录，跳转到登录页
        // request.setAttribute("msg", "没有权限，请先登录");
        // request.getRequestDispatcher("/user/login").forward(request, response);
        // ?toUrl=request.getServletPath() 携带上用户原来要请求的URL
        response.sendRedirect(request.getContextPath() + "/user/login?toUrl=" + request.getServletPath());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
