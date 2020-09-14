package com.java.dao;

import com.java.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDynamicSqlMapper {

    // 根据传入的employee对象，查与此对象有值的属性都符合的员工信息。多个属性之间为and关系
    // 最精确查找
    List<Employee> getEmployeesByConditionIf(Employee employee);

    // 功能同上，<where>的使用
    List<Employee> getEmployeesByConditionWhere(Employee employee);

    List<Employee> getEmployeesByConditionWhere2(Employee employee);

    // <trim>自定义字符串截取规则
    List<Employee> getEpmloyeesByConditionTrim(Employee employee);

    // <choose>分支查询
    List<Employee> getEmployeesByConditionChoose(Employee employee);

    // <set> 更新
    void updateEmployeeById(Employee employee);

    void updateEmployeeById2(Employee employee);

    // 查询员工ID在指定集合中的员工信息
    // in (集合) <foreach>测试
    List<Employee> getEmployeesByIds(@Param("ids") List<Long> ids);

    List<Employee> getEmployeesByIds2(@Param("ids") List<Long> ids);

    Boolean batchInsertEmployees(@Param("employeeList") List<Employee> employeeList);

    Boolean batchInsertEmployees2(@Param("employeeList") List<Employee> employeeList);

    // oracle批量插入，方式1
    Boolean oracleBatchInsertEmployees(@Param("employeeList") List<Employee> employeeList);

    // oracle批量插入，方式2
    Boolean oracleBatchInsertEmployees2(@Param("employeeList") List<Employee> employeeList);

    // mybatis两个内置参数:_parameter, _databaseId
    // 根据姓名模糊查询员工信息
    List<Employee> getEmployeesTestInnerParamter(Employee employee);

    // 测试<bind>
    //      根据姓名模糊查询员工信息
    List<Employee> getEmployeesTestBind(Employee employee);

    // <sql> 抽取重用的sql片段，以及测试 <include>
    Employee getEmployeeByIdTestSql(Long id);

}
