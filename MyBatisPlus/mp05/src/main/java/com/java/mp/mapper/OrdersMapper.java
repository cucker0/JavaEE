package com.java.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.mp.bean.Orders;
import org.springframework.stereotype.Component;

@Component
public interface OrdersMapper extends BaseMapper<Orders> {
    Integer myCount();
}
