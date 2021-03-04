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
– IntelliJ IDEA 2019 x64、或STS 
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
[Starters](https://docs.spring.io/spring-boot/docs/2.4.3/reference/html/using-spring-boot.html#using-boot-starter)
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
#### 主入口类
* [HelloWorldMainApplication](../SpringBoot/springboot-01-helloworld/src/main/java/com/java/HelloWorldMainApplication.java) 
    ```java
    package com.java;
    
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    
    /**
     * @SpringBootApplication 标注一个主程序类，说明这是一个Spring Boot应用
     */
    @SpringBootApplication
    public class HelloWorldMainApplication {
        public static void main(String[] args) {
            // 启动Spring应用
            SpringApplication.run(HelloWorldMainApplication.class, args);
        }
    }
    ```
* @SpringBootApplication [对应的java文件](../readme/SpringBootApplication.java)
    ```java
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Inherited
    @SpringBootConfiguration
    @EnableAutoConfiguration
    @ComponentScan(
        excludeFilters = {@Filter(
        type = FilterType.CUSTOM,
        classes = {TypeExcludeFilter.class}
    ), @Filter(
        type = FilterType.CUSTOM,
        classes = {AutoConfigurationExcludeFilter.class}
    )}
    )
    public @interface SpringBootApplication {
        // ...
    }
    ```
* **@[SpringBootConfiguration](../readme/SpringBootConfiguration.java)**

    Spring Boot的配置类
    ```java
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Configuration
    public @interface SpringBootConfiguration {
        // ...
    }
    ```
    * @[Configuration](../readme/Configuration.java)
    
        配置类 -----  配置文件；配置类也是容器中的一个组件；@Component
* @[EnableAutoConfiguration](../readme/EnableAutoConfiguration.java)
    ```text
    开启自动配置功能。
    以前我们需要配置的东西，Spring Boot帮我们自动配置；
    @EnableAutoConfiguration告诉SpringBoot开启自动配置功能；这样自动配置才能生效
    ```
    * **@AutoConfigurationPackage**  
        自动配置包
    * **@Import(AutoConfigurationPackages.Registrar.class)**
    ```text
    Spring的底层注解@Import，给容器中导入一个组件；
    导入的组件由AutoConfigurationPackages.Registrar.class；
    
    将主配置类（@SpringBootApplication标注的类）的所在包及下面所有子包里面的所有组件扫描到Spring容器
    ```
* @**Import**(EnableAutoConfigurationImportSelector.class)  
    给容器中导入组件
    
    * AutoConfigurationImportSelector
        ```text
        导入哪些组件的选择器
        
        将所有需要导入的组件以全类名的方式返回；这些组件就会被添加到容器中；
        ​		会给容器中导入非常多的自动配置类（xxxAutoConfiguration）；
        就是给容器中导入这个场景需要的所有组件，并配置好这些组件
        ```
        ![](../images/SpringBoot/EnableAutoConfigurationImportSelector.png)
        ```text
        有了自动配置类，免去了我们手动编写配置注入功能组件等的工作；
        ​		SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class,classLoader)；
 
        ==Spring Boot在启动的时候从类路径下的 spring-boot-autoconfigure-2.4.3.jar/META-INF/spring.factories中获取EnableAutoConfiguration指定的值，将这些值作为自动配置类导入到容器中，自动配置类就生效，帮我们进行自动配置工作；==以前我们需要自己配置的东西，自动配置类都帮我们；
        
        J2EE的整体整合解决方案和自动配置都在 spring-boot-autoconfigure-2.4.3.jar
        ```

### Spring Initializr快速创建SpringBoot项目
1. 新建Project

    ![](../images/SpringBoot/SpringInitializr01.png)

2. 创建Spring Initializr类型的项目

    选择SDK（即JDK）
    ![](../images/SpringBoot/SpringInitializr02.png)

3. 设置Project Metadata

    选择java版本
    ![](../images/SpringBoot/SpringInitializr03.png)

4. 选择依赖模块

    Dependencies中选择需要的模块即可
    ![](../images/SpringBoot/SpringInitializr04.png)

5. 设置项目名、保存位置、Module名等

    ![](../images/SpringBoot/SpringInitializr05.png)

6. 项目结构

    ![](../images/SpringBoot/SpringInitializr06.png)


* 主程序已经生成好，只需要写的逻辑
* resources
    ```text
    src/main/resources
        static  // 静态资源目录，可放css、js、images等
        templates  // 模板页面目录，不支持JSP页面。可以使用模板引擎：thymeleaf、freemarker
        application.properties  // Spring Boot应用的配置文件，可以修改一些默认配置
    ```

## SpringBoot配置
配置文件作用：修改Spring Boot自动配置的默认值

全局配置配置文件，文件名是固定的，为下面两个中的任意一个
* application.properties
* application.yml

### YAML
YAML: YAML Ain't Markup Language

YAML强调以数据为中心

YAML is a human friendly data serialization
  standard for all programming languages.

* [YAML官网](https://yaml.org/)

* [YAML的书写规范](../readme/YAML书写规范.md)

#### 语法
以 空格 的缩颈来控制层级关系；只要是左对齐的一列数据，都是同一个层级的
```yaml
#表示一对键值对, :冒号后必须有空格
k: v
```

#### 值的写法
* 字面量
    
    普通值，数据、字符串，布尔类型
    * k: v  
        字面值直接写
    * k: "v"  
        不转义值中的特殊字符
    * k: 'v'  
        对值中的特殊字符进行转义
* 对象、Map
    ```yaml
    employee:
      name: Karry
      age: 20
    ```
    行内写法
    ```yaml
    employee: {name: Karry, age: 20}    
    ```
* 数组、List、Set
    ```yaml
    persons:
      -
        name: 张三
        age: 24
      -
        name: 王五
        age: 26
    ```
    ```yaml
    pets:
      - cat
      - dog
      - parrot
    ```
    行内写法
    ```yaml
    pets: [cat, dog, parrot]
    ```

### 从配置文件中获取值并注入到指定对象
配置文件：application.properties 或 application.yml

#### application.properties中文乱码问题
在idea的 Settings /Editor /File Encodings 中

3个Encoding都设置为 UTF-8， 勾上 Transparent native-toascii conversion

![](../images/SpringBoot/application.properties中文乱码处理.png)

#### @ConfigurationProperties与@Value注入值比较

比较项/项 |@ConfigurationProperties |@Value |备注
:--- |:--- |:--- |:--- 
功能 |从配置文件中获取值，<br>再批量注入到Bean对象中 |单个值注入 | 
松散语法 |支持 |不支持 |如Bean属性名为lastName，<br>松散语法: lastName、last-name都可获取此值<br>严格语法: 只有last-name能获取值 
SpEL |不支持 |支持 | 
JSR303数据校验 |支持 |不支持 | 
获取复杂类型 <br>值为Map、List等非基本类型时 |支持 |不支持 | 

* [@ConfigurationProperties示例](../SpringBoot/springboot-config/)
    * pom.xml导入配置文件处理器
        ```xml
                <!--导入配置文件处理器，配置文件进行绑定就会有提示-->
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-configuration-processor</artifactId>
                    <optional>true</optional>
                </dependency>
        ```
    * [Person JavaBean](../SpringBoot/springboot-config/src/main/java/com/java/springbootconfig/bean/Person.java)
        ```java
        @Component
        @ConfigurationProperties(prefix = "person")
        public class Person {
        
        }
        ```
* [@Value示例](../SpringBoot/springboot-config-2/src/main/java/com/java/springbootconfig2/bean/Employee.java)
* 如只需要获取一个值，使用@Value
* 编写专门的JavaBean与配置文件进行映射，使用@ConfigurationProperties

### @RunWith(SpringRunner.class)的作用
* 问题
    ```text
    查了好多文章说@RunWith(SpringRunner.class)注解是一个测试启动器，
    可以加载Springboot测试注解。
    
    本人好奇@RunWith(SpringRunner.class)的作用，
    于是在IDEA中把这个注解去掉后发现Bean也可以通过@Autowired注解进行注入。
    于是比较怀疑@RunWith注解的作用
    ```

* 解释
    ```text
    在正常情况下测试类是需要@RunWith的，作用是告诉java你这个类通过用什么运行环境运行，
    例如启动和创建spring的应用上下文。否则你需要为此在启动时写一堆的环境配置代码。
    你在IDEA里去掉@RunWith仍然能跑是因为在IDEA里识别为一个JUNIT的运行环境，相当于就是一个自识别的RUNWITH环境配置。
    但在其他IDE里并没有。
    ```

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

