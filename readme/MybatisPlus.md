Mybatis-Plus
==

## Mybatis-plus概述
MyBatis-Plus（简称 MP）是一个 MyBatis的增强工具，在 MyBatis 的基础上只做增强不做改变，
为简化开发、提高效率而生。
比较适用于单表操作，尤其是JavaBean

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
    
## Spring集成Mybatis-plus步骤
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

* 配置Spring环境、数据库连接信息、mybatis、log4j2
    * [applicationContext.xml](../MyBatisPlus/mp00/src/main/resources/applicationContext.xml)
    * [db.properties](../MyBatisPlus/mp00/src/main/resources/db.properties)
    * [mybatis-config.xml](../MyBatisPlus/mp00/src/main/resources/mybatis-config.xml)
    * [log4j2.xml](../MyBatisPlus/mp00/src/main/resources/log4j2.xml)
4. 测试
    [testDataSource()](../MyBatisPlus/mp00/src/test/java/com/java/mp/TestMP.java)

## HelloWorld入门
项目：[mp00(Mybatis-plus 3.4)](../MyBatisPlus/mp00)

项目：[mp00(Mybatis-plus 2.3)](../MyBatisPlus/mp01)

* 指[定实体类](../MyBatisPlus/mp00/src/main/java/com/java/mp/bean/Employee.java)要操作的表、主键策略
    
    MybatisPlus 默认使用实体类的类名到数据库找对应的表名，即此类名全部字母小写对应的表名
    * 方式1：可以在定义实体类时，加注释指定要操作的表、主键策略，可指定表中不存在的字段
        ```text
        @TableName(value = "tbl_employee")
        public class Employee {
            ...
            @TableId(value = "id", type = IdType.AUTO)
            private Integer id;
            
            // @TableField(exist = false)，表示非表的字段
            @TableField(exist = false)
            private Double salary;
            ...  
        }
        ```
    * 方式2：在applicationContext.xml通过dbConfig定义表前缀
        ```text
            <!-- 定义dbConfig -->
            <bean id="dbConfig" class="com.baomidou.mybatisplus.core.config.GlobalConfig.DbConfig">
                <!-- 全局的主键策略
                 AUTO(0, "数据库ID自增"),
                 INPUT(1, "用户输入ID"),
                 ID_WORKER(2, "全局唯一ID"),
                 UUID(3, "全局唯一ID"),
                 NONE(4, "该类型为未设置主键类型"),
                 ID_WORKER_STR(5, "字符串全局唯一ID");
                 -->
                <property name="idType" value="AUTO"></property>
                <!-- 全局的表前缀策略配置 -->
                <property name="tablePrefix" value="tbl_"></property>
            </bean>
      
            <!-- 定义MybatisPlus的全局策略配置-->
            <bean id ="globalConfig" class="com.baomidou.mybatisplus.core.config.GlobalConfig">
                <property name="dbConfig" ref="dbConfig"></property>
            </bean>
      
            <bean id="sqlSessionFactory" class="com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean">
                <property name="dataSource" ref="dataSource"/>
                <!-- 注入MybatisPlus的全局策略配置 -->
                <property name="globalConfig" ref="globalConfig"></property>
            </bean>
        ```
    
### 通过CRUD
* 场景
    ```text
    有一张有tbl_employee，对应的实体类Employee，需要实现对tbl_employee表CRUD操作，应如何做？
    ```
* 实现方式
    * 基于Mybatis
        ```text
        1. 编写EmployeeMapper接口，定义CRUD方法
        2. 添加EmployeeMapper.xml映射文件，编写每个方法对应的SQL语句
        ```
    * 基于Mybatis-plus
        ```text
        1. 编写Employee接口，接口继续BaseMapper接口即可。
        这样就实现了多部分通用的方法，可以不创建SQL映射文件
        ```
#### 插入操作
```text
int insert(T entity)

支持主键自增的数据库插入数据获取主键值
Mybatis: 需要通过 useGeneratedKeys 以及 keyProperty 来设置
MP: 自动将主键值回写到实体类中
```

* 示例 [testCommonInsert()](../MyBatisPlus/mp00/src/test/java/com/java/mp/TestMP.java)

    [testInsert2()](../MyBatisPlus/mp00/src/test/java/com/java/mp/TestMP.java)

    

#### 更新操作
```text
int updateById(@Param("et") T entity)
```

* 示例 [testCommonUpdate()](../MyBatisPlus/mp00/src/test/java/com/java/mp/TestMP.java)

#### 查询操作
```text
T selectById(Serializable id)
T selectOne(@Param("ew") Wrapper<T> queryWrapper)
List<T> selectBatchIds(@Param("coll") Collection<? extends Serializable> idList)
List<T> selectByMap(@Param("cm") Map<String, Object> columnMap)
<E extends IPage<T>> E selectPage(E page, @Param("ew") Wrapper<T> queryWrapper)
```

* 示例 [testCommonSelect() - testCommonSelect5()](../MyBatisPlus/mp00/src/test/java/com/java/mp/TestMP.java)

#### 删除操作
```text
int delete(@Param("ew") Wrapper<T> queryWrapper)

int deleteById(Serializable id)

int deleteByMap(@Param("cm") Map<String, Object> columnMap)

int deleteBatchIds(@Param("coll") Collection<? extends Serializable> idList)
```

* 示例 [testCommonDelete() - testCommonDelete3()](../MyBatisPlus/mp00/src/test/java/com/java/mp/TestMP.java)

## Mybatis-plus启动时就注入SQL原理
1. xxxMapper接口继承了BaseMapper<T>，BaseMapper提供了通用的CRUD方法，方法来源的于BaseMapper。
有方法就必须有SQL语句，Mybatis最终还是需要通过SQL语句操作数据

2. employeeMpper的本质：org.apache.ibatis.binding.MapperProxy<T>

    MapperProxy中的sqlSession->SqlSessionFactory
    ![](../images/MybatisPlus/employeeMapper.png)
    
    SqlSessionFactory->Configuration-MappedStatements
    一个mappedStatement对应Mapper接口中的一个方法与Mapper映射文件中的一个SQL语句
    
    MP在启动时就会逐个分析xxxMapper操中的方法，并且将对应的SQL语句处理好，保存到configuration对象
    的mappedStatement中
    
    本质:
    启动注入SQL 
    ![](../images/MybatisPlus/addMappedStatement.png)
    
    Configuration: Mybatis或MP全局配置对象
    MappedStatement: 一个 MappedStatement 对象对应 Mapper 配置文件中的一个
                     select/update/insert/delete 节点，主要描述的是一条 SQL 语句
                     
    SqlMethod: 枚举对象，MP支持的SQL方法
    TableInfo: 数据库表反射的信息，可以获取到数据库表相关的信息
    SqlSource: SQL语句处理对象
    MapperBuilderAssistant: 用于缓存、SQL参数、查询方法结果集处理等
    通过MapperBuilderAssistant将第一个mappedStatement添加到configuration对象的mappedstatement中
    ![](../images/MybatisPlus/configuration.png)  
    
    ![](../images/MybatisPlus/mappedStatements.png)  
    
    ![](../images/MybatisPlus/mappedStatements2.png)  


## 条件构造器
### AbstractWrapper
用于生成 sql 的 where 条件, entity 属性也用于生成 sql 的 where 条件。
注意: entity 生成的 where 条件与 使用各个 api 生成的 where 条件没有任何关联行为

是QueryWrapper、UpdateWrapper

* AbstractWrapper内置方法
可链式编程，因为每个方法最后都return this 对象本身

    https://baomidou.com/guide/wrapper.html#abstractwrapper

方法名 |说明 |语法 |示例
:---|:---|:---|:---
allEq |部eq(或个别isNull) |allEq(Map<R, V> params) <br>allEq(Map<R, V> params, boolean null2IsNull) <br>allEq(boolean condition, Map<R, V> params, boolean null2IsNull) |allEq({id:1,name:"老王",age:null}) --->id = 1 and name = '老王' and age is null 
eq |等于 = |eq(R column, Object val) <br>eq(boolean condition, R column, Object val) |eq("name", "老王") --->name = '老王' 
ne |不等于 <> |ne(R column, Object val) <br>ne(boolean condition, R column, Object val) |ne("name", "老王") --->name <> '老王' 
gt |大于 > |gt(R column, Object val) <br>gt(boolean condition, R column, Object val) |gt("age", 18) --->age > 18 
ge |大于等于 >= |ge(R column, Object val) <br>ge(boolean condition, R column, Object val) |ge("age", 18) --->age >= 18 
lt |小于 < |lt(R column, Object val) <br>lt(boolean condition, R column, Object val) |lt("age", 18) --->age < 18 
le |小于等于 <= |le(R column, Object val) <br>le(boolean condition, R column, Object val) |例: le("age", 18) --->age <= 18 
between |BETWEEN 值1 AND 值2 |between(R column, Object val1, Object val2) <br>between(boolean condition, R column, Object val1, Object val2) |between("age", 18, 30) --->age between 18 and 30 
notBetween |NOT BETWEEN 值1 AND 值2 |notBetween(R column, Object val1, Object val2) <br>notBetween(boolean condition, R column, Object val1, Object val2) |notBetween("age", 18, 30) --->age not between 18 and 30 
like |LIKE '%值%' |like(R column, Object val) <br>like(boolean condition, R column, Object val) |like("name", "王") --->name like '%王%' 
notLike |NOT LIKE '%值%' |notLike(R column, Object val) <br>notLike(boolean condition, R column, Object val) |notLike("name", "王") --->name not like '%王%' 
likeLeft |LIKE '%值' |likeLeft(R column, Object val) <br>likeLeft(boolean condition, R column, Object val) |likeLeft("name", "王") --->name like '%王'
likeRight |LIKE '值%' |likeRight(R column, Object val) <br>likeRight(boolean condition, R column, Object val) |likeRight("name", "王") --->name like '王%' 
isNull |字段 IS NULL |isNull(R column) <br>isNull(boolean condition, R column) |isNull("name")--->name is null 
isNotNull |字段 IS NOT NULL |isNotNull(R column) <br>isNotNull(boolean condition, R column) |isNotNull("name") --->name is not null 
in |字段 IN (value.get(0), value.get(1), ...) |in(R column, Collection<?> value) <br>in(boolean condition, R column, Collection<?> value) |in("age",{1,2,3})--->age in (1,2,3) 
notIn |字段 NOT IN (value.get(0), value.get(1), ...) <br><br>字段 NOT IN (v0, v1, ...) |notIn(R column, Collection<?> value) <br>notIn(boolean condition, R column, Collection<?> value) <br><br>notIn(R column, Object... values) <br>notIn(boolean condition, R column, Object... values) |notIn("age",{1,2,3}) --->age not in (1,2,3) <br><br>notIn("age", 1, 2, 3)---> age not in (1,2,3) 
inSql |字段 IN ( sql语句 ) |inSql(R column, String inValue) <br>inSql(boolean condition, R column, String inValue) |inSql("age", "1,2,3,4,5,6") ---> age in (1,2,3,4,5,6) <br><br>inSql("id", "select id from table where id < 3") ---> id in (select id from table where id < 3) 
notInSql |字段 NOT IN ( sql语句 ) |notInSql(R column, String inValue) <br>notInSql(boolean condition, R column, String inValue) |notInSql("age", "1,2,3,4,5,6") ---> age not in (1,2,3,4,5,6) <br><br>notInSql("id", "select id from table where id < 3") ---> id not in (select id from table where id < 3) 
groupBy |分组：GROUP BY 字段, ... |groupBy(R... columns) <br>groupBy(boolean condition, R... columns) |groupBy("id", "name") --->group by id,name 
orderByAsc |排序：ORDER BY 字段, ... ASC |orderByAsc(R... columns) <br>orderByAsc(boolean condition, R... columns) |orderByAsc("id", "name") ---> order by id ASC,name ASC 
orderByDesc |排序：ORDER BY 字段, ... DESC |orderByDesc(R... columns) <br>orderByDesc(boolean condition, R... columns) |orderByDesc("id", "name") ---> order by id DESC,name DESC 
orderBy |排序：ORDER BY 字段, ... |orderBy(boolean condition, boolean isAsc, R... columns) |orderBy(true, true, "id", "name") --->order by id ASC,name ASC 
having |HAVING ( sql语句 ) |having(String sqlHaving, Object... params) <br>having(boolean condition, String sqlHaving, Object... params) |having("sum(age) > 10") ---> having sum(age) > 10  <br><br>having("sum(age) > {0}", 11)---> having sum(age) > 11
func |func 方法(主要方便在出现if...else下调用不同方法能不断链) |func(Consumer<Children> consumer) <br>func(boolean condition, Consumer<Children> consumer) |func(i -> if(true) {i.eq("id", 1)} else {i.ne("id", 1)}) 
or |拼接 OR <br>主动调用or表示紧接着下一个方法不是用and连接<br>!(不调用or则默认为使用and连接) <br><br>OR 嵌套 |or() <br>or(boolean condition) <br><br>or(Consumer\<Param> consumer) <br>or(boolean condition, Consumer\<Param> consumer) |eq("id",1).or().eq("name","老王") ---> id = 1 or name = '老王'  <br><br>or(i -> i.eq("name", "李白").ne("status", "活着")) ---> or (name = '李白' and status <> '活着')
and |AND 嵌套 |and(Consumer\<Param> consumer) <br>and(boolean condition, Consumer\<Param> consumer) |and(i -> i.eq("name", "李白").ne("status", "活着")) ---> and (name = '李白' and status <> '活着') 
nested |正常嵌套 不带 AND 或者 OR |nested(Consumer\<Param> consumer) <br>nested(boolean condition, Consumer\<Param> consumer) |nested(i -> i.eq("name", "李白").ne("status", "活着")) ---> (name = '李白' and status <> '活着') 
apply |拼接 sql |apply(String applySql, Object... params) <br>apply(boolean condition, String applySql, Object... params) |apply("id = 1") ---> id = 1 <br>apply("date_format(dateColumn,'%Y-%m-%d') = '2008-08-08'") ---> date_format(dateColumn,'%Y-%m-%d') = '2008-08-08'") 
last |无视优化规则直接拼接到 sql 的最后 <br>只能调用一次,多次调用以最后一次为准 有sql注入的风险,请谨慎使用|last(String lastSql) <br>last(boolean condition, String lastSql) |last("limit 1") 
exists |拼接 EXISTS ( sql语句 ) |exists(String existsSql) <br>exists(boolean condition, String existsSql) |exists("select id from table where age = 1") ---> exists (select id from table where age = 1) 
notExists |拼接 NOT EXISTS ( sql语句 ) |notExists(String notExistsSql) <br>notExists(boolean condition, String notExistsSql) |notExists("select id from table where age = 1") ---> not exists (select id from table where age = 1)
 
* QueryWrapper

    继承自 AbstractWrapper
    **新增方法**
    * select  
        * 功能  
        设置查询字段
        
        * 语法
        ```text
        select(String... sqlSelect)
        select(Predicate<TableFieldInfo> predicate)
        select(Class<T> entityClass, Predicate<TableFieldInfo> predicate)
        ```
        * 示例
        ```text
        select("id", "name", "age")
        select(i -> i.getProperty().startsWith("test
        ```

* UpdateWrapper


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