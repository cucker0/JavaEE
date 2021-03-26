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
        ```text
        public class Employee {
            ...
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            private Date birth;
            ...
        ```
    * 解决方法2
    
        配置Spring Boot，指定日期格式
        application.properties
        ```text
         #指定日期格式
         spring.mvc.format.date=yyyy-MM-dd
         spring.mvc.format.date-time=yyyy-MM-dd HH:mm:ss
        ```