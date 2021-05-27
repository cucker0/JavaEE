//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.boot.autoconfigure.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import javax.sql.DataSource;
import oracle.jdbc.OracleConnection;
import oracle.ucp.jdbc.PoolDataSourceImpl;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

abstract class DataSourceConfiguration {
    DataSourceConfiguration() {
    }

    protected static <T> T createDataSource(DataSourceProperties properties, Class<? extends DataSource> type) {
        return properties.initializeDataSourceBuilder().type(type).build();
    }

    // 自定义数据源类型
    // spring.datasource.type属性可以自定义数据源类型
    @Configuration(
            proxyBeanMethods = false
    )
    @ConditionalOnMissingBean({DataSource.class})
    @ConditionalOnProperty(
            name = {"spring.datasource.type"}
    )
    static class Generic {
        Generic() {
        }

        @Bean
        DataSource dataSource(DataSourceProperties properties) {
            // 使用DataSourceBuilder创建数据源，利用反射创建响应type的数据源，并且绑定相关属性
            return properties.initializeDataSourceBuilder().build();
        }
    }

    @Configuration(
            proxyBeanMethods = false
    )
    @ConditionalOnClass({PoolDataSourceImpl.class, OracleConnection.class})
    @ConditionalOnMissingBean({DataSource.class})
    @ConditionalOnProperty(
            name = {"spring.datasource.type"},
            havingValue = "oracle.ucp.jdbc.PoolDataSource",
            matchIfMissing = true
    )
    static class OracleUcp {
        OracleUcp() {
        }

        @Bean
        @ConfigurationProperties(
                prefix = "spring.datasource.oracleucp"
        )
        PoolDataSourceImpl dataSource(DataSourceProperties properties) throws SQLException {
            PoolDataSourceImpl dataSource = (PoolDataSourceImpl)DataSourceConfiguration.createDataSource(properties, PoolDataSourceImpl.class);
            dataSource.setValidateConnectionOnBorrow(true);
            if (StringUtils.hasText(properties.getName())) {
                dataSource.setConnectionPoolName(properties.getName());
            }

            return dataSource;
        }
    }

    @Configuration(
            proxyBeanMethods = false
    )
    @ConditionalOnClass({BasicDataSource.class})
    @ConditionalOnMissingBean({DataSource.class})
    @ConditionalOnProperty(
            name = {"spring.datasource.type"},
            havingValue = "org.apache.commons.dbcp2.BasicDataSource",
            matchIfMissing = true
    )
    static class Dbcp2 {
        Dbcp2() {
        }

        @Bean
        @ConfigurationProperties(
                prefix = "spring.datasource.dbcp2"
        )
        BasicDataSource dataSource(DataSourceProperties properties) {
            return (BasicDataSource)DataSourceConfiguration.createDataSource(properties, BasicDataSource.class);
        }
    }

    @Configuration(
            proxyBeanMethods = false
    )
    @ConditionalOnClass({HikariDataSource.class})
    @ConditionalOnMissingBean({DataSource.class})
    @ConditionalOnProperty(
            name = {"spring.datasource.type"},
            havingValue = "com.zaxxer.hikari.HikariDataSource",
            matchIfMissing = true
    )
    static class Hikari {
        Hikari() {
        }

        @Bean
        @ConfigurationProperties(
                prefix = "spring.datasource.hikari"
        )
        HikariDataSource dataSource(DataSourceProperties properties) {
            HikariDataSource dataSource = (HikariDataSource)DataSourceConfiguration.createDataSource(properties, HikariDataSource.class);
            if (StringUtils.hasText(properties.getName())) {
                dataSource.setPoolName(properties.getName());
            }

            return dataSource;
        }
    }

    @Configuration(
            proxyBeanMethods = false
    )
    @ConditionalOnClass({org.apache.tomcat.jdbc.pool.DataSource.class})
    @ConditionalOnMissingBean({DataSource.class})
    @ConditionalOnProperty(
            name = {"spring.datasource.type"},
            havingValue = "org.apache.tomcat.jdbc.pool.DataSource",
            matchIfMissing = true
    )
    static class Tomcat {
        Tomcat() {
        }

        @Bean
        @ConfigurationProperties(
                prefix = "spring.datasource.tomcat"
        )
        org.apache.tomcat.jdbc.pool.DataSource dataSource(DataSourceProperties properties) {
            org.apache.tomcat.jdbc.pool.DataSource dataSource = (org.apache.tomcat.jdbc.pool.DataSource)DataSourceConfiguration.createDataSource(properties, org.apache.tomcat.jdbc.pool.DataSource.class);
            DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(properties.determineUrl());
            String validationQuery = databaseDriver.getValidationQuery();
            if (validationQuery != null) {
                dataSource.setTestOnBorrow(true);
                dataSource.setValidationQuery(validationQuery);
            }

            return dataSource;
        }
    }
}
