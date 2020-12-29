package com.java.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.java.mp.bean.Employee;
import com.java.mp.mapper.EmployeeMapper;
import com.java.mp.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoFillTest {
    private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeMapper employeeMapper = ctx.getBean(EmployeeMapper.class);

    @Test
    public void testAutoFill() {
        Employee employee = new Employee();
        employee.setLastName("王勃");
        employee.setEmail("wangbo@qq.com");
        employee.setGender("1");

        int lines = employeeMapper.insert(employee);
        /*
        Preparing: INSERT INTO tbl_employee ( last_name, email, gender, age ) VALUES ( ?, ?, ?, ? )
        Parameters: 王勃(String), wangbo@qq.com(String), 1(String), 18(Integer)
        Updates: 1
         */
        System.out.println(lines);
    }

    @Test
    public void testSelect() {
        Employee employee = employeeMapper.selectOne(
                new QueryWrapper<Employee>()
                        .like("last_name", "王勃")
        );
        System.out.println(employee);
    }
}
