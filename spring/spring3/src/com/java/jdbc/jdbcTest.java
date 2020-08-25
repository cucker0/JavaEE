package com.java.jdbc;

import com.java.jdbc.bean.Employee;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class jdbcTest {
    private ApplicationContext ctx;
    private JdbcTemplate jdbcTemplate;

    {
        ctx = new ClassPathXmlApplicationContext("jdbc.xml");
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

    // 执行insert、update、delete
    @Test
    public void testUpdate() {
        String sql = "UPDATE employee SET age = ? WHERE `name`= ? ;";
        // count为执行sql的影响的行数
        int count = jdbcTemplate.update(sql, 20, "lilei");
        System.out.println(count);  // 1
    }

    // 批量更新操作，insert、update、delete
    // int[] batchUpdate(String sql, List<Object[]> batchArgs)
    @Test
    public void testBatch() {
        String sql = "INSERT INTO employee (`name`, age, department_id) VALUES (?, ?, ?)";
        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{"刘备", 23, 2});
        batchArgs.add(new Object[]{"张飞", 20, 2});
        batchArgs.add(new Object[]{"关羽", 18, 2});
        // ints为每个sql执行影响的行数组成的数组
        // 参数batchArgs要求类型为List<Object[]>
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
        for (int anInt : ints) {
            System.out.println(anInt);
            // 1
            // 1
            // 1
        }
    }


    /**
     * 从数据库查询一条记录，并得到一个对应的bean对象
     * RowMapper指定如何映射结果集的行
     * 使用sql中的别名完成列名说类属性名的映射
     * 不支持级联属性，JdbcTemplate只是一个JDBC小工具，不是ORM框架
     */
    @Test
    public void testQueryForObject() {
        String sql = "SELECT id, `name`, age, department_id as 'department.id' FROM employee WHERE id = ?;";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, 1);
        System.out.println(employee);  // Employee{id=1, name='lilei', age=20, department=null}
    }

    /**
     * 查询实体类的集合
     *
     * 方法：jdbcTemplate.query
     */
    @Test
    public void testQueryForList() {
        String sql = "SELECT id, `name`, age, department_id as 'department.id' FROM employee;";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        List<Employee> employees = jdbcTemplate.query(sql, rowMapper);
        System.out.println(employees);
    }

    /**
     * 查询单个列的值，如做统计查询
     */
    @Test
    public void testQueryForOneValue() {
        String sql = "SELECT COUNT(*) FROM employee;";
        long count = jdbcTemplate.queryForObject(sql, Long.class);
        System.out.println("员工数：" + count);
    }
}
