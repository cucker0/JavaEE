package com.java.spring.hibernate.daoImpl;

import com.java.spring.hibernate.dao.BookDao;
import com.java.spring.hibernate.exception.AccountBalanceException;
import com.java.spring.hibernate.exception.BookStockException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Queue;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private SessionFactory sessionFactory;

    // 获取和当前线程绑定的Session
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 查询指定sn的图书的价格
     *
     * @param bookSn 图书sn
     * @return 图书价格
     */
    @Override
    public double getBookPriceBySn(String bookSn) {
        String hql = "SELECT price FROM TBook WHERE sn = ?0";
        Query query = getSession().createQuery(hql).setParameter(0, bookSn);
        return (double) query.uniqueResult();
    }

    /**
     * 查询指定sn的图书的库存
     *
     * @param bookSn 图书sn
     * @return 查询到的库数量
     */
    @Override
    public int getBookStockBySn(String bookSn) {
        String hsq = "SELECT stock FROM TBook WHERE sn = ?0";
        Query query = getSession().createQuery(hsq).setParameter(0, bookSn);
        return (int) query.uniqueResult();
    }

    /**
     * 减少图书库存，如出货
     *
     * @param bookSn 图书sn
     * @param num    数量，要求>0
     */
    @Override
    public void reduceBookStockBySn(String bookSn, int num) {
        if (num <= 0) {
            System.out.println("减少的图书的数量不能为负数");
            return;
        }
        int stock = getBookStockBySn(bookSn);
        if (stock < num) {
            throw new BookStockException("图书库存不足，当前图书库存：" + stock + ", 所需图书数量为：" + num);
        }
        // 给hsq参数编号
        String hsq = "UPDATE TBook SET stock = stock - ?0 WHERE sn = ?1";
        getSession().createQuery(hsq).setParameter(0, num).setParameter(1, bookSn).executeUpdate();
    }

    /**
     * 增加图书库存，如进货
     *
     * @param bookSn 图书sn
     * @param num    数量，要求>0
     */
    @Override
    public void increaseBookStockBySn(String bookSn, int num) {
        String hsq = "UPDATE TBook SET stock = stock + ?0 WHERE sn = ?1";
        getSession().createQuery(hsq).setParameter(0, num).setParameter(1, bookSn).executeUpdate();
    }

    /**
     * 查询指定账户的余额
     *
     * @param accountId 账户ID
     * @return 账户余额
     */
    @Override
    public double getAccountBalanceById(int accountId) {
        String hsq = "SELECT balance FROM TAccount WHERE id = ?0";
        Query query = getSession().createQuery(hsq).setParameter(0, accountId);
        return (double) query.uniqueResult();
    }

    /**
     * 减少用户余额
     *
     * @param accountId 账号id
     * @param amount    金额，要求>0
     */
    @Override
    public void reduceAccountBalance(int accountId, double amount) {
        if (amount <= 0) {
            System.out.println("减少的金额数量不能为负数");
            return;
        }
        double balance = getAccountBalanceById(accountId);
        if (balance < amount) {
            throw new AccountBalanceException("账户余额不足，当前账户余额：￥" + balance + ", 需要支付金额：￥" + amount);
        }
        String hsq = "UPDATE TAccount SET balance = balance - ?0 WHERE id = ?1";
        getSession().createQuery(hsq).setParameter(0, amount).setParameter(1, accountId).executeUpdate();
    }

    /**
     * 增加用户余额
     *
     * @param accountId 账号id
     * @param amount    金额，要求>0
     */
    @Override
    public void increaseAccountBalance(int accountId, double amount) {
        if (amount <= 0) {
            System.out.println("增加的金额数量不能为负数");
            return;
        }
        // sql语句给参数起别名，别名必须以:开头
        String hsq = "UPDATE TAccount SET balance = balance + :amount WHERE id = :accId";
        getSession().createQuery(hsq).setParameter("amount", amount).setParameter("accId", accountId).executeUpdate();
    }
}
