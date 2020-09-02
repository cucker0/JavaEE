package com.java.dao;

import com.java.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * mybatis 允许 增删改 直接定义 以下类型的返回值
 * Integer(int)、
 * Long(long)、
 * Boolean(boolean)、
 * void
 */
public interface EmployeeMapper {
    Employee getEmployeeById(Long id);

    void addEmployee(Employee employee);

    int updateEmployee(Employee employee);

    boolean deleteEmployeeById(Long id);

    Employee getEmployeeByIdAndLastName(Long id, String lastName);

    // 多参数，会被mybatis封装成一个map
    // @Param("keyName") 指定该参数在map中的key名
    Employee getEmployeeByIdAndLastName2(@Param("id") Long id, @Param("lastName") String lastName);

    Employee getEmployeeMap(Map<String, Object> map);
}
