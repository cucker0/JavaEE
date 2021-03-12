package com.java.springbootlog2;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SpringbootLog2ApplicationTests {
    // 生成日志记录器
    private  final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void contextLoads() {
        logger.trace("trace日志：===");
        logger.debug("debug日志：===");
        logger.info("info日志：===");
        logger.warn("warn日志：===");
        logger.error("error：===");
    }

}
