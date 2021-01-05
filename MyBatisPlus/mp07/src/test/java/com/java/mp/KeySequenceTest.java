package com.java.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.java.mp.bean.User;
import com.java.mp.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

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
        user.setUsername("于洋2");
        user.setGender(1);
        user.setPhone("13199114455");
        int line = userMapper.insert(user);
        /*
        Preparing: SELECT seq_tbl_user.NEXTVAL FROM DUAL
        Preparing: INSERT INTO tbl_user ( id, username, gender, phone ) VALUES ( ?, ?, ?, ? )
        Parameters: 21(Long), 于洋2(String), 1(Integer), 13199114455(String)
         */
    }

    @Test
    public void testSelect() {
        List<User> users = userMapper.selectList(
                new QueryWrapper<User>()
                        .like("username", "于洋")
        );
        System.out.println(users);
    }

}
