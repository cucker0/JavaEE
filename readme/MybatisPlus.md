Mybatis-Plus
==

## Mybatis-plus概述
MyBatis-Plus（简称 MP）是一个 MyBatis的增强工具，在 MyBatis 的基础上只做增强不做改变，
为简化开发、提高效率而生。

* 特性
    * 无侵入
    * 损耗小
    * 强大的CRUD操作
    * 支持Lambda形式调用
    * 支持Sequence主键自动生成
    * 支持ActiveRecord模式
    * 支持自定义全局通用方法注入
    * 内置代码生成器（支持Mapper、Model、Service、Controller层代码的生成，支持模板引擎）
    * 内置分页插件
    * 内置性能分析插件
    * 内置全局拦截器

* 框架结构
    ![](../images/MybatisPlus/mybatis_plus_framework.jpg)

* [官网](https://baomidou.com)
* [Mybatis-plus githug](https://github.com/baomidou/mybatis-plus)
    
## 集成Mybatis-plus步骤
* 前置知识
    * Mybatis
    * Spring 
    * Maven

1. 创建数据表
    这里以mysql为例，[sql脚本](../MyBatisPlus/sql/mp.sql)

2. 创建工程  
    参见[maven的使用](./maven的使用.md)
3. 添加maven依赖，编辑pom.xml文件
    添加mybatis-plus、spring、JDBC连接池、mysql驱动、log4j
    
    参考[pom.xml](../MyBatisPlus/mp00/pom.xml)

* 配置applicationContext.xml、db.properties、mybatis-config.xml、log4j2.xml
    * [applicationContext.xml](../MyBatisPlus/mp00/src/main/resources/applicationContext.xml)
    * [db.properties](../MyBatisPlus/mp00/src/main/resources/db.properties)
    * [mybatis-config.xml](../MyBatisPlus/mp00/src/main/resources/mybatis-config.xml)
    * [log4j2.xml](../MyBatisPlus/mp00/src/main/resources/log4j2.xml)

## MybatisX快速开发插件
MybatisX 辅助 idea 快速开发 mybatis 插件，为效率而生。

* MybatisX安装方法
    * 官方安装
        >File -> Settings -> Plugins -> Marketplace搜索框中 输入 mybatisx，安装
    
    * Jar 安装
        >File -> Settings -> Plugins -> 此轮中选择Install plugin from disk.. 选中 mybatisx.xxx.jar 安装

* [MybatisX使用文档](https://baomidou.com/guide/mybatisx-idea-plugin.html)

* 功能
    * XML跳转
    * 生成代码
    * JPA提示
        * 生成新增
        * 生成查询
        * 生成修改
        * 生成删除