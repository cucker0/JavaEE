package com.java.ref.www;

import com.java.first.bean.User;
import com.java.ref.bean.Dao;
import com.java.ref.bean.Person;
import com.java.ref.bean.Web;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.text.DateFormat;
import java.util.Date;

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

    // 测试实例工厂方法
    @Test
    public void testDateFormat() {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
        System.out.println("dateFormat:");
        System.out.println(dateFormat);

        DateFormat df = (DateFormat) ctx.getBean("dateFormat");
        System.out.println("df:");
        System.out.println(df.format(new Date()));

        Date date = (Date) ctx.getBean("datetime");
        System.out.println("date:");
        System.out.println(date);  // Sun Feb 23 06:06:06 CST 2020
    }

    @Test
    public void testUserBean() {
        User user = (User) ctx.getBean("user51");
        User user2 = (User) ctx.getBean("user51");
        System.out.println(user);
        System.out.println(user == user2);
    }
}
