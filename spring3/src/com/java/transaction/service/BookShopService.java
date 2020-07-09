package com.java.transaction.service;

public interface BookShopService {
    /**
     * 假设用户每次选一种图书就去结账
     *
     * @param accountId 账户ID
     * @param bookSn 图书SN
     * @param bookNum 选购此图书的数量
     */
    void purchase(String accountId, String bookSn, int bookNum);
}
