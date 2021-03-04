package com.java.springbootconfig2.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @PropertySource
 *      加载指定的 properties配置文件
 *      只能读取 properties类型的文件，无法加载 yml文件
 *
 */

@Component
@PropertySource(value = {"classpath:company.properties"})
@ConfigurationProperties(prefix = "company")
public class Company {
    private String name;
    private String location;
    private String url;

    public Company() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
