package com.java.dao;

import com.java.bean.Employee;

public interface EmployeeMapper {
    Employee getEmployeeById(Long id);

    Long addEmployee(Employee employee);

    Employee getEmployeeById2(Long id);

    Long addEmployee2(Employee employee);
}