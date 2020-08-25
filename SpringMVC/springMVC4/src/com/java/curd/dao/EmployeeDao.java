package com.java.curd.dao;

import com.java.curd.bean.Employee;

import java.util.List;

public interface EmployeeDao {
    Integer addEmployee(Employee employee);

    Integer deleteEmployeeById(Long id);

    void updateEmployee(Employee employee);

    Employee queryEmployeeById(Long id);

    List<Employee> queryAllEmployees();
}
