MyBatis
==

## MyBatis简介
```text
MyBatis是支持定制化SQL、存储过程、高级映射的优秀持久层框架。

是一个半自动化的持久层框架，SQL同开发人员编写，其他由MyBatis框架完成

优点：
MyBatis避免了几乎所有的JDBC代码和手动设置参数以及获取结果集
MyBatis使用xml或基于注解配置和原始映射，将接口和Java的POJO对象(java bean)映射成数据库的记录
sql与java代码分开，功能边界清晰，一个专注业务，一个专注数据


MyBatis使用介绍：https://mybatis.org/mybatis-3/

项目地址：https://github.com/mybatis/mybatis-3
```

## MyBatis_HelloWorld工程
**[MyBatis Hello World工程示例](../MyBatis/mybatis1)**
1. [创建数据库表](../MyBatis/mybatis1/sql/mybatis.sql)
2. 创建[JavaBean](../MyBatis/mybatis1/src/com/java/bean/Employee.java)
3. 创建[mybatis配置文件](../MyBatis/mybatis1/src/conf/mybatis-config.xml)、[sql Mapper文件](../MyBatis/mybatis1/src/conf/EmployeeMapper.xml)
    >mybatis配置文件<mappers>中把sql映射文件注册进来
4. [测试](../MyBatis/mybatis1/src/test/com/java/mybatis/TestMybatis.java)
    1. 根据mybatis全局配置文件创建SqlSessionFactory
    2. 在通过 SqlSessionFactory创建SqlSession对象
    3. SqlSession在根据sql映射的方法id进行操作

**[MyBatis Hello World_接口式编程 工程示例](../MyBatis/mybatis2)**  
基于上面的工程做的改进

1. 创建dao接口
    >定义需要使用的方法
2. 修改[Mapper文件](../MyBatis/mybatis2/src/com/java/dao/EmployeeMapper.java)
    >\<mapper> namespace: 使用对应的dao接口的全称接口名
3. [测试](../MyBatis/mybatis2/src/test/com/java/mybatis/TestMybatis.java)
    ```text
    1）、根据mybatis全局配置文件创建SqlSessionFactory
    2）、使用SqlSessionFactory，获取一个sqlSession对象使用他来执行增删改查
       一个sqlSession就是代表和数据库的一次会话，不是线程安全的，不要共享，使用后需要关闭
    3）、sqlSession.getMapper(Mappler类.class) 获取相应的映射器，获取到Dao接口的代理类，执行代理对象的方法（建议用此方法，因为可以更安全地检查类型）
       当然也可以直接调用方法ID来进行数据库操作，如：
       sqlSession.selectOne("com.java.EmployeeMapper.selectEmp", 1);
    4）、使用sql的唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的。
    ```
    * [创建SqlSessionFactory方法: getSqlSessionFactory](../MyBatis/mybatis2/src/test/com/java/mybatis/TestMybatis.java)

## MyBatis全局配置文件
xml配置文件<configuration>块内结构，必须按照下列顺写，可写的可以省略。否则会报错！
* configuration
    * properties 属性
    * settings 设置
    * typeAliases 类型别名处理器
    * typeHandlers 类型处理器
    * objectFactory 对象工厂
    * plugins 插件
    * environments JDBC环境
        * environment JDBC环境ID
            * transactionManager 事务管理器
            * dataSource 数据源
    * databaseIdProvider 数据库厂商标识
    * mappers 映射器

* [配置文件示例](../MyBatis/mybatis3/src/conf/mybatis-config.xml)
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE configuration
            PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
            
    <configuration>
        <!-- 各模块配置的必须按下面的顺序写，不需要的可以不配置
         properties?,
         settings?,
         typeAliases?,
         typeHandlers?,
         objectFactory?,
         objectWrapperFactory?,
         reflectorFactory?,
         plugins?,
         environments?,
         databaseIdProvider?,
         mappers?
         -->
    </configuration>
    ```

### properties属性
配置可外部化的、可提替换的属性，如引入外部的properties配置文件。  
或者通过<propertie>子标签设置propertie属性值

* resource: 引入类路径下的资源
    ```xml
    <properties resource="conf/db.properties"/>
    ```

* url: 引入网络路径或磁盘路径下的资源
    ```xml
    <properties url="file:///E:/dev/JavaEE/MyBatis/mybatis3/src/conf/db.properties"/>
    ```
    ```xml
    <properties url="http://10.100.240.209:8081/conf/db.properties"/>
    ```

* 添加全局的propertie属性
    
    在配置文件中需要引用此属性方法，${属性名}
    ```xml
    <properties resource="org/mybatis/example/config.properties">
        <property name="systemname" value="考勤系统"/>
        <property name="version" value="v1.2.0"/>
    </properties>
    ```

**多个地方法配置了同一个属性的加载顺序**  
1. 在 properties 元素体内指定的属性首先被读取。
2. 然后根据 properties 元素中的 resource 属性读取类路径下属性文件或根
    据 url 属性指定的路径读取属性文件，并覆盖已读取的同名属性。 
3. 最后读取作为方法参数传递的属性，并覆盖已读取的同名属性。

### settings设置
这时mybatis中非常重要的配置模块，可以修改mybatis的运行时行为

settings中的配置项

setting |说明 |可选值 |默认值
:--- |:--- |:--- |:---
cacheEnabled |是否开启全局缓存，也就是二级缓存的开关 |true/ false |true 
lazyLoadingEnabled |是否开启sql懒加载(按需加载sql语句)，<br>开启懒加载，必须lazyLoadingEnabled = true 且 aggressiveLazyLoading = false，<br>如果想是某个mapper不受此影响，可以使用fetchType属性指定加载类型，lazy：懒加载，eager：全加载 |true/ false |false 
aggressiveLazyLoading |是否开启入侵懒加载（即破坏懒加载特性），若开启则为全加载（即使lazyLoadingEnabled = true），关闭则允许使用懒加载特性 |true/ false |false (true in ≤3.4.1)
multipleResultSetsEnabled |是否允许一个statement返回多个结果集 |true/ false |true 
useColumnLabel |是否开启使用列标签代替列名（即为列名起别名） |true/ false |true 
useGeneratedKeys |是否允许JDBC生成keys |true/ false |false 
autoMappingBehavior |自动映sql列到JavaBean字段(属性)的方式。<br>NONE：禁用自动映射 <br>PARTIAL：自动映射结果，不会映射嵌套(关联)的JavaBean属性，<br>FULL：自动映射结果，包括嵌套的JavaBean属性等  |NONE, PARTIAL, FULL |PARTIAL 
autoMappingUnknownColum |自动映射未知的sql列或JavaBean的属性的行为方法。<br>NONE：不做任何事； <br>WARNING：输出警告日志（log级别需>=WARN）； <br>FAILING：抛出SqlSessionExcept异常 |NONE, WARNING, FAILING |NONE 
defaultExecutorType |默认Executor执行器的类型。<br>SIMPLE：不做任何特殊处理；<br>REUSE：重复使用prepared statements；<br>BATCH：重复使用prepared statements，并且使用批量update |SIMPLE, REUSE, BATCH |SIMPLE 
defaultStatementTimeout |设置执行sql的超时时间，单位为秒 |任何正整数 |Not Set (null) 
defaultFetchSize |从查询返回结果集中提取的记录条数，可被mapper中的<select>中fetchSize所覆盖 |任何正整数 |Not Set (null) 
defaultResultSetType |默认的[resultSetType](Mybatis_resultSetType属性.md)，用于控制jdbc中ResultSet对象的行为，它的取值对应着ResultSetType枚举对象的实例。<br>FORWARD_ONLY,SCROLL_SENSITIVE、SCROLL_INSENSITIVE与<br>JDBC中的TYPE_FORWARD_ONLY、TYPE_SCROLL_SENSITIVE、TYPE_SCROLL_INSENSITIVE一一对应 |FORWARD_ONLY, SCROLL_SENSITIVE, SCROLL_INSENSITIVE, DEFAULT(即'Not Set')) |Not Set (null) 
safeRowBoundsEnabled |是否允许在嵌套语句中使用分页,false：允许|true/ false |false 
safeResultHandlerEnabled |是否允许使用嵌套的statements. false: 允许|true/ false |true 
mapUnderscoreToCamelCase |是否开启自动驼峰命名映射，即类似从数据库列名A_COLUMN到JavaBean属性名aColumn的映射 |true/ false |false 
localCacheScope |本地缓存的作用域，SESSION：同一个SqlSession共享Session级缓存(以及缓存).<br>STATEMENT：同一个SqlSession的也不共享数据，相当于关闭以及缓存 |SESSION, STATEMENT |SESSION 
jdbcTypeForNull |当没有为参数提供特定的JDBC类型时，为空值指定JDBC类型，尤其是insert数据时 |JdbcType枚举类中常见的：NULL, VARCHAR, OTHER |OTHER 
lazyLoadTriggerMethods |指定触发懒加载的对象的方法 |多个时，用逗号隔开，如{方法1,方法2} |equals,clone,hashCode,toString 
defaultScriptingLanguage |指定动态SQL生成的默认语言 |可以配置类的别名或者类的全限定名  |org.apache.ibatis.scripting.xmltags.XMLLanguage
defaultEnumTypeHandler |TypeHandler的默认枚举类 |可以配置类的别名或者类的全限定名 |org.apache.ibatis.type.EnumTypeHandler 
callSettersOnNulls |当指定结果集中的值为null时是否调用映射对象的setter（map对应的put）方法，这对于有Map.keyset()依赖或null值初始化的时候是有用的。注意基本数据类型（int、boolean等）是不能设置为null的 |true, false |false 
returnInstanceForEmptyRow |当查询的行结果为空时是否返回对应的空实例。默认情况下则返回null |true, false |false 
logPrefix |指定mybatis增加到日志名称的前缀 |任何字符串 |Not set
logImpl |指定mybatis所用日志的具体实现，未指定时自动查找 |SLF4J, LOG4J, LOG4J2, JDK_LOGGING, COMMONS_LOGGING, STDOUT_LOGGING, NO_LOGGING |Not set 
proxyFactory |指定mybatis创建具有延迟加载功能的对象所用到的代理工具 |CGLIB, JAVASSIST |JAVASSIST (MyBatis 3.3 or above) 
vfsImpl |指定VFS的实现 |自定义的VFS类的全限定名 |Not set 
useActualParamName |是否引用真实的参数名 |true/ false |true 
configurationFactory |自定义Configuration工厂类，该类需要有个static Configuration getConfiguration()方法|配置类的别名或者类的全限定名 |Not set  
shrinkWhitespacesInSql |从SQL语句中删除多余的空白字符，注意：同样会影响字符串 |true/ false |false 

* 示例
    ```xml
        <settings>
            <setting name="logImpl" value="LOG4J"/>
            <!-- 使用真实参数名 -->
            <setting name="useActualParamName" value="false"/>
        </settings>
    ```

### typeAliases类型别名
```text
为某个Java类型起别名。作用：起别名后，sqlMapper中可以引用别名，省去写全类名

typeAliases: 为某个Java类型起别名。作用：起别名后，sqlMapper中可以引用别名，省去写全类名
    type: 要起别名的类型的全类名;
        当不写alias时即为默认别名，默认别名为：首字母小写的类名
    alias: 指定新的别名

package:  为某个包下的所有类批量起别名
    name: 指定包名（为当前包以及下面所有的后代包的每一个类都起一个默认别名（类名小写））
    注：当包下有同名的类名，可使用@Alias("别名")注解为某个类型指定新的别名
```
* 示例
    ```xml
        <typeAliases>
            <typeAlias type="com.java.bean.Employee" alias="emp"/>
            <package name="com.java.bean"/>
        </typeAliases>
    ```

* MyBatis内建的类型别名

    别名 |Mapped Type
    :--- |:--- 
    _byte |byte
    _long |long
    _short |short
    _int |int
    _integer |int
    _double |double
    _float |float
    _boolean |boolean
    string |String
    byte |Byte
    long |Long
    short |Short
    int |Integer
    integer |Integer
    double |Double
    float |Float
    boolean |Boolean
    date |Date
    decimal |BigDecimal
    bigdecimal |BigDecimal
    object |Object
    map |Map
    hashmap |HashMap
    list |List
    arraylist |ArrayList
    collection |Collection
    iterator |Iterator


### typeHandlers类型处理器
typeHandler将获取的值以合适的方式转换成 Java 类型

Type Handler |Java Types |JDBC Types
:--- |:--- |:--- 
BooleanTypeHandler |java.lang.Boolean, <br>boolean |数据库兼容的BOOLEAN
ByteTypeHandler |java.lang.Byte, byte |数据库兼容的NUMERIC or BYTE
ShortTypeHandler |java.lang.Short, short |数据库兼容的NUMERIC or SMALLINT
IntegerTypeHandler |java.lang.Integer, int |Any compatible NUMERIC or INTEGER
LongTypeHandler |java.lang.Long, long |Any compatible NUMERIC or BIGINT
FloatTypeHandler |java.lang.Float, float |Any compatible NUMERIC or FLOAT
DoubleTypeHandler |java.lang.Double, double |Any compatible NUMERIC or DOUBLE
BigDecimalTypeHandler |java.math.BigDecimal |Any compatible NUMERIC or DECIMAL
StringTypeHandler |java.lang.String |CHAR, VARCHAR
ClobReaderTypeHandler |java.io.Reader |-
ClobTypeHandler |java.lang.String |CLOB, LONGVARCHAR
NStringTypeHandler |java.lang.String |NVARCHAR, NCHAR
NClobTypeHandler |java.lang.String |NCLOB
BlobInputStreamTypeHandler |java.io.InputStream |-
ByteArrayTypeHandler |byte[] |Any compatible byte stream type
BlobTypeHandler |byte[] |BLOB, LONGVARBINARY
DateTypeHandler |java.util.Date |TIMESTAMP
DateOnlyTypeHandler |java.util.Date |DATE
TimeOnlyTypeHandler |java.util.Date |TIME
SqlTimestampTypeHandler |java.sql.Timestamp |TIMESTAMP
SqlDateTypeHandler |java.sql.Date |DATE
SqlTimeTypeHandler |java.sql.Time |TIME
ObjectTypeHandler |Any |OTHER, or unspecified
EnumTypeHandler |Enumeration Type |VARCHAR any string compatible type, as the code is stored (not index).
EnumOrdinalTypeHandler |Enumeration Type |Any compatible NUMERIC or DOUBLE, as the position is stored (not the code itself).
SqlxmlTypeHandler |java.lang.String |SQLXML
InstantTypeHandler |java.time.Instant |TIMESTAMP
LocalDateTimeTypeHandler |java.time.LocalDateTime |TIMESTAMP
LocalDateTypeHandler |java.time.LocalDate |DATE
LocalTimeTypeHandler |java.time.LocalTime |TIME
OffsetDateTimeTypeHandler |java.time.OffsetDateTime |TIMESTAMP
OffsetTimeTypeHandler |java.time.OffsetTime |TIME
ZonedDateTimeTypeHandler |java.time.ZonedDateTime |TIMESTAMP
YearTypeHandler |java.time.Year |INTEGER
MonthTypeHandler |java.time.Month |INTEGER
YearMonthTypeHandler |java.time.YearMonth |VARCHAR or LONGVARCHAR
JapaneseDateTypeHandler |java.time.chrono.JapaneseDate |DATE


* 日期类型的处理
    ```text
    JDK1.8已经实现全部的JSR310规范，日期处理器会自动注册
    ```

#### 自定义类型处理器
步骤
1. 实现org.apache.ibatis.type.TypeHandler接口  
    或者继承org.apache.ibatis.type.BaseTypeHandler
2. 指定其映射某个JDBC类型（可选操作）
3. 在[mybatis-config.xml](../MyBatis/enum/conf/mybatis-config.xml)全局配置文件中注册，使自定义的处理器在全局生效。
    ```xml
        <typeHandlers>
            <!-- 注册在这里的类型处理器，是全局生效的 -->
            <typeHandler handler="com.java.typehandler.EmployeeStatusTypeHandler" javaType="com.java.bean.EmployeeStatus"/>
        </typeHandlers>
    ```
4. 只需要局部使用自定义类型处理器  
    不需要mybatis-config.xml配置文件中注册  
    [在mapper.xml文件中单独指定自定义类型处理器](../MyBatis/enum/conf/com/java/dao/EmployeeMapper.xml)

**自定义类型处理器示例**

[EmployeeStatusTypeHandler](../MyBatis/enum/src/com/java/typehandler/EmployeeStatusTypeHandler.java)

### plugins插件
可以通过插件来修改MyBatis的一些核心行为。插件通过动态代理机制

可以介入下列四大对象
* Executor(update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
* ParameterHandler(getParameterObject, setParameters)
* ResultSetHandler(handleResultSets, handleOutputParameters)
* StatementHandler(prepare, parameterize, batch, update, query)

### environments JDBC环境
<environments>标签可以配置JDBC连接数据的多个环境，通过环境的ID标识可以快速切换环境。但运行时只能使用指定的这一个环境

environments配置示例
```xml
    <environments default="dev_mysql">
        <environment id="dev_mysql">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${mysql.driver}"/>
                <property name="url" value="${mysql.url}"/>
                <property name="username" value="${mysql.user}"/>
                <property name="password" value="${mysql.password}"/>
            </dataSource>
        </environment>
        <environment id="dev_oracle">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${oracle.driver}"/>
                <property name="url" value="${oracle.url}"/>
                <property name="username" value="${oracle.user}"/>
                <property name="password" value="${oracle.password}"/>
            </dataSource>
        </environment>
    </environments>
```
```text
<environments default="id_name">：表示使用哪个环境
<environment id="dev_mysql">：一个JDBC连接数据库环境的ID标识，id为字符串
<transactionManager type="JDBC"/>：事务管理器类型，可选项：type="[JDBC|MANAGED]"
    JDBC：可对连接进行commit、rollback操作，还需要配置dataSource
    MANAGED：几乎没有任何作用，它不能commit提交,也不能rollback会话。它把事务的整个生命周期交给容器来管理
        默认它会关闭连接，如果不希望关闭连接，则可以像下面这样设置
        <transactionManager type="MANAGED">
         <property name="closeConnection" value="false"/>
        </transactionManager>

<dataSource type="POOLED">：dataSource类型，可选值：type="[UNPOOLED|POOLED|JNDI]"
    UNPOOLED：不使用连接池，每次操作都需要打开一个连接和关闭该连接
    POOLED：使用连接池
    JNDI：Java命名和目录接口，与EJB 或Application Servers一起使用
    自定义dataSource：需要实现DataSourceFactory接口

<property name="driver" value="${mysql.driver}"/>：驱动
<property name="url" value="${mysql.url}"/>：mysql连接地址
<property name="username" value="${mysql.user}"/>：用户名
<property name="password" value="${mysql.password}"/>：密码
<property name="defaultTransactionIsolationLevel" value=""/>：事务隔离级别
<property name="defaultNetworkTimeout" value=""/>：网络超时时间，单位：ms
<property name="driver.encoding" value="UTF8"/>：指定数据库驱动的编码字符集
```
实际开发中我们使用Spring管理数据源，mybatis中不配置dataSource

### databaseIdProvider数据库厂商标识
为不同的数据库厂商设置不同的短标识，方便引用，用来根据不同的数据库厂商执行不同的sql语句

```text
<databaseIdProvider type="DB_VENDOR">：使用MyBatis提供的VendorDatabaseIdProvider解析数据库厂商标识，
    会通过 DatabaseMetaData.getDatabaseProductName() 来按照配置设置短标识
<property name="MySQL" value="mysql"/>：name：数据库厂商名，value：别名

在mapper的sql语句中的databaseId="数据库短标识名" 来指定相应的数据库厂商
```

databaseIdProvider示例
```xml
    <databaseIdProvider type="DB_VENDOR">
        <!-- 为不同的数据库厂商起别名 -->
        <property name="MySQL" value="mysql"/>
        <property name="Oracle" value="oracle"/>
        <property name="SQL Server" value="sqlserver"/>
        <property name="DB2" value="db2"/>
    </databaseIdProvider>
```

[mapper.xml文件中引用](../MyBatis/mybatis3/src/com/java/dao/EmployeeMapper.xml)


#### MyBatis匹配规则数据库厂商规则
1. 如果没有配置databaseIdProvider标签，那么databaseId=null
2. 如果配置了databaseIdProvider标签，使用标签配置的name去匹配数据库信息，匹配上设置databaseId=配置指定的值，否则依旧为null
3. 如果databaseId不为null，他只会找到配置databaseId的sql语句
4. MyBatis 会加载不带 databaseId 属性和带有匹配当前数据库databaseId 属性的所有语句。如果同时找到带有 databaseId 和不带databaseId 的相同语句，则后者会被舍弃。

### mappers
注册SQL映射文件

* SQL映射文件单个注册
    ```xml
        <mappers>
            <!-- 使用相对于src类路径的资源引用 -->
            <mapper resource="conf/EmployeeMapper.xml"/>
            <mapper class="com.java.dao.EmployeeMapperAnnotation"/>
            <mapper class="com.java.dao.EmployeeMapper"/>
            <package name="com.java.dao"/>
        </mappers>
    ```
* SQL映射文件批量注册

    SQL映射文件名必须和接口名相同，并且在同一目录下
    ```xml
        <mappers>
            <package name="com.java.dao"/>
        </mappers>
    ```


## MyBatis映射文件

## MyBatis动态SQL

## MyBatis缓存机制

## MyBatis与Spring整合(ssm)

## MyBatis逆向工程

## MyBatis工作原理

## MyBatis插件开发



## MyBatis其他


## SQL映射文件
* 单个参数
    ```text
    mybatis不会做特殊处理，
    
    取出值用法：
    #{参数名/任意名}
    ```

* 多参数
    ```text
    多个参数会被封装成一个map对象
        key: param1, ..., paramN，或者参数的索引也可以
        value: 传入的参数值
        
        #{key}: 从map对象中获取指定的key值
      
        当未给参数指定key名时，可以使用如下的key名
        #{arg0}, #{arg1}, ..., #{argN-1}  // 指定index方式
        #{param1}, #{param2}, ..., #{paramN}  // 指定序号方式
    ```
    
    异常
    ```text
    Error querying database.
    Cause: org.apache.ibatis.binding.BindingException:
        Parameter 'id' not found. Available parameters are [arg1, arg0, param1, param2]
    ```
    
    * 为参数指定key名
        ```text
        在dao接口方法的参数前使用如下注解
        
        @Param("keyName")
            明确指定封装参数时map的key
        ```
        
## 注意
* sql映射文件注释说明
    ```text
    注意sql语句块内的
        -- sql注释
        # 注释 
    会引起其他歧义
    所以在sql语句块内，建议使用xml的注释
    
    ```


```text
public Employee getEmp(Integer id,@Param("e")Employee emp);
	取值：id==>#{param1}    lastName===>#{param2.lastName/e.lastName}

##特别注意：如果是Collection（List、Set）类型或者是数组，
		 也会特殊处理。也是把传入的list或者数组封装在map中。
			key：Collection（collection）,如果是List还可以使用这个key(list)
				数组(array)
public Employee getEmpById(List<Integer> ids);
	取值：取出第一个id的值：   #{list[0]}
	
```

## \#{key}与${key}获取参数异同
* 同
    >都可以获取map中key对应的值，或者获取POJO(bean)对象的属性值
* 异
    * \#{key} 是以预编译的方式，将占位参数设置到sql语句中，利用PreparedStatement，以?占位参数的形式设置参数。可以防止sql注入
    * ${key} 取出的值直接拼接在sql语句中，主要用于拼接SQL语句。存在sql注入的安全问题，还有可能导致语句错误问题
        * 分表、排序方式、按照年份拆分表的... ...
            ```mysql
            SELECT * FROM ${YEAR}_salary WHERE id = xxx;
            SELECT * FROM t_employee ORDER BY ${f_name} ${order};
            ```
        * 原生的JDBC不支持?占位符参数，也可以使用${key}取值
    * 大多数情况下，我们取参数时应该使用 #{key}，防止SQL注入
    
* MyBatis接口参数解析原理

```java
/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package java.lang.reflect;

import java.lang.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import sun.reflect.annotation.AnnotationSupport;

/**
 * Information about method parameters.
 *
 * A {@code Parameter} provides information about method parameters,
 * including its name and modifiers.  It also provides an alternate
 * means of obtaining attributes for the parameter.
 *
 * @since 1.8
 */
public final class Parameter implements AnnotatedElement {

    private final String name;
    private final int modifiers;
    private final Executable executable;
    private final int index;
    
    // ... ...
    
    public String getName() {
        // Note: empty strings as parameter names are now outlawed.
        // The .isEmpty() is for compatibility with current JVM
        // behavior.  It may be removed at some point.
        if(name == null || name.isEmpty())
            return "arg" + index;
        else
            return name;
    }
    
    // ... ...
}    
```

```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.apache.ibatis.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParamNameUtil {
    public static List<String> getParamNames(Method method) {
        return getParameterNames(method);
    }

    public static List<String> getParamNames(Constructor<?> constructor) {
        return getParameterNames(constructor);
    }

    private static List<String> getParameterNames(Executable executable) {
        return (List)Arrays.stream(executable.getParameters()).map(Parameter::getName).collect(Collectors.toList());
    }

    private ParamNameUtil() {
    }
}
```

```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.apache.ibatis.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

public class ParamNameResolver {
    public static final String GENERIC_NAME_PREFIX = "param";
    private final boolean useActualParamName;
    private final SortedMap<Integer, String> names;
    private boolean hasParamAnnotation;

    public ParamNameResolver(Configuration config, Method method) {
        this.useActualParamName = config.isUseActualParamName();
        Class<?>[] paramTypes = method.getParameterTypes();
        Annotation[][] paramAnnotations = method.getParameterAnnotations();
        SortedMap<Integer, String> map = new TreeMap();
        int paramCount = paramAnnotations.length;

        for(int paramIndex = 0; paramIndex < paramCount; ++paramIndex) {
            if (!isSpecialParameter(paramTypes[paramIndex])) {
                String name = null;
                Annotation[] var9 = paramAnnotations[paramIndex];
                int var10 = var9.length;

                for(int var11 = 0; var11 < var10; ++var11) {
                    Annotation annotation = var9[var11];
                    if (annotation instanceof Param) {
                        this.hasParamAnnotation = true;
                        name = ((Param)annotation).value();
                        break;
                    }
                }

                if (name == null) {
                    if (this.useActualParamName) {
                        name = this.getActualParamName(method, paramIndex);
                    }

                    if (name == null) {
                        name = String.valueOf(map.size());
                    }
                }

                map.put(paramIndex, name);
            }
        }

        this.names = Collections.unmodifiableSortedMap(map);
    }
    
    // 注意：useActualParamName 默认为true，开启使用当前参数名后，当未使用@Param()指定参数名的，则参数名命名规则：arg + 当前参数的index索引号
    private String getActualParamName(Method method, int paramIndex) {
        return (String)ParamNameUtil.getParamNames(method).get(paramIndex);
    }

    private static boolean isSpecialParameter(Class<?> clazz) {
        return RowBounds.class.isAssignableFrom(clazz) || ResultHandler.class.isAssignableFrom(clazz);
    }

    public String[] getNames() {
        return (String[])this.names.values().toArray(new String[0]);
    }

    public Object getNamedParams(Object[] args) {
        int paramCount = this.names.size();
        if (args != null && paramCount != 0) {
            if (!this.hasParamAnnotation && paramCount == 1) {
                Object value = args[(Integer)this.names.firstKey()];
                return wrapToMapIfCollection(value, this.useActualParamName ? (String)this.names.get(0) : null);
            } else {
                Map<String, Object> param = new ParamMap();
                int i = 0;

                for(Iterator var5 = this.names.entrySet().iterator(); var5.hasNext(); ++i) {
                    Entry<Integer, String> entry = (Entry)var5.next();
                    param.put(entry.getValue(), args[(Integer)entry.getKey()]);
                    String genericParamName = "param" + (i + 1);
                    if (!this.names.containsValue(genericParamName)) {
                        param.put(genericParamName, args[(Integer)entry.getKey()]);
                    }
                }

                return param;
            }
        } else {
            return null;
        }
    }
    
    // 参数为Collection集合类型
    public static Object wrapToMapIfCollection(Object object, String actualParamName) {
        ParamMap map;
        if (object instanceof Collection) {
            map = new ParamMap();
            map.put("collection", object);
            if (object instanceof List) {
                map.put("list", object);
            }

            Optional.ofNullable(actualParamName).ifPresent((name) -> {
                map.put(name, object);
            });
            return map;
        } else if (object != null && object.getClass().isArray()) {
            map = new ParamMap();
            map.put("array", object);
            Optional.ofNullable(actualParamName).ifPresent((name) -> {
                map.put(name, object);
            });
            return map;
        } else {
            return object;
        }
    }
}

```

### `#{key}`更丰富的操作
```text
javaType、
jdbcType、
mode（存储过程）、
numericScale、
resultMap、
typeHandler、
jdbcTypeName、
expression（表达式，预设的新功能，还未实现）
```

* jdbcType
    >通常需要在某种特定的条件小被设置
    * 当数据为插入的数据为null，有些数据库不能识别mybatis对null的默认处理。比如Oracle（报错）
        ```text
        JdbcType为OTHER：无效的类型，因为mybatis对所有的null都映射的是原生Jdbc的OTHER类型，oracle不能正确处理;
        mybatis默认的jdbcType=OTHER
        ```
    

```text
#4 with JdbcType OTHER . Try setting a different JdbcType for this parameter or a different jdbcTypeForNull configuration property. 
Cause: java.sql.SQLException: 无效的列类型: 1111
```

JDBCType
```text
BIT(Types.BIT),
TINYINT(Types.TINYINT),
SMALLINT(Types.SMALLINT),
INTEGER(Types.INTEGER),
BIGINT(Types.BIGINT),
FLOAT(Types.FLOAT),
REAL(Types.REAL),
DOUBLE(Types.DOUBLE),
NUMERIC(Types.NUMERIC),
DECIMAL(Types.DECIMAL),
CHAR(Types.CHAR),
VARCHAR(Types.VARCHAR),
LONGVARCHAR(Types.LONGVARCHAR),
DATE(Types.DATE),
TIME(Types.TIME),
TIMESTAMP(Types.TIMESTAMP),
BINARY(Types.BINARY),
VARBINARY(Types.VARBINARY),
LONGVARBINARY(Types.LONGVARBINARY),
NULL(Types.NULL),
OTHER(Types.OTHER),
JAVA_OBJECT(Types.JAVA_OBJECT),
DISTINCT(Types.DISTINCT),
STRUCT(Types.STRUCT),
ARRAY(Types.ARRAY),
BLOB(Types.BLOB),
CLOB(Types.CLOB),
REF(Types.REF),
DATALINK(Types.DATALINK),
BOOLEAN(Types.BOOLEAN),
ROWID(Types.ROWID),
NCHAR(Types.NCHAR),
NVARCHAR(Types.NVARCHAR),
LONGNVARCHAR(Types.LONGNVARCHAR),
NCLOB(Types.NCLOB),
SQLXML(Types.SQLXML),
/* JDBC 4.2 Types */
REF_CURSOR(Types.REF_CURSOR),
TIME_WITH_TIMEZONE(Types.TIME_WITH_TIMEZONE),
TIMESTAMP_WITH_TIMEZONE(Types.TIMESTAMP_WITH_TIMEZONE);
```




## OGNL
```text
http://commons.apache.org/proper/commons-ognl/index.html

使用说明
http://commons.apache.org/proper/commons-ognl/language-guide.html
```

## MyBatis缓存机制
### 一级缓存
```text
* MyBatis一级缓存的生命周期与SqlSession的一致。
* MyBatis一级缓存内部设计简单，只是一个没有容量限定的HashMap，在缓存的功能性上有所欠缺。
* MyBatis的一级缓存最大范围是SqlSession内部，
    有多个SqlSession或者分布式的环境下，数据库写操作会引起脏数据，
    建议设定缓存级别为Statement。
    
    <setting name="localCacheScope" value="SESSION"/>
    <setting name="localCacheScope" value="STATEMENT"/>
本地缓存，只对当前的SqlSession有效
```

### 二级缓存
```text
* 二级缓存生命周期与SqlSessionFactory的一致，
    即必须由一个SqlSessionFactory打开的多个SqlSession会话才能共享缓存
    一个session会话在执行了 commit()或close()时才会把一级缓存中的数据添加到相应的二级缓存中
* MyBatis的二级缓存相对于一级缓存来说，实现了SqlSession之间缓存数据的共享，同时粒度更加的细，能够到namespace级别，通过Cache接口实现类不同的组合，对Cache的可控性也更强。
* MyBatis在多表查询时，极大可能会出现脏数据，有设计上的缺陷，安全使用二级缓存的条件比较苛刻。
* 在分布式环境下，由于默认的MyBatis Cache实现都是基于本地的，分布式环境下必然会出现读取到脏数据，需要使用集中式缓存将MyBatis的Cache接口实现，有一定的开发成本，直接使用Redis、Memcached等分布式缓存可能成本更低，安全性也更高。
```

开启二级缓存方法
```text
mybatis全局配置文件中配置 开启二级缓存
<setting name="cacheEnabled" value="true"/>

在SQL XML mapper文件中添加
<cache/>
```

缓存工作机制
先查二级缓存 -->再查一级缓存  -->最后查数据库


## mybatis-generator
```
https://mybatis.org/generator/

```

## BATCH类型的SqlSession批量执行
```text
SqlSession sqlSession = getSqlSessionFactory().openSession(ExecutorType.BATCH, true);

```

[BatchExecutor](../readme/BatchExecutor.java)

Spring,SpringMVC,Mybatis整合时批量执行
```text
    <!-- 创建SqlSessionFactory实例对象 -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!-- configLocation: 指定MyBatis全局配置文件 -->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <!-- mapperLocations 指定mapper文件位置，mapper不能放在dao包下，mapper文件名要与dao接口文件名相同(后缀不同) -->
        <property name="mapperLocations" value="classpath:mybatis/mapper/*.xml"/>
    </bean>

    <!-- 配置一个可以批量执行的SqlSession -->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg name="sqlSessionFactory" ref="sqlSessionFactory"/>
        <constructor-arg name="executorType" value="BATCH"/>
    </bean>
```

* 在mybatis.xml中配置批量SqlSession
```xml
    <settings>
        <!-- 指定SqlSession的ExecutorType类型
         可选值：
            SIMPLE: 默认，非批量
            REUSE: 游标
            BATCH: 批量
         -->
        <setting name="defaultExecutorType" value="BATCH"/>
    <settings>
```