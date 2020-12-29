package com.java.mp;

import com.java.mp.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SqlInjectorTest {
    private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeMapper employeeMapper = ctx.getBean(EmployeeMapper.class);


    /**
     * 测试自定义SQL注入的全局操作方法
     */
    @Test
    public void testMySqlInjector() {
        Long aLong = employeeMapper.deleteAll();
        System.out.println(aLong);
    }
}
