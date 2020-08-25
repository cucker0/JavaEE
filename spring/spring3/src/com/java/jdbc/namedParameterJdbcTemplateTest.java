package com.java.jdbc;

import com.java.jdbc.bean.Department;
import com.java.jdbc.bean.Employee;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.HashMap;
import java.util.Map;

public class namedParameterJdbcTemplateTest {
    private ApplicationContext ctx;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    {
        ctx = new ClassPathXmlApplicationContext("jdbc.xml");
        namedParameterJdbcTemplate = ctx.getBean(NamedParameterJdbcTemplate.class);
    }


    /**
     * 可以为参数起名字
     *
     * 优点：当参数个数比较多时，对应参数名即可，不用关心位置
     */
    @Test
    public void testUpdate() {
        String sql = "INSERT INTO employee (`name`, age, department_id) VALUES (:name, :age, :deptId)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("age", 15);
        paramMap.put("deptId", 2);
        paramMap.put("name", "关欣");
        // i 受影响的行数
        int i = namedParameterJdbcTemplate.update(sql, paramMap);
        System.out.println(i);
    }

    /**
     * 可以使用级联属性，如department.id
     */
    @Test
    public void testUpdate2() {
        String sql = "INSERT INTO employee (`name`, age, department_id) VALUES (:name, :age, :department.id)";
        Employee employee = new Employee();
        employee.setName("蒙恬");
        employee.setAge(33);
        employee.setDepartment(getDepartmentById(1));
        System.out.println(employee);
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(employee);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public Department getDepartmentById(Integer id) {
        String sql = "SELECT id, `name` FROM t_department WHERE id = :id";
        RowMapper<Department> rowMapper = new BeanPropertyRowMapper<>(Department.class);
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        Department department = namedParameterJdbcTemplate.queryForObject(sql, map, rowMapper);
        System.out.println(department);
        return department;
    }
}
