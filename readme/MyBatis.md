MyBatis
==

## MyBatis概述
```text
MyBatis使用介绍：https://mybatis.org/mybatis-3/

项目地址：https://github.com/mybatis/mybatis-3
```


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