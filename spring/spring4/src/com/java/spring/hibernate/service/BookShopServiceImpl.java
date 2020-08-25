package com.java.spring.hibernate.service;

import com.java.spring.hibernate.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookShopServiceImpl implements BookShopService {

    @Autowired
    private BookDao bookDao;

    /**
     * 假设用户每次选一种图书就去结账
     *
     * @param accountId 账户ID
     * @param bookSn    图书SN
     * @param bookNum   选购此图书的数量
     */
    @Override
    public void purchase(int accountId, String bookSn, int bookNum) {
        double price = bookDao.getBookPriceBySn(bookSn);
        bookDao.reduceBookStockBySn(bookSn, bookNum);
        bookDao.reduceAccountBalance(accountId, price * bookNum);
    }
}
