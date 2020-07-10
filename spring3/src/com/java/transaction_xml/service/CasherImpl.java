package com.java.transaction_xml.service;

import com.java.transaction_xml.service.Casher;
import com.java.transaction_xml.service.BookShopService;

import java.util.List;
import java.util.Map;

// 事务的传播
public class CasherImpl implements Casher {
    private BookShopService bookShopService;

    // xml配置需要添加setter方法
    public void setBookShopService(BookShopService bookShopService) {
        this.bookShopService = bookShopService;
    }

    @Override
    public void checkOut(String accountId, List<Map<String, Object>> books) {
        for (Map<String, Object> book : books) {
            // 调用的bookShopService有开启事务的
            bookShopService.purchase(accountId, (String) book.get("sn"), (Integer) book.get("num"));
        }
    }
}
