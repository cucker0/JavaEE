package com.java.springbootconfig2;

import com.java.springbootconfig2.bean.Company;
import com.java.springbootconfig2.bean.Employee;
import com.java.springbootconfig2.bean.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootConfig2ApplicationTests {
    @Autowired
    ApplicationContext ioc;

    @Autowired
    Person person;
    @Autowired
    Employee employee;
    @Autowired
    Company company;

    @Test
    void contextLoads() {
        System.out.println(person);
    }

    @Test
    void testEmployee() {
        System.out.println(employee);
        /*
        Employee{name='karry', age=23, salary=36000.0}
         */
    }

    @Test
    void testCompany() {
        System.out.println(company);
    }
}
