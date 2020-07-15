package com.java.spring.struts2.listenner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SpringServletContextListener implements ServletContextListener {

    // Public constructor is required by servlet spec
    public SpringServletContextListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        // 1. 获取Spring配置文件名称
        ServletContext servletContext = sce.getServletContext();
        String configLocation = servletContext.getInitParameter("configLocation");

        // 2. 创建IOC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocation);

        // 3. 把IOC容器保存到ServletContext的一个属性中
        servletContext.setAttribute("applicationContext", ctx);
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

}
