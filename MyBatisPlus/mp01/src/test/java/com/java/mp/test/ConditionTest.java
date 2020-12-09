package com.java.mp.test;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.java.mp.bean.Employee;
import com.java.mp.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 条件构造器
 * Condition 继承了 Wrapper
 *
 */
public class ConditionTest {
    private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeMapper employeeMapper = ioc.getBean(EmployeeMapper.class);

    // 分页查询查询，年龄在18-50岁之间，且性别为男，且姓名为Tom的所有用户
    @Test
    public void test() {
        List<Employee> list = employeeMapper.selectPage(new Page<Employee>(1, 3),
                Condition.create()
                        .between("age", 18, 50)
                        .eq("gender", 1)
                        .eq("last_name", "Tom")
        );
        System.out.println(list);
    }
}
