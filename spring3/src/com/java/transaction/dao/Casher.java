package com.java.transaction.dao;

import java.util.List;
import java.util.Map;

public interface Casher {
    // 收银员进行结算
    void checkOut(String accountId, List<Map<String, Object>> books);
}
