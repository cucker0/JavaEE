crud resfull
==

* 添加员工时，日期格式不匹配
    ```text
    .springframework.validation.BeanPropertyBindingResult: 1 errors
    Field error in object 'employee' on field 'birth': 
    rejected value [2021-03-18]; codes [typeMismatch.employee.birth,
    typeMismatch.birth,typeMismatch.java.util.Date,typeMismatch]
    ```
    * 解决方法1
    
        配置Spring Boot，指定日期格式，全局生效
        application.properties
        ```text
         #指定日期格式
         spring.mvc.format.date=yyyy-MM-dd
         spring.mvc.format.date-time=yyyy-MM-dd HH:mm:ss
        ```
    * 解决方法2
        ```text
        public class Employee {
            ...
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            private Date birth;
            ...
        ```

* Spring Boot开启delete请求方法

    Spring Boot默认没有开启delete请求方法的支持，springboot在2.0以上默认关闭这个方法了
    ```text
    页面：
    Whitelabel Error Page
    This application has no explicit mapping for /error, so you are seeing this as a fallback.
    
    Fri Mar 26 16:32:53 CST 2021
    There was an unexpected error (type=Method Not Allowed, status=405).
    
    idea 控制台：
    Resolved [org.springframework.web.HttpRequestMethodNotSupportedException: 
    Request method 'POST' not supported]
    ```
    
    * 解决方法
    
        application.properties 添加如下配置
        ```properties
        # 可把POST 请求转为 DELETE 或 PUT 请求
        spring.mvc.hiddenmethod.filter.enabled=true
        ```