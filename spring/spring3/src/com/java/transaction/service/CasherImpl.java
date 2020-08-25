package com.java.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

// 事务的传播
@Service("Casher")
public class CasherImpl implements Casher {
    @Autowired
    private BookShopService bookShopService;

    @Transactional
    @Override
    public void checkOut(String accountId, List<Map<String, Object>> books) {
        for (Map<String, Object> book : books) {
            // 调用的bookShopService有开启事务的
            bookShopService.purchase(accountId, (String) book.get("sn"), (Integer) book.get("num"));
        }
    }
}
