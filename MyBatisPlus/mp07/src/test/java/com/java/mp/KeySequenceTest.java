package com.java.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.java.mp.bean.User;
import com.java.mp.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KeySequenceTest {
    private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    private UserMapper userMapper = ctx.getBean(UserMapper.class);

    /**
     * 测试 Oracle Key Sequence
     *
     */
    @Test
    public void testKeySequence() {
        User user = new User();
        user.setUsername("于洋");
        user.setGender(0);
        user.setPhone("13199114433");
        int line = userMapper.insert(user);

    }

    @Test
    public void testSelect() {
        User user = userMapper.selectOne(
                new QueryWrapper<User>()
                        .like("username", "于洋")
        );
        System.out.println(user);
    }

}
