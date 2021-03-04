package com.java.springbootconfig4.config;

import com.java.springbootconfig4.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration
 *  指定当前类一个配置类，通过配置的方法来替换Spring的配置文件
 *  这也是Spring Boot推荐的方式
 *
 */
@Configuration
public class MyAppConfig {
    // 将方法的返回值添加到 IOC容器中，对应组件的id为方法名
    // 相当于Spring配置文件中的<bean>标签
    @Bean
    public HelloService helloService() {
        return new HelloService();
    }
}
