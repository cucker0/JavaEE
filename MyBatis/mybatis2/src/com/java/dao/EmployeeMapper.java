package com.java.dao;

import com.java.bean.Employee;

public interface EmployeeMapper {
    Employee getEmployeeById(Long id);
}
