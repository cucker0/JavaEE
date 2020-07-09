package com.java.transaction.service;

import com.java.transaction.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {
    @Autowired
    private BookDao bookDao;

    /**
     * 假设用户每次选一种图书就去结账
     *
     * @Transactional 开启事务
     *
     * @param accountId 账户ID
     * @param bookSn    图书SN
     * @param bookNum   选购此图书的数量
     */
    // @Transactional(propagation = Propagation.REQUIRED)  // 开启事务，相当于@Transactional，即有事务即可，当父方法有事务时，不再开启自己的事务
    @Transactional(propagation = Propagation.REQUIRES_NEW)  // 开启使用自己的事务，不管父方法是否有事务
    @Override
    public void purchase(String accountId, String bookSn, int bookNum) {
        // 计算图书的总价
        double totalPrice = bookDao.getBookPriceBySn(bookSn) * bookNum;
        // 更新图书的库存
        bookDao.reduceBookStockBySn(bookSn, bookNum);
        // 更新账户余额
        bookDao.reduceAccountBalance(accountId, totalPrice);
    }
}
