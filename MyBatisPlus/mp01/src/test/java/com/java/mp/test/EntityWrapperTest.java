package com.java.mp.test;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.java.mp.bean.Employee;
import com.java.mp.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


/**
 * 条件构造器 EntityWrapper
 *      实体包装器，用于处理 sql 拼接，排序，实体参数查询等！
 *      补充说明：使用的是数据库字段，不是java属性
 * 实体包装器 EntityWrapper 继承 Wrapper
 */
public class EntityWrapperTest {
    private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeMapper employeeMapper = ioc.getBean(EmployeeMapper.class);

    /**
     * 条件禁构造器--查询操作
     */
    // 分页查询查询，年龄在18-50岁之间，且性别为男，且姓名为Tom的所有用户
    @Test
    public void testEntityWrapperSelect() {
        List<Employee> employees = employeeMapper.selectPage(new Page<Employee>(1, 3),
                new EntityWrapper<Employee>()
                        .between("age", 18, 50)
                        .eq("gender", 1)
                        .eq("last_name", "Tom")
        );
        System.out.println(employees);
    }

}
