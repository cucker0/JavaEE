package com.java.typehandler;

import com.java.bean.EmployeeStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 员工状态不枚举类的类型处理
 *  可以实现TypeHandler接口
 *  或继承 BaseTypeHandler
 *
 */
public class EmployeeStatusTypeHandler implements TypeHandler<EmployeeStatus> {

    /**
     * 定义数据如何保存到数据库中
     *
     * @param preparedStatement
     * @param i  参数的索引位置
     * @param employeeStatus  传入的参数对象
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, EmployeeStatus employeeStatus, JdbcType jdbcType) throws SQLException {
        // preparedStatement相应的参数使用 employeeStatus的code属性填充
        preparedStatement.setString(i, employeeStatus.getCode().toString());
    }

    /*
    * 查询员工信息时，根据员工状态值返回相应的枚举类对象
    * */
    @Override
    public EmployeeStatus getResult(ResultSet resultSet, String s) throws SQLException {
        int code = resultSet.getInt(s);
        return EmployeeStatus.getInstanceByCode(code);
    }

    // 同上
    @Override
    public EmployeeStatus getResult(ResultSet resultSet, int i) throws SQLException {
        int code = resultSet.getInt(i);
        return EmployeeStatus.getInstanceByCode(code);
    }

    // 同上
    @Override
    public EmployeeStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
        int code = callableStatement.getInt(i);
        return EmployeeStatus.getInstanceByCode(code);
    }
}
