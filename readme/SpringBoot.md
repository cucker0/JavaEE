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

    位于 `用户家目录\.m2\settings.xml`

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
一个WEB，功能：浏览器发送hello请求，服务器接受请求并处理，响应Hello World字符串；

1. [环境准备](#SpringBoot环境)

2. 创建一个maven工程

    ![](../images/SpringBoot/newSpringBootProject1.png)
    
    ![](../images/SpringBoot/newSpringBootProject2.png)
    
    ![](../images/SpringBoot/newSpringBootProject3.png)
    
    ![](../images/SpringBoot/newSpringBootProject4.png)

3. 导入spring boot相关的依赖  
    [./pom.xml](../SpringBoot/springboot-01-helloworld/pom.xml)
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
    
    * 在./pom.xml中添加打包插件
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
8. [SpringBoot jar 包部署成系统服务](./SpringBoot%20jar%20包部署成系统服务.md)
    

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
    Choose Initializr Service URL（初始化服务地址），可进行自定义  
    
    * 默认地址: https://start.spring.io
    * 国内地址(香港 华为云): http://start.springboot.io
    * 阿里云: https://start.aliyun.com
    
    ![](../images/SpringBoot/SpringInitializr02.png)
    
    * Tips  
    使用非默认的Initializr Service URL时，需要开启 HTTP Proxy，否则在创建 Spring Initializr 项目时会报错：  
    `Initialization failed for https://start.aliyun.com'please check URL, network and proxy settings`  
    ![](../images/SpringBoot/enable_http_proxy0.png)  
    
    解决方法：开启 HTTP Proxy  
    选中 Auto-detect proxy settings  
    ![](../images/SpringBoot/enable_http_proxy1.png)  
    
    测试 HTTP proxy 连接是否正常  
    ![](../images/SpringBoot/enable_http_proxy2.png)  
    ![](../images/SpringBoot/enable_http_proxy3.png)  
    
    然后重新创建 Spring Initializr 项目

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

3个Encoding都设置为 UTF-8，勾上 Transparent native-toascii conversion

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

#### @RunWith(SpringRunner.class)的作用
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
#### @PropertySource加载指定的properties配置文件
默认是加载application.properties或application.yml

示例: 从 company.properties中获取数据注入到 Company JavaBean中
* src/main/resources下添加 [company.properties](../SpringBoot/springboot-config-2/src/main/resources/company.properties)
* [Company 类](../SpringBoot/springboot-config-2/src/main/java/com/java/springbootconfig2/bean/Company.java)
    ```java
    @Component
    @PropertySource(value = {"classpath:company.properties"})
    @ConfigurationProperties(prefix = "company")
    public class Company {
        private String name;
        private String location;
        private String url;
    }
    ```
* 测试[testCompany()](../SpringBoot/springboot-config-2/src/test/java/com/java/springbootconfig2/SpringbootConfig2ApplicationTests.java)

#### @ImportResource导入Spring配置
* src/main/resources添加[spring-config.xml](../SpringBoot/springboot-config3/src/main/resources/spring-config.xml)配置文件
    
    添加组件，目的：把HelloService组件添加到IOC容器中
    ```xml
        <bean id="helloService" class="com.java.springbootconfig3.service.HelloService"/>
    ```
* 在主程序类中指定要导入的Spring配置文件，[SpringbootConfig3Application](../SpringBoot/springboot-config3/src/main/java/com/java/springbootconfig3/SpringbootConfig3Application.java)
    ```java
    @ImportResource(locations = {"classpath:spring-config.xml"})
    @SpringBootApplication
    public class SpringbootConfig3Application {
    
        public static void main(String[] args) {
            SpringApplication.run(SpringbootConfig3Application.class, args);
        }
    
    }
    ```
* 测试[testHelloService()](../SpringBoot/springboot-config3/src/test/java/com/java/springbootconfig3/SpringbootConfig3ApplicationTests.java)

#### 通过配置类的注解导入Spring配置
* 添加配置类[MyAppConfig](../SpringBoot/springboot-config4/src/main/java/com/java/springbootconfig4/config/MyAppConfig.java)
    ```java
    @Configuration
    public class MyAppConfig {
        // 将方法的返回值添加到 IOC容器中，对应组件的id为方法名
        // 相当于Spring配置文件中的<bean>标签
        @Bean
        public HelloService helloService() {
            return new HelloService();
        }
    }
    ```
* 测试[testHelloService()](../SpringBoot/springboot-config4/src/test/java/com/java/springbootconfig4/SpringbootConfig4ApplicationTests.java)

#### 配置文件中的内置变量和引用变量
application.properties、application.yml都适用

* 随机数
    ```text
    ${random.value}
    ${random.int}
    ${random.long}
    ${random.int(10)}
    ${random.int[1024,65536]}
    ```
* 引用变量
    ```text
    ${key}
    ```
* 示例
    ```properties
    person.last-name=张三${random.uuid}
    person.age=${random.int}
    person.birth=2017/12/15
    person.boss=false
    person.maps.k1=v1
    person.maps.k2=14
    person.lists=a,b,c
    #如果person.hello为空，则使用默认值hello
    person.dog.name=${person.hello:hello}_dog
    person.dog.age=15
    ```

### profile
#### 多个profile文件
```text
配置文件名，可以是
application-{profile}.properties
application-{profile}.yml

默认使用 application.properties 或 application.yml
如果application.properties、application.yml同时存在，优先使用application.properties，
即application.properties的配置覆盖application.yml的配置，最后为两者的合并结果
```

* [示例](../SpringBoot/springboot-profiles)
    * [application.yml](../SpringBoot/springboot-profiles/src/main/resources/application.yml)
        在application.yml指定要激活的配置文件
        ```yaml
        spring:
          profiles:
            #这里可以指定激活哪个配置文件
            active: prod
        ```
    * [application-dev.yml](../SpringBoot/springboot-profiles/src/main/resources/application-dev.yml)
    * [application-prod.yml](../SpringBoot/springboot-profiles/src/main/resources/application-prod.yml)

#### yml多文档块方式配置多个profile
* [application.yml](../SpringBoot/springboot-profiles2/src/main/resources/application.yml)


#### 激活指定的profile
主要有以下几种方式来激活profile

* 命令启动时通过参数指定profile
    >java -jar springboot-profiles2-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
    
    ![](../images/SpringBoot/profile04.png)  
    ![](../images/SpringBoot/profile05.png)  
    ![](../images/SpringBoot/profile06.png)  
    ![](../images/SpringBoot/profile07.png)  

* idea设置启动参数，与上面的情况是类似，都是设置启动参数

    --spring.profiles.active=dev  
    ![](../images/SpringBoot/profile01.png)  
    ![](../images/SpringBoot/profile02.png)

* 设置VM参数
    >-Dspring.profiles.active=dev
    
    ![](../images/SpringBoot/profile03.png)


### 配置文件加载位置
spring boot会扫描以下位置的 application.properties或application.yml配置文件，并加载

[External Application Properties外部应用配置](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-external-config-files)
```text
启动参数 --spring.config.location=application.properties的绝对路径

file:./config/
// jar包所在目录下的子目录 ./config

file:./
// jar包所在目录

classpath:/config/

classpath:/
// 即src/main/resources/ 目录下
```
* 从上到下的作用优先级为由高到低
* 优先级高的配置会覆盖优先级的配置
* 以上几个位置的主配置文件Spring Boot都会加载，优先级高的配置会覆盖优先级的配置，最终为互补后的配置结果总和

在启动参数指定指定配置文件示例
```bash
java -jar springboot-profiles2-0.0.1-SNAPSHOT.jar --spring.config.location=optional:G:/application.properties
```
--spring.config.location指定位置的配置文件作用优先级比上面4个位置的都高，同样与其他位置的配置文件互补后的配置结果总和共同作用

Spring Boot 2.4.3调整

Locations configured by using `spring.config.location` replace the default locations

If you prefer to add addition locations, rather than replacing them, you can use `spring.config.additional-location`

1. `optional:classpath:/`

2. `optional:classpath:/config/`

3. `optional:file:./`

4. `optional:file:./config/`

5. `optional:file:./config/*/`

6. `optional:classpath:custom-config/`

7. `optional:file:./custom-config/`

optional的作用允许配置文件不存在，也不会报错

如果配置文件名不是像 application 这样的，
可以在启动jar包时指定前缀名
```bash
java -jar myproject.jar --spring.config.name=myproject
```

指定两个位置的配置文件
```bash
java -jar myproject.jar --spring.config.location=optional:classpath:/default.properties,optional:classpath:/override.properties
```

### 外部配置加载顺序及作用优先级
[外部配置加载顺序Externalized Configuration 官方文档](https://docs.spring.io/spring-boot/docs/2.4.3/reference/html/spring-boot-features.html#boot-features-external-config)

1. Default properties (specified by setting SpringApplication.setDefaultProperties).

2. @PropertySource annotations on your @Configuration classes.

    Please note that such property sources are not added to the Environment until the application context is being refreshed. This is too late to configure certain properties such as logging.* and spring.main.* which are read before refresh begins.

3. **Config data (such as application.properties files)，jar包外的作用优先级高，含-{profile}配置文件比没有的作用优先级高**
    1. Application properties packaged **inside** your jar (**application.properties** and YAML variants变体).
        1. The classpath root
        2. The classpath /config package
    2. Profile-specific application properties packaged **inside** your jar (**application-{profile}.properties** and YAML variants).
    
    3. Application properties **outside** of your packaged jar (**application.properties** and YAML variants).
        1. The /config subdirectory in the current directory
        2. The current directory
    4. Profile-specific application properties **outside** of your packaged jar (**application-{profile}.properties** and YAML variants).

4. A RandomValuePropertySource that has properties only in random.*.

5. OS environment variables.

6. Java System properties (System.getProperties()).

7. JNDI attributes from java:comp/env.

8. ServletContext init parameters.

9. ServletConfig init parameters.

10. Properties from SPRING_APPLICATION_JSON (inline JSON embedded in an environment variable or system property).

11. **Command line arguments.命令行参数**
    ```text
    java -jar spring-boot-config-02-0.0.1-SNAPSHOT.jar --server.port=8087 --server.servlet.context-path=/location
    java -jar spring-boot-config-02-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
    多个配置用空格分开； --配置项=值
    // server.context-path 在spring 2.x.x 改为了 server.servlet.context-path
    ```
    
12. properties attribute on your tests. Available on @SpringBootTest and the test annotations for testing a particular slice of your application.

13. @TestPropertySource annotations on your tests.

14. Devtools global settings properties in the $HOME/.config/spring-boot directory when devtools is active.

#### 原则
* **序号越大，它的配置作用优先级越高，即序号大的会覆盖序号小的配置，以上位置的配置覆盖互补组合后的配置结果为最终的配置**
* 同一层级下同时存在application.properties和application.yml，则application.properties作用优先级高

### 自动配置原理

自动配置类必须在一定的条件下才能生效

1. SpringBoot入口程序类，有@SpringBootApplication注解
    ```java
    @SpringBootApplication
    public class SpringbootAutoconfigApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(SpringbootAutoconfigApplication.class, args);
        }
    
    }
    ```
2. 注解的 SpringBootApplication接口，有@EnableAutoConfiguration注解
    ```java
    @EnableAutoConfiguration
    public @interface SpringBootApplication {
    
    }
    ```

3. EnableAutoConfiguration接口中导入了 AutoConfigurationImportSelector
    ```java
    @Import({AutoConfigurationImportSelector.class})
    public @interface EnableAutoConfiguration {
    }
    ```

    * [AutoConfigurationImportSelector](../readme/AutoConfigurationImportSelector.java)
        * selectImports()方法
        * protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes)
    
    * 加载指定的组件
        ```text
        将 spring-boot-autoconfigure-2.4.3.jar 类路径下的META-INF/spring.factories配置中的 
        org.springframework.boot.autoconfigure.EnableAutoConfiguration=指定的组件 加载到IOC容器中
        ```
        [spring.factories](../readme/spring.factories)
        ![](../images/SpringBoot/autoconfig02.png)

4. @Conditional派生注解
作用：必须是@Conditional指定的条件成立，才给容器中添加组件，配置配里面的所有内容才生效；

| @Conditional扩展注解            | 作用（判断是否满足当前指定条件）                 |
| ------------------------------- | ------------------------------------------------ |
| @ConditionalOnJava              | 系统的java版本是否符合要求                       |
| @ConditionalOnBean              | 容器中存在指定Bean；                             |
| @ConditionalOnMissingBean       | 容器中不存在指定Bean；                           |
| @ConditionalOnExpression        | 满足SpEL表达式指定                               |
| @ConditionalOnClass             | 系统中有指定的类                                 |
| @ConditionalOnMissingClass      | 系统中没有指定的类                               |
| @ConditionalOnSingleCandidate   | 容器中只有一个指定的Bean，或者这个Bean是首选Bean |
| @ConditionalOnProperty          | 系统中指定的属性是否有指定的值                   |
| @ConditionalOnResource          | 类路径下是否存在指定资源文件                     |
| @ConditionalOnWebApplication    | 当前是web环境                                    |
| @ConditionalOnNotWebApplication | 当前不是web环境                                  |
| @ConditionalOnJndi              | JNDI存在指定项                                   |

#### 查看自动配置的结果报告
在application.properties配置中添加如下配置
```text
debug=true
```
在控制台中可以看到 CONDITIONS EVALUATION REPORT

Positive matches为已经加载的
![](../images/SpringBoot/autoconfig.png)

## SpringBoot日志
日志框架、日志配置

### 日志框架
* 常见的日志框架
    ```text
    JUL (Java Util Logging)
    JCL (Jakarta Commons Logging，即Apache Commons Logging)
    jboss-logging
    log4j (作者：Ceki Gülcü，他把log4j捐献给了Apache软件基金会)
    logback (与log4j为同一个开发者所写，取代log4j的一个日志框架，是slf4j的原生实现)
    log4j2 (apache小组开发，是log4j 1.x和logback的改进版)
    slf4j (Simple Logging Facade for Java，目的为替代commons-logging)
    ```
    * slf4j工作原理
        ```text
        slf4j只是一个日志标准(interface)，
        它只做两件事：1.LoggerFactory类用来获取Logger 2.Logger类用来打印日志
        ```
* 日志接口抽象层框架
    * slf4j
    * JCL
    * jboss-loggin

* 日志实现层框架
    * log4j
    * logback
    * log4j2
    * JUL

Spring框架默认使用的日志框架是JCL

SpringBoot默认使用的日志框架是slf4j + logback


### slf4j的使用
[slf4j官网](http://www.slf4j.org/)

* HelloWorld
    ```text
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    
    public class HelloWorld {
      public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(HelloWorld.class);
        logger.info("Hello World");
      }
    }
    ```

* [示例](../SpringBoot/springboot-log/src/test/java/com/java/springbootlog/SpringbootLogApplicationTests.java)
    ```java
    package com.java.springbootlog;
    
    import org.junit.jupiter.api.Test;
    import org.junit.runner.RunWith;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.test.context.junit4.SpringRunner;
    
    
    @RunWith(SpringRunner.class)
    @SpringBootTest
    class SpringbootLogApplicationTests {
        // 生成日志记录器
        private final Logger logger = LoggerFactory.getLogger(getClass());
    
        @Test
        void contextLoads() {
            logger.trace("trace日志：===");
            logger.debug("debug日志：===");
            logger.info("info日志：===");
            logger.warn("warn日志：===");
            logger.error("error：===");
        }
    
    }
    ```
    
* 参数占位符模式
    ```text
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    
    public class Wombat {
        final Logger logger = LoggerFactory.getLogger(Wombat.class);
        Integer t;
        Integer oldT;
    
        public void setTemperature(Integer temperature) {
            oldT = t;        
            t = temperature;
    
            logger.debug("Temperature set to {}. Old temperature was {}.", t, oldT);
    
            if(temperature.intValue() > 50) {
                logger.info("Temperature has risen above 50 degrees.");
            }
        }
    } 
    ```

* 流式API模式
    ```text
    // 示例1
    logger.atInfo().log("Hello world");
    // <==>
    logger.info("Hello world.");
    
    
    // 示例2
    int newT = 15;
    int oldT = 16;
    // using traditional API
    logger.debug("Temperature set to {}. Old temperature was {}.", newT, oldT);
    
    // 示例3
    // using fluent API, log message with arguments
    logger.atDebug().log("Temperature set to {}. Old temperature was {}.", newT, oldT);
    
    
    // 示例4
    // using fluent API, add arguments one by one and then log message
    logger.atDebug().addArgument(newT).addArgument(oldT).log("Temperature set to {}. Old temperature was {}.");
    
    // 示例5
    // using fluent API, add one argument and then log message providing one more argument
    logger.atDebug().addArgument(newT).log("Temperature set to {}. Old temperature was {}.", oldT);
    
    // 示例6
    // using classical API
    logger.debug("oldT={} newT={} Temperature changed.", newT, oldT);
    // using fluent API, 与上面等效
    logger.atDebug().addKeyValue("oldT", oldT).addKeyValue("newT", newT).log("Temperature changed.");
    
    ```

#### 绑定日志框架
```text
slf4j-log4j12-1.7.28.jar
Binding for log4j version 1.2, a widely used logging framework. You also need to place log4j.jar on your class path.

slf4j-jdk14-1.7.28.jar
Binding for java.util.logging, also referred to as JDK 1.4 logging

slf4j-nop-1.7.28.jar
Binding for NOP, silently discarding all logging.

slf4j-simple-1.7.28.jar
Binding for Simple implementation, which outputs all events to System.err. Only messages of level INFO and higher are printed. This binding may be useful in the context of small applications.

slf4j-jcl-1.7.28.jar
Binding for Jakarta Commons Logging. This binding will delegate all SLF4J logging to JCL.

logback-classic-1.2.3.jar (requires logback-core-1.2.3.jar)
slf4j本地实现
```

slf4j日志框架总体思路图
![](../images/SpringBoot/logging3.png)


#### 自定义日志配置
SpringBoot将自动加载以下日志配置

Logging System |Customization |备注
:--- |:--- |:--- 
Logback |logback-spring.xml, <br>logback-spring.groovy, <br>logback.xml, <br>logback.groovy |logback.xml只能被slf4j日志架构所识别，绕过了SpringBooot框架。<br>logback-spring.xml能被SpringBoot解析
Log4j2 |log4j2-spring.xml, <br>log4j2.xml 
JDK (Java Util Logging) |logging.properties

* 注意
    * 每一个日志的实现框架都有自己的配置文件。
    * 接口层使用slf4j日志框架后，**配置文件还是做成日志实现框架自己本身的配置文件**

xxx-spring.xml或logging.config才能支持 <springProfile>的特殊profile配置，否则报错
```xml
<springProfile name="staging">
    <!-- configuration to be enabled when the "staging" profile is active -->
</springProfile>

<!-- 对dev或staging环境生效 -->
<springProfile name="dev | staging">
    <!-- configuration to be enabled when the "dev" or "staging" profiles are active -->
</springProfile>

<springProfile name="!production">
    <!-- configuration to be enabled when the "production" profile is not active -->
</springProfile>
```

#### 桥接历史遗留日志API
![](../images/SpringBoot/logging04_legacy.png)

#### SpringBoot的日志关系
Spring Boot默认的日志框架是slf4j + logback 

组件依赖关系如下

显示pom.xml组件依赖关系
![](../images/SpringBoot/logging1.png)

![](../images/SpringBoot/logging2.png)

* ./pom.xml
    ```xml
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
        </dependencies>
    ```

    * spring-boot-starter-web-2.4.3.pom
        ```xml
          <dependencies>
            <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter</artifactId>
              <version>2.4.3</version>
              <scope>compile</scope>
              </dependency>
          </dependencies>
        ```

        * spring-boot-starter-2.4.3.pom
            ```xml
              <dependencies>
                <dependency>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-starter-logging</artifactId>
                  <version>2.4.3</version>
                  <scope>compile</scope>
                </dependency>
              </dependencies>
            ```

            * spring-boot-starter-logging-2.4.3.pom
                ```xml
                  <dependencies>
                    <dependency>
                      <groupId>ch.qos.logback</groupId>
                      <artifactId>logback-classic</artifactId>
                      <version>1.2.3</version>
                      <scope>compile</scope>
                    </dependency>
                  </dependencies>
                ```

                * logback-classic-1.2.3.pom
                    ```xml
                      <dependencies>
                        <dependency>
                          <groupId>ch.qos.logback</groupId>
                          <artifactId>logback-core</artifactId>
                          <scope>compile</scope>
                        </dependency>
                      </dependencies>
                    ```

* SpringBoot底层使用slf4j(日志抽象层框架) + logback(日志实现框架)记录日志
* SpringBoot把其他的日志替换成了slf4j
* 其他日志转slf4j包
    ```text
    jcl-over-slf4j.jar
    log4j-over-slf4j.jar
    jul-to-slf4j.jar
    ```
* 如果要引入其他的日志框架，必须把当前框架中默认的日志依赖排除掉

SpringBoot能自动适配所有的日志，而且底层使用slf4j+logback的方式记录日志，
引入其他框架的时候，只需要把这个框架依赖的日志框架排除掉即可

##### 设置保存日志的路径
相关参数：logging.file.name、logging.file.path

* logging.file.name
    ```text
    指定文件名
    
    相对路径，是相对于jar包所在的目录。只写文件名
    绝对路径
    ```
    示例
    ```properties
    logging.file.name=app.log
    #或
    logging.file.name=E:\\dev\\JavaEE\\SpringBoot\\springboot-log\\log\\app.log
    ```
* logging.file.path
    ```text
    指定保存日志的目录，保存日志的文件名自动默认为设置为spring.log
    ```
    示例
    ```properties
    #最后保存日志的文件为 /var/log/spring.log
    logging.file.path=/var/log
    ```

**当logging.file.name和logging.file.path都未设置时，只在控制台打印日志**

**当同时指定了logging.file.name和logging.file.path时，只有logging.file.name的设置生效**

##### 设置日志输出格式
```text
%d  日期时间
%thread  线程名
%-5level  级别从左显示5个字符宽度
%logger{50}  表示logger名字最长50个字符，否则按照句点分割
%msg  日志消息
%n  换行符
```

* logging.pattern.console
    ```text
    设置控制台日志的输出格式

    示例：
    logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss,SSS} ## [%thread] %-5level %logger{50} - %msg%n
    ```

* logging.pattern.file
    ```text
    设置文件日志的输出格式

    示例：
    logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss.SSS} == [%thread] %-5level %logger{50} - %msg%n
    ```

[SpringBoot日志官网文档](https://docs.spring.io/spring-boot/docs/2.4.3/reference/html/spring-boot-features.html#boot-features-logging)

### 切换日志框架
抽象层统一使用slf4j框架的解决方案

**统一多个系统的日志抽象层框架slf4j**
1. 将系统中其他日志框架先排除
2. 用中间包来替换原有的日志框架
3. 导入slf4j的实现日志框架

#### SpringBoot日志框架切换为slf4j+log4j
不建议这么做，slf4j+logback，本来logback就是log4j的升级版

思路：如下图，需要把图一的组件依赖关系 变成 图二的组件依赖关系
1. 排除log4j-over-slf4j，（如果有的话）
2. 排除logback组件
3. 添加slf4j-log4j12组件
![](../images/SpringBoot/log4j_0.png)

**操作步骤**
* SpringBoot初始化的POM组件依赖关系，(只选择了WEB模块)
* 排除logback组件
    ![](../images/SpringBoot/log4j_1.png)
    ```xml
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
                <exclusions>
                    <exclusion>
                        <artifactId>logback-classic</artifactId>
                        <groupId>ch.qos.logback</groupId>
                    </exclusion>
                </exclusions>
            </dependency>
    ```
* 添加slf4j-log4j12组件
    ```xml
            <dependency>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-log4j12</artifactId>
            </dependency>
    ```
    最终的配置[pom.xml](../SpringBoot/springboot-log2/pom.xml)
    ![](../images/SpringBoot/log4j_2.png)
* [log4j日志配置](../SpringBoot/springboot-log2/src/main/resources/log4j.properties)

#### SpringBoot日志框架切换为slf4j+log4j2
思路：使用spring-boot-starter-log4j2 starter替换spring-boot-starter-logging starter

**操作步骤：**
1. 排除SpringBoot默认的logback组件，即logback-classic
2. 添加spring-boot-starter-log4j2依赖

starter说明：
[using-boot-starter](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#using-boot-starter)
、
[using-spring-boot#using-boot-starter](https://docs.spring.io/spring-boot/docs/2.4.3/reference/html/using-spring-boot.html#using-boot-starter)

* SpringBoot初始化的POM组件依赖关系，(只选择了WEB模块)
    ![](../images/SpringBoot/log4j2_1.png)

* 排除spring-boot-starter-logging
    ![](../images/SpringBoot/log4j2_2.png)
    ```xml
    <project>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
                <exclusions>
                    <!-- 这是上面这操作,idea自动添加的排除项 -->
                    <exclusion>
                        <artifactId>spring-boot-starter-logging</artifactId>
                        <groupId>org.springframework.boot</groupId>
                    </exclusion>
                </exclusions>
            </dependency>
        </dependencies>
    </project>
    ```

* 添加spring-boot-starter-log4j2依赖
    [pom.xml](../SpringBoot/springboot-log3/pom.xml)
    ```xml
    <project>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
                <exclusions>
                    <exclusion>
                        <artifactId>spring-boot-starter-logging</artifactId>
                        <groupId>org.springframework.boot</groupId>
                    </exclusion>
                </exclusions>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-log4j2</artifactId>
            </dependency>
        </dependencies>
    </project>
    ```
    slf4j组件依赖关系
    ![](../images/SpringBoot/log4j2_3.png)
    
* [log4j2配置](../SpringBoot/springboot-log3/src/main/resources/log4j2-spring.xml)

## SpringBoot WEB开发
SpringBoot web项目的创建参考 [创建SpringBoot HelloWorld工程](readme/SpringBoot.md#创建SpringBoot-HelloWorld工程)

### 静态资源的映射规则
* /webjars/** 到classpath:/META-INF/resources/webjars/ 查找资源
    * [maven引入webjars资源方法](https://www.webjars.org/)

* "/**" 未被显示映射时，都到以下静态资源映射路径中找
    ```text
    [
    "classpath:/META-INF/resources/", 
    "classpath:/resources/",
    "classpath:/static/", 
    "classpath:/public/" 
    "/":当前项目的根路径
    ]
    ```
* 欢迎页，到上面的静态资源路径下查找index.html，被映射为
    >/index.html
* favicon.ico在所有静态资路径中找favicon.ico文件，即**/favicon.ico
    >/favicon.ico
    * favicon.ico在Spring Boot 2.2.X后被删除了


[ResourceProperties](../readme/ResourceProperties.java)

[WebMvcAutoConfiguration](../readme/WebMvcAutoConfiguration.java)
```java
@Configuration(
    proxyBeanMethods = false
)
@ConditionalOnWebApplication(
    type = Type.SERVLET
)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})
@ConditionalOnMissingBean({WebMvcConfigurationSupport.class})
@AutoConfigureOrder(-2147483638)
@AutoConfigureAfter({DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class, ValidationAutoConfiguration.class})
public class WebMvcAutoConfiguration {
    // /webjars/** 静态资源映射
    @Configuration(
        proxyBeanMethods = false
    )
    @EnableConfigurationProperties({WebProperties.class})
    public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration implements ResourceLoaderAware {
        protected void addResourceHandlers(ResourceHandlerRegistry registry) {
            super.addResourceHandlers(registry);
            if (!this.resourceProperties.isAddMappings()) {
                logger.debug("Default resource handling disabled");
            } else {
                ServletContext servletContext = this.getServletContext();
                this.addResourceHandler(registry, "/webjars/**", "classpath:/META-INF/resources/webjars/");
                this.addResourceHandler(registry, this.mvcProperties.getStaticPathPattern(), (registration) -> {
                    registration.addResourceLocations(this.resourceProperties.getStaticLocations());
                    if (servletContext != null) {
                        registration.addResourceLocations(new Resource[]{new ServletContextResource(servletContext, "/")});
                    }

                });
            }
        }
        
        // 欢迎页，/index.html
        @Bean
        public WelcomePageHandlerMapping welcomePageHandlerMapping(ApplicationContext applicationContext, FormattingConversionService mvcConversionService, ResourceUrlProvider mvcResourceUrlProvider) {
            WelcomePageHandlerMapping welcomePageHandlerMapping = new WelcomePageHandlerMapping(new TemplateAvailabilityProviders(applicationContext), applicationContext, this.getWelcomePage(), this.mvcProperties.getStaticPathPattern());
            welcomePageHandlerMapping.setInterceptors(this.getInterceptors(mvcConversionService, mvcResourceUrlProvider));
            welcomePageHandlerMapping.setCorsConfigurations(this.getCorsConfigurations());
            return welcomePageHandlerMapping;
        }
        
    }
}
```

* webjars
    ![](../images/SpringBoot/web1.png)
    
    http://127.0.0.1:8080/webjars/jquery/3.6.0/jquery.js


### Thymeleaf模板引擎
[thymeleaf官网](https://www.thymeleaf.org/)

常见的模板引擎有JSP、Velocity、Freemarker、Thymeleaf

Spring Boot推荐使用Thymeleaf

#### 引入thymeleaf
pom.xml中添加如下配置，一般为最新稳定版
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```

#### 使用thymeleaf
* 模板文件存放位置
    ```text
    // 路径前缀
    classpath:/templates/
    
    // 文件后缀
    .html
    ```
    
    ThymeleafProperties类（一般放置于/config 目录下）
    ```java
    @ConfigurationProperties(
        prefix = "spring.thymeleaf"
    )
    public class ThymeleafProperties {
        private static final Charset DEFAULT_ENCODING;
        public static final String DEFAULT_PREFIX = "classpath:/templates/";
        public static final String DEFAULT_SUFFIX = ".html";
        private boolean checkTemplate = true;
        private boolean checkTemplateLocation = true;
        private String prefix = "classpath:/templates/";
        private String suffix = ".html";
        private String mode = "HTML";
        ... ...
    }
    ```
    @ConfigurationProperties 注解将配置文件(application.properties/yml) 中前缀为 spring.thymeleaf 的配置 和 这个类中的属性绑定。
    
* html文件中导入thymeleaf命名空间，在编写html文件时方便弹出thymeleaf的语法提示

    不添加也不会影响thymeleaf的模板渲染
    
    ```html
    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
    ```
* 使用thymeleaf语法
    ```html
    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
    
      <head>
        <title>Good Thymes Virtual Grocery</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" media="all" 
              href="../../css/gtvg.css" th:href="@{/css/gtvg.css}" />
      </head>
    
      <body>
      
        <p th:text="#{home.welcome}">Welcome to our grocery store!</p>
      
      </body>
    
    </html>
    ```

#### thymeleaf使用手册
[thymeleaf使用手册](../readme/thymeleaf_manual.md)


### Spring MVC自动配置
[Spring MVC Auto-configuration](https://docs.spring.io/spring-boot/docs/2.4.4/reference/html/spring-boot-features.html#boot-features-spring-mvc-auto-configuration)


SpringBoot自动配置好了SpringMVC，WebMvcAutoConfiguration默认添加了以下功能:

* Inclusion of `ContentNegotiatingViewResolver` and `BeanNameViewResolver` beans.
    * BeanNameViewResolver: 根据方法返回的值得到视图对象，视图对象决定如何渲染（forward或redirect）
    * ContentNegotiatingViewResolver
    * 如何定制：可以给IOC容器中添加一个视图解析器；SpringBoot会自动将其组合进来  
    [MyViewResolver](../SpringBoot/springmvc-autoconfig/src/main/java/com/java/springmvcautoconfig/config/MyViewResolver.java)
    [注册自定义ViewResolver](../SpringBoot/springmvc-autoconfig/src/main/java/com/java/springmvcautoconfig/SpringmvcAutoconfigApplication.java)
* Support for serving static resources, including support for `WebJars`

* Automatic registration of `Converter`, `GenericConverter`, and `Formatter` beans.
    * Converter：转换器
    * GenericConverter：泛型转换器
    * Formatter:格式化器
* Support for `HttpMessageConverters`.
    * HttpMessageConverters:从IOC容器中获取所有的HttpMessageConverter
    * HttpMessageConverter:SpringMVC用来转换转换HTTP请求和响应的消息；如User对象--->JSON
    * 添加自定义的HttpMessageConverter：将自定义的组件注册到容器（@Bean，@Component）
* Automatic registration of `MessageCodesResolver`.
    >定义错误代码生成规则
* Static `index.html` support.

* Automatic use of a `ConfigurableWebBindingInitializer` bean.

#### 扩展SringMVC配置
作用：既保留所有的自动配置，又能用我们扩展的MVC配置

If you want to keep those Spring Boot MVC customizations and make more MVC customizations 

(interceptors, formatters, view controllers, and other features), 

you can add your own `@Configuration` class of type `WebMvcConfigurer` but without `@EnableWebMvc`.


**SpringMVC一般的情况下可有SrpingMVC.xml来配置它，但不Spring Boot没有了xml的配置文件，如何来配置SpringMVC呢**
假设源SpringMVC.xml配置如下
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <mvc:view-controller path="/hello" view-name="success"/>
    <mvc:interceptors>
        <!-- 自定义拦截器 -->
        <mvc:interceptor>
            <!-- <mvc:mapping> 指定需要拦截的路径 -->
            <mvc:mapping path="/login"/>
            <!-- <mvc:exclude-mapping> 排除不拦截的路径 -->
            <!-- <mvc:exclude-mapping path="/i18n"/> -->
            <bean class="com.java.springmvcautoconfig.config.AdminInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>
</beans>
```

* 方法
```text
编写一个配置类，并在类上加上@Configuration注解，实现WebMvcConfigurer接口，重写相应的方法即可
注意不能加 @EnableWebMvc注解，如果加了则是完全接管Spring MVC配置了，就不是扩展Spring MVC配置了
```

* [扩展Spring MVC配置示例](../SpringBoot/springmvc-autoconfig/src/main/java/com/java/springmvcautoconfig/config/MyMvcConfig.java)
    ```java    
    @Configuration
    public class MyMvcConfig implements WebMvcConfigurer {
        // 重写WebMvcConfigurer接口中相应的方法即可
    
        // 添加View控制器
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/hello").setViewName("success");
        }
    
        // 添加拦截器
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/login");
        }
    }
    ```

#### 全面接管SpringMVC的自动配置
当不需要Spring Boot中的SpringMVC自动配置时，由自己来配置SpringMVC。

* 示例

    在上面[扩展SringMVC配置](readme/SpringBoot.md#扩展SringMVC配置)的基础上添加 @EnableWebMvc 注解即可
    ```java
    @EnableWebMvc
    @Configuration
    public class MyMvcConfig implements WebMvcConfigurer {
        // 重写WebMvcConfigurer接口中相应的方法即可
    
        // 添加View控制器
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/hello").setViewName("success");
        }
    
        // 添加拦截器
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/login");
        }
    }
    ``` 

### 修改SpringBoot的默认配置
机制
1. 检查application.properties(或yaml格式)的配置
2. Spring Boot在自动配置组件的时候，先看IOC容器中是否有用户自己配置的组件(有@Bean、@Component注解的)，
    * 一类组件中只允许有一个组件的，在用户没有配置此类组件时，将自动配置
    * 一类组件中允许有多个组件的，将用户配置的和自动配置的组件组合起来
3. Spring Boot中有很多的xxxConfigure进行自动配置
4. Spring Boot中有很多的xxxCustomize进行定制配置

### crud-resful工程示例
#### 默认首页
* 可以写Controller方法
* 也可以通过配置，添加view视图
    [MyMvcConfig](../SpringBoot/crud-resful/src/main/java/com/java/crudresful/config/MyMvcConfig.java)
    ```java
    @Configuration
    public class MyMvcConfig implements WebMvcConfigurer {
        /**
         * 添加视图
         * @param registry
         */
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/").setViewName("index");
            registry.addViewController("/index").setViewName("index");
            registry.addViewController("/index.html").setViewName("index");
            // 登录成功的view
            registry.addViewController("/main.html").setViewName("emp/dashboard");
        }
    }
    ```
    
    http://127.0.0.1:8080/crud/
    
    ![](../images/SpringBoot/crud4.png)

#### 国际化
1. 编写国际化配置文件
    
    * [login.properties](../SpringBoot/crud-resful/src/main/resources/i18n/login.properties)
    * [login_en_US.properties](../SpringBoot/crud-resful/src/main/resources/i18n/login_en_US.properties)
    * [login_zh_CN.properties](../SpringBoot/crud-resful/src/main/resources/i18n/login_zh_CN.properties)  
    ![](../images/SpringBoot/crud1.png)
2. application.properties中设置basename

    MessageSourceAutoConfiguration已经配置了默认的basename为：messages
    ```properties
    spring.messages.basename=i18n.login
    ```
    * application.properties乱码问题
        ![](../images/SpringBoot/application.properties中文乱码处理.png)

3. html页面中获取国际化的值

    登录页国际化[signin.html](../SpringBoot/crud-resful/src/main/resources/templates/user/signin.html)

    原理:国际化Locale（区域信息对象）；LocaleResolver（获取区域信息对象）
    ```java
    @Configuration(
        proxyBeanMethods = false
    )
    @ConditionalOnWebApplication(
        type = Type.SERVLET
    )
    @ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})
    @ConditionalOnMissingBean({WebMvcConfigurationSupport.class})
    @AutoConfigureOrder(-2147483638)
    @AutoConfigureAfter({DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class, ValidationAutoConfiguration.class})
    public class WebMvcAutoConfiguration {
        @Configuration(
            proxyBeanMethods = false
        )
        @EnableConfigurationProperties({WebProperties.class})
        public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration implements ResourceLoaderAware {
            @Bean
            @ConditionalOnMissingBean(
                name = {"localeResolver"}
            )
            public LocaleResolver localeResolver() {
                if (this.webProperties.getLocaleResolver() == org.springframework.boot.autoconfigure.web.WebProperties.LocaleResolver.FIXED) {
                    return new FixedLocaleResolver(this.webProperties.getLocale());
                } else if (this.mvcProperties.getLocaleResolver() == org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.LocaleResolver.FIXED) {
                    return new FixedLocaleResolver(this.mvcProperties.getLocale());
                } else {
                    AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
                    Locale locale = this.webProperties.getLocale() != null ? this.webProperties.getLocale() : this.mvcProperties.getLocale();
                    localeResolver.setDefaultLocale(locale);
                    return localeResolver;
                }
            }
        // ...
        } 
        // ...
    }
    ```
    默认的就是根据请求头带(Accept-language字段)来的区域信息获取Locale进行国际化
    ![](../images/SpringBoot/crud2.png)
    
4. 点击链接切换语言
    
    * 在请求参数中携带区域信息  
        [signin.html](../SpringBoot/crud-resful/src/main/resources/templates/user/signin.html)
        ```html
                <a class="btn btn-sm" th:href="@{/user/login(lang=zh_CN)}">中文</a>
                <a class="btn btn-sm" th:href="@{/user/login(lang=en_US)}">English</a>
        ```
        ![](../images/SpringBoot/crud3.png)
    
    * 自定义Locale解析器
        [MyLocaleResolver](../SpringBoot/crud-resful/src/main/java/com/java/crudresful/component/MyLocaleResolver.java)
        ```java
        public class MyLocaleResolver implements LocaleResolver {
            @Override
            public Locale resolveLocale(HttpServletRequest httpServletRequest) {
                String lang = httpServletRequest.getParameter("lang");
                Locale locale = Locale.getDefault();
                if (!StringUtils.isEmptyOrWhitespace(lang)) {
                    String[] langSplit = lang.split("_");
                    locale = new Locale(langSplit[0], langSplit[1]);
                }
                return locale;
            }
        
            @Override
            public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {
        
            }
        }
        ```
    
    * 配置添加一个Locale区域解析器[MyMvcConfig](../SpringBoot/crud-resful/src/main/java/com/java/crudresful/config/MyMvcConfig.java)
        ```java
        @Configuration
        public class MyMvcConfig implements WebMvcConfigurer {    
            /**
             * 添加一个 Locale区域解析器
             */
            @Bean(name = "localeResolver")
            public LocaleResolver localeResolver() {
                return new MyLocaleResolver();
            }
        }
        ```

#### 登录
1. 登录/登出 控制器[LoginController](../SpringBoot/crud-resful/src/main/java/com/java/crudresful/controller/LoginController.java)
2. 自定义登录处理拦截器[LoginHandlerInterceptor](../SpringBoot/crud-resful/src/main/java/com/java/crudresful/component/LoginHandlerInterceptor.java)
    * 注册拦截器
        [MyMvcConfig](../SpringBoot/crud-resful/src/main/java/com/java/crudresful/config/MyMvcConfig.java)
        ```java
        @Configuration
        public class MyMvcConfig implements WebMvcConfigurer {
            /**
             * 注册拦截器
             *
             * @param registry
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // LoginHandlerInterceptor拦截器，主要功能是登录状态检测
                // .addPathPatterns() 要拦截的路径，/** 所有路径
                // .excludePathPatterns()  排除的路径，
                // 静态资源是放行的，css,js,img等
                // "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg", "/**/*.gif", "/**/fonts/*", "/**/*.svg"
                registry.addInterceptor(new LoginHandlerInterceptor())
                        .addPathPatterns("/**")
                        .excludePathPatterns("/", "/index", "/index.html", "/user/login", "/assert/**");
            }
        }
        ```
3. 登录页面[signin.html](../SpringBoot/crud-resful/src/main/resources/templates/user/signin.html)
4. 注册登录成功的视图
    ```java
    package com.java.crudresful.config;
    
    @Configuration
    public class MyMvcConfig implements WebMvcConfigurer {
        /**
         * 添加视图
         * @param registry
         */
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            // 登录成功的view
            registry.addViewController("/main.html").setViewName("emp/dashboard");
        }
    }
    ```
    ![](../images/SpringBoot/crud3.png)

#### 员工CRUD操作
* 项目要求
    * CRUD满足Restful风格
    * URI格式
        ```text
        /资源名称/资源标识
        
        用http请求方式来区分对资源的CRUD操作
        ```
    
        操作/风格 |普通CRUD(用uri来区分操作) |Restful CRUD 
        :--- |:--- |:--- 
        添加 |/addEmp?k1=v1&k2=v2 |POST /emp
        查询 |/getEmp |GET /emp
        修改 |/updateEmp?id=xxx&xx=xxx |PUT /emp/{id}
        删除 |/deleteEmp?id=1 |DELETE /emp/{id}
    
    * 项目请求架构

        功能 |请求方式 |请求URI 
        :--- |:--- |:--- 
        查询所有员工 |GET |/emps 
        显示添加员工的操作页面 |GET |/emp 
        添加员工 |POST |/emp 
        显示员工修改页面，展示指定员工的信息 |GET |/emp/1 
        修改员工信息 |PUT |/emp 
        删除员工 |DELETE |/emp/1 

##### 准备工作
* bean
    * [Department](../SpringBoot/crud-resful/src/main/java/com/java/crudresful/bean/Department.java)
    * [Employee](../SpringBoot/crud-resful/src/main/java/com/java/crudresful/bean/Employee.java)

* dao
    * [DepartmentDao、并构造若干部门信息](../SpringBoot/crud-resful/src/main/java/com/java/crudresful/dao/DepartmentDao.java)
    * [EmployeeDao、并构造若干员工信息](../SpringBoot/crud-resful/src/main/java/com/java/crudresful/dao/EmployeeDao.java)
  
##### 员工列表
###### thymeleaf公共片段抽取
* [header](../SpringBoot/crud-resful/src/main/resources/templates/common/header.html)
* [footer](../SpringBoot/crud-resful/src/main/resources/templates/common/footer.html)
* [bar](../SpringBoot/crud-resful/src/main/resources/templates/common/bar.html)
    * topbar
    * sidebar

###### 员工控制器--列出员工方法
[EmployeeController list(Model model)](../SpringBoot/crud-resful/src/main/java/com/java/crudresful/controller/EmployeeController.java)

获取所有员工，传参给thymeleaf模板，返回模板[list.html](../SpringBoot/crud-resful/src/main/resources/templates/emp/list.html)

![](../images/SpringBoot/crud_list.png)

##### 添加员工
* 员工控制器--显示添加员工操作方法
    [EmployeeController toAddPage(Model model)](../SpringBoot/crud-resful/src/main/java/com/java/crudresful/controller/EmployeeController.java)

* 显示添加员工的操作页
    [add.html](../SpringBoot/crud-resful/src/main/resources/templates/emp/add.html)

    ![](../images/SpringBoot/crud_toAdd.png)
    
    * 日期格式注意事项
    
        默认日期是按照/分隔的方式    
        
        * application设置spring mvc的日期、时间格式，全局生效
            [application.properties](../SpringBoot/crud-resful/src/main/resources/application.properties)
            ```properties
            spring.mvc.format.date=yyyy-MM-dd
            spring.mvc.format.date-time=yyyy-MM-dd HH:mm:ss
            ``` 
            
            也可以在Bean的相应字段中使用DateTimeFormat注解，当个Bean类生效
            ```java
            public class Employee {
                @DateTimeFormat(pattern = "yyyy-MM-dd")
                private Date birth;
            }
            ```

* 员工控制器--添加员工方法
    [EmployeeController addEmp(Employee e)](../SpringBoot/crud-resful/src/main/java/com/java/crudresful/controller/EmployeeController.java)
    
    
##### 修改员工
* 员工控制器--显示修改员工操作方法
    [EmployeeController toEditPage(@PathVariable("id") Integer id, Model model)](../SpringBoot/crud-resful/src/main/java/com/java/crudresful/controller/EmployeeController.java)

* 显示修改员工的操作页
    [edit.html](../SpringBoot/crud-resful/src/main/resources/templates/emp/edit.html)

* 员工控制器--修改员工方法
    [EmployeeController updateEmp(Employee e)](../SpringBoot/crud-resful/src/main/java/com/java/crudresful/controller/EmployeeController.java)

##### 删除员工
* 员工控制器--删除员工方法
    [EmployeeController deleteEmp(@PathVariable("id") Integer id)](../SpringBoot/crud-resful/src/main/java/com/java/crudresful/controller/EmployeeController.java)

* html前端

    [list.html](../SpringBoot/crud-resful/src/main/resources/templates/emp/list.html) 
    ```html
    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                ...
                <th>operate</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="emp : ${emps}">
                ...
                <td>
                    <a class="btn btn-sm btn-primary" th:href="@{/emp/} + ${emp.id}">修改</a>
                    <button class="btn btn-sm btn-danger delete-btn" th:attr="delete-uri=@{/emp/} + ${emp.id}">删除</button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
  
    <form id="delete-employee-form" method="post">
      <input type="hidden" name="_method" value="delete">
    </form>
  
    <script>
        // 给 删除 按钮绑定事件
        $(".delete-btn").click(function () {
            var _uri = $(this).attr("delete-uri");
            $("#delete-employee-form").attr("action", _uri).submit();
            // 取消默认行为
            return false;
        });
    </script>
    ```

### SpringBoot定制4xx、5xx错误页
#### SpringBoot默认的错误处理机制
* 浏览器error page
    ![](../images/SpringBoot/springboot_error1.png)

* 其他客户端(服务端Response的数据为json数据)
    ![](../images/SpringBoot/springboot_error4.png)


* SpringBoot怎么识别不同类型的客户点
    
    答：通过客户端发起的Requst Header中的Accept字段来识别
    * 浏览器
        ![](../images/SpringBoot/springboot_error2.png)
        
    * 其他客户端，如Postman
        ![](../images/SpringBoot/springboot_error4.png)

* 原理
    * [ErrorMvcAutoConfiguration](../readme/ErrorMvcAutoConfiguration.java)自动配置，给容器中添加了DefaultErrorAttributes组件
        ```java
        @Configuration(
            proxyBeanMethods = false
        )
        @ConditionalOnWebApplication(
            type = Type.SERVLET
        )
        @ConditionalOnClass({Servlet.class, DispatcherServlet.class})
        @AutoConfigureBefore({WebMvcAutoConfiguration.class})
        @EnableConfigurationProperties({ServerProperties.class, WebMvcProperties.class})
        public class ErrorMvcAutoConfiguration {
            // ...
            public DefaultErrorAttributes errorAttributes() {
                return new DefaultErrorAttributes();
            }
            // ...
        }
        ```
        
        [DefaultErrorAttributes.java](../readme/DefaultErrorAttributes.java)
        ```java
        public class DefaultErrorAttributes implements ErrorAttributes, HandlerExceptionResolver, Ordered {
            // ...
            // 获取错误信息，展示到 error page
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                Map<String, Object> errorAttributes = this.getErrorAttributes(webRequest, options.isIncluded(Include.STACK_TRACE));
                if (Boolean.TRUE.equals(this.includeException)) {
                    options = options.including(new Include[]{Include.EXCEPTION});
                }
        
                if (!options.isIncluded(Include.EXCEPTION)) {
                    errorAttributes.remove("exception");
                }
        
                if (!options.isIncluded(Include.STACK_TRACE)) {
                    errorAttributes.remove("trace");
                }
        
                if (!options.isIncluded(Include.MESSAGE) && errorAttributes.get("message") != null) {
                    errorAttributes.put("message", "");
                }
        
                if (!options.isIncluded(Include.BINDING_ERRORS)) {
                    errorAttributes.remove("errors");
                }
        
                return errorAttributes;
            }
            // ...
        }
        ```
    * [BasicErrorController](../readme/BasicErrorController.java)
        ```java
        @Controller
        @RequestMapping({"${server.error.path:${error.path:/error}}"})
        public class BasicErrorController extends AbstractErrorController {
            ...
            public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
                HttpStatus status = this.getStatus(request);
                Map<String, Object> model = Collections.unmodifiableMap(this.getErrorAttributes(request, this.getErrorAttributeOptions(request, MediaType.TEXT_HTML)));
                response.setStatus(status.value());
                // 去哪个页面作为错误页面；包含页面地址和页面内容
                ModelAndView modelAndView = this.resolveErrorView(request, response, status, model);
                return modelAndView != null ? modelAndView : new ModelAndView("error", model);
            }
        
            // 非浏览器类型的错误处理，响应json数据
            @RequestMapping
            public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
                HttpStatus status = this.getStatus(request);
                if (status == HttpStatus.NO_CONTENT) {
                    return new ResponseEntity(status);
                } else {
                    Map<String, Object> body = this.getErrorAttributes(request, this.getErrorAttributeOptions(request, MediaType.ALL));
                    return new ResponseEntity(body, status);
                }
            }
            ...
        }
        ```
    * [ErrorProperties](../readme/ErrorProperties.java)
    
        Ctrl + 点击 application.application.properties中的 server.error.include-exception=true，会单开 ErrorProperties类
        ```java
        public class ErrorProperties {
            @Value("${error.path:/error}")
            // 错误请求的路径
            private String path = "/error";
            ...
        }
        ```
        
    * [ErrorPageCustomizer 128行](../readme/ErrorMvcAutoConfiguration.java)
        ```java
            static class ErrorPageCustomizer implements ErrorPageRegistrar, Ordered {
                private final ServerProperties properties;
                private final DispatcherServletPath dispatcherServletPath;
        
                protected ErrorPageCustomizer(ServerProperties properties, DispatcherServletPath dispatcherServletPath) {
                    this.properties = properties;
                    this.dispatcherServletPath = dispatcherServletPath;
                }
        
                public void registerErrorPages(ErrorPageRegistry errorPageRegistry) {
                    ErrorPage errorPage = new ErrorPage(this.dispatcherServletPath.getRelativePath(this.properties.getError().getPath()));
                    errorPageRegistry.addErrorPages(new ErrorPage[]{errorPage});
                }
        
                public int getOrder() {
                    return 0;
                }
            }
        ```
    
    * [DefaultErrorViewResolver](../readme/DefaultErrorViewResolver.java)
    
        决定了响应哪个页面
        ```java
        public class DefaultErrorViewResolver implements ErrorViewResolver, Ordered {
            ...
            private ModelAndView resolve(String viewName, Map<String, Object> model) {
                String errorViewName = "error/" + viewName;
                TemplateAvailabilityProvider provider = this.templateAvailabilityProviders.getProvider(errorViewName, this.applicationContext);
                return provider != null ? new ModelAndView(errorViewName, model) : this.resolveResource(errorViewName, model);
            }
            
            static {
                Map<Series, String> views = new EnumMap(Series.class);
                views.put(Series.CLIENT_ERROR, "4xx");
                views.put(Series.SERVER_ERROR, "5xx");
                SERIES_VIEWS = Collections.unmodifiableMap(views);
            }
            ...
        }
        ```
    * 工作步骤
        1. 一旦系统出现4xx、5xx错误，ErrorPageCustomizer生效，定制错误的响应规则
        2. 把请求转发到/error
        3. 由BasicErrorController处理

#### 定制错误页面
1. 编写错误页面

    错误页面资源路径查找顺序
    1. 有模板引擎的情况
        ```text
        ./resources/templates/error/状态码.html
        ```
        可以命名为4xx.html, 5xx.html, 500.html. 优先精确匹配
        
        示例
        * [404.html](../SpringBoot/crud-resful/src/main/resources/templates/error/404.html)
            ![](../images/SpringBoot/springboot_4xx_4.png)
        * [4xx.html](../SpringBoot/crud-resful/src/main/resources/templates/error/4xx.html)
        * [5xx.html](../SpringBoot/crud-resful/src/main/resources/templates/error/5xx.html)
            ![](../images/SpringBoot/springboot_5xx_3.png)
        页面可获取的error信息属性**getErrorAttributes**
        ```text
        timestamp
        path
        status
        error
        message
        requestId
        exception
        trace
        errors
        ```
    2. 没有模板引擎的情况，在静态资源目录下查找 ./resources/static/error/状态码.html
    
    3. 以上两种情况都没有错误页面，则响应SpringBoot的默认错误页面

2. 定制错误消息

    自定义异常处理器[MyExceptionHandler](../SpringBoot/crud-resful/src/main/java/com/java/crudresful/controller/MyExceptionHandler.java)
    ```java
    @ControllerAdvice
    public class MyExceptionHandler {
        /**
         * 方法2
         *  自适应浏览器或其他客户端
         * @param e
         * @return
         */
        @ExceptionHandler
        public String handlerException(Exception e, HttpServletRequest request) {
            // 指定状态码
            request.setAttribute("javax.servlet.error.status_code", 500);
            Map<String, Object> map = new HashMap<>();
            map.put("msg", e.getMessage());
            map.put("code", "601 --user not exist");
            request.setAttribute("ext", map);
            // 转发到/error，让Spring Boot默认的ErrorView处理器处理，它能自适应浏览器或其他客户端
            return "forward:/error";
        }
    }
    ```

3. 将定制的错误消息传递给前端页面
    * [MyErrorAttributes组件](../SpringBoot/crud-resful/src/main/java/com/java/crudresful/component/MyErrorAttributes.java)

        自定义ErrorAttributes
        ```java
        package com.java.crudresful.component;
        
        @Component
        public class MyErrorAttributes extends DefaultErrorAttributes {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
                // 异常处理携带的数据，自适应携带 handlerException 传过来的数据
                Map<String, Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", 0);
                errorAttributes.put("ext", ext);
                return errorAttributes;
            }
        }
        ```

    * 错误页面  
        [5xx.html](../SpringBoot/crud-resful/src/main/resources/templates/error/5xx.html)
    
    * 效果  
        ![](../images/SpringBoot/springboot_5xx_3.png)
        
        ![](../images/SpringBoot/springboot_5xx_4.png)

### 配置嵌入式Servlet容器
嵌入式Servlet容器，应用打包成可执行的jar包，不需要额外安装tomcat servlet容器

* 优点
    * 简单、便携
* 缺点
    * 默认不支持jsp、优化定制复杂
        ```text
        定制可使用定制器ServerProperties(application.properties)、自定义EmbeddedServletContainerCustomizer、编写嵌入式Servlet容器的创建工厂(EmbeddedServletContainerFactory)
        ```

#### 定制和修改Servlet容器的相关配置
* 方式1-- 配置application.properties
    ```properties
    server.port=8082
    server.tomcat.uri-encoding=UTF-8
    ```
* 方式2--编写一个[WebServerFactoryCustomizer](../SpringBoot/tomcat-servlet/src/main/java/com/java/tomcatservlet/config/MyServerConfig.java)
    ```java
    @Configuration
    public class MyServerConfig {
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
    ```
#### 注册Servlet三大组件(Servlet,Filter,Listener)
* 编写Servlet  
    [MyServlet](../SpringBoot/tomcat-servlet/src/main/java/com/java/tomcatservlet/servlet/MyServlet.java)

* 编写Filter  
    [MyFilter](../SpringBoot/tomcat-servlet/src/main/java/com/java/tomcatservlet/filter/MyFilter.java)

* 编写Listener  
    [MyListener](../SpringBoot/tomcat-servlet/src/main/java/com/java/tomcatservlet/listener/MyListener.java)
* 编写SpringBoot配置注册3大组件  
    [MyServerConfig](../SpringBoot/tomcat-servlet/src/main/java/com/java/tomcatservlet/config/MyServerConfig.java)
    ```java
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
    }
    ```

#### 自动配置SpringMVC时注册的DispatcherServlet
```java
@AutoConfigureOrder(-2147483648)
@Configuration(
    proxyBeanMethods = false
)
@ConditionalOnWebApplication(
    type = Type.SERVLET
)
@ConditionalOnClass({DispatcherServlet.class})
@AutoConfigureAfter({ServletWebServerFactoryAutoConfiguration.class})
public class DispatcherServletAutoConfiguration {
    @Configuration(
        proxyBeanMethods = false
    )
    @Conditional({DispatcherServletAutoConfiguration.DispatcherServletRegistrationCondition.class})
    @ConditionalOnClass({ServletRegistration.class})
    @EnableConfigurationProperties({WebMvcProperties.class})
    @Import({DispatcherServletAutoConfiguration.DispatcherServletConfiguration.class})
    protected static class DispatcherServletRegistrationConfiguration {
        protected DispatcherServletRegistrationConfiguration() {
        }

        @Bean(
            name = {"dispatcherServletRegistration"}
        )
        @ConditionalOnBean(
            value = {DispatcherServlet.class},
            name = {"dispatcherServlet"}
        )
        public DispatcherServletRegistrationBean dispatcherServletRegistration(DispatcherServlet dispatcherServlet, WebMvcProperties webMvcProperties, ObjectProvider<MultipartConfigElement> multipartConfig) {
            // webMvcProperties.getServlet().getPath()
            // 默认拦截的路径为 "/"，即所有路径，包括静态资源，但不包括.jsp
            // 可以通过设置 server.servletPath 参数来配置默认拦截的路径
            DispatcherServletRegistrationBean registration = new DispatcherServletRegistrationBean(dispatcherServlet, webMvcProperties.getServlet().getPath());
            registration.setName("dispatcherServlet");
            registration.setLoadOnStartup(webMvcProperties.getServlet().getLoadOnStartup());
            multipartConfig.ifAvailable(registration::setMultipartConfig);
            return registration;
        }
    }
}

```

#### 使用Jetty,Undertow嵌入式Servlet容器
Spring Boot默认使用的是嵌入式的Tomcat作为Servlet容器

* 使用Jetty
    
    适合场景：长连接（如IM聊天）
    
    在这方面也有另外的框架：netty，一个客户端-服务端NIO框架，开发简单快速
    
    去除默认的tomcat
    ![](../images/SpringBoot/jetty-undertow.png)
    
    [pom.xml](../SpringBoot/jetty-undertow/pom.xml)
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
    
* Undertow
    
    适用场景：高IO密集型服务（如文件服务器），缺点：不支持jsp
    
    [pom.xml](../SpringBoot/jetty-undertow/pom.xml)
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
                <artifactId>spring-boot-starter-undertow</artifactId>
                <groupId>org.springframework.boot</groupId>
            </dependency>
        </dependencies>
    ```
    
    
### 使用外置的Servlet容器(war包)
外置Servlet容器：安装tomcat、应用打包成war包

**创建项目步骤**
1. 创建war工程项目，可利用idea  
   ![](../images/SpringBoot/war1.png)
   
   Packaging选择War  
   ![](../images/SpringBoot/war2.png)
   
   ![](../images/SpringBoot/war3.png)
   
   ![](../images/SpringBoot/war4.png)
   
   设置Template/Tomcat Server/Local  
   ![](../images/SpringBoot/war5.png)
   
   ![](../images/SpringBoot/war6.png)
   ![](../images/SpringBoot/war7.png)
   ![](../images/SpringBoot/war8.png)
   ![](../images/SpringBoot/war9.png)
   ![](../images/SpringBoot/war10.png)
   ![](../images/SpringBoot/war11.png)
   
   添加Tomcat Server实例  
   ![](../images/SpringBoot/war12.png)
   
   启动服务  
   ![](../images/SpringBoot/war13.png)

2. 将嵌入式的Tomcat指定为provided

    [pom.xml](../SpringBoot/war/pom.xml)
    ```xml
    <project>
       <packaging>war</packaging>
       <dependencies>
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-tomcat</artifactId>
               <scope>provided</scope>
           </dependency>
       </dependencies>
    </project>
    ```

3. 编写一个SpringBootServletInitializer的子类，重写configure方法  
    [ServletInitializer.java](../SpringBoot/war/src/main/java/com/java/war/ServletInitializer.java)
    ```java
    package com.java.war;
    
    import org.springframework.boot.builder.SpringApplicationBuilder;
    import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
    
    public class ServletInitializer extends SpringBootServletInitializer {
    
        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            // WarApplication为SpringBoot main()方法类
            return application.sources(WarApplication.class);
        }
    
    }
    ```
4. 启动Tomcat服务
    
    启动流程  
    1. 启动Servlet容器
    2. 启动SpringBoot应用

## Docker
[docker](https://github.com/cucker0/docker)

## SpringBoot与数据访问
Spring boot访问数据库

### JDBC

* 创建Spring Initializr项目[springboot-data](../SpringBoot/springboot-data)

    选择web、mysql模块

* [pom.xml](../SpringBoot/springboot-data/pom.xml)添加模块
    ```xml
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
    
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-jdbc</artifactId>
                <version>2.4.1</version>
            </dependency>
    
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <scope>runtime</scope>
            </dependency>
        </dependencies>
    ```

* application配置文件中配置datasource数据库连接信息
    
    [application.yml](../SpringBoot/springboot-data/src/main/resources/application.yml)
    ```yaml
    spring:
      datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://10.100.240.209:13306/mydata?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false&allowMultiQueries=true
        username: root
        password: py123456
        #type: 指定自定义的数据源类型
    ```

    默认使用的数据库连接池 org.apache.tomcat.jdbc.pool.DataSource
    
    自动配置原理：参考 org.springframework.boot.autoconfigure.jdbc 下的[DataSourceConfiguration](../readme/DataSourceConfiguration.java)类  
    ![](../images/SpringBoot/springboot-data01.png)

    org.springframework.boot.autoconfigure.jdbc下的[DataSourceProperties](../readme/DataSourceProperties.java)指定了可配置哪些属性

* 自动创建表结构(schema)、初始化表数据(data)

    org.springframework.boot.autoconfigure.jdbc下的DataSourceInitializer可创建表schema和初始化表数据(插入数据)
    
    * 默认的schema、data sql文件位置
        
        * 默认的表[schema sql](../SpringBoot/springboot-data/src/main/resources/schema.sql)文件位置：`classpath*:schema.sql`, `classpath*:schema-*.sql`  
            `*`表示platform，即OS平台，下同
        * 默认的表[data sql](../SpringBoot/springboot-data/src/main/resources/data.sql)文件位置：`classpath*:data.sql`, `classpath*:data-*.sql`
    
        ![](../images/SpringBoot/springboot-data02.png)
        
    * 指定schema、data sql文件位置
        ```yaml
        spring:
          datasource:
            # 指定schema sql文件位置
            schema:
              - classpath:sql/t_employee.sql
            # 指定data sql文件位置
            data:
              - classpath:sql/data4t_employee.sql
        ```
        
* 数据库操作
    
    因为自动配置了JdbcTemplate，使用JdbcTemplate操作数据
    
    创建[controller](../SpringBoot/springboot-data/src/main/java/com/java/springbootdata/controller/DepartmentController.java)
    
* 启动spring boot应用并测试

    浏览器打开http://127.0.0.1:8080去测试

### SpringBoot整合Druid数据源
#### 配置版
通过application配置文件来配置Druid所有的属性(stat-view-servlet管理后台、web-stat-filter、stat,wall,commons-log filter等)

工程 [springboot-druid](../SpringBoot/springboot-druid)

* 创建Spring boot项目，选择web、mysql模块
* [pom.xml](../SpringBoot/springboot-druid/pom.xml)添加jdbc、druid模块
    ```xml
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-data-jdbc</artifactId>
            </dependency>
            <!-- https://mvnrepository.com/artifact/com.alibaba/druid-spring-boot-starter -->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid-spring-boot-starter</artifactId>
                <version>1.2.6</version>
            </dependency>
        </dependencies>
    ```
* [application.yml](../SpringBoot/springboot-druid/src/main/resources/application.yml)添加datasource、Druid相关配置

* 编写controller，使用JdbcTemplate操作数据库

    [DepartmentController](../SpringBoot/springboot-druid/src/main/java/com/java/springbootdruid/controller/DepartmentController.java)

* 启动spring boot应用并测试

    浏览器打开http://127.0.0.1:8080去测试

#### 配置类版
通过编写配置类来配置Druid的属性(stat-view-servlet管理后台、web-stat-filter、stat,wall,commons-log filter等)

工程 [springboot-druid2](../SpringBoot/springboot-druid2)

* 创建Spring boot项目，选择web、mysql模块
* [pom.xml](../SpringBoot/springboot-druid2/pom.xml)添加jdbc、druid-spring-boot-starter模块
    ```xml
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-data-jdbc</artifactId>
            </dependency>
            <!-- https://mvnrepository.com/artifact/com.alibaba/druid-spring-boot-starter -->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid-spring-boot-starter</artifactId>
                <version>1.2.6</version>
            </dependency>
        </dependencies>
    ```
* [application.yml](../SpringBoot/springboot-druid2/src/main/resources/application.yml)添加datasource、Druid基本配置

* 编写druid配置类，配置druid管理后台、filter等
    [DruidConfig](../SpringBoot/springboot-druid2/src/main/java/com/java/springbootdruid2/config/DruidConfig.java)

* 编写controller，使用JdbcTemplate操作数据库

    [DepartmentController](../SpringBoot/springboot-druid2/src/main/java/com/java/springbootdruid/controller/DepartmentController.java)

* 启动spring boot应用并测试

    浏览器打开http://127.0.0.1:8080去测试

### SpringBoot整合MyBatis
SpringBoot整合MyBatisPlus与此相同

#### mapper注解版
1. 参考[SpringBoot整合Druid数据源](readme/SpringBoot.md#SpringBoot整合Druid数据源)创建一个Spring Boot的工程
    [springboot-data-mybatis](../SpringBoot/springboot-data-mybatis)
    
2. pom.xml添加模块
    * 整合MyBatis [pom.xml](../SpringBoot/springboot-data-mybatis/pom.xml)再额外添加mybatis-spring-boot-starter模块
        ```xml
                <!-- https://mvnrepository.com/artifact/org.mybatis.spring.boot/mybatis-spring-boot-starter -->
                <dependency>
                    <groupId>org.mybatis.spring.boot</groupId>
                    <artifactId>mybatis-spring-boot-starter</artifactId>
                    <version>2.1.4</version>
                </dependency>
        ```
        也可以在创建SpringBoot工程时，选择MyBatis模块，省去手动添加maven依赖模块
        ![](../images/SpringBoot/data-MyBatis.png)
    * 整合MyBatisPlus pom.xml再额外添加mybatis-plus-boot-starter模块
        ```xml
                <!-- https://github.com/baomidou/mybatis-plus#getting-started -->
                <dependency>
                    <groupId>com.baomidou</groupId>
                    <artifactId>mybatis-plus-boot-starter</artifactId>
                    <version>Latest Version</version>
                </dependency>       
        ```
3. 创建数据库表  
    [springboot-data.sql](../SpringBoot/sql/springboot-data.sql)
4. 创建JavaBean
5. 创建mapper接口

    操作数据表的sql，使用注解写在mapper接口的方法上
6. 编写MyBatis配置类，配置MyBatist
    
    [MyBatisConfig示例](../SpringBoot/springboot-data-mybatis/src/main/java/com/java/springbootdatamybatis/config/MyBatisConfig.java)
    通过org.apache.ibatis.session.Configuration的set方法设置
7. 编写controller
8. **指定Mapper接口扫描的位置**
    
    如不指定mapper接口位置，启动应用会出现['xxMapper' that could not be found](../SpringBoot/springboot-data-mybatis/README.md)
    
    在main()方法主程序类上注解@MapperScan(value = {"mapper 1包名", "mapper 2包名"})
    
    [SpringbootDataMybatisApplication示例](../SpringBoot/springboot-data-mybatis/src/main/java/com/java/springbootdatamybatis/SpringbootDataMybatisApplication.java)
    ```java
    @MapperScan(value = {"com.java.springbootdatamybatis.mapper"})
    @SpringBootApplication
    public class SpringbootDataMybatisApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(SpringbootDataMybatisApplication.class, args);
        }
    
    }
    ```
9. 启动应用进行测试
    * druid管理后台
        ![](../images/SpringBoot/druid1.png)
        
        ![](../images/SpringBoot/druid2.png)

#### mapper xml文件配置版
1. 参考[SpringBoot整合Druid数据源](readme/SpringBoot.md#SpringBoot整合Druid数据源)创建一个Spring Boot的工程
    [springboot-data-mybatis2](../SpringBoot/springboot-data-mybatis2)
    
2. pom.xml添加模块，同[mapper注解版.2](readme/SpringBoot.md#mapper注解版)
3. 创建数据库表，同[mapper注解版.3](readme/SpringBoot.md#mapper注解版)
4. 创建JavaBean同[mapper注解版.4](readme/SpringBoot.md#mapper注解版)
5. 创建mapper接口
    * mapper接口只写接口方法
6. 指定mybatis-config.xml位置、mapper文件位置
    * resources/mybatis/mapper目录下编写mapper.xml文件，参考[MyBatis映射文件](../readme/MyBatis.md#MyBatis映射文件)
    * 编写resources/mybatis/mybatis-config.xml配置文件，参考[MyBatis全局配置文件](../readme/MyBatis.md#MyBatis全局配置文件)
    * [application.yml](../SpringBoot/springboot-data-mybatis2/src/main/resources/application.yml)指定mybatis-config.xml位置、mapper文件位置
        ```yaml
        mybatis:
          config-location: classpath:mybatis/mybatis-config.xml
          mapper-locations: classpath:mybatis/mapper/*.xml
        ```
7. 编写controller，同[mapper注解版.7](readme/SpringBoot.md#mapper注解版)
8. 指定Mapper接口扫描的位置，同[mapper注解版.8](readme/SpringBoot.md#mapper注解版)
    
9. 启动应用进行测试

### SpringBoot整合SpringData JPA
JPA: Java Persistence API（java 持久化API），JPA即使ORM(Object Relational Mapping)

SpringData架构图
![](../images/SpringBoot/springdata架构.png)


**整合步骤**

[创建数据库sql](../SpringBoot/sql/springboot-data-jpa.sql)

1. 参考[SpringBoot整合Druid数据源](readme/SpringBoot.md#SpringBoot整合Druid数据源)

    创建一个Spring Boot的工程  
    [springboot-data-jpa](../SpringBoot/springboot-data-jpa)

    * 目录结构  
        ![](../images/SpringBoot/springboot-data-jpa01.png)

2. 创建实体类(Entity)与表字段进行映射

    要求配置好映射关系，
    
    * [User](../SpringBoot/springboot-data-jpa/src/main/java/com/java/springboot/entity/User.java)
3. 编写Dao接口来操作实体类对应的数据表(Repository)
    * [UserRepository](../SpringBoot/springboot-data-jpa/src/main/java/com/java/springboot/repository/UserRepository.java)

4. 配置JPA Properties  
    [application.yml](../SpringBoot/springboot-data-jpa/src/main/resources/application.yml)
    ```yaml
    spring:
      jpa:
        hibernate:
          # 自动更新或创建数据表结构
          ddl-auto: update
        # 控制台显示SQL
        show-sql: true
    ```
5. 编写Controller
6. 测试
    * [创建用户](../SpringBoot/springboot-data-jpa/src/test/java/com/java/springboot/SpringbootDataJpaApplicationTests.java)
    * 启动应用测试 

    
## SpringBoot启动配置原理
几个重要的事件回调机制

* 配置在 META-INF/spring.factories
    * ApplicationContextInitializer
    * SpringApplicationRunListener


* 只需要放在ioc容器中
    * ApplicationRunner
    * CommandLineRunner

### SpringBoot启动流程
1. 创建SpringApplication对象
    ```java
    initialize(sources);
    private void initialize(Object[] sources) {
        //保存主配置类
        if (sources != null && sources.length > 0) {
            this.sources.addAll(Arrays.asList(sources));
        }
        //判断当前是否一个web应用
        this.webEnvironment = deduceWebEnvironment();
        //从类路径下找到META-INF/spring.factories配置的所有ApplicationContextInitializer；然后保存起来
        setInitializers((Collection) getSpringFactoriesInstances(
            ApplicationContextInitializer.class));
        //从类路径下找到ETA-INF/spring.factories配置的所有ApplicationListener
        setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
        //从多个配置类中找到有main方法的主配置类
        this.mainApplicationClass = deduceMainApplicationClass();
    }
    ```
    ![](../images/SpringBoot/springboot-start01.png)
    
    ![](../images/SpringBoot/springboot-start02.png)

2. 运行run方法
    ```java
    public ConfigurableApplicationContext run(String... args) {
       StopWatch stopWatch = new StopWatch();
       stopWatch.start();
       ConfigurableApplicationContext context = null;
       FailureAnalyzers analyzers = null;
       configureHeadlessProperty();
        
       // 获取SpringApplicationRunListeners；从类路径下META-INF/spring.factories
       SpringApplicationRunListeners listeners = getRunListeners(args);
        // 回调所有的获取SpringApplicationRunListener.starting()方法
       listeners.starting();
       try {
           // 封装命令行参数
          ApplicationArguments applicationArguments = new DefaultApplicationArguments(
                args);
          // 准备环境
          ConfigurableEnvironment environment = prepareEnvironment(listeners,
                applicationArguments);
           		// 创建环境完成后回调SpringApplicationRunListener.environmentPrepared()；表示环境准备完成
           
          Banner printedBanner = printBanner(environment);
           
           // 创建ApplicationContext；决定创建web的ioc还是普通的ioc
          context = createApplicationContext();
           
          analyzers = new FailureAnalyzers(context);
           // 准备上下文环境;将environment保存到ioc中；而且applyInitializers()；
           // applyInitializers()：回调之前保存的所有的ApplicationContextInitializer的initialize方法
           // 回调所有的SpringApplicationRunListener的contextPrepared()；
           //
          prepareContext(context, environment, listeners, applicationArguments,
                printedBanner);
           //prepareContext运行完成以后回调所有的SpringApplicationRunListener的contextLoaded（）；
           
           // 刷新容器；ioc容器初始化（如果是web应用还会创建嵌入式的Tomcat）；Spring注解版
           // 扫描，创建，加载所有组件的地方；（配置类，组件，自动配置）
          refreshContext(context);
           // 从ioc容器中获取所有的ApplicationRunner和CommandLineRunner进行回调
           // ApplicationRunner先回调，CommandLineRunner再回调
          afterRefresh(context, applicationArguments);
           // 所有的SpringApplicationRunListener回调finished方法
          listeners.finished(context, null);
          stopWatch.stop();
          if (this.logStartupInfo) {
             new StartupInfoLogger(this.mainApplicationClass)
                   .logStarted(getApplicationLog(), stopWatch);
          }
           // 整个SpringBoot应用启动完成以后返回启动的ioc容器；
          return context;
       }
       catch (Throwable ex) {
          handleRunFailure(context, listeners, analyzers, ex);
          throw new IllegalStateException(ex);
       }
    }
    ```

3. 事件监听机制

    * 配置在 META-INF/spring.factories
        ```text
        org.springframework.context.ApplicationContextInitializer=\
        com.atguigu.springboot.listener.HelloApplicationContextInitializer
        
        org.springframework.boot.SpringApplicationRunListener=\
        com.atguigu.springboot.listener.HelloSpringApplicationRunListener
        ```
        * ApplicationContextInitializer
            ```java
            public class HelloApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
                @Override
                public void initialize(ConfigurableApplicationContext applicationContext) {
                    System.out.println("ApplicationContextInitializer...initialize..."+applicationContext);
                }
            }
            ```
        * SpringApplicationRunListener
            ```java
            public class HelloSpringApplicationRunListener implements SpringApplicationRunListener {
            
                //必须有的构造器
                public HelloSpringApplicationRunListener(SpringApplication application, String[] args){
            
                }
            
                @Override
                public void starting() {
                    System.out.println("SpringApplicationRunListener...starting...");
                }
            
                @Override
                public void environmentPrepared(ConfigurableEnvironment environment) {
                    Object o = environment.getSystemProperties().get("os.name");
                    System.out.println("SpringApplicationRunListener...environmentPrepared.."+o);
                }
            
                @Override
                public void contextPrepared(ConfigurableApplicationContext context) {
                    System.out.println("SpringApplicationRunListener...contextPrepared...");
                }
            
                @Override
                public void contextLoaded(ConfigurableApplicationContext context) {
                    System.out.println("SpringApplicationRunListener...contextLoaded...");
                }
            
                @Override
                public void finished(ConfigurableApplicationContext context, Throwable exception) {
                    System.out.println("SpringApplicationRunListener...finished...");
                }
            }
            ```
    * 只需要放在ioc容器中
        * ApplicationRunner
            ```java
            @Component
            public class HelloApplicationRunner implements ApplicationRunner {
                @Override
                public void run(ApplicationArguments args) throws Exception {
                    System.out.println("ApplicationRunner...run....");
                }
            }
            ```
        * CommandLineRunner 
            ```java
            @Component
            public class HelloCommandLineRunner implements CommandLineRunner {
                @Override
                public void run(String... args) throws Exception {
                    System.out.println("CommandLineRunner...run..."+ Arrays.asList(args));
                }
            }
            ```
## SpringBoot自定义starts
创建自己的spring boot starter，方便其他工程项目直接引用，并自动完成配置

### 创建步骤
1. 创建Empty工程  
    ![](../images/SpringBoot/starter1_1.png)
    
    ![](../images/SpringBoot/starter1_2.png)
    
2. 添加Module
    * maven模块(starter模块)  
        ![](../images/SpringBoot/starter2_1.png)
        
        ![](../images/SpringBoot/starter2_2.png)
        
        GroupId：com.java  
        ArtifactId：talk-spring-boot-starter (模块命名规则：xx-spring-boot-starter)   
        
        ![](../images/SpringBoot/starter2_3.png)
        
        Module name：talk-spring-boot-starter  
        ![](../images/SpringBoot/starter2_4.png)

    * Spring Initializr模块(autoconfigure自动配置模块)  
        ![](../images/SpringBoot/starter3_1.png)
        
        GroupId：com.java    
        ArtifactId：talk-spring-boot-starter-autoconfigure (模块命名规则：xx-spring-boot-starter-autoconfigure)    
        ![](../images/SpringBoot/starter3_2.png)
        
        不选择任何模块  
        ![](../images/SpringBoot/starter3_3.png)
        
        ![](../images/SpringBoot/starter3_4.png)
        
        ![](../images/SpringBoot/starter3_5.png)
        
        选择Language level：为12，jdk12  
        ![](../images/SpringBoot/starter4_1.png)  
        ![](../images/SpringBoot/starter4_2.png)

3. starter引入自动配置模块
    [pom.xml（talk-spring-boot-starter）](../SpringBoot/talk-starter/talk-spring-boot-starter/pom.xml)    
    ```xml
        <dependencies>
            <!-- 引入自动配置模块 -->
            <dependency>
                <groupId>com.java</groupId>
                <artifactId>talk-spring-boot-starter-autoconfigure</artifactId>
                <version>0.0.1-SNAPSHOT</version>
            </dependency>
        </dependencies>
    ```

4. 删除不必要的文件和文件夹  
    ![](../images/SpringBoot/starter5_1.png)

5. autoconfigure模块添加必要的maven依赖，删除不必要的maven依赖

    [pom.xml（talk-spring-boot-starter-autoconfigure）](../SpringBoot/talk-starter/talk-spring-boot-starter-autoconfigure/pom.xml)    
    ```xml
        <dependencies>
            <!-- 引入spring-boot-starter -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter</artifactId>
            </dependency>
            <dependency>
                <!-- 编译时自动生成配置元数据 classpath:META-INF/spring-configuration-metadata.json -->
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-configuration-processor</artifactId>
                <optional>true</optional>
            </dependency>
        </dependencies>
    
    ```
    * 参考autoconfigure Module
        * [Creating Your Own Auto-configuration 2.5.0](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.developing-auto-configuration)
        
        * [Creating Your Own Auto-configuration 2.4.6](https://docs.spring.io/spring-boot/docs/2.4.6/reference/html/spring-boot-features.html#boot-features-developing-auto-configuration)
         
        * [boot-features-custom-starter](https://docs.spring.io/spring-boot/docs/2.1.15.RELEASE/reference/html/boot-features-developing-auto-configuration.html#boot-features-custom-starter)
             
        Spring Boot uses an annotation processor to collect the conditions on auto-configurations in a metadata file 
        (`META-INF/spring-autoconfigure-metadata.properties`). 
        If that file is present, it is used to eagerly filter auto-configurations that do not match, which will improve startup time. 
        It is recommended to add the following dependency in a module that contains auto-configurations:
        ```xml
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-autoconfigure-processor</artifactId>
                    <optional>true</optional>
                </dependency>
        ```
        
6. 编写Properties、Service、AutoConfigure等类
    * [TalkProperties](../SpringBoot/talk-starter/talk-spring-boot-starter-autoconfigure/src/main/java/com/java/properties/TalkProperties.java)
    * [TalkService](../SpringBoot/talk-starter/talk-spring-boot-starter-autoconfigure/src/main/java/com/java/service/TalkService.java)
    * [TalkAutoConfigure](../SpringBoot/talk-starter/talk-spring-boot-starter-autoconfigure/src/main/java/com/java/TalkAutoConfigure.java)

7. resources下添加META-INF/spring.factories文件，注册自动配置类
    
    [spring.factories](../SpringBoot/talk-starter/talk-spring-boot-starter-autoconfigure/src/main/resources/META-INF/spring.factories)  
    ![](../images/SpringBoot/starter6_1.png)
    
8. #### IDEA使用自定义的stater，在编写application配置文件时无法自动提示属性的解决方法
    
    在编写application.properies(.yml)配置文件不能自动提示属性，这让人不爽也。
    
    **解决方法：**  
    生成 META-INF/additional-spring-configuration-metadata.json 文件
    
    [additional-spring-configuration-metadata.json 示例](../SpringBoot/talk-starter/talk-spring-boot-starter-autoconfigure/src/main/resources/META-INF/additional-spring-configuration-metadata.json)
    
    **自动生成additional-spring-configuration-metadata.json方法**
    1. IDEA设置中开启annotation processing  
        ![](../images/SpringBoot/starter6_2.png)
    2. 编译"autoconfigure自动配置模块"
    
        编译后，自动生成target/classes/META-INF/spring-configuration-metadata.json  
        ![](../images/SpringBoot/starter6_3.png)
    3. 复制上一步生成的 spring-configuration-metadata.json  
        到autoconfigure自动配置模块的 resources/META-INF目录下，  
        重名为 additional-spring-configuration-metadata.json  
        ![](../images/SpringBoot/starter6_4.png)
        
        此时jar包中就包含了additional-spring-configuration-metadata.json  
        ![](../images/SpringBoot/starter6_5.png)
        
9. 编译、安装"autoconfigure自动配置模块"
    
    将该模块安装到IDEA继承的maven仓库中  
    ![](../images/SpringBoot/starter6_6.png)
    
    此时  
    ![](../images/SpringBoot/starter6_8.png)
    
10. 编译、安装"starter模块"  
    ![](../images/SpringBoot/starter6_7.png)
        
### 测试自定义的starter
1. 创建SpringBoot工程
2. [pom.xml引入自定义的starter模块](../SpringBoot/my-starter-test/pom.xml)
    ```xml
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
    
            <!-- 引入自定义的starter -->
            <dependency>
                <groupId>com.java</groupId>
                <artifactId>talk-spring-boot-starter</artifactId>
                <version>1.0-SNAPSHOT</version>
            </dependency>
        </dependencies>
    ```
3. [application.yml 配置属性](../SpringBoot/my-starter-test/src/main/resources/application.yml)

4. 编写测试的[Controller](../SpringBoot/my-starter-test/src/main/java/com/java/mystartertest/controller/DemoController.java)
5. 运行springboot应用，测试

    在浏览器上访问http://127.0.0.1:8080
    ![](../images/SpringBoot/my-starter-test03.png)

## SpringBoot缓存

## SpringBoot消息

## SpringBoot检索

## SpringBoot任务

## SpringBoot安全

## SpringBoot分布式

## SpringBoot开发热部署

## SpringBoot监控原理

## SpringBoot整合Mybatis
[参考](https://blog.csdn.net/iku5200/article/details/82856621)

### 新建一个Spring Initializr项目
![](../images/SpringBoot/springboot_mybatis1.png)

![](../images/SpringBoot/springboot_mybatis2.png)

![](../images/SpringBoot/springboot_mybatis3.png)

![](../images/SpringBoot/springboot_mybatis4.png)

### application配置
* application-dev.yml
    ```yaml
    server:
      port: 8080
     
    spring:
      datasource:
        username: root
        password: 1234
        url: jdbc:mysql://localhost:3306/springboot?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC
        driver-class-name: com.mysql.jdbc.Driver
     
    mybatis:
      mapper-locations: classpath:mapping/*Mapper.xml
      type-aliases-package: com.example.entity
     
    #showSql
    logging:
      level:
        com:
          example:
            mapper : debug

    ```

### sql
```mysql
CREATE TABLE `user` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `userName` varchar(32) NOT NULL,
  `passWord` varchar(50) NOT NULL,
  `realName` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

```

### 项目结构
![](../images/SpringBoot/springboot_mybatis5.png)


### 代码
* User.java
    ```java
    package com.example.entity;
     
    /**
     * @Author:wjup
     * @Date: 2018/9/26 0026
     * @Time: 14:39
     */
    public class User {
        private Integer id;
        private String userName;
        private String passWord;
        private String realName;
     
        public Integer getId() {
            return id;
        }
     
        public void setId(Integer id) {
            this.id = id;
        }
     
        public String getUserName() {
            return userName;
        }
     
        public void setUserName(String userName) {
            this.userName = userName;
        }
     
        public String getPassWord() {
            return passWord;
        }
     
        public void setPassWord(String passWord) {
            this.passWord = passWord;
        }
     
        public String getRealName() {
            return realName;
        }
     
        public void setRealName(String realName) {
            this.realName = realName;
        }
     
        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", userName='" + userName + '\'' +
                    ", passWord='" + passWord + '\'' +
                    ", realName='" + realName + '\'' +
                    '}';
        }
    }
    ```

* UserMapper.java
    ```java
    package com.example.mapper;
     
    import com.example.entity.User;
    import org.apache.ibatis.annotations.Select;
    import org.springframework.stereotype.Repository;
     
    /**
     * @Author:wjup
     * @Date: 2018/9/26 0026
     * @Time: 15:20
     */
    @Repository
    public interface UserMapper {
     
        User Sel(int id);
    }
    ```

* UserMapping.xml
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace="com.example.mapper.UserMapper">
     
        <resultMap id="BaseResultMap" type="com.example.entity.User">
            <result column="id" jdbcType="INTEGER" property="id" />
            <result column="userName" jdbcType="VARCHAR" property="userName" />
            <result column="passWord" jdbcType="VARCHAR" property="passWord" />
            <result column="realName" jdbcType="VARCHAR" property="realName" />
        </resultMap>
     
        <select id="Sel" resultType="com.example.entity.User">
            select * from user where id = #{id}
        </select>
     
    </mapper>
    ```

* UserService.java
    ```java
    package com.example.service;
     
    import com.example.entity.User;
    import com.example.mapper.UserMapper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
     
    /**
     * @Author:wjup
     * @Date: 2018/9/26 0026
     * @Time: 15:23
     */
    @Service
    public class UserService {
        @Autowired
        UserMapper userMapper;
        public User Sel(int id){
            return userMapper.Sel(id);
        }
    }
    ```

* UserController.java
    ```java
    package com.example.controller;
     
    import com.example.entity.User;
    import com.example.service.UserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
     
    /**
     * @Author:wjup
     * @Date: 2018/9/26 0026
     * @Time: 14:42
     */
     
    @RestController
    @RequestMapping("/testBoot")
    public class UserController {
     
        @Autowired
        private UserService userService;
     
        @RequestMapping("getUser/{id}")
        public String GetUser(@PathVariable int id){
            return userService.Sel(id).toString();
        }
    }
    ```
    
### 在启动类里添加需要扫描的mapper路径
```java
package com.example;
 
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
@MapperScan("com.example.mapper") //扫描的mapper
@SpringBootApplication
public class DemoApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
```

## SpringBoot整合MyBatis-Plus
[参考](https://blog.csdn.net/yongbutingxide/article/details/106389924)

### 手动版
#### 添加依赖
pom.xml
```xml
    <!-- https://mvnrepository.com/artifact/com.baomidou/mybatis-plus-boot-starter -->
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.4.3</version>
    </dependency>
```

#### sql
```mysql
# create table
CREATE TABLE stock_kv (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `key` VARCHAR(32) NOT NULL COMMENT '对应redis key',
    `value` TEXT COMMENT '对应redis value（json字符串）',
    `expire` DATETIME DEFAULT '2999-12-31' COMMENT '过期时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='股票key-value表';

# 给stock_kv的key列创建唯一索引
ALTER TABLE stock_kv ADD UNIQUE INDEX idx_stock_kv_key (`key`);
```

#### application.yml
```yaml
server:
  port: 8081
  servlet:
    context-path: /

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/demo?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true
    username: root
    password: lyja
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: GMT+8
    serialization:
      write-dates-as-timestamps: false

mybatis-plus:
  mapper-locations: classpath:mappers/*.xml  #注意：一定要对应mapper映射xml文件的所在路径
  config-location: classpath:mybatis-config.xml
```

#### entity
* StockKv.java
    ```java
    package com.hongquan.web.jdbc.entity;
    
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    
    import java.time.LocalDateTime;
    
    /**
     * stock_kv record.
     */
    public class StockKv {
        @TableId(value = "id", type = IdType.AUTO)
        private Integer id;
        private String key;
        private String value;
        private LocalDateTime expire;
        private LocalDateTime updateTime;
    
        public StockKv() {}
    
        public Integer getId() {
            return id;
        }
    
        public void setId(Integer id) {
            this.id = id;
        }
    
        public String getKey() {
            return key;
        }
    
        public void setKey(String key) {
            this.key = key;
        }
    
        public String getValue() {
            return value;
        }
    
        public void setValue(String value) {
            this.value = value;
        }
    
        public LocalDateTime getExpire() {
            return expire;
        }
    
        public void setExpire(LocalDateTime expire) {
            this.expire = expire;
        }
    
        public LocalDateTime getUpdateTime() {
            return updateTime;
        }
    
        public void setUpdateTime(LocalDateTime updateTime) {
            this.updateTime = updateTime;
        }
    
        @Override
        public String toString() {
            return "StockKv{" +
                    "id=" + id +
                    ", key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    ", expire=" + expire +
                    ", updateTime=" + updateTime +
                    '}';
        }
    }
    ```

#### mapper
* StockKvMapper.java

    BaseMapper已经封装好了CRUD方法
    ```java
    package com.hongquan.web.jdbc.mapper;
    
    import com.baomidou.mybatisplus.core.mapper.BaseMapper;
    import com.hongquan.web.jdbc.entity.StockKv;
    
    public interface StockKvMapper extends BaseMapper<StockKv> {
    }
    
    ```

#### jdcb.service
* IStockKvService.java

    IService 内部进一步封装了 BaseMapper 接口的方法（当然也提供了更详细的方法）。
    使用时，可以通过 生成的 mapper 类进行 CRUD 操作，也可以通过 生成的 service 的实现类进行 CRUD 操作。
    （当然，自定义代码执行也可,不选择继承IService）
    ```java
    package com.hongquan.web.jdbc.service;
    
    import com.baomidou.mybatisplus.extension.service.IService;
    import com.hongquan.web.jdbc.entity.StockKv;
    
    public interface IStockKvService extends IService<StockKv> {
    }
    
    ```

* StockKvServiceImpl.java
    ```java
    package com.hongquan.web.jdbc.service.impl;
    
    import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
    import com.hongquan.web.jdbc.entity.StockKv;
    import com.hongquan.web.jdbc.mapper.StockKvMapper;
    import com.hongquan.web.jdbc.service.IStockKvService;
    import org.springframework.stereotype.Service;
    
    @Service
    public class StockKvServiceImpl extends ServiceImpl<StockKvMapper, StockKv> implements IStockKvService {
    }
    
    ```
    
#### service
* StockService.java
    ```java
    package com.hongquan.web.service;
    
    import com.alibaba.fastjson.JSONObject;
    import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
    import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
    import com.baomidou.mybatisplus.core.toolkit.Wrappers;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.hongquan.web.jdbc.entity.StockKv;
    import com.hongquan.web.jdbc.service.IStockKvService;
    import com.hongquan.web.util.RedisUtil;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    
    import java.util.Map;
    
    @Service
    public class StockService {
        @Autowired
        private RedisUtil redisUtil;
    
        @Autowired
        private IStockKvService stockKvService;
        /**
         * 获取指定key的value（Redis String类型）
         *
         * @param key: redis key
         * @return
         */
        public Object get(String key) {
            // LambdaQueryWrapper<StockKv> queryWrapper = Wrappers.lambdaQuery();
            // queryWrapper.select(StockKv::getKey, StockKv::getValue).eq(StockKv::getKey, key);
            // StockKv stockKv = stockKvService.getOne(queryWrapper);
    
            try {
                // 先从redis获取key
                return redisUtil.get(key);
            } catch (Exception e) {
                e.printStackTrace();
                // 从数据库获取key
                QueryWrapper<StockKv> query = Wrappers.query();
                query.select("`key`", "`value`").eq("`key`", key);
                StockKv stockKv = stockKvService.getOne(query);
                return JSONObject.parseObject(stockKv.getValue(), Map.class);
            }
        }
    }
    
    ```

#### controller
* StockApiController.java
    ```java
    package com.hongquan.web.controller;
    
    import com.hongquan.web.service.StockService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.repository.query.Param;
    import org.springframework.web.bind.annotation.*;
    
    import java.util.List;
    import java.util.Map;
    
    @RequestMapping(value = "/stock-api")
    @RestController
    public class StockApiController {
        @Autowired
        private StockService stockService;
    
        @GetMapping("get")
        public Object get(@Param(value = "key") String key) {
            return stockService.get(key);
        }
    
        @GetMapping("get/{key:.+}")
        public Object getList(@PathVariable("key") String key) {
            return stockService.get(key);
        }
    
        @GetMapping("line/{code:.+}")
        public Object getSeries(@PathVariable("code") String code) {
            return stockService.get(code + "_line");
        }
    }
    
    ```

#### 启动类中添加要扫描的mapper路径
```java
package com.hongquan.web;

import com.hongquan.web.config.LCAWebConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@MapperScan(basePackages = {"com.hongquan.web.**.mapper"})
public class LCAWebApplication {
    private static final Logger logger = LoggerFactory.getLogger(LCAWebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LCAWebApplication.class, args);
        logger.info("程序启动成功!!!");
    }
}
```

### 自动生成代码版
#### 新建springboot项目
![](../images/SpringBoot/springboot_mybatis-plus1.jpg)

#### pom.xml
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <!-- mybatis plus 代码生成器 -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.2.0</version>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.2.0</version>
        </dependency>
        <dependency>
            <groupId>org.freemarker</groupId>
            <artifactId>freemarker</artifactId>
            <version>2.3.28</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.47</version>
        </dependency>
```

#### application.yml
```yaml
server:
  port: 8081
  servlet:
    context-path: /

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/demo?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true
    username: root
    password: lyja
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: GMT+8
    serialization:
      write-dates-as-timestamps: false

mybatis-plus:
  configuration:
    map-underscore-to-camel-case: true
    auto-mapping-behavior: full
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  mapper-locations: classpath*:mapper/**/*Mapper.xml
  global-config:
    # 逻辑删除配置
    db-config:
      # 删除前
      logic-not-delete-value: 1
      # 删除后
      logic-delete-value: 0
```

#### mybatisplus分页插件MybatisPlusConfig
```java
package com.example.conf;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置分页插件
 *
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

```

#### mybatisplus自动生成代码GeneratorCodeConfig.java
```java
package com.example.conf;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Scanner;

/**
 * 自动生成mybatisplus的相关代码
 */
public class GeneratorCodeConfig {

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("astupidcoder");
        gc.setOpen(false);
        //实体属性 Swagger2 注解
        gc.setSwagger2(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/demo?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("lyja");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.example");
        pc.setEntity("model.auto");
        pc.setMapper("mapper.auto");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);

        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };

        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.baomidou.mybatisplus.extension.activerecord.Model");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        strategy.setEntityLombokModel(true);
        // 公共父类
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
```

#### 建表并生成代码
```mysql
CREATE TABLE `user` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,   
    user_name VARCHAR(255),
    `password` VARCHAR(255)
)
```

执行GeneratorCodeConfig.java文件，输入表名user  
![](../images/SpringBoot/springboot_mybatis-plus2.jpg)

解决方法：在数据库连接中配置添加allowPublicKeyRetrieval=true  
![](../images/SpringBoot/springboot_mybatis-plus3.jpg)

查看生成的文件  
![](../images/SpringBoot/springboot_mybatis-plus4.jpg)


#### 添加扫描mapper注解
启动springboot的application启动类：会报错，提示找不到mapper文件，我们需要在springboot启动类上添加扫描mapper的注解

```java
package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

```

#### UserController.java中新增接口
```java
  @Autowired
    private IUserService userService;
    @PostMapping("/getUser")
    public User getUser(){
        return userService.getById(1);
    }
    
```

#### postman测试
![](../images/SpringBoot/springboot_mybatis-plus5.jpg)

![](../images/SpringBoot/springboot_mybatis-plus6.jpg)

没问题。

上面是mybatisplus测试成功

#### 手动写sql
1. 在resources目录下新建mapper文件夹，新建UserMapper.xml文件

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    
    <mapper namespace="com.example.mapper.auto.UserMapper">
    
        <!-- 查找用户信息 -->
        <select id="findAllUser" resultType="com.example.model.auto.User">
           select * from user
        </select>
    
    </mapper>
    ```

2. UserMapper.java
    ```java
    package com.example.mapper.auto;
    
    import com.baomidou.mybatisplus.core.mapper.BaseMapper;
    import com.example.model.auto.User;
    
    import java.util.List;
    
    /**
     * <p>
     *  Mapper 接口
     * </p>
     *
     * @author astupidcoder
     * @since 2020-05-13
     */
    public interface UserMapper extends BaseMapper<User> {
    
        public List<User>findAllUser();
    }
    ```

3. IUserService.java
    ```java
    package com.example.service;
    
    import com.baomidou.mybatisplus.extension.service.IService;
    import com.example.model.auto.User;
    
    import java.util.List;
    
    /**
     * <p>
     *  服务类
     * </p>
     *
     * @author astupidcoder
     * @since 2020-05-13
     */
    public interface IUserService extends IService<User> {
    
        public List<User> findAllUser();
    }
    ```
4. UseServiceImpl.java
    ```java
    package com.example.service.impl;
    
    import com.example.model.auto.User;
    import com.example.mapper.auto.UserMapper;
    import com.example.service.IUserService;
    import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    
    import java.util.List;
    
    /**
     * <p>
     *  服务实现类
     * </p>
     *
     * @author astupidcoder
     * @since 2020-05-13
     */
    @Service
    public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    
        @Autowired
        private UserMapper userMapper;
        @Override
        public List<User> findAllUser() {
            return userMapper.findAllUser();
        }
    }
    ```

5. UserController.java
    ```java
    package com.example.controller;
    
    
    import com.example.model.auto.User;
    import com.example.service.IUserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    
    import org.springframework.web.bind.annotation.RestController;
    
    import java.util.List;
    
    /**
     * <p>
     *  前端控制器
     * </p>
     *
     * @author astupidcoder
     * @since 2020-05-13
     */
    @RestController
    @RequestMapping("/user")
    public class UserController {
    
        @Autowired
        private IUserService userService;
        @PostMapping("/getUser")
        public User getUser(){
            return userService.getById(1);
        }
    
    
        @PostMapping("/findAllUser")
        public List<User> findAllUser(){
            return userService.findAllUser();
        }
    }
    ```
6. 测试findAllUser接口
    ![](../images/SpringBoot/springboot_mybatis-plus7.jpg)

#### 附录
##### 常用的工具类
* ResultInfo.java
    ```java
    package com.example.conf;
    
    import lombok.Data;
    
    import java.io.Serializable;
    
    /**
     *返回结果类统一封装
     */
    @Data
    public class ResultInfo implements Serializable {
    
        // 状态码
        private Integer code;
        // 消息
        private String message;
        // 数据对象
        private Object result;
    
        private Integer total;
    
        /**
         * 无参构造器
         */
        public ResultInfo() {
            super();
        }
    
        public ResultInfo(Status status) {
            super();
            this.code = status.code;
            this.message = status.message;
        }
    
        public ResultInfo result(Object result) {
            this.result = result;
            return this;
        }
    
        public ResultInfo message(String message) {
            this.message = message;
            return this;
        }
        public ResultInfo total(Integer total) {
            this.total = total;
            return this;
        }
    
        /**
         * 只返回状态，状态码，消息
         *
         * @param code
         * @param message
         */
        public ResultInfo(Integer code, String message) {
            super();
            this.code = code;
            this.message = message;
        }
    
        /**
         * 只返回状态，状态码，数据对象
         *
         * @param code
         * @param result
         */
        public ResultInfo(Integer code, Object result) {
            super();
            this.code = code;
            this.result = result;
        }
    
        /**
         * 返回全部信息即状态，状态码，消息，数据对象
         *
         * @param code
         * @param message
         * @param result
         */
        public ResultInfo(Integer code, String message, Object result) {
            super();
            this.code = code;
            this.message = message;
            this.result = result;
        }
    }
    ```

* Status.java
    ```java
    package com.example.conf;
    
    /**
     * 枚举类对象
     */
    public enum Status {
    
        // 公共
        SUCCESS(2000, "成功"),
        UNKNOWN_ERROR(9998,"未知异常"),
        SYSTEM_ERROR(9999, "系统异常"),
    
    
        INSUFFICIENT_PERMISSION(4003, "权限不足"),
    
        WARN(9000, "失败"),
        REQUEST_PARAMETER_ERROR(1002, "请求参数错误"),
    
        // 登录
        LOGIN_EXPIRE(2001, "未登录或者登录失效"),
        LOGIN_CODE_ERROR(2002, "登录验证码错误"),
        LOGIN_ERROR(2003, "用户名不存在或密码错误"),
        LOGIN_USER_STATUS_ERROR(2004, "用户状态不正确"),
        LOGOUT_ERROR(2005, "退出失败，token不存在"),
        LOGIN_USER_NOT_EXIST(2006, "该用户不存在"),
        LOGIN_USER_EXIST(2007, "该用户已存在");
    
        public int code;
        public String message;
    
        Status(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }
    ```
    
##### 一份详尽的yml配置文件
```yaml
server:
  port: 8085
  servlet:
    context-path: /test


spring:
  #redis集群
  redis:
    host: 127.0.0.1
    port: 6379
    timeout: 20000
    #    集群环境打开下面注释，单机不需要打开
    #    cluster:
    #      集群信息
    #      nodes: xxx.xxx.xxx.xxx:xxxx,xxx.xxx.xxx.xxx:xxxx,xxx.xxx.xxx.xxx:xxxx
    #      #默认值是5 一般当此值设置过大时，容易报：Too many Cluster redirections
    #      maxRedirects: 3
    password: lyja
    application:
      name: test
    jedis:
      pool:
        max-active: 8
        min-idle: 0
        max-idle: 8
        max-wait: -1
    database: 0

  autoconfigure:
    exclude: com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure
  datasource:
    dynamic:
      #设置默认的数据源或者数据源组,默认值即为master
      primary: master
      strict: false
      datasource:
        master:
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://127.0.0.1:3306/test?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false
          username: root
          password: lyja
    # 数据源配置
    druid:
      # druid连接池监控
      stat-view-servlet:
        enabled: true
        url-pattern: /druid/*
        login-username: admin
        login-password: admin
        # 初始化时建立物理连接的个数
        initial-size: 5
        # 最大连接池数量
        max-active: 30
        # 最小连接池数量
        min-idle: 5
        # 获取连接时最大等待时间，单位毫秒
        max-wait: 60000
        # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        time-between-eviction-runs-millis: 60000
        # 连接保持空闲而不被驱逐的最小时间
        min-evictable-idle-time-millis: 300000
        # 用来检测连接是否有效的sql，要求是一个查询语句
        validation-query: select count(*) from dual
        # 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
        test-while-idle: true
        # 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
        test-on-borrow: false
        # 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
        test-on-return: false
        # 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
        pool-prepared-statements: false
        # 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。
        max-pool-prepared-statement-per-connection-size: 50
        # 配置监控统计拦截的filters，去掉后监控界面sql无法统计
        filters: stat,wall
        # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
        connection-properties:
          druid.stat.mergeSql: true
          druid.stat.slowSqlMillis: 500
        # 合并多个DruidDataSource的监控数据
        use-global-data-source-stat: true
        filter:
          stat:
            log-slow-sql: true
            slow-sql-millis: 1000
            merge-sql: true
          wall:
            config:
              multi-statement-allow: true
  servlet:
    multipart:
      # 开启 multipart 上传功能
      enabled: true
      # 文件写入磁盘的阈值
      file-size-threshold: 2KB
      # 最大文件大小
      max-file-size: 200MB
      # 最大请求大小
      max-request-size: 215MB

mybatis-plus:
  configuration:
    map-underscore-to-camel-case: true
    auto-mapping-behavior: full
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  mapper-locations: classpath*:mapper/**/*Mapper.xml
  global-config:
    # 逻辑删除配置
    db-config:
      # 删除前
      logic-not-delete-value: 1
      # 删除后
      logic-delete-value: 0
logging:
  level:
    root: info
    com.example: debug
```