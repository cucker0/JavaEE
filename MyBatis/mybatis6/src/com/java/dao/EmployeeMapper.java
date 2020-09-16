package com.java.dao;

import com.java.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {

    Employee getEmpById(Long id);

    Boolean addEmp(Employee employee);

    Employee getEmpById2(Long id);

    Employee getEmpById3(Long id);

    Boolean addEmp2(Employee employee);
}
