package com.java.transaction.service;

import com.java.transaction.dao.BookDao;
import com.java.transaction.exception.BookStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionTimedOutException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLException;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {
    @Autowired
    private BookDao bookDao;

    /**
     * 假设用户每次选一种图书就去结账
     *
     * @Transactional 开启事务
     *      1. 事务传播控制
     *          @Transactional(propagation = Propagation.REQUIRED)  // 开启事务，相当于@Transactional，即有事务即可，当父方法有事务时，不再开启自己的事务
     *          @Transactional(propagation = Propagation.REQUIRES_NEW)  // 开启使用自己的事务，执行时，先挂起父方法的事务
     *      2. 指定事务的隔离级别
     *          isolation：指定事务的隔离级别，可选值：Isolation.DEFAULT, Isolation.READ_UNCOMMITTED, Isolation.READ_COMMITTED, Isolation.REPEATABLE_READ, Isolation.SERIALIZABLE
     *      3. 异常回滚控制
     *          默认回滚所有的RuntimeException异常
     *          rollbackFor：必须回滚的异常，可以指定多个，{}列表里指定多个
     *          noRollbackFor： 不回滚的异常，可以指定多个，{}列表里指定多个
     *      4. readOnly指定事务是否为只读
     *          可选值：true, false
     *      5. 事务超时控制
     *          timeout: 超时时间，单位为秒，默认值为 -1，表示不超时rollbackFor与noRollbackFor的情况
     *          超时后，将强制回滚所有事务
     *
     * @param accountId 账户ID
     * @param bookSn    图书SN
     * @param bookNum   选购此图书的数量
     */
    // @Transactional(propagation = Propagation.REQUIRES_NEW)  // 开启使用自己的事务，执行时，先挂起父方法的事务
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.READ_COMMITTED,
            rollbackFor = {IOException.class, SQLException.class},
            noRollbackFor = {BookStockException.class},
            readOnly = false,
            timeout = 3)
    @Override
    public void purchase(String accountId, String bookSn, int bookNum) {
        // 计算图书的总价
        double totalPrice = bookDao.getBookPriceBySn(bookSn) * bookNum;
        // 更新图书的库存
        bookDao.reduceBookStockBySn(bookSn, bookNum);
        try {
            // Thread.sleep(2000);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
