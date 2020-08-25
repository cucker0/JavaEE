package com.java.spring.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CasherImpl implements Casher {
    @Autowired
    private BookShopService bookShopService;

    /**
     * Spring hibernate事务流程
     * 1. 在方法之前
     *      1.1 获取Session
     *      1.2 把Session与当前线程绑定，这样就可以在Dao中使用SessionFactory的getCurrentSession()方法来获取Session了
     *      1.3 开启事务
     * 2. 若方法正常结束，即没有出现异常
     *      2.1 提交事务
     *      2.2 当前线程与Session解除绑定
     *      2.3 关闭Session
     * 3. 若方法出现异常
     *      3.1 回滚事务
     *      3.2 当前线程与Session解除绑定
     *      3.3 关闭Session
     *
     *
     * @param accountId
     * @param books
     */
    @Override
    public void checkOut(int accountId, List<Map<String, Object>> books) {
        for (Map<String, Object> book : books) {
            bookShopService.purchase(accountId, (String) book.get("sn"), (Integer) book.get("num"));
        }
    }
}
