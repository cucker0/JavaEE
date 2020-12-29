package com.java.mp.tenant.mapper;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.mp.tenant.bean.Orders;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface OrdersMapper extends BaseMapper<Orders> {

    /**
     * 自定Wrapper, @SqlParser(filter = true)注解代表不进行SQL解析也就没有租户的附加条件。
     *
     * @return
     */
    @SqlParser(filter = true)
    @Select("SELECT count(*) FROM orders ")
    Integer myCount();
}
