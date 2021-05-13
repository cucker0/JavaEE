package com.java.springboot.mapper;

import com.java.springboot.bean.Department;

import java.util.List;

public interface DepartmentMapper {
    public Department getDepartmentById(Integer id);

    public int deleteDepartmentById(Integer id);

    public int insertDepartment(Department department);

    public int updateDepartment(Department department);

    public List<Department> getAllDepartments();
}
