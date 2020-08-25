package com.java.curd.dao;

import com.java.curd.bean.Department;

import java.util.List;

public interface DepartmentDao {

    Integer addDepartment(Department department);

    Integer deleteDepartmentById(Long id);

    void updateDepartment(Department department);

    Department queryDepartmentById(Long id);

    List<Department> queryAllDepartments();

    boolean isValidDepartmentById(Long id);
}
