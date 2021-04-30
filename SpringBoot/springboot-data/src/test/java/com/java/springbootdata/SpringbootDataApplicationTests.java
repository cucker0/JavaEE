package com.java.springbootdata;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class SpringbootDataApplicationTests {
    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println("== class ==: " + dataSource.getClass());
        /*
        输出结果：
        == class ==: class com.zaxxer.hikari.HikariDataSource
         */

        Connection connection = dataSource.getConnection();
        System.out.println("== connection ==: " + connection);
        /*
        输出结果：
        == connection ==: HikariProxyConnection@2062667890 wrapping com.mysql.cj.jdbc.ConnectionImpl@27ffd9f8
         */

        connection.close();
    }

}
