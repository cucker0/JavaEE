package com.java.springbootconfig3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @ImportResource
 *      导入配置
 *
 */

@ImportResource(locations = {"classpath:spring-config.xml"})
@SpringBootApplication
public class SpringbootConfig3Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootConfig3Application.class, args);
    }

}
