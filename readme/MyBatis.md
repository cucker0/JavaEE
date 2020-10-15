MyBatis
==

## MyBatis简介
```text
MyBatis是支持定制化SQL、存储过程、高级映射的优秀持久层框架。

是一个半自动化的持久层框架，SQL语句由开发人员编写，其他由MyBatis框架完成。这样可以更好的优化SQL语句

优点：
MyBatis避免了几乎所有的JDBC代码和手动设置参数以及获取结果集
MyBatis使用xml或基于注解配置和原始映射，将接口和Java的POJO对象(java bean)映射成数据库的记录
sql与java代码分开，功能边界清晰，一个专注业务，一个专注数据


MyBatis使用介绍：https://mybatis.org/mybatis-3/

项目地址：https://github.com/mybatis/mybatis-3
```

**MyBatis实验所涉及sql:**
* [mybatis.sql](../MyBatis/mybatis1/sql/mybatis.sql)
* [employee_oracle.sql](../MyBatis/mybatis3/sql/employee_oracle.sql)

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
映射文件是一个xml文件，mapper.xml文件格式如下
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.java.dao.EmployeeMapper">
    <!-- ... ... -->
</mapper>
```

**mapper.xml映射文件注释说明**
```text
注意sql语句块内的
    -- sql注释
    # 注释 
会引起其他歧义
所以在sql语句块内，建议使用xml的注释
```

* mapper文件中支持的标签
    * select
        >映射查询语句
    * insert
        >映射插入语句
    * update
        >映射更新语句
    * delete
        >映射删除语句
    * resultMap
        >自定义结果集映射
    * parameterMap
        >已废弃！老式风格的参数映射
    * sql
        >抽取可重用语句块
    * cache
        >命名空间的二级缓存配置
    * cache-ref
        >其他命名空间缓存配置的引用

### mapper文件的增删改查

#### \<select>查询
* [dao接口EmployeeMapper  Employee getEmployeeById(Long id)](../MyBatis/mybatis4/src/com/java/dao/EmployeeMapper.java)
    ```java
    public interface EmployeeMapper {
        Employee getEmployeeById(Long id);
    }
    ```
* [EmployeeMapper.xml (id="getEmployeeById")](../MyBatis/mybatis4/src/com/java/dao/EmployeeMapper.xml)
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE mapper
            PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace="com.java.dao.EmployeeMapper">
        <select id="getEmployeeById" resultType="com.java.bean.Employee">
            SELECT id, last_name AS lastName, gender, email FROM t_employee WHERE id = #{id};
        </select>
    </mapper>
    ```
    * namespace
        >命名空间，使用对应的dao接口文件的全称类名（可以让mytabis知道是映射哪个interface文件）
    * id
        >使用接口中的方法名
    * resultType
        >返回的类型，要去写全称类名(FQCN: Fully Qualified Class Name)  
        不能与resultMap同时使用
    * sql语句末尾的;
        >sql语句尾的;可以写，也可省略不写
    * databaseId
        >数据库厂商别名

    **这样 sql语句就 与 dao接口EmployeeMapper中 Employee getEmployeeById(Long id) 绑定**
* [测试方法TestEmployeeMapper testSelectEmployee()](../MyBatis/mybatis4/src/test/com/java/mybatis/TestEmployeeMapper.java)


#### \<insert>插入记录
示例
```xml
<mapper namespace="com.java.dao.EmployeeMapper">
    <!--
    获取mysql自增主键的值
        useGeneratedKeys="true" 表示开启获取自增AUTO_INCREMENT 的值，
        keyProperty="keyName" 把获取的AUTO_INCREMENT值封装给Bean对象的哪个属性，不是方法的返回值（addEmployee）。默认不是会封装此属性的 
    -->
    <!-- void addEmployee(Employee employee); -->
    <insert id="addEmployee" parameterType="com.java.bean.Employee" databaseId="mysql"
            useGeneratedKeys="true" keyProperty="id">
        INSERT INTO t_employee(last_name, gender, email) VALUES
        (#{lastName}, #{gender}, #{email})
    </insert>
</mapper>
```

* insert数据时如何获取自增主键的值

    * 主要针对支持自增ID的数据库，如mysql
        ```text
        useGeneratedKeys="true" 表示开启获取自增AUTO_INCREMENT 的值，
        keyProperty="keyName" 把获取的AUTO_INCREMENT值封装给Bean对象的哪个属性，
              不是方法的返回值（addEmployee）。默认不是会封装此属性的
        ```
    * Oracle不支持自增，Oracle使用序列来模拟自增
    
        每次插入主键数据时，都去序列中拿新的值，如何获取该值呢
        1. 创建t_emp表的自增id计数器
           ```oracle
            CREATE SEQUENCE t_emp_id INCREMENT BY 1 START WITH 1;
            ```
        2. 使用\<selectKey>在插入前先获取一个t_emp_id的值，并赋值给Bean对象的指定属性
        ```xml
            <insert id="addEmployee" parameterType="com.java.bean.Employee" databaseId="oracle">
                <!--
                查询主键值的子查询sql
                order="BEFORE" 表示此sql语句在主sql语句之前执行
                keyProperty="keyName" 将此select语句查询结果封装给Bean对象的哪个属性
                #{email, jdbcType=NULL} ：指定当email值为null时，jdbcType的类型
                 -->
                <selectKey keyProperty="id" order="BEFORE" resultType="Long">
                    SELECT t_emp_id.NEXTVAL FROM DUAL
                </selectKey>
                INSERT INTO t_emp(id, last_name, gender, email) VALUES
                (#{id}, #{lastName}, #{gender}, #{email})
                <!-- (#{id}, #{lastName}, #{gender}, #{email, jdbcType=NULL}) -->
            </insert>
        ```
        
#### \<update>更新记录
示例
```xml
    <!-- void updateEmployee(Employee employee); -->
    <update id="updateEmployee">
        UPDATE t_employee SET last_name = #{lastName}, gender = #{gender}, email = #{email} WHERE id = #{id}
    </update>
```

#### \<delete>删除记录
示例
```xml
    <!-- boolean deleteEmployeeById(Long id); -->
    <delete id="deleteEmployeeById">
        DELETE FROM t_employee WHERE id = #{id}
    </delete>
```
* 接口删除方法的返回值
    ```text
    删除操作的是否成功的状态要根据接口方法的返回结果的类型自动转换，
    
    返回类型为boolean，操作成功为true，否则为true
    返回类型为数值型(int, Long)，操作成功为1，否则为0
    ```
    
### \#{key}与${key}获取参数异同
* 同
    >都可以获取map中key对应的值，或者获取POJO(bean)对象的属性值
* 异
    * \#{key} 是以预编译的方式，将占位参数设置到sql语句中，利用PreparedStatement，以?占位参数的形式设置参数。可以防止sql注入
    * ${key} 取出的值直接拼接在sql语句中，主要用于拼接SQL语句。存在sql注入的安全问题，还有可能导致语句错误问题
        * 分表、排序方式、按照年份拆分表的... ...
            ```mysql
            SELECT * FROM ${YEAR}_salary WHERE id = xxx;
            SELECT * FROM t_employee ORDER BY ${field_name} ${order_type};
            ```
        * 原生的JDBC不支持?占位符参数，也可以使用${key}取值
    * 大多数情况下，我们取参数时应该使用 #{key}，防止SQL注入
    
### 参数传递
* 单个参数
    ```text
    可以接受基本类型、对象类型、集合类型的值。
    此情况下MyBatis可直接使用这个参数，不需要经过任何处理

    mapper.xml中获取该参数: 
        #{接口方法中定义的形参名}，或 #{任意字符串}
    ```
    ```text
    mapper.xml中获取该参数特殊情况：
    public Employee getEmp(Integer id, @Param("e") Employee emp);
        取值：id ==> #{param1}    lastName ===> #{param2.lastName/e.lastName}
    
    ##特别注意：如果是Collection（List、Set）类型或者是数组，
             也会特殊处理。也是把传入的list或者数组封装在map中。
                key名：
                Collection的可以key名: collection,
                如果是List还可以使用这个key名：list
                数组key名：array
    public Employee getEmpById(List<Integer> ids);
        取值：取出第一个id的值： #{list[0]}
    ```
* 多个参数
    ```text
    任意多个参数(>1)，这些参数会被mybatis包装成一个Map传入
    
    mapper.xml中获取参数方法：
        默认情况下，方式1： #{param1}, #{param2}, ..., #{paramN}来获取参数，编号从1开始
        方式2： #{arg0}, #{arg1}, ..., #{argN-1}，编号从0开始，即index
        
        当 mybatis-config.xml <settings>块中配置了配置了 <setting name="useActualParamName" value="false"/>，
        则表示不使用 实际的参数名。默认情况下 useActualParamName=true
        此时，可以用 #{0}, #{1}, ..., #{n} 取参数，将不能用方式2获取参数了，方式1仍然生效
    ```
    [dao接口方法 getEmployeeByIdAndLastName(Long id, String lastName)](../MyBatis/mybatis4/src/com/java/dao/EmployeeMapper.java)
    ```java
    public interface EmployeeMapper {
        Employee getEmployeeByIdAndLastName(Long id, String lastName);
    }
    ```
    [EmployeeMapper id="getEmployeeByIdAndLastName"](../MyBatis/mybatis4/src/com/java/dao/EmployeeMapper.xml)
    ```xml
        <select id="getEmployeeByIdAndLastName" resultType="com.java.bean.Employee" databaseId="mysql">
             SELECT id, last_name AS lastName, gender, email FROM t_employee WHERE id=#{param1} AND last_name=#{param2}
            <!-- SELECT id, last_name AS lastName, gender, email FROM t_employee WHERE id=#{arg0} AND last_name=#{arg1} -->
            <!-- 当设置了 useActualParamName=false 时，(该参数默认为true)
                可用 #{0} ... #{n} 取参数, 这时#{arg0}等失效，但#{param1}等仍然生效
             -->
            <!-- SELECT id, last_name AS lastName, gender, email FROM t_employee WHERE id=#{0} AND last_name=#{1} -->
        </select>
    ```
    
    获取参数可能报的异常
    ```text
    Error querying database.
    Cause: org.apache.ibatis.binding.BindingException:
        Parameter 'id' not found. Available parameters are [arg1, arg0, param1, param2]
    ```
    
* 命名参数
    ```text
    通过@Param注释为参数起名，即其在Map中的key

    mapper.xml中获取参数方法：
        #{Param注解的key}
    ```
    [dao接口方法 getEmployeeByIdAndLastName2(@Param("id") Long id, @Param("lastName") String lastName)](../MyBatis/mybatis4/src/com/java/dao/EmployeeMapper.java)
    ```java
    public interface EmployeeMapper {
        Employee getEmployeeByIdAndLastName2(@Param("id") Long id, @Param("lastName") String lastName);
    }
    ```
    
    [EmployeeMapper id="getEmployeeByIdAndLastName"](../MyBatis/mybatis4/src/com/java/dao/EmployeeMapper.xml)
    ```xml
        <!-- Employee getEmployeeByIdAndLastName2(@Param("id") Long id, @Param("lastName") String lastName); -->
        <select id="getEmployeeByIdAndLastName2" resultType="com.java.bean.Employee">
            SELECT id, last_name AS lastName, gender, email FROM t_employee WHERE id=#{id} AND last_name=#{lastName}
        </select>
    ```

* POJO(JavaBean)
    ```text
    参数属于我们业务POJO(JavaBean)时，直接传递POJO，相当于传递单个参数
    
    mapper.xml中获取参数方法：
        ${POJO对象的属性名}
        可以通过多层点获取属性对象的属性
    Plain Ordinary Java Object：简单的Java对象
    ```
    [dao接口方法 updateEmployee(Employee employee)](../MyBatis/mybatis4/src/com/java/dao/EmployeeMapper.java)
    ```java
    public interface EmployeeMapper {
        int updateEmployee(Employee employee);
    }
    ```
    
    [EmployeeMapper id="updateEmployee"](../MyBatis/mybatis4/src/com/java/dao/EmployeeMapper.xml)
    ```xml
        <!-- void updateEmployee(Employee employee); -->
        <update id="updateEmployee">
            UPDATE t_employee SET last_name = #{lastName}, gender = #{gender}, email = #{email} WHERE id = #{id}
        </update>
    ```
    
* Map
    ```text
    把多个参数封装一个Map，直接传递，相当于单个参数

    mapper.xml中获取参数方法：
        ${map对象的key}
    ```
    [dao接口方法 getEmployeeMap(Map<String, Object> map)](../MyBatis/mybatis4/src/com/java/dao/EmployeeMapper.java)
    ```java
    public interface EmployeeMapper {
        Employee getEmployeeMap(Map<String, Object> map);
    }
    ```
    
    [EmployeeMapper id="getEmployeeMap"](../MyBatis/mybatis4/src/com/java/dao/EmployeeMapper.xml)
    ```xml
        <!-- Employee getEmployeeMap(Map<String, Object> map); -->
        <select id="getEmployeeMap" resultType="com.java.bean.Employee">
            <!-- 取 表名 时，使用${key名}，因为它是sql语句，不是字符串 -->
            SELECT id, last_name AS lastName, gender, email FROM ${tableName} WHERE id=#{id} AND last_name=#{lastName}
    
            <!-- 't_employee' WHERE id=1 AND last_name='大山'
            SELECT id, last_name AS lastName, gender, email FROM #{tableName} WHERE id=#{id} AND last_name=#{lastName}
    
            // 传一个map参数：{"id":1, "lastName":"大山", "table": "t_employee"}，最后SQL语句如下，将报语法错误
            SELECT id, last_name AS lastName, gender, email FROM 't_employee' WHERE id=1 AND last_name='大山';
            -->
        </select>
    ```

### 把查询结果集中的多条记录封装成一个list返回
由dao接口方法的返回类型来决定
* [dao接口方法](../MyBatis/mybatis4/src/com/java/dao/EmployeeMapper.java)
    ```java
    public interface EmployeeMapper {
        List<Employee> getAllEmployees();
    }
    ```
* [id="getAllEmployees"](../MyBatis/mybatis4/src/com/java/dao/EmployeeMapper.xml)
    ```xml
    <!-- List<Employee> getAllEmployees();
     如果返回的是一个集合，则resultType返回类型写集合中元素的类型
     -->
    <select id="getAllEmployees" resultType="com.java.bean.Employee">
        SELECT id, last_name AS lastName, gender, email FROM t_employee
    </select>
    ```

#### 把查询结果集中的多条记录封装成一个map返回
* [dao接口方法 getEmployeeByLastNameLikeReturnMap(String lastNameKey)](../MyBatis/mybatis4/src/com/java/dao/EmployeeMapper.java)
    ```java
    public interface EmployeeMapper {
        /*
        * 把多条记录封装成一个map，Map<Long, Employee>，key为该记录的主键值, value为记录封装后的javaBean对象
        * @MapKey("id") 指定用Bean对象的哪个属性作为map的key，
        *       注意key是不允许重复的，所以当key值出现重复时，则map的元素会被最后的记录所覆盖
        * */
        @MapKey("id")
        Map<Long, Employee> getEmployeeByLastNameLikeReturnMap(String lastNameKey);
    }
    ```
* [EmployeeMapper id="getEmployeeByLastNameLikeReturnMap"](../MyBatis/mybatis4/src/com/java/dao/EmployeeMapper.xml)
    ```xml
    <!-- Map<Long, Employee> getEmployeeByLastNameLikeReturnMap(String lastNameKey); -->
    <select id="getEmployeeByLastNameLikeReturnMap" resultType="com.java.bean.Employee">
        SELECT id, last_name AS lastName, gender, email FROM t_employee WHERE last_name like #{lastNameKey}
    </select>
    ```
    
* [TestEmployeeMapper测试](../MyBatis/mybatis4/src/test/com/java/mybatis/TestEmployeeMapper.java)

### [MyBatis解析dao接口参数方法的参封装成map的原理](./MyBatis解析dao接口参数方法的参封装成map的原理.md)

### 查询结果集记录的字段与JavaBean对象属性的自动映射
```text
全局配置中 autoMappingBehavior 默认是PARTIAL。即开启自动映射功能。
autoMappingBehavior 设置为null 则会取消自动映射

要求：列名和JavaBean属性名必须一致

数据库字段命名规范，POJO属性符合驼峰命名法，如
A_COLUMNaColumn，我们可以开启自动驼峰命名规则映射功能，
全局配置中添加 <setting name="mapUnderscoreToCamelCase" value="true"></setting>
```

### \<resultMap>自定义结果集映射规则
* [dao接口方法 getEmployeeById(Long id)](../MyBatis/mybatis4/src/com/java/dao/EmployeePlusMapper.java)
    ```java
    public interface EmployeePlusMapper {
        Employee getEmployeeById(Long id);
    }
    ```
* [EmployeePlusMapper id="MyEmployeeMap"](../MyBatis/mybatis4/src/com/java/dao/EmployeePlusMapper.xml)
    ```xml
        <!-- 自定义映射规则
        自定义某个JavaBean的封装规则
        id: 查询结果映射规则
        type: 封装成哪种Bean类型
        <id column="" property=""/>: 指定主键列的封装规则
            column: 查询时显示的列名
            property: 对应JavaBean的属性名
            下同
        <result /> 非主键列的映射定义
            当有不指定映射规则的列，则自动映射，即使用相同的列名与属性名对应
         -->
        <resultMap id="MyEmployeeMap" type="com.java.bean.Employee">
            <id column="id" property="id"/>
            <result column="last_name" property="lastName"/>
            <result column="gender" property="gender"/>
            <result column="email" property="email"/>
        </resultMap>
        
        <!-- 使用自定义映射规则封装结果
         Employee getEmployeeById(Long id);
         这里的 last_name字段名不需要再额外设置label别名
         -->
        <select id="getEmployeeById" resultMap="MyEmployeeMap">
            SELECT id, last_name, gender, email FROM t_employee WHERE id = #{id}
        </select>
    ```
* [测试TestEmployeePlusMapper.testGetEmployeeById()](../MyBatis/mybatis4/src/test/com/java/mybatis/TestEmployeePlusMapper.java)


### 关联查询_\<resultMap>自定义结果集映射
* JavaBean
    * [EmployeeX](../MyBatis/mybatis4/src/com/java/bean/EmployeeX.java)
    * [Department](../MyBatis/mybatis4/src/com/java/bean/Department.java)

#### 级联属性的封装映射
* 方式1
    * [dao接口方法 getEmployeeXById(Long id)](../MyBatis/mybatis4/src/com/java/dao/EmployeePlusMapper.java)
    
    * [EmployeePlusMapper \<resultMap id="EmployeeXMap">](../MyBatis/mybatis4/src/com/java/dao/EmployeePlusMapper.xml)
        ```xml
            <!-- 级联属性映射规则 方式1
            查询员工及其所在部门
             -->
            <resultMap id="EmployeeXMap" type="com.java.bean.EmployeeX">
                <id column="id" property="id"/>
                <result column="last_name" property="lastName"/>
                <result column="gender" property="gender"/>
                <result column="email" property="email"/>
                <!-- 级联属性 -->
                <result column="dep_id" property="department.id"/>
                <result column="dep_name" property="department.depName"/>
            </resultMap>
        
            <!-- EmployeeX getEmployeeXById(Long id); -->
            <select id="getEmployeeXById" resultMap="EmployeeXMap">
                SELECT e.id, e.last_name, e.gender, e.email, e.dep_id, d.dep_name
                FROM t_employee_x e
                LEFT OUTER JOIN t_department d
                ON e.dep_id = d.id WHERE e.id = #{id};
            </select>
        ```
* 方式2(\<association>级联属性的映射)
    * [dao接口方法 EmployeeX getEmployeeXById2(Long id)](../MyBatis/mybatis4/src/com/java/dao/EmployeePlusMapper.java)
    
    * [EmployeePlusMapper \<resultMap id="EmployeeXMap2">](../MyBatis/mybatis4/src/com/java/dao/EmployeePlusMapper.xml)
    ```xml
        <!-- 级联属性映射规则 方式2 -->
        <resultMap id="EmployeeXMap2" type="com.java.bean.EmployeeX">
            <id column="id" property="id"/>
            <result column="last_name" property="lastName"/>
            <result column="gender" property="gender"/>
            <result column="email" property="email"/>
            <!--
             <association> 定义关联的单个Bean对象的映射规则，级联属性映射规则
                 property: 指定级联的属性名
                 javaType: 级联属性对象的java类型，不能省略
             -->
            <association property="department" javaType="com.java.bean.Department">
                <id column="dep_id" property="id"/>
                <result column="dep_name" property="depName"/>
            </association>
        </resultMap>
        <!-- EmployeeX getEmployeeXById2(Long id); -->
        <select id="getEmployeeXById2" resultMap="EmployeeXMap2">
            <!-- id  last_name  gender  email  dep_id  dep_name -->
            SELECT e.id, e.last_name, e.gender, e.email, e.dep_id, d.dep_name
            FROM t_employee_x e
            LEFT OUTER JOIN t_department d
            ON e.dep_id = d.id WHERE e.id = #{id};
        </select>
    ```

### 关联查询_resultMap_<\association>分步查询
* [dao接口方法 getEmployeeXStepById(Long id)](../MyBatis/mybatis4/src/com/java/dao/EmployeePlusMapper.java)

* [dao接口方法 getDepartmentById(Long id)](../MyBatis/mybatis4/src/com/java/dao/DepartmentMapper.java)

* [DepartmentMapper \<select id="getDepartmentById">](../MyBatis/mybatis4/src/com/java/dao/DepartmentMapper.xml)

* [EmployeePlusMapper \<resultMap id="EmployeeXStepMap">](../MyBatis/mybatis4/src/com/java/dao/EmployeePlusMapper.xml)
    ```xml
        <!-- <association> 级联分步查询
        ①先根据员工id查询出员工信息
        ②再根据①中查询到的员工信息中的 dep_id 去查询department
        ③把②查询到的department对象设置到员工对象中
         -->
        <resultMap id="EmployeeXStepMap" type="com.java.bean.EmployeeX">
            <id column="id" property="id"/>
            <result column="last_name" property="lastName"/>
            <result column="gender" property="gender"/>
            <result column="email" property="email"/>
            <!-- 子查询
            <association
                select: 指定调用哪个方法，可以使用其他dao接口里的方法
                column: 将当前查询中的哪一列的值作为select指定方法的传入参数
                    为select指定的方法传多个参数方法：把多个参数封装成一个map进行传递
                        {key1=column1, key2=column2, ...}
                        key为方法的形参名，value为要传入值对应的列名或label别名
                property: select指定方法的返回值封装给Bean的哪个属性
    
                流程：将column指定列的值，作为select指定方法的入参，执行select指定方法的返回值封装给property指定的属性
             -->
            <association column="dep_id"
                         select="com.java.dao.DepartmentMapper.getDepartmentById"
                         property="department">
            </association>
        </resultMap>
    
        <!-- EmployeeX getEmployeeXStepById(Long id); -->
        <select id="getEmployeeXStepById" resultMap="EmployeeXStepMap">
            <!-- id  last_name  gender  email  dep_id -->
            SELECT id, last_name, gender, email, dep_id FROM t_employee_x WHERE id = #{id}
        </select>
    ```

#### resultMap_<\association>分步查询并按需查询(懒加载)
在分步查询情况下，可以根据查询的需要，是执行一部分sql，还是执行所有的sql语句。尤其是级别属性没有使用到时则可以不执行与之相关的sql

在mybatis-config.xml配置文件中添加如下设置
```xml
    <settings>
        <!-- 开启懒加载，即按需加载，根据需要，有些分步查询是否可以不执行 -->
        <setting name="lazyLoadingEnabled" value="true"/>
        <!-- 关闭侵入性懒加载。默认为关闭，若开启的话则 懒加载 功能不生效 -->
        <setting name="aggressiveLazyLoading" value="false"/>
    </settings>
```

### resultMap_\<collection>集合类型的属性映射(嵌套结果集)
* [javaBean DepartmentX](../MyBatis/mybatis4/src/com/java/bean/DepartmentX.java)
    ```java
    public class DepartmentX {
        private Long id;
        private String depName;
        // 该部门的员工
        private List<EmployeeX> employeeList;
        // ...
    }
    ```
* [dao接口方法 getDepartmentXById(Long id)](../MyBatis/mybatis4/src/com/java/dao/DepartmentMapper.java)
    
* [DepartmentMapper.xml id="DepartmentXMap"](../MyBatis/mybatis4/src/com/java/dao/DepartmentMapper.xml)
    ```xml
        <!-- 使用collection 嵌套结果集 -->
        <resultMap id="DepartmentXMap" type="com.java.bean.DepartmentX">
            <id column="id" property="id"/>
            <result column="dep_name" property="depName"/>
            <!--
            <collection>: 定义集合类型属性的封装规则
                property: 封装给哪个属性
                ofType: 集合元素的Java类型
             -->
            <collection property="employeeList" ofType="com.java.bean.EmployeeX">
                <id column="e_id" property="id"/>
                <result column="last_name" property="lastName"/>
                <result column="gender" property="gender"/>
                <result column="email" property="email"/>
            </collection>
        </resultMap>
    
        <!-- DepartmentX getDepartmentXById(Long id); -->
        <select id="getDepartmentXById" resultMap="DepartmentXMap">
            <!-- id  dep_name    e_id  last_name  gender  email dep_id -->
            SELECT d.id, d.dep_name, e.id e_id, e.last_name, e.gender, e.email
            FROM t_department d
            LEFT OUTER JOIN t_employee_x e
            ON d.id = e.dep_id
            WHERE d.id = #{id}
        </select>
    ```
* [测试 testGetDepartmentXById()](../MyBatis/mybatis4/src/test/com/java/mybatis/TestDepartmentMapper.java)

### resultMap_\<collection>分步查询及懒加载
`<collection>`分步查询
* [EmployeePlusMapper dao接口方法 getEmployeeXsByDepId(Long depId)](../MyBatis/mybatis4/src/com/java/dao/EmployeePlusMapper.java)
* [EmployeePlusMapper.xml id="getEmployeeXsByDepId"](../MyBatis/mybatis4/src/com/java/dao/EmployeePlusMapper.xml)
    ```xml
        <!-- List<EmployeeX> getEmployeeXsByDepId(Long depId); -->
        <select id="getEmployeeXsByDepId" resultType="com.java.bean.EmployeeX">
            SELECT id, last_name lastName, gender, email FROM t_employee_x WHERE dep_id = #{depId}
        </select>
    ```

* [DepartmentMapper dao接口DepartmentMapper方法 getDepartmentXStepById(Long id)](../MyBatis/mybatis4/src/com/java/dao/DepartmentMapper.java)
    
* [DepartmentMapper.xml id="getDepartmentXById"](../MyBatis/mybatis4/src/com/java/dao/DepartmentMapper.xml)
    ```xml
        <!-- collection 分步查询 -->
        <resultMap id="DepartmentXMap1" type="com.java.bean.DepartmentX">
            <id column="id" property="id"/>
            <result column="dep_name" property="depName"/>
            <!--
             <collection> 分步查询
                column: 指定那列的值作为参数传入select指定的方法中
                select: 指定调用哪个方法
                property: 把select指定方法的返回值封装给哪个属性
             -->
            <collection column="id"
                        select="com.java.dao.EmployeePlusMapper.getEmployeeXsByDepId"
                        property="employeeList">
            </collection>
        </resultMap>
    
        <!-- DepartmentX getDepartmentXStepById(Long id); -->
        <select id="getDepartmentXStepById" resultMap="DepartmentXMap1">
            SELECT id, dep_name FROM t_department WHERE id = #{id};
        </select>
    ```
    **`<collection column="">`的column如何向select指定的方法传递多个参数？**  
    [DepartmentMapper.xml id="DepartmentXMap2"](../MyBatis/mybatis4/src/com/java/dao/DepartmentMapper.xml)
* [TestDepartmentMapper.testGetDepartmentXStepById()](../MyBatis/mybatis4/src/test/com/java/mybatis/TestDepartmentMapper.java)


#### resultMap_\<collection>分步查询并懒加载(按需加载)
在mybatis-config.xml配置文件中添加如下设置，这是全局生效
```xml
    <settings>
        <!-- 开启懒加载，即按需加载，根据需要，有些分步查询是否可以不执行 -->
        <setting name="lazyLoadingEnabled" value="true"/>
        <!-- 关闭侵入性懒加载。默认为关闭，若开启的话则 懒加载 功能不生效 -->
        <setting name="aggressiveLazyLoading" value="false"/>
    </settings>
```

`<collection fetchType="">`局部生效，将覆盖全局的懒加载策略
* [DepartmentMapper.xml id="DepartmentXMap3"](../MyBatis/mybatis4/src/com/java/dao/DepartmentMapper.xml)
    ```xml
        <resultMap id="DepartmentXMap3" type="com.java.bean.DepartmentX">
            <id column="id" property="id"/>
            <result column="dep_name" property="depName"/>
            <!--
            fetchType=""：延迟加载方式，值影响此resultMap，不受全局的懒加载影响
                * lazy：延迟加载(懒加载)
                * eager：立即（执行全部步骤）
             -->
            <collection column="{depId=id, depName=dep_name}"
                        select="com.java.dao.EmployeePlusMapper.getEmployeeXsByDepartment"
                        property="employeeList"
                        fetchType="eager">
            </collection>
        </resultMap>
    
        <!-- DepartmentX getDepartmentXStepById3(Long id); -->
        <select id="getDepartmentXStepById3" resultMap="DepartmentXMap3">
            SELECT id, dep_name FROM t_department WHERE id = #{id};
        </select>
    ```
### resultMap_\<discriminator>鉴别器
根据一个字段的不同值做出不同的操作，像if的功能，类似动态SQL功能

示例 [EmployeePlusMapper.xml id="EmployeeXDis"](../MyBatis/mybatis4/src/com/java/dao/EmployeePlusMapper.xml)：
```text
根据以下规则查询员工信息
性别为女性：把部门信息查询出来，否则不查询；
性别为男性：把last_name这一列的值赋值给email属性;
```
```xml
    <resultMap id="EmployeeXDis" type="com.java.bean.EmployeeX">
        <!-- 不同case情况都共用的映射规则 -->
        <id column="id" property="id"/>
        <result column="last_name" property="lastName"/>
        <result column="gender" property="gender"/>
        <result column="email" property="email"/>
        <!--
        <discriminator>
            column: 要判断的列
            javaType: column列值的Java类型
         -->
        <discriminator javaType="java.lang.String" column="gender">
            <case value="0" resultType="com.java.bean.EmployeeX">
                <association column="dep_id"
                             select="com.java.dao.DepartmentMapper.getDepartmentById"
                             property="department">
                </association>
            </case>
            <case value="1" resultType="com.java.bean.EmployeeX">
                <result column="last_name" property="email"/>
            </case>
        </discriminator>
    </resultMap>
```

## MyBatis Mapper动态SQL
动态拼装SQL语句。MyBatis 采用功能强大的基于 OGNL 的表达式来简化操作。
* OGNL
    ```text
    http://commons.apache.org/proper/commons-ognl/index.html
    
    使用说明
    http://commons.apache.org/proper/commons-ognl/language-guide.html
    ```

### \<if>
最精确查找
根据传入的employee对象，查与此对象有值的属性都符合的员工信息。多个属性之间为and关系
* [EmployeeDynamicSqlMapper接口的方法 getEmployeesByConditionIf(Employee employee)](../MyBatis/mybatis5/src/com/java/dao/EmployeeDynamicSqlMapper.java)
* [EmployeeDynamicSqlMapper.xml id="getEmployeesByConditionIf"](../MyBatis/mybatis5/src/com/java/dao/EmployeeDynamicSqlMapper.xml)
    ```xml
        <!-- List<Employee> getEmployeesByConditionIf(Employee employee); -->
        <select id="getEmployeesByConditionIf" resultType="com.java.bean.Employee">
            <!-- 这种写法有缺点，WHERE后的第一个查询条件为空是，将报语法错误，因为会导致SQl语句WHERE后多了一个AND或OR
             一种憋足的方法是：在WHERE后加一个恒成立条件，如 WHERE 1 = 1
             -->
            SELECT id, last_name lastName, gender, email FROM t_employee_x
            WHERE
            <if test="id != null">
                id = #{id}
            </if>
            <if test="lastName != null">
                AND last_name LIKE #{lastName}
            </if>
            <!-- 会自动传入参数的gender属性转数字，原来字符串 -->
            <if test="gender == 0 or gender == 1">
                AND gender = #{gender}
            </if>
            <if test="email != null and email.trim() != ''">
                AND email = #{email}
            </if>
        </select>
    ```
    
```text
这种写法有缺点，WHERE后的第一个查询条件为空是，将报语法错误，因为会导致SQl语句WHERE后多了一个AND或OR
一种憋足的方法是：在WHERE后加一个恒成立条件，如 WHERE 1 = 1
```

### \<where>
为解决上面这个问题，可以用<where>把<if>包裹起来，<where> 只能处理前面的AND、OR关键字，而无法处理后面的AND、OR关键字
* [EmployeeDynamicSqlMapper.xml id="getEmployeesByConditionWhere"](../MyBatis/mybatis5/src/com/java/dao/EmployeeDynamicSqlMapper.xml)
    ```xml
    <!-- List<Employee> getEmployeesByConditionWhere(Employee employee); -->
        <select id="getEmployeesByConditionWhere" resultType="com.java.bean.Employee">
            <!-- <where> 只能处理前面的AND、OR关键字 -->
            SELECT id, last_name lastName, gender, email FROM t_employee_x
            <where>
                <if test="id != null">
                    id = #{id}
                </if>
                <if test="lastName != null">
                    AND last_name LIKE #{lastName}
                </if>
                <if test="gender == 0 or gender == 1">
                    AND gender = #{gender}
                </if>
                <if test="email != null and email.trim() != ''">
                    AND email = #{email}
                </if>
            </where>
        </select>
    ```
    无法处理下面这种情况
    ```xml
        <!-- List<Employee> getEmployeesByConditionWhere2(Employee employee); -->
        <select id="getEmployeesByConditionWhere2" resultType="com.java.bean.Employee">
            <!-- <where> 只能处理前面的AND、OR关键字，而无法处理后面的AND、OR关键字 -->
            SELECT id, last_name lastName, gender, email FROM t_employee_x
            <where>
                <if test="id != null">
                    id = #{id} AND
                </if>
                <if test="lastName != null">
                    last_name LIKE #{lastName} AND
                </if>
                <if test="gender == 0 or gender == 1">
                    gender = #{gender} AND
                </if>
                <if test="email != null and email.trim() != ''">
                    email = #{email}
                </if>
            </where>
        </select>
    ```

### \<trim>自定义字符串截取规则
```xml
    <!-- List<Employee> getEpmloyeesByConditionTrim(Employee employee); -->
    <select id="getEpmloyeesByConditionTrim" resultType="com.java.bean.Employee">
        <!--
        <trim>自定义字符串的截取规则
            prefix="":  前缀，trim标签中的字符串拼接后，最后在其基础上加上前缀
            prefixOverrides="":  前缀覆盖，去掉整个trim拼接串后多余的前缀
            suffix="":  后缀，trim标签中的字符串拼接后，最后在其基础上加上后缀
            suffixOverrides="":  后缀覆盖，去掉整个trim拼接串后多余的后缀
            
            以上4个属性均为可选
         -->
        SELECT id, last_name lastName, gender, email FROM t_employee_x
        <trim prefix="WHERE" suffixOverrides="and">
            <if test="id != null">
                id = #{id} AND
            </if>
            <if test="lastName != null">
                last_name LIKE #{lastName} AND
            </if>
            <if test="gender == 0 or gender == 1">
                gender = #{gender} AND
            </if>
            <if test="email != null and email.trim() != ''">
                email = #{email}
            </if>
        </trim>
    </select>
```

### \<choose>_\<when>\<otherwise>分支选择
```xml
    <!-- List<Employee> getEmployeesByConditionChoose(Employee employee); -->
    <select id="getEmployeesByConditionChoose" resultType="com.java.bean.Employee">
        SELECT id, last_name lastName, gender, email FROM t_employee_x
        <where>
            <!--
            <choose>分支查询：从上到下，用第一成立的条件去查询，最多会进入一个查询条件，或者都不成立

             -->
            <choose>
                <when test="id != null">
                    id = #{id}
                </when>
                <when test="lastName != null">
                    last_name = #{lastName}
                </when>
                <when test="email != null">
                    email = #{email}
                </when>
                <otherwise>
                    gender = '0'
                </otherwise>
            </choose>
        </where>
    </select>
```

### \<set>与\<if>结合动态更新
自动拼接符合条件的列，并在列之间自动加入分隔符,
```xml
    <!-- void updateEmployeeById(Employee employee); -->
    <update id="updateEmployeeById">
        UPDATE t_employee_x
        <set>
            <if test="lastName != null">
                last_name = #{lastName},
            </if>
            <if test="gender != null">
                gender = #{gender},
            </if>
            <if test="email != null and email.trim() != ''">
                email = #{email}
            </if>
        </set>
        WHERE id = #{id}
    </update>
```

* \<trim> 实现set更新
    ```xml
        <!-- void updateEmployeeById2(Employee employee); -->
        <update id="updateEmployeeById2">
            <!--
             <trim> 实现set更新
             -->
            UPDATE t_employee_x
            <trim prefix="SET" suffixOverrides=",">
                <if test="lastName != null">
                    last_name = #{lastName},
                </if>
                <if test="gender != null">
                    gender = #{gender},
                </if>
                <if test="email != null and email.trim() != ''">
                    email = #{email}
                </if>
            </trim>
            WHERE id = #{id}
        </update>
    ```

### \<foreach>
* select查询语句通常用于构建IN后的集合选项
    ```xml
        <!-- List<Employee> getEmployeesByIds2(@Param("ids") List<Long> ids); -->
        <select id="getEmployeesByIds2" resultType="com.java.bean.Employee">
            SELECT id, last_name lastName, gender, email FROM t_employee_x WHERE id IN
            <!--
            <foreach> 遍历集合
                collection: 要遍历的集合。要求必须制定
                    list类型的参数会特殊处理封装在map中，
                    map的key就叫list
                item: 遍历出来的元素赋值给哪个变量
                index: 遍历list的时候是index就是索引，item就是当前值
                    遍历map的时候index表示的就是map的key，item就是map的值
                open: 遍历出所有结果拼接一个开始的字符
                close: 遍历出所有结果拼接一个结束的字符
                separator: 每个元素之间的分隔符
    
                #{item指定的变量名} 当前遍历出的元素
             -->
            <foreach collection="ids" item="item_id" separator="," open="(" close=")">
                #{item_id}
            </foreach>
        </select>
    ```
    
    或
    ```xml
        <!-- List<Employee> getEmployeesByIds(@Param("ids") List<Long> ids); -->
        <select id="getEmployeesByIds" resultType="com.java.bean.Employee">
            SELECT id, last_name lastName, gender, email FROM t_employee_x WHERE id IN (
                <foreach collection="ids" item="item_id" separator=",">
                    #{item_id}
                </foreach>
            )
        </select>
    ```
* mysql批量插入
    ```xml
    <!-- mysql批量插入，方式1
    INSERT INTO t_employee_x (last_name, gender, email, dep_id) VALUES
    ('last_name', 'gender', 'email', 'dep_id'),
    ('last_name', 'gender', 'email', 'dep_id'),
    ('last_name', 'gender', 'email', 'dep_id');
     -->
    <!-- Boolean batchInsertEmployees(@Param("employeeList") List<Employee> employeeList); -->
    <insert id="batchInsertEmployees">
        INSERT INTO t_employee_x (last_name, gender, email, dep_id) VALUES
        <foreach collection="employeeList" item="emp" separator="," close=";">
            (#{emp.lastName}, #{emp.gender}, #{emp.email}, #{emp.department.id})
        </foreach>
    </insert>
    ```
    ```xml
    <!-- mysql批量插入，方式2
    INSERT INTO t_employee_x (last_name, gender, email, dep_id) VALUES ('last_name', 'gender', 'email', 'dep_id');
    INSERT INTO t_employee_x (last_name, gender, email, dep_id) VALUES ('last_name', 'gender', 'email', 'dep_id');
    ... ...

     注意 jdbc中要开启支持多;sql语句，mysql.url= 中添加allowMultiQueries=true参数
     mysql.url=jdbc:mysql://127.0.0.1:3306/mybatis?allowMultiQueries=true
     -->
    <!-- Boolean batchInsertEmployees2(@Param("employeeList") List<Employee> employeeList); -->
    <insert id="batchInsertEmployees2">
        <foreach collection="employeeList" item="emp">
            INSERT INTO t_employee_x (last_name, gender, email, dep_id) VALUES (#{emp.lastName}, #{emp.gender}, #{emp.email}, #{emp.department.id});
        </foreach>
    </insert>
    ```
* oracle批量插入
    ```xml
        <!-- oracle批量插入方式1：
        BEGIN
        INSERT INTO t_emp (id, last_name, gender, email) VALUES
            (t_emp_id.NEXTVAL, '俞敏洪', '1', 'yumh@xindongf.com');
        INSERT INTO t_emp (id, last_name, gender, email) VALUES
            (t_emp_id.NEXTVAL, '刘胡兰', '0', 'liufl@china.cn');
        INSERT INTO t_emp (id, last_name, gender, email) VALUES
            (t_emp_id.NEXTVAL, '童第周', '1', 'tongdz@kx.com');
        END;
         -->
        <!-- Boolean oracleBatchInsertEmployees(@Param("employeeList") List<Employee> employeeList); -->
        <insert id="oracleBatchInsertEmployees">
            BEGIN
                <foreach collection="employeeList" item="emp">
                INSERT INTO t_emp (id, last_name, gender, email) VALUES
                    (t_emp_id.NEXTVAL, #{emp.lastName}, #{emp.gender}, #{emp.email});
                </foreach>
            END;
        </insert>
    ```
    ```xml
        <!-- oracle批量插入方式2：
        INSERT INTO t_emp (id, last_name, gender, email)
        SELECT t_emp_id.NEXTVAL, lastName, gender, email
        FROM
            (
                SELECT 'suzhen' lastName, '0' gender, 'sz@kk.com' email FROM DUAL
                UNION
                SELECT 'dally' lastName, '0' gender, 'dally@kk.com' email FROM DUAL
            )
    
        -->
        <!-- Boolean oracleBatchInsertEmployees2(@Param("employeeList") List<Employee> employeeList); -->
        <insert  id="oracleBatchInsertEmployees2">
            INSERT INTO t_emp (ID, last_name, gender, email)
            SELECT t_emp_id.NEXTVAL, lastName, gender, email
            FROM
                (
                <foreach collection="employeeList" item="emp" separator="UNION">
                    SELECT #{emp.lastName} lastName, #{emp.gender} gender, #{emp.email} email FROM dual
                </foreach>
                )
        </insert>
    ```

### 内置参数_parameter,_databaseId
```text
mybatis两个默认的内置参数
    _parameter: 代表整个从dao方法里传过来的参数
        传单个参数: _parameter 就是这个参数
        传多个参数: 参数会被封装为一个map，_parameter指向这个map
    _databaseId: 当前使用JDBC数据库产商别名
        要求必须配置databaseIdProvider
```
```xml
    <!-- List<Employee> getEmployeesTestInnerParamter(Employee employee); -->
    <select id="getEmployeesTestInnerParamter" resultType="com.java.bean.Employee">
        <if test="_databaseId == 'mysql'">
            SELECT id, last_name lastName, gender, email FROM t_employee_x
        </if>
        <if test="_databaseId == 'oracle'">
            SELECT id, last_name lastName, gender, email FROM t_emp
        </if>
        <!-- 一个参数时，#{_parameter.lastName} 与#{lastName} 效果是一样的 -->
        WHERE last_name like #{_parameter.lastName}
    </select>
```

### \<bind>绑定变量
```xml
    <!-- List<Employee> getEmployeesTestBind(Employee employee); -->
    <select id="getEmployeesTestBind" resultType="com.java.bean.Employee" databaseId="mysql">
        <!--
        <bind>: 可以将OGNL表达式的值绑定到一个变量中，方便后来引用这个变量的值
        -->
        <bind name="_lastName" value="'%' + lastName + '%'"/>
        SELECT id, last_name lastName, gender, email FROM t_employee_x
        WHERE last_name LIKE #{_lastName}
    </select>
```

### \<sql>抽取重用的sql片段,\<include>来引用它
使用`<include refid="sql_id">` 来引用`<sql>`定义的sql片段 
```xml
    <!--
     <sql>: 抽取重用的sql片段，方便后面引用
        id="": 此sql语句的id
        1. 抽取经常要查询的列、或经抽取经常要插入的列名，方便方便需要的地方引用
        2. 使用 <include> 来引用抽取的sql语句，使用属性 refid="sql_id"
        3. <include> 可以自定义一些 property，<sql>标签中则可以使用其自定义的 property 的变量，
            这是比较特殊的地方，传递值倒过来了(由<include>传值给<sql>，<include>又引用<sql>)，有点反人类
                <property name="" value=""/>
                <sql>中获取 <include> 自定义的property方式：${property_name}
     -->
    <sql id="selectEmplooyeColumn">
        <if test="_databaseId == 'mysql'">
            SELECT id, last_name lastName, gender, email FROM t_employee_x
        </if>
        <if test="_databaseId == 'oracle'">
            SELECT id, last_name lastName, gender, ${emailColumn} FROM t_emp
        </if>
    </sql>

    <!-- Employee getEmployeeByIdTestSql(Long id); -->
    <select id="getEmployeeByIdTestSql" resultType="com.java.bean.Employee">
        <include refid="selectEmplooyeColumn">
            <property name="emailColumn" value="email"/>
        </include>
        WHERE id = #{id}
    </select>
```

## MyBatis缓存机制

## MyBatis与Spring整合(ssm)

## MyBatis逆向工程

## MyBatis工作原理

## MyBatis插件开发



## MyBatis其他


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