package com.java.transaction;

import com.java.transaction.dao.BookDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private ApplicationContext ctx;
    private BookDao bookDao;

    {
        ctx = new ClassPathXmlApplicationContext("transaction.xml");
        bookDao = ctx.getBean(BookDao.class);
    }

    @Test
    public void test1() {
        double price = bookDao.getBookPriceBySn("s2001");
        System.out.println(price);
    }
}
