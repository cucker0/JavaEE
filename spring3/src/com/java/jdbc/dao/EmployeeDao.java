package com.java.jdbc.dao;

import com.java.jdbc.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


// 推荐@Autowired自动装配使JdbcTemplate作为Dao类的成员变量
@Repository
public class EmployeeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Employee getEmployeeById(Integer id) {
        String sql = "SELECT id, `name`, age, department_id as 'department.id' FROM employee WHERE id = ?";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, id);
        return employee;
    }
}
