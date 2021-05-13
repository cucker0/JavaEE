package com.java.springbootdatamybatis.controller;

import com.java.springbootdatamybatis.bean.Employee;
import com.java.springbootdatamybatis.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id) {
        return employeeMapper.getEmployeeById(id);
    }

    @GetMapping("emps")
    public List<Employee> getAllEmployees() {
        return employeeMapper.getAllEmployees();
    }
}
