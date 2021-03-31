SpringBoot使用Jetty、Undertow web容器
==

## 概述
SpringBoot默认使用的web容器是嵌入tomcat

## Jetty
适合场景：长连接（如IM聊天）

在这方面也有另外的框架：netty，一个客户端-服务端NIO框架，开发简单快速

```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                    <groupId>org.springframework.boot</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <artifactId>spring-boot-starter-jetty</artifactId>
            <groupId>org.springframework.boot</groupId>
        </dependency>
    </dependencies>
```
## Undertow
适用场景：高IO密集型服务（如文件服务器），缺点：不支持jsp
