package com.java.springboot.mapper;

import com.java.springboot.bean.Employee;

import java.util.List;

public interface EmployeeMapper {
    public Employee getEmployeeById(Integer id);

    public void insertEmployee(Employee e);

    public List<Employee> getAllEmployees();
}
