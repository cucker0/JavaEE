package com.java.dao;

import com.java.bean.Employee;

import java.util.List;

public interface EmployeeMapper {
    Employee getEmployeeById(Long id);

    List<Employee> getAllEmployees();

    Long addEmployee(Employee employee);
}
