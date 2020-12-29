package com.java.mp.tenant;

import com.java.mp.tenant.bean.Orders;
import com.java.mp.tenant.config.ApiContext;
import com.java.mp.tenant.mapper.OrdersMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;;

// @Slf4j
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
@SpringBootTest
class TenantApplicationTests {
    @Autowired
    private ApiContext apiContext;

    @Autowired
    private OrdersMapper ordersMapper;

    @Test
    void contextLoads() {
    }

    /**
     * 模拟当前系统的多租户Id
     */
    @BeforeEach
    public void before() throws Exception {
        // 在上下文中设置当前多租户id
        apiContext.setCurrentTenantId(1L);
    }

    @Test
    public void selectList() {
        ordersMapper.selectList(null).forEach((e) -> {
            // log.info("查询数据{}", e);
            Assert.assertTrue(apiContext.getCurrentTenantId() == e.getTenantId());
            System.out.println(e);
        });
        /*
        * Preparing: SELECT id, expire_date, amount, tenant_id FROM orders WHERE tenant_id = 1
        * */
    }

    @Test
    public void testMyCount() {
        Integer count = ordersMapper.myCount();
        System.out.println(count);
    }

}
