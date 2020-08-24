package com.java.springmvc.dao;

import com.java.springmvc.bean.Employee;

import java.util.List;

public interface EmployeeDao {
    Integer addEmployee(Employee employee);

    Integer deleteEmployeeById(Long id);

    void updateEmployee(Employee employee);

    Employee queryEmployeeById(Long id);

    List<Employee> queryAllEmployees();
}
