JavaEE
==


## Table Of Contents
<details>
<summary>Spring</summary>

<details>
<summary>Spring入门</summary>

* [什么是Spring](spring/spring#什么是Spring)
* [idea创建spring工程](spring/spring#idea创建spring工程)
* [spring中的bean配置](spring/spring#spring中的bean配置)
    * [IOC容器(DI容器)](spring/spring#IOC容器DI容器)
    * [配置bean](spring/spring#配置bean)
        * [基于xml文件配置bean，即spring配置文件配置bean](spring/spring#基于xml文件配置bean即spring配置文件配置bean)
    * [spring IOC容器](spring/spring#spring-IOC容器)
    * [ApplicationContext](spring/spring#ApplicationContext)
    * [从IOC容器中获取bean实例](spring/spring#从IOC容器中获取bean实例)
    * [依赖注入的方式](spring/spring#依赖注入的方式)
    * [引用其他bean](spring/spring#引用其他bean)
    * [内部bean](spring/spring#内部bean)
    * [null值属性](spring/spring#null值属性)
    * [级联属性](spring/spring#级联属性)
    * [集合属性](spring/spring#集合属性)
    * [使用p命名空间](spring/spring#使用p命名空间)
    * [继承bean配置](spring/spring#继承bean配置)
    * [xml配置bean自动装配](spring/spring#xml配置bean自动装配)
    * [继承bean配置](spring/spring#继承bean配置)
    * [依赖bean配置](spring/spring#依赖bean配置)
    * [scope属性配置bean的作用域](spring/spring#scope属性配置bean的作用域)
    * [使用外部属性文件](spring/spring#使用外部属性文件)
    * [SpEL](spring/spring#SpEL)
* [IOC容器中的bean的生命周期方法](spring/spring#IOC容器中的bean的生命周期方法)
* [创建bean后置处理器](spring/spring#创建bean后置处理器)
* [通过注解扫描组件](spring/spring#通过注解扫描组件)
* [整合多个配置文件](spring/spring#整合多个配置文件)
</details>

* Spring AOP
    * [Spring AOP](spring/spring2#Spring-AOP)
    * [普通版AOP动态代理](spring/spring2#普通版AOP动态代理)
    * [AOP相关术语](spring/spring2#AOP相关术语)
    * [AspectJ](spring/spring2#AspectJ)
        * [启用AspectJ注解支持](spring/spring2#启用AspectJ注解支持)
        * [用AspectJ注解声明切面](spring/spring2#用AspectJ注解声明切面)
    * [AspectJ切入点表达式](spring/spring2#AspectJ切入点表达式)
    * [指定切面的优先级](spring/spring2#指定切面的优先级)
    * [基于xml配置声明切面](spring/spring2#基于xml配置声明切面)

* Spring对JDBC的支持
    * [JdbcTemplate](spring/spring3#JdbcTemplate)
        * [简化JdbcTemplate查询](spring/spring3#简化JdbcTemplate查询)
        * [注入JDBC模板配置](spring/spring3#注入JDBC模板配置)
        * [JdbcTemplate数据库操作](spring/spring3#JdbcTemplate数据库操作)
        * [在JdbcTemplate中使用具名参数](spring/spring3#在JdbcTemplate中使用具名参数)
        * [JdbcTemplate级联属性解决方法，重写RowMapper方法，见示例queryAllEmployees()](SpringMVC/springMVC4/src/com/java/curd/daoImpl/EmployeeDaoImpl.java)
    * [Spring对sql事务管理](spring/spring3#Spring对sql事务管理)
    * [用@Transactional注解声明式地管理事务](spring/spring3#用@Transactional注解声明式地管理事务)
        * [事务传播属性](spring/spring3#事务传播属性)
        * [事务隔离级别、异常回滚控制、readOnly指定事务是否为只读、事务超时控制](spring/spring3#事务隔离级别异常回滚控制readOnly指定事务是否为只读事务超时控制)
    * [xml配置声明式管理事务](spring/spring3#xml配置声明式管理事务)

* Spring与Hibernate整合
    * [环境要求](spring/spring4#环境要求)
    * [整合操作步骤](spring/spring4#整合操作步骤)

* [web中使用Spring](spring/spring5/README.md)

* Spring与Struts2整合
    * [整合目的](spring/spring6#整合目的)
    * [整合步骤](spring/spring6#整合步骤)
    * [整合的工作原理](spring/spring6#整合的工作原理)
</details>

<details>
<summary>SpringMVC</summary>

* [SpringMVC概述](readme/SpringMVC.md#SpringMVC概述)
* [SpringMVC的HelloWorld](readme/SpringMVC.md#SpringMVC的HelloWorld)
* [使用@RequestMapping映射请求路径](readme/SpringMVC.md#使用@RequestMapping映射请求路径)
    * [@RequestMapping属性params和headers支持简单的表达式](readme/SpringMVC.md#@RequestMapping属性params和headers支持简单的表达式)
    * [@RequestMapping可映射请求参数、请求方法或请求头](readme/SpringMVC.md#@RequestMapping可映射请求参数请求方法或请求头)
    * [@RequestMapping的path支持ant风格的匹配符](readme/SpringMVC.md#@RequestMapping的path支持ant风格的匹配符)
    * [@PathVariable获取路径变量(映射URL绑定的占位符)](readme/SpringMVC.md#@PathVariable获取路径变量映射URL绑定的占位符)
* [映射请求参数、请求头、Cookie](readme/SpringMVC.md#映射请求参数请求头Cookie)
    * [@RequestParam映射请求参数](readme/SpringMVC.md#@RequestParam映射请求参数)
    * [@RequestHeader映射请求头](readme/SpringMVC.md#@RequestHeader映射请求头)
    * [@CookieValue映射Cookie](readme/SpringMVC.md#@CookieValue映射Cookie)
* [使用POJO(bean)对象绑定请求参数值](readme/SpringMVC.md#使用POJObean对象绑定请求参数值)
* [使用Servlet API作为入参](readme/SpringMVC.md#使用Servlet-API作为入参)
* [处理模型数据](readme/SpringMVC.md#处理模型数据)
* [视图和视图解析器](readme/SpringMVC.md#视图和视图解析器)
    * [直接转发的页面](readme/SpringMVC.md#直接转发的页面)
    * [Excel视图](readme/SpringMVC.md#Excel视图)
    * [redirect302重定向和forward转发](readme/SpringMVC.md#redirect302重定向和forward转发)
* [REST](readme/SpringMVC.md#REST)
    * [REST特点](readme/SpringMVC.md#REST特点)
* [RESTful的CRUD](readme/SpringMVC.md#RESTful的CRUD)
    * [客户端如何用POST请求来转为PUT、DELETE请求](readme/SpringMVC.md#客户端如何用POST请求来转为PUTDELETE请求)
* [SpringMVC的form标签](readme/SpringMVC.md#SpringMVC的form标签)
* [处理静态资源](readme/SpringMVC.md#处理静态资源)
* [数据转换、数据格式化、数据校验](readme/SpringMVC.md#数据转换数据格式化数据校验)
    * [数据绑定流程](readme/SpringMVC.md#数据绑定流程)
    * [自定义数据类型转换](readme/SpringMVC.md#自定义数据类型转换)
    * [关于\<mvc:annotation-driven />](readme/SpringMVC.md#关于mvcannotation-driven-/)
    * [@InitBinder](readme/SpringMVC.md#@InitBinder)
    * [数据格式化](readme/SpringMVC.md#数据格式化)
        * [日期格式化](readme/SpringMVC.md#日期格式化)
    * [数值格式化](readme/SpringMVC.md#数值格式化)
    * [数据校验](readme/SpringMVC.md#数据校验)
    * [SpringMVC数据校验](readme/SpringMVC.md#SpringMVC数据校验)
    * [提示消息国际化](readme/SpringMVC.md#提示消息国际化)
* [HttpMessageConverter处理JSON](readme/SpringMVC.md#HttpMessageConverter处理JSON)
    * [JackSon处理JSON](readme/SpringMVC.md#JackSon处理JSON)
* [i18n国际化](readme/SpringMVC.md#i18n国际化)
    * [本地化解析器和本地化拦截器](readme/SpringMVC.md#本地化解析器和本地化拦截器)
* [文件的上传](readme/SpringMVC.md#文件的上传)
* [自定义拦截器](readme/SpringMVC.md#自定义拦截器)
* [异常处理](readme/SpringMVC.md#异常处理)
* [SpringMVC的运行流程](readme/SpringMVC.md#SpringMVC的运行流程)
* [Spring与SpringMVC整合](readme/SpringMVC.md#Spring与SpringMVC整合)
* [SpringMVC与Struts2对比](readme/SpringMVC.md#SpringMVC与Struts2对比)
</details>

<details>
<summary>MyBatis</summary>

* [MyBatis简介](readme/MyBatis.md#MyBatis简介)
* [MyBatis_HelloWorld工程](readme/MyBatis.md#MyBatis_HelloWorld工程)
* [MyBatis全局配置文件](readme/MyBatis.md#MyBatis全局配置文件)
    * [properties属性](readme/MyBatis.md#properties属性)
    * [settings设置](readme/MyBatis.md#settings设置)
    * [typeAliases类型别名](readme/MyBatis.md#typeAliases类型别名)
    * [typeHandlers类型处理器](readme/MyBatis.md#typeHandlers类型处理器)
        * [自定义类型处理器](readme/MyBatis.md#自定义类型处理器)
    * [plugins插件](readme/MyBatis.md#plugins插件)
    * [environments JDBC环境](readme/MyBatis.md#environments-JDBC环境)
    * [databaseIdProvider数据库厂商标识](readme/MyBatis.md#databaseIdProvider数据库厂商标识)
        * [MyBatis匹配规则数据库厂商规则](readme/MyBatis.md#MyBatis匹配规则数据库厂商规则)
    * [mappers](readme/MyBatis.md#mappers)
* [MyBatis映射文件](readme/MyBatis.md#MyBatis映射文件)
    * [mapper文件的增删改查](readme/MyBatis.md#mapper文件的增删改查)
        * [\<select>查询](readme/MyBatis.md#select查询)
        * [\<insert>插入记录](readme/MyBatis.md#insert插入记录)
        * [\<update>更新记录](readme/MyBatis.md#update更新记录)
        * [\<delete>删除记录](readme/MyBatis.md#delete删除记录)
    * [\#{key}与${key}获取参数异同](readme/MyBatis.md#{key}与{key}获取参数异同)
    * [参数传递](readme/MyBatis.md#参数传递)
    * [把查询结果集中的多条记录封装成一个list返回](readme/MyBatis.md#把查询结果集中的多条记录封装成一个list返回)
        * [把查询结果集中的多条记录封装成一个map返回](readme/MyBatis.md#把查询结果集中的多条记录封装成一个map返回)
    * [MyBatis解析dao接口参数方法的参封装成map的原理](./MyBatis解析dao接口参数方法的参封装成map的原理.md)
    * [查询结果集记录的字段与JavaBean对象属性的自动映射](readme/MyBatis.md#查询结果集记录的字段与JavaBean对象属性的自动映射)
    * [\<resultMap>自定义结果集映射规则](readme/MyBatis.md#resultMap自定义结果集映射规则)
    * [关联查询_\<resultMap>自定义结果集映射](readme/MyBatis.md#关联查询_resultMap自定义结果集映射)
        * [级联属性的封装映射](readme/MyBatis.md#级联属性的封装映射)
    * [关联查询_resultMap_<\association>分步查询](readme/MyBatis.md#关联查询_resultMap_association分步查询)
        * [resultMap_<\association>分步查询并按需查询(懒加载)](readme/MyBatis.md#resultMap_association分步查询并按需查询懒加载)
    * [resultMap_\<collection>集合类型的属性映射(嵌套结果集)](readme/MyBatis.md#resultMap_collection集合类型的属性映射嵌套结果集)
    * [resultMap_\<collection>分步查询及懒加载](readme/MyBatis.md#resultMap_collection分步查询及懒加载)
        * [resultMap_\<collection>分步查询并懒加载(按需加载)](readme/MyBatis.md#resultMap_collection分步查询并懒加载按需加载)
    * [resultMap_\<discriminator>鉴别器](readme/MyBatis.md#resultMap_discriminator鉴别器)
* [MyBatis Mapper动态SQL](readme/MyBatis.md#MyBatis-Mapper动态SQL)
    * [\<if>](readme/MyBatis.md#if)
    * [\<where>](readme/MyBatis.md#where)
    * [\<trim>自定义字符串截取规则](readme/MyBatis.md#trim自定义字符串截取规则)
    * [\<choose>_\<when>\<otherwise>分支选择](readme/MyBatis.md#choose_whenotherwise分支选择)
    * [\<set>与\<if>结合动态更新](readme/MyBatis.md#set与if结合动态更新)
    * [\<foreach>](readme/MyBatis.md#foreach)
    * [\<bind>绑定变量](readme/MyBatis.md#bind绑定变量)
    * [\<sql>抽取重用的sql片段,\<include>来引用它](readme/MyBatis.md#sql抽取重用的sql片段include来引用它)
    * [内置参数_parameter,_databaseId](readme/MyBatis.md#内置参数_parameter_databaseId)
* [MyBatis缓存机制](readme/MyBatis.md#MyBatis缓存机制)
    * [一级缓存](readme/MyBatis.md#一级缓存)
        * [一级缓存失效的情况](readme/MyBatis.md#一级缓存失效的情况)
    * [二级缓存](readme/MyBatis.md#二级缓存)
        * [二级缓存使用步骤](readme/MyBatis.md#二级缓存使用步骤)
    * [缓存有关设置](readme/MyBatis.md#缓存有关设置)
    * [整合第三方缓存](readme/MyBatis.md#整合第三方缓存)
* [MyBatis与Spring整合(ssm)](readme/MyBatis.md#MyBatis与Spring整合ssm)
* [MyBatis逆向工程](readme/MyBatis.md#MyBatis逆向工程)
    * [mybatis-generato使用步骤](readme/MyBatis.md#mybatis-generato使用步骤)
* [MyBatis工作原理](readme/MyBatis.md#MyBatis工作原理)
* [MyBatis插件开发](readme/MyBatis.md#MyBatis插件开发)
    * [mybatis插件开发步骤](readme/MyBatis.md#mybatis插件开发步骤)
    * [插件原理](readme/MyBatis.md#插件原理)
* [MyBatis其他](readme/MyBatis.md#MyBatis其他)
    * [PageHelper插件进行分页](readme/MyBatis.md#PageHelper插件进行分页)
    * [Oracle分页(调用存储过程and游标处理)](readme/MyBatis.md#Oracle分页调用存储过程and游标处理)
    * [批量操作](readme/MyBatis.md#批量操作)
    * [Spring,SpringMVC,Mybatis整合时批量执行](readme/MyBatis.md#SpringSpringMVCMybatis整合时批量执行)
    * [自定义TypeHandler](readme/MyBatis.md#自定义TypeHandler)
</details>

<details>
<summary>MyBatis-Plus</summary>

* [Mybatis-plus概述](readme/MybatisPlus.md#Mybatis-plus概述)
* [Spring集成Mybatis-plus步骤](readme/MybatisPlus.md#Spring集成Mybatis-plus步骤)
* [HelloWorld入门](readme/MybatisPlus.md#HelloWorld入门)
    * [通过CRUD](readme/MybatisPlus.md#通过CRUD)
        * [插入操作](readme/MybatisPlus.md#插入操作)
        * [更新操作](readme/MybatisPlus.md#更新操作)
        * [查询操作](readme/MybatisPlus.md#查询操作)
        * [删除操作](readme/MybatisPlus.md#删除操作)
* [Mybatis-plus启动时就注入SQL原理](readme/MybatisPlus.md#Mybatis-plus启动时就注入SQL原理)
* [条件构造器](readme/MybatisPlus.md#条件构造器)
    * [AbstractWrapper](readme/MybatisPlus.md#AbstractWrapper)
    * [QueryWrapper](readme/MybatisPlus.md#QueryWrapper)
    * [UpdateWrapper](readme/MybatisPlus.md#UpdateWrapper)
    * [LambdaQueryWrapper](readme/MybatisPlus.md#LambdaQueryWrapper)
* [ActiveRecord](readme/MybatisPlus.md#ActiveRecord)
    * [ActiveRecord模式的使用(AR模式)](readme/MybatisPlus.md#ActiveRecord模式的使用AR模式)
    * [AR模式的CRUD](readme/MybatisPlus.md#AR模式的CRUD)
* [代码生成器](readme/MybatisPlus.md#代码生成器)
    * [代码生成器依赖](readme/MybatisPlus.md#代码生成器依赖)
    * [MyBatis-plus代码生成器示例代码](readme/MybatisPlus.md#MyBatis-plus代码生成器示例代码)
* [插件](readme/MybatisPlus.md#插件)
    * [InnerInterceptor](readme/MybatisPlus.md#InnerInterceptor)
    * [MyBatis插件机制](readme/MybatisPlus.md#MyBatis插件机制)
    * [MyBatis插件原理](readme/MybatisPlus.md#MyBatis插件原理)
    * [分页插件](readme/MybatisPlus.md#分页插件)
    * [乐观锁插件](readme/MybatisPlus.md#乐观锁插件)
    * [防止全表更新与删除插件](readme/MybatisPlus.md#防止全表更新与删除插件)
    * [多租户插件](readme/MybatisPlus.md#多租户插件)
* [扩展](readme/MybatisPlus.md#扩展)
    * [执行SQL分析打印](readme/MybatisPlus.md#执行SQL分析打印)
    * [Sql注入器](readme/MybatisPlus.md#Sql注入器)
    * [逻辑删除](readme/MybatisPlus.md#逻辑删除)
    * [字段值自动填充功能](readme/MybatisPlus.md#字段值自动填充功能)
* [Oracle Sequence主键](readme/MybatisPlus.md#Oracle-Sequence主键)
* [MybatisX快速开发插件](readme/MybatisPlus.md#MybatisX快速开发插件)
</details>

***
<details>
<summary>Spring Boot</summary>


* [thymeleaf使用手册](readme/thymeleaf_manual.md)
</details>

***

## 其他
* [maven的使用](./readme/maven的使用.md)
* [YAML书写规范](./readme/YAML书写规范.md)
* [如何把github项目打包成jar文件](./readme/如何把github项目打包成jar文件.md)
* [idea不支持发行版本5](./readme/idea不支持发行版本5.md)
* [idea maven添加oracle依赖](./readme/idea_maven_添加_oracle依赖.md)