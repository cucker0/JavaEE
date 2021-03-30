package com.java.tomcatservlet.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {
    //初始阶段
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter process ...");
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
    }
    // 销毁阶段
    @Override
    public void destroy() {

    }
}
