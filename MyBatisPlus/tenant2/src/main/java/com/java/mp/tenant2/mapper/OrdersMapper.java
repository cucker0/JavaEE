package com.java.mp.tenant2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.mp.tenant2.bean.Orders;
import org.springframework.stereotype.Component;

@Component
public interface OrdersMapper extends BaseMapper<Orders> {
    Integer myCount();
}
