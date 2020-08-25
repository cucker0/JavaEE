package com.java.transaction;

import com.java.transaction.dao.BookDao;
import com.java.transaction.service.Casher;
import com.java.transaction.service.BookShopService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private ApplicationContext ctx;
    private BookDao bookDao;
    private BookShopService bookShopService;
    private Casher casher;

    {
        ctx = new ClassPathXmlApplicationContext("transaction.xml");
        bookDao = ctx.getBean(BookDao.class);
        bookShopService = ctx.getBean(BookShopService.class);
        casher = ctx.getBean(Casher.class);
    }

    @Test
    public void test1() {
        double price = bookDao.getBookPriceBySn("s2001");
        System.out.println(price);
    }

    @Test
    public void testUpdateBookStock() {
        int stock = bookDao.getBookStockBySn("s2001");
        System.out.println("图书s2001 原库存：" + stock);
        bookDao.reduceBookStockBySn("s2001", 6);
        System.out.println("图书s2001 卖出6本");
        // bookDao.increaseBookStockBySn("s2001", 5);
        // System.out.println("图书s2001 进货5本");
        int stock2 = bookDao.getBookStockBySn("s2001");
        System.out.println("图书s2001 当前库存：" + stock2);
    }

    @Test
    public void testUpdateAccountBalance() {
        double balance = bookDao.getAccountBalanceById("6100110011");
        System.out.println("账户6100110011 原余额：￥" + balance);
        bookDao.reduceAccountBalance("6100110011", 500);
        System.out.println("账户6100110011支付了 ￥50.00");
        System.out.println("账户6100110011 原余额：￥" + bookDao.getAccountBalanceById("6100110011"));
    }

    @Test
    public void testBookShopService() {
        bookShopService.purchase("6100110011", "s2002", 3);
    }

    // 测试事务的传播
    @Test
    public void testCasher() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> book1 = new HashMap<>();
        book1.put("sn", "s2001");
        book1.put("num", 1);
        list.add(book1);
        Map<String, Object> book2 = new HashMap<>();
        book2.put("sn", "s2002");
        book2.put("num", 1);
        list.add(book2);
        casher.checkOut("6100110011", list);  // 当买第一本时账户余额是够的，买第二本时不够钱的情况下，事务的结果
    }
}
