package com.java.jdbc;

import com.java.jdbc.bean.Department;
import com.java.jdbc.bean.Employee;
import com.java.jdbc.dao.DepartmentDao;
import com.java.jdbc.dao.EmployeeDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class daoTest {
    private ApplicationContext ctx;
    private EmployeeDao employeeDao;
    private DepartmentDao departmentDao;

    {
        ctx = new ClassPathXmlApplicationContext("jdbc.xml");
        employeeDao = ctx.getBean(EmployeeDao.class);
        departmentDao = ctx.getBean(DepartmentDao.class);
    }

    @Test
    public void testEmployeeDao() {
        Employee em = employeeDao.getEmployeeById(3);
        System.out.println(em);
    }

    @Test
    public void testDepartmentDao() {
        Department department = departmentDao.getDepartmentById(1);
        System.out.println(department);
    }

}
