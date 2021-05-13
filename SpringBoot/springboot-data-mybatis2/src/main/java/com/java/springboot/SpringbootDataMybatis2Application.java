package com.java.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @MapperScan(value = {}) value 是一个Stringp[]，可以指定多个package路径
@MapperScan(value = {"com.java.springboot.mapper"})
@SpringBootApplication
public class SpringbootDataMybatis2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDataMybatis2Application.class, args);
    }

}
