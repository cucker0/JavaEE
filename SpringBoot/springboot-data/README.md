springboot-data
==


* 测试异常
```text
org.springframework.beans.factory.UnsatisfiedDependencyException: 
    Error creating bean with name 'com.java.springbootdata.SpringbootDataApplicationTests': 
        Unsatisfied dependency expressed through field 'dataSource'; 
        nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: 
            No qualifying bean of type 'javax.sql.DataSource' available: 
                expected at least 1 bean which qualifies as autowire candidate. 
                Dependency annotations: 
                    {@org.springframework.beans.factory.annotation.Autowired(required=true)}

```

原因没有添加 jdbc 依赖，解决方法，添加jdbc依赖
pom.xml
```xml
<project>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
            <version>2.4.1</version>
        </dependency>
    </dependencies>

</project>
```

## Department

* list department
    >http://127.0.0.1:8080/deps
* select a department
    >http://127.0.0.1:8080/dep/2
* add a department
    >http://127.0.0.1:8080/dep/add?dep_name=投资部
