package com.java.curd.daoImpl;

import com.java.curd.bean.Department;
import com.java.curd.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("departmentDao")
public class DepartmentDaoImpl implements DepartmentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer addDepartment(Department department) {
        if (department == null) {
            return null;
        }
        String sql = "INSERT INTO t_department (department_name) VALUES (?);";
        return jdbcTemplate.update(sql, department.getDepartmentName());
    }

    @Override
    public Integer deleteDepartmentById(Long id) {
        String sql = "DELETE FROM t_department WHERE id = ?;";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateDepartment(Department department) {
        String sql = "UPDATE t_department SET department_name = ? WHERE id = ?;";
        jdbcTemplate.update(sql, department.getDepartmentName(), department.getId());
    }

    @Override
    public Department queryDepartmentById(Long id) {
        String sql = "SELECT id, department_name FROM t_department WHERE id = ?;";
        RowMapper<Department> rowMapper = new BeanPropertyRowMapper<>(Department.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Department> queryAllDepartments() {
        String sql = "SELECT id, department_name FROM t_department;";
        RowMapper<Department> rowMapper = new BeanPropertyRowMapper<>(Department.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public boolean isValidDepartmentById(Long id) {
        return queryDepartmentById(id) != null;
    }
}
