package com.java.springboot.controller;

import com.java.springboot.bean.Department;
import com.java.springboot.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentMapper departmentMapper;

    @GetMapping("/dep/{id}")
    public Department getDepartment(@PathVariable("id") Integer id) {
        return departmentMapper.getDepartmentById(id);
    }

    @GetMapping("dep")
    public Department insertDepartment(Department d) {
        departmentMapper.insertDepartment(d);
        return d;
    }

    @GetMapping("deps")
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments();
    }
}
