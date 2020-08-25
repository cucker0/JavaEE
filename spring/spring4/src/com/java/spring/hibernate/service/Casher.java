package com.java.spring.hibernate.service;

import java.util.List;
import java.util.Map;

public interface Casher {
    // 收银员进行结算
    void checkOut(int accountId, List<Map<String, Object>> books);
}
