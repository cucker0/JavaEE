package com.java.curd.daoImpl;

import com.java.curd.bean.Employee;
import com.java.curd.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer addEmployee(Employee employee) {
        String sql = "INSERT INTO t_employee (last_name, gender, email, salary, birth, department_id) VALUES (?, ?, ?, ?, ?, ?);";
        return jdbcTemplate.update(sql, employee.getLast_name(), employee.getGender(), employee.getEmial(),
                employee.getSalary(), employee.getBirth(), employee.getDepartment_id());
    }

    @Override
    public Integer deleteEmployeeById(Long id) {
        String sql = "DELETE FROM t_employee WHERE id = ?;";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE t_employee SET last_name = ?, gender = ?, email = ?, salary = ?, birth = ?, department_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, employee.getLast_name(), employee.getGender(), employee.getEmial(), employee.getSalary(),
                employee.getBirth(), employee.getDepartment_id(), employee.getId());
    }

    @Override
    public Employee queryEmployeeById(Long id) {
        String sql = "SELECT id, last_name, gender, email, salary, birth, department_id FROM t_employee WHERE id = ?;";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Employee> queryAllEmployees() {
        String sql = "SELECT id, last_name, gender, email, salary, birth, department_id FROM t_employee;";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        return jdbcTemplate.query(sql, rowMapper);
    }
}
