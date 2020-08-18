springMVC4
==

## 说明
```text
这是RESTful风格的 员工信息的CRUD操作
```



* Tomcat 8及以上版默认只支持客户端使用OPTIONS, GET, HEAD, POST请求方法

* tomcat不支持jqeury的XMLHttpRequest(ajax)发送的请求，虽然后台能接收到请求，但会报405

## 数据校验
```text
这里用Hibernate Validator
http://hibernate.org/validator/releases/

1. 数据类型转换
2. 数据类型格式化
3. 数据校验. 
    1). 如何校验 ? 注解 ?
        ①. 使用 JSR 303 验证标准
        ②. 加入 hibernate validator 验证框架的 jar 包
        ③. 在 SpringMVC 配置文件中添加 <mvc:annotation-driven />
        ④. 需要在 bean 的属性上添加对应的注解
        ⑤. 在目标方法 bean 类型的前面添加 @Valid 注解
    2). 验证出错转向到哪一个页面 ?
    注意: 需校验的 Bean 对象和其绑定结果对象或错误对象时成对出现的，它们之间不允许声明其他的入参
    3). 错误消息 ? 如何显示, 如何把错误消息进行国际化
```

