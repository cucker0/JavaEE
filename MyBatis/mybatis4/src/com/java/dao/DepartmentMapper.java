package com.java.dao;

import com.java.bean.Department;
import com.java.bean.DepartmentX;

public interface DepartmentMapper {
    Department getDepartmentById(Long id);

    DepartmentX getDepartmentXById(Long id);

    DepartmentX getDepartmentXStepById(Long id);

    DepartmentX getDepartmentXStepById2(Long id);

    DepartmentX getDepartmentXStepById3(Long id);
}
