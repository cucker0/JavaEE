package com.java.transaction.daoImpl;

import com.java.transaction.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询指定sn的图书的价格
     *
     * @param bookSn 图书sn
     * @return 图书价格
     */
    @Override
    public double getBookPriceBySn(String bookSn) {
        String sql = "SELECT price  FROM t_book WHERE sn = ?;";
        return jdbcTemplate.queryForObject(sql, Double.class, bookSn);
    }

    /**
     * 查询指定sn的图书的库存
     *
     * @param bookSn 图书sn
     * @return 查询到的库数量
     */
    @Override
    public int getBookStockBySn(String bookSn) {
        String sql = "SELECT stock FROM book_stock WHERE book_sn = ?;";
        return jdbcTemplate.queryForObject(sql, Integer.class, bookSn);
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
            return;
        }
        // 库存不够
        if (num > getBookStockBySn(bookSn)) {
            return;
        }
        String sql = "UPDATE book_stock SET stock = stock - ? WHERE book_sn = ?;";
        jdbcTemplate.update(sql, num, bookSn);
    }

    /**
     * 增加图书库存，如进货
     *
     * @param bookSn 图书sn
     * @param num    数量，要求>0
     */
    @Override
    public void increaseBookStockBySn(String bookSn, int num) {
        if (num <= 0) {
            return;
        }
        String sql = "UPDATE book_stock SET stock = stock + ? WHERE book_sn = ?;";
        jdbcTemplate.update(sql, num, bookSn);
    }

    /**
     * 查询指定账户的余额
     *
     * @param accountId 账户ID
     * @return 账户余额
     */
    @Override
    public double getAccountBalanceById(String accountId) {
        String sql = "SELECT balance FROM t_account WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, Double.class, accountId);
    }

    /**
     * 减少用户余额
     *
     * @param accountId 账号id
     * @param amount    金额，要求>0
     */
    @Override
    public void reduceAccountBalance(String accountId, int amount) {
        if (amount <= 0) {
            return;
        }
        if (amount > getAccountBalanceById(accountId)) {
            return;
        }
        String sql = "UPDATE t_account SET balance = balance - ? WHERE id = ?; ";
        jdbcTemplate.update(sql, amount, accountId);
    }

    /**
     * 增加用户余额
     *
     * @param accountId 账号id
     * @param amount    金额，要求>0
     */
    @Override
    public void increaseAccountBalance(String accountId, int amount) {
        if (amount <= 0) {
            return;
        }
        String sql = "UPDATE t_account SET balance = balance + ? WHERE id = ?;";
        jdbcTemplate.update(sql, amount, accountId);
    }
}
