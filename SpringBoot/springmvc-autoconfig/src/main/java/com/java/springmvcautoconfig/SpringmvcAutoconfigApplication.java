package com.java.springmvcautoconfig;

import com.java.springmvcautoconfig.config.MyViewResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;


@SpringBootApplication
public class SpringmvcAutoconfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringmvcAutoconfigApplication.class, args);
    }

    // 注册自定义的ViewResolver
    @Bean
    public ViewResolver myViewResolver() {
        return new MyViewResolver();
    }

}
