package com.java.dao;

import com.java.bean.Employee;
import com.java.bean.EmployeeX;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeePlusMapper {
    Employee getEmployeeById(Long id);

    EmployeeX getEmployeeXById(Long id);

    EmployeeX getEmployeeXById2(Long id);

    // 分步查询
    EmployeeX getEmployeeXStepById(Long id);

    // 查询指定部门ID的员工信息
    List<EmployeeX> getEmployeeXsByDepId(Long depId);

    // 根据部门ID和部门名查员工信息，多参数传到 xml mapper文件时，会被封装一个map
    List<EmployeeX> getEmployeeXsByDepartment(@Param("depId") Long depId, @Param("depName") String depName);

    // 鉴别器，根据不同的条件封装按不同的规则封装
    EmployeeX getEmployeeXDiscriminatorById(Long id);
}
