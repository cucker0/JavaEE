package com.java.mp.tenant2;

import com.java.mp.tenant2.bean.Orders;
import com.java.mp.tenant2.config.ApiContext;
import com.java.mp.tenant2.mapper.OrdersMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class Tenant2ApplicationTests {
    // @Resource
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private ApiContext apiContext;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void before() {
        apiContext.setCurrentTenantId(2L);
    }

    @Test
    public void manualSqlTenantFilterTest() {
        System.out.println("======>" + ordersMapper);
        System.out.println(ordersMapper.myCount());
    }

    @Test
    public void select() {
        List<Orders> orders = ordersMapper.selectList(null);
        System.out.println(orders);
    }

}
