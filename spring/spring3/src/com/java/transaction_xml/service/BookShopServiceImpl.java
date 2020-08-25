package com.java.transaction_xml.service;

import com.java.transaction_xml.dao.BookDao;

public class BookShopServiceImpl implements BookShopService {
    private BookDao bookDao;

    // xml配置需要添加setter方法
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void purchase(String accountId, String bookSn, int bookNum) {
        // 计算图书的总价
        double totalPrice = bookDao.getBookPriceBySn(bookSn) * bookNum;
        // 更新图书的库存
        bookDao.reduceBookStockBySn(bookSn, bookNum);
        // try {
        //     // Thread.sleep(2000);
        //     Thread.sleep(5000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        // 更新账户余额
        bookDao.reduceAccountBalance(accountId, totalPrice);
        // 注意在所有的sql执行操作后的超时不生效
        // try {
        //     // Thread.sleep(2000);
        //     Thread.sleep(5000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
    }
}
