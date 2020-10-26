JDBCType
==

## JDBCType
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

* jdbcType
    >通常需要在某种特定的条件小被设置
    * 当数据为插入的数据为null，有些数据库不能识别mybatis对null的默认处理。比如Oracle（报错）
        ```text
        JdbcType为OTHER：无效的类型，因为mybatis对所有的null都映射的是原生Jdbc的OTHER类型，oracle不能正确处理;
        mybatis默认的jdbcType=OTHER
        ```
    
    * 示指定jdbcType时可能会报
        ```text
        #4 with JdbcType OTHER . Try setting a different JdbcType for this parameter or a different jdbcTypeForNull configuration property. 
        Cause: java.sql.SQLException: 无效的列类型: 1111
        ```