package com.java.tomcatservlet.config;

import com.java.tomcatservlet.filter.MyFilter;
import com.java.tomcatservlet.listener.MyListener;
import com.java.tomcatservlet.servlet.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;
import java.util.Arrays;

@Configuration
public class MyServerConfig {
    // 注册3大组件
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MyServlet(), "/servlet");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/hello", "/filter"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean listenerRegistrationBean() {
        ServletListenerRegistrationBean<MyListener> listenerRegistrationBean = new ServletListenerRegistrationBean<>();
        listenerRegistrationBean.setListener(new MyListener());
        return listenerRegistrationBean;
    }
    // 注册3大组件 --end

    /**
     * 配置嵌入式Servlet参数，优先于 application.properties 配置的参数
     * SpringBoot 2.x.x 是 WebServerFactoryCustomizer
     * 旧版的为 EmbeddedServletContainerCustomizer
     *
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                // 监听端口
                factory.setPort(8083);
            }
        };
    }
}
