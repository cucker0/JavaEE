package com.java.mp.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 删除全部记录
 */
public class DeleteAll extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        // 要执行的SQL语句
        String sql = "DELETE FROM " + tableInfo.getTableName();
        // 方法名，与MyBaseMapper 接口中定义的方法名要相同
        String method = "deleteAll";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        // 构造一个deleteAll的MappedStatement
        return this.addDeleteMappedStatement(mapperClass, method, sqlSource);
    }
}
