package com.java.dao;

import com.java.bean.Employee;

/**
 * mybatis 允许 增删改 直接定义 以下类型的返回值
 *      Integer(int)、
 *      Long(long)、
 *      Boolean(boolean)、
 *      void
 *
 *
 */
public interface EmployeeMapper {
    Employee getEmployeeById(Long id);

    void addEmployee(Employee employee);

    int updateEmployee(Employee employee);

    boolean deleteEmployeeById(Long id);
}
