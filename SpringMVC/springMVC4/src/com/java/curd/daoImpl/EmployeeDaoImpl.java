package com.java.curd.daoImpl;

import com.java.curd.bean.Department;
import com.java.curd.bean.Employee;

import com.java.curd.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer addEmployee(Employee employee) {
        String sql = "INSERT INTO t_employee (last_name, gender, email, salary, birth, department_id) VALUES (?, ?, ?, ?, ?, ?);";
        return jdbcTemplate.update(sql, employee.getLastName(), employee.getGender(), employee.getEmail(),
                employee.getSalary(), employee.getBirth(), employee.getDepartment().getId());
    }

    @Override
    public Integer deleteEmployeeById(Long id) {
        String sql = "DELETE FROM t_employee WHERE id = ?;";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE t_employee SET last_name = ?, gender = ?, email = ?, salary = ?, birth = ?, department_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, employee.getLastName(), employee.getGender(), employee.getEmail(), employee.getSalary(),
                employee.getBirth(), employee.getDepartment().getId(), employee.getId());
    }

    @Override
    public Employee queryEmployeeById(Long id) {
        String sql = "SELECT id, last_name, gender, email, salary, birth, department_id as 'department.id' FROM t_employee WHERE id = ?;";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Employee> queryAllEmployees() {
        // String sql = "SELECT id, last_name, gender, email, salary, birth, department_id as 'department.id' FROM t_employee;";
        String sql = "SELECT e.id, e.last_name lastName, e.gender, e.email, e.salary, e.birth, d.id 'department.id', d.department_name 'department.departmentName' " +
                "FROM t_employee e " +
                "INNER JOIN t_department d " +
                "ON e.department_id = d.id;";
        // RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        // jdbcTemplate级联属性的处理方法，重写RowMapper方法
        RowMapper<Employee> rowMapper = new RowMapper(){
            public Employee mapRow(ResultSet rs, int rowNum) {
                Employee emp = new Employee();
                try {
                    emp.setId(rs.getLong("id"));
                    emp.setLastName(rs.getString("lastName"));
                    emp.setGender(rs.getInt("gender"));
                    emp.setEmail(rs.getString("email"));
                    emp.setSalary(rs.getDouble("salary"));
                    emp.setBirth(LocalDate.parse(rs.getString("birth"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    emp.setDepartment(new Department());
                    emp.getDepartment().setId(rs.getLong("department.id"));
                    emp.getDepartment().setDepartmentName(rs.getString("department.departmentName"));
                } catch (SQLException e) {
                    emp = null;
                }
                return emp;
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }
}
