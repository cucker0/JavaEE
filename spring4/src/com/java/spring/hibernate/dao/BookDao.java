package com.java.spring.hibernate.dao;

public interface BookDao {
    /**
     * 查询指定sn的图书的价格
     *
     * @param bookSn 图书sn
     * @return 图书价格
     */
    double getBookPriceBySn(String bookSn);

    /**
     * 查询指定sn的图书的库存
     *
     * @param bookSn 图书sn
     * @return 查询到的库数量
     */
    int getBookStockBySn(String bookSn);

    /**
     * 减少图书库存，如出货
     *
     * @param bookSn 图书sn
     * @param num 数量，要求>0
     */
    void reduceBookStockBySn(String bookSn, int num);

    /**
     *  增加图书库存，如进货
     *
     * @param bookSn 图书sn
     * @param num 数量，要求>0
     */
    void increaseBookStockBySn(String bookSn, int num);

    /**
     * 查询指定账户的余额
     *
     * @param accountId 账户ID
     * @return 账户余额
     */
    double getAccountBalanceById(int accountId);

    /**
     * 减少用户余额
     *
     * @param accountId 账号id
     * @param amount 金额，要求>0
     */
    void reduceAccountBalance(int accountId, double amount);

    /**
     * 增加用户余额
     *
     * @param accountId 账号id
     * @param amount 金额，要求>0
     */
    void increaseAccountBalance(int accountId, double amount);
}
