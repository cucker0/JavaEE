package com.java.mp;

import com.java.mp.bean.User;
import com.java.mp.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LogicDeleteTest {
    private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    private UserMapper userMapper = ctx.getBean(UserMapper.class);

    /**
     * 逻辑删除
     */
    @Test
    public void testLogicDelete() {
        int line = userMapper.deleteById(1);
        /*
        Preparing: UPDATE tbl_user SET deleted=1 WHERE id=? AND deleted=0
         */
        System.out.println(line);
    }

    @Test
    public void testSelect() {
        User user = userMapper.selectById(1);
        /*
        Preparing: SELECT id,username,gender,phone,deleted FROM tbl_user WHERE id=? AND deleted=0
        Parameters: 1(Integer)
        Total: 0
        其实记录在数据时存在的，只是 deleted字段值为 1
         */
        System.out.println(user);  // null
    }
}
