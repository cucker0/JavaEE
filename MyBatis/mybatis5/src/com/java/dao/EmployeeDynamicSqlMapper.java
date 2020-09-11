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
}
