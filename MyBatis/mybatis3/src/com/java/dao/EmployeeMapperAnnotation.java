package com.java.dao;

import com.java.bean.Employee;
import org.apache.ibatis.annotations.Select;

// 通过注解把sql语句写在接口方法上
public interface EmployeeMapperAnnotation {
    @Select("SELECT id, last_name lastName, gender, email FROM t_employee WHERE id=#{id}")
    Employee getEmpById(Long id);
}
