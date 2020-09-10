package com.java.dao;

import com.java.bean.Employee;

import java.util.List;

public interface EmployeeDynamicSqlMapper {

    // 根据传入的employee对象，查与此对象有值的属性都符合的员工信息。多个属性之间为and关系
    // 最精确查找
    List<Employee> getEmployeesByConditionIf(Employee employee);

    // 同上
    List<Employee> getEmployeesByConditionIf2(Employee employee);


}
