package com.java.ref.www;

import com.java.ref.bean.Dao;
import com.java.ref.bean.Person;
import com.java.ref.bean.Web;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

public class BeansAutoTest {
    private ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans-auto.xml");


    @Test
    public void test1() {
        Dao dao1 = (Dao) ctx.getBean("dao2");
        Dao dao2 = (Dao) ctx.getBean("dao2");
        System.out.println(dao1 == dao2);  // false
    }

    @Test
    public void testOutFileResource() {
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        System.out.println(dataSource);
    }

    @Test
    public void testSpEL() {
        Person p1 = (Person) ctx.getBean("person1");
        Person p2 = (Person) ctx.getBean("person2");
        System.out.println(p1);
        System.out.println(p2);  // Person{name='商鞅', fatherName='范仲淹'}
    }
}
