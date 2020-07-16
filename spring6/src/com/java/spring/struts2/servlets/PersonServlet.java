package com.java.spring.struts2.servlets;

import com.java.spring.struts2.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PersonServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        // 从application域对象中获取IOC容器的引用
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());

        // 从IOC容器中获取需要的bean对象
        Person person = ctx.getBean(Person.class);
        person.info();
        response.getWriter().write(System.currentTimeMillis() + ": " + person.toString());
    }
}
