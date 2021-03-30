package com.java.tomcatservlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听器
 *     可监听的类型
 *     ServletContextAttributeListener
 *     ServletRequestListener
 *     ServletRequestAttributeListener
 *     HttpSessionAttributeListener
 *     HttpSessionListener
 *     ServletContextListener
 *
 */
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("web应用已启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("web应用已停止");
    }
}
