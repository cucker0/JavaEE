package com.java.springbootconfig3;

import com.java.springbootconfig3.service.HelloService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootConfig3ApplicationTests {
    @Autowired
    ApplicationContext ioc;

    @Test
    void testHelloService() {
        HelloService helloService = (HelloService) ioc.getBean("helloService");
        System.out.println(helloService.sayHello());
    }

}
