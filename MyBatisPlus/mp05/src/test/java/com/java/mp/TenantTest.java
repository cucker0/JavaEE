package com.java.mp;

import com.java.mp.bean.Orders;
import com.java.mp.config.ApiContext;
import com.java.mp.mapper.OrdersMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.List;

public class TenantTest {
    private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    // @Resource
    // @Autowired
    private OrdersMapper ordersMapper = ctx.getBean(OrdersMapper.class);
    // @Autowired
    private ApiContext apiContext = ctx.getBean(ApiContext.class);

    @Before
    public void before() {
        apiContext.setCurrentTenantId(1L);
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
