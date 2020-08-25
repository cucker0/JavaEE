package com.java.transaction_xml.daoImpl;

import com.java.transaction_xml.dao.BookDao;
import com.java.transaction_xml.exception.AccountBalanceException;
import com.java.transaction_xml.exception.BookStockException;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoImpl implements BookDao {
    private JdbcTemplate jdbcTemplate;

    // xml配置需要添加setter方法
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
            throw new BookStockException("需要的图书数量不能为负数，当前需要的图书数量为：" + num);
        }
        // 库存不够
        int stock = getBookStockBySn(bookSn);
        if (num > stock) {
            throw new BookStockException("图书库存不足，库存为：" + stock + ", 需要图书：" + num);
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
            throw new BookStockException("增加的图书数量不能为负数，当前增加的图书数量为：" + num);
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
    public void reduceAccountBalance(String accountId, double amount) {
        if (amount <= 0) {
            throw new AccountBalanceException("要支付的金额不足为负数，当前需要支付的金额为：￥" + amount);
        }
        double balance = getAccountBalanceById(accountId);
        if (amount > balance) {
            throw new AccountBalanceException("账户余额不足，当前账户余额：￥" + balance + "，需要支付的金额为：￥" + amount);
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
    public void increaseAccountBalance(String accountId, double amount) {
        if (amount <= 0) {
            throw new AccountBalanceException("增加的金额不足为负数，当前增加的金额为：￥" + amount);
        }
        String sql = "UPDATE t_account SET balance = balance + ? WHERE id = ?;";
        jdbcTemplate.update(sql, amount, accountId);
    }
}
