package com.java.springbootlog3;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootLog3ApplicationTests {
    // 生成日志记录器
    private  final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void contextLoads() {
        logger.trace("trace日志：log4j2===");
        logger.debug("debug日志：log4j2===");
        logger.info("info日志：log4j2===");
        logger.warn("warn日志：log4j2===");
        logger.error("error：log4j2===");
    }
}
