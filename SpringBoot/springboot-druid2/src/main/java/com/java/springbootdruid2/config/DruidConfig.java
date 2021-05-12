package com.java.springbootdruid2.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {
    // 从application配置文件获取datasource相关的参数配置
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    // druid管理后台的Servlet
    @Bean
    public ServletRegistrationBean druidServlet() {
        // 注册Servlet，配置映射的URL
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(
                new StatViewServlet(), "/druid/*");
        // 白名单,多个用逗号分割， 如果allow没有配置或者为空，则允许所有访问
        bean.addInitParameter("allow", "127.0.0.1,172.29.32.54");
        // 黑名单,多个用逗号分割 (共同存在时，deny优先于allow)
        bean.addInitParameter("deny", "192.168.1.0/24");
        // 控制台管理用户名
        bean.addInitParameter("loginUsername", "admin");
        // 控制台管理密码
        bean.addInitParameter("loginPassword", "ad123456");
        // 是否可以重置数据源，禁用HTML页面上的“Reset All”功能
        bean.addInitParameter("resetEnable", "false");
        return bean;
    }

    /*
    druid管理后台的Servlet
    或使用这种写法
    @Bean
    public ServletRegistrationBean druidServlet2() {
        // 注册Servlet，配置映射的URL
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(
                new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "ad123456");
        initParams.put("allow", "127.0.0.1,172.29.32.54");
        initParams.put("deny", "192.168.1.0/24,192.168.2.0/24");
        initParams.put("resetEnable", "false");
        bean.setInitParameters(initParams);
        return bean;
    }
    */

    // druid管理后台的WebStatFilte
    @Bean
    public FilterRegistrationBean WebStatFilter() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new WebStatFilter());
        // 所有请求进行监控处理
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加忽略的url资源
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.css,*.gif,*.jpg,*.png,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
