package com.java.springbootdatamybatis.mapper;

import com.java.springbootdatamybatis.bean.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmployeeMapper {
    @Select("SELECT id, last_name, gender, email, dep_id FROM t_employee WHERE id=#{id}")
    public Employee getEmployeeById(Integer id);

    @Insert("INSERT INTO t_employee (last_name, gender, email, dep_id) VALUES (#{lastName}, #{gender}, #{email}, #{depId});")
    public void insertEmployee(Employee e);

    @Select("SELECT id, last_name, gender, email, dep_id FROM t_employee LIMIT 0, 1000;")
    public List<Employee> getAllEmployees();
}
