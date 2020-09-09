package com.java.dao;

import com.java.bean.Employee;
import com.java.bean.EmployeeX;

public interface EmployeePlusMapper {
    Employee getEmployeeById(Long id);

    EmployeeX getEmployeeXById(Long id);

    // 分步查询
    EmployeeX getEmployeeXStepById(Long id);
}
