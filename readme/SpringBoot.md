SpringBoot
==

## SpringBoot入门
### Spring Boot简介
* 一个简化Spring应用开发的框架
* 整个Spring技术栈的一个大整合
* J2EE开发的一站式解决方案
    * Spring Cloud分布式整体解决方案

### 微服务
25 March 2014, [martin fowler提出微服务](https://martinfowler.com/articles/microservices.html)
        
#### 单体应用
一个war包, 有着项目所有功能, 即ALL IN ONE.

* 优点
    1. 便于共享
        >单个归档文件包含所有功能, 便于在团队之间以及不同的部署阶段之间共享.
    
    2. 易于测试
        >单体应用一旦部署, 所有的服务或特性就都可以使用了, 这简化了测试过程, 因为没有额外的依赖, 每项测试都可以在部署完成后立刻开始.
    3. 易于部署
        >只需将单个归档文件复制到单个目录下.

* 缺点
    1. 复杂性高
        ```text
        由于是单个归档文件, 所以整个项目文件包含的模块非常多, 导致模块的边界模糊, 
        依赖关系不清晰, 代码的质量参差不齐, 混乱的堆在一起, 使得整个项目非常复杂. 
        以致每次修改代码, 都非常小心, 可能添加一个简单的功能, 或者修改一个Bug都会带来隐藏的缺陷.
        ```
    2. 技术债务
        >随着时间的推移, 需求的变更和技术人员的更替, 会逐渐形成应用程序的技术债务, 并且越积越多.
    
    3. 扩展能力受限
        >单体应用只能作为一个整体进行扩展, 无法根据业务模块的需要进行伸缩.
    
    4. 阻碍技术创新
        >对于单体应用来说, 技术是在开发之前经过慎重评估后选定的, 每个团队成员都必须使用相同的开发语言, 持久化存储及消息系统.

#### 单体应用与微服务的比较
![](../images/SpringBoot/单体应用与微服务的比较.png)

#### 微服务如何工作
![](../images/SpringBoot/微服务如何工作.png)

* 微服务特点
    1. 架构风格（服务微化）
    2. 每个功能单元都独立出来，可独立替换和独立升级的软件单元
    3. 单元间互连互调, 通过HTTP方式.
    4. 最终形成连接网.


### SpringBoot环境
```text
- jdk1.8及以上
– maven3.x：maven 3.3以上版本；Apache Maven 3.3.9 
– IntelliJ IDEA 2019 x64、STS 
– SpringBoot RELEASE：2.4.3
```

* maven设置

    maven的settings.xml配置文件的profiles标签添加，指定jdk版本、设置maven镜像仓库为阿里云maven仓库(在中国下载依赖包更快)

    位于 用户家目录\.m2\settings.xml

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
        <mirrors>
            <mirror>
                <id>aliyunmaven</id>
                <mirrorOf>*</mirrorOf>
                <name>阿里云公共仓库</name>
                <url>https://maven.aliyun.com/repository/public</url>
            </mirror>
            <!--
            <mirror>
                <id>nexus</id>
                <name>internal nexus repository</name>
                <url>https://repo1.maven.org/maven2/</url>
                <mirrorOf>central</mirrorOf>
            </mirror>
            -->
        </mirrors>
        <profiles>
            <profile>
                <id>jdk‐12</id>
                <activation>
                    <activeByDefault>true</activeByDefault>
                    <jdk>12</jdk>
                </activation>
                <properties>
                    <maven.compiler.source>12</maven.compiler.source>
                    <maven.compiler.target>12</maven.compiler.target>
                    <maven.compiler.compilerVersion>12</maven.compiler.compilerVersion>
                </properties>
            </profile>
        </profiles>
    </settings>
    ```

* IDEA设置

    ![](../images/SpringBoot/maven_settings.png)
    Override框可选可不选，因为默认就是使用这个配置文件

### 创建SpringBoot HelloWorld工程
一个功能： 浏览器发送hello请求，服务器接受请求并处理，响应Hello World字符串；

1. [环境准备](#SpringBoot环境)

2. 创建一个maven工程
    ![](../images/SpringBoot/newSpringBootProject1.png)
    
    ![](../images/SpringBoot/newSpringBootProject2.png)
    
    ![](../images/SpringBoot/newSpringBootProject3.png)
    
    ![](../images/SpringBoot/newSpringBootProject4.png)

3. 导入spring boot相关的依赖  
    [pom.xml](../SpringBoot/springboot-01-helloworld/pom.xml)
    ```xml
    <project>
        <properties>
            <java.version>12</java.version>
        </properties>
    
        <parent>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-parent</artifactId>
            <version>2.4.3</version>
            <relativePath/>
        </parent>
    
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
    
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-test</artifactId>
                <scope>test</scope>
            </dependency>
        </dependencies>
    </project>
    ```
    
4. 编写一个启动Spring Boot应用的主程序  
    [HelloWorldMainApplication](../SpringBoot/springboot-01-helloworld/src/main/java/com/java/HelloWorldMainApplication.java)

5. 编写相关的Controller、Service
    
    [HelloController](../SpringBoot/springboot-01-helloworld/src/main/java/com/java/controller/HelloController.java)
    
6. 运行主程序测试
    ![](../images/SpringBoot/run_springboot_app.png)
    
    ![](../images/SpringBoot/test_springboot_helloworld.png)
    
7. 简化部署
    
    * 在pom.xml中添加打包插件
        ```xml
        <project>
        
            <build>
                <plugins>
                    <!-- 此插件可以将应用打包成一个可执行的jar包 -->
                    <plugin>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-maven-plugin</artifactId>
                    </plugin>
                </plugins>
            </build>
        
        </project>
        ```
    
    * 打包操作
        ![](../images/SpringBoot/springboot打包.png)
    
    * 应用jar包的运行
        ```bash
        java -jar jar包路径
        ```

### Hello World探究
#### pom.xml配置
##### parent配置
父项目[spring-boot-starter-parent](../readme/spring-boot-starter-parent-2.4.3.pom)

在pom.xml文件按住Ctrl，光标移动到 spring-boot-starter-parent 上，可跳转到对应的父依赖配置
```xml
<project>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.4.3</version>
        <relativePath/>
    </parent>
  
</project>
```
* spring-boot-starter-parent中有又指定了parent配置[spring-boot-dependencies](../readme/spring-boot-dependencies-2.4.3.pom)
    ```xml
      <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>2.4.3</version>
      </parent>
    ```
    spring-boot-dependencies管理Spring Boot应用里面的所有依赖版本，指定了依赖组件的版本  
    以后我们导入依赖默认是不需要写版本；（没有在dependencies里面管理的依赖自然需要声明版本号）
    ```xml
      <properties>
        <activemq.version>5.16.1</activemq.version>
        <antlr2.version>2.7.7</antlr2.version>
        <appengine-sdk.version>1.9.86</appengine-sdk.version>
        <artemis.version>2.15.0</artemis.version>
        <aspectj.version>1.9.6</aspectj.version>
        <assertj.version>3.18.1</assertj.version>
        <atomikos.version>4.0.6</atomikos.version>
        ...
        <tomcat.version>9.0.43</tomcat.version>
      </properties>
    ```
    依赖管理
    ```xml
    <dependencyManagement>
      <dependencies>
        <dependency>
          <groupId>org.apache.activemq</groupId>
          <artifactId>activemq-amqp</artifactId>
          <version>${activemq.version}</version>
        </dependency>
        <dependency>
          <groupId>org.apache.tomcat</groupId>
          <artifactId>tomcat-jdbc</artifactId>
          <version>${tomcat.version}</version>
        </dependency>
        <dependency>
          <groupId>org.apache.tomcat.embed</groupId>
          <artifactId>tomcat-embed-core</artifactId>
          <version>${tomcat.version}</version>
        </dependency>
        <dependency>
          <groupId>org.apache.tomcat.embed</groupId>
          <artifactId>tomcat-embed-websocket</artifactId>
          <version>${tomcat.version}</version>
        </dependency>
        ...
      </dependencies>
    </dependencyManagement>
    ```
##### 启动器
```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
```
* [spring-boot-starter-web](../readme/spring-boot-starter-web-2.4.3.pom)
    ```text
    spring-boot场景启动器；帮我们导入了web模块正常运行所依赖的组件；  
    
    Spring Boot将所有的功能场景都抽取出来，做成一个个的starters（启动器），
    只需要在项目里面引入这些starter相关场景的所有依赖都会导入进来。
    要用什么功能就导入什么场景的启动器
    ```
    
    
## SpringBoot配置

## SpringBoot日志

## SpringBoot WEB开发

## SpringBoot与Docker

## SpringBoot与数据访问

## SpringBoot启动配置原理

## SpringBoot自定义starts

## SpringBoot缓存

## SpringBoot消息

## SpringBoot检索

## SpringBoot任务

## SpringBoot安全

## SpringBoot分布式

## SpringBoot开发热部署

## SpringBoot监控原理

