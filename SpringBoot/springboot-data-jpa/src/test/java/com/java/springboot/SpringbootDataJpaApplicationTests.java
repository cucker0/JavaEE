package com.java.springboot;

import com.java.springboot.entity.User;
import com.java.springboot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootDataJpaApplicationTests {
    @Autowired
    UserRepository userRepository;

    @Test
    void contextLoads() {
        User user = new User();
        user.setLastName("zhangsan");
        user.setPasswd("sz123456");
        user.setEmail("zhangsan@qq.com");
        userRepository.save(user);
    }

}
