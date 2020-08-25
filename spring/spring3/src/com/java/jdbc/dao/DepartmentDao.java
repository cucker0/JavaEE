package com.java.jdbc.dao;

import com.java.jdbc.bean.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;


// 不推荐通过继承JdbcDaoSupport，推荐直接使用JdbcTemplate作为Dao类的成员变量
@Repository
public class DepartmentDao extends JdbcDaoSupport {
    @Autowired
    public void setDataSource2(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public Department getDepartmentById(Integer id) {
        String sql = "SELECT id, `name` FROM t_department WHERE id = ?;";
        RowMapper<Department> rowMapper = new BeanPropertyRowMapper<>(Department.class);
        Department department = this.getJdbcTemplate().queryForObject(sql, rowMapper, id);
        return department;
    }
}
