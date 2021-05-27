springboot-data-mybatis
==


## 常见问题
* @MapperScan(values = {"xx.xx.mapper"}) 和@Mapper在不同包中,@Mapper注解失效
    ```text
    启动项目报错
    
    Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
    2021-05-27 16:03:23.757 ERROR 2756 --- [           main] o.s.b.d.LoggingFailureAnalysisReporter   : 
    
    ***************************
    APPLICATION FAILED TO START
    ***************************
    
    Description:
    
    Field departmentMapper in com.java.springbootdatamybatis.controller.DepartmentController required a bean of type 'com.java.springbootdatamybatis.mapper.DepartmentMapper' that could not be found.
    
    The injection point has the following annotations:
    	- @org.springframework.beans.factory.annotation.Autowired(required=true)
    
    
    Action:
    
    Consider defining a bean of type 'com.java.springbootdatamybatis.mapper.DepartmentMapper' in your configuration.
    ```
    
    **处理建议**
    ```text
    只是用 @MapperScan 注解，可添加多个mapper 所在的包路径
    ```