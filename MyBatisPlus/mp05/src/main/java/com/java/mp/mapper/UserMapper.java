package com.java.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.mp.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    /**
     * 自定义SQL：默认也会增加多租户条件
     *
     */

    Integer myCount();

    List<User> getUser(@Param("username") String username);

    List<User> getAddr(@Param("address") String address);
}
