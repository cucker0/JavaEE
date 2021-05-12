# springboot-data druid


## 常见问题
* application.yml配置filters log4j问题
    ```yaml
    spring:
      datasource:
        druid:
          filters: stat,wall,log4j
    ```
    
    启动报错
    ```text
    ***************************
    APPLICATION FAILED TO START
    ***************************
    
    Description:
    
    Failed to bind properties under 'spring.datasource.druid' to javax.sql.DataSource:
    
        Property: spring.datasource.druid.filters
        Value: stat,wall,log4j
        Origin: class path resource [application.yml] - 32:16
        Reason: org.apache.log4j.Logger
    
    Action:
    
    Update your application's configuration
    ```
    
    解决方法
        
    主要原因 log4j在新版的 druid（1.2.6）中配置方式变了
    ```yaml
    spring:
      datasource:
        druid:
          filters:
            commons-log.connection-logger-name: stat,wall,log4j
    ```