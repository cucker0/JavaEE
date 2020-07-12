package test.com.java.spring.hibernate;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

public class Main {
    private ApplicationContext ctx;

    {
        ctx = new ClassPathXmlApplicationContext("spring.xml");
    }

    @Test
    public void testDataSource() {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource);
    }

/*
Hibernate:

    create table t_account (
       id integer not null auto_increment,
        username varchar(255),
        password varchar(255),
        balance double precision,
        primary key (id)
    ) engine=InnoDB
Hibernate:

    create table t_book (
       id integer not null auto_increment,
        sn varchar(255),
        bookName varchar(255),
        price double precision,
        stock integer,
        primary key (id)
    ) engine=InnoDB

 */

}
