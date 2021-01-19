mp05
==

## Spring MVC、Mybatis-plus多租户
1. [sql--sys_tenant、orders表](../../MyBatisPlus/sql/mp.sql)

2. pom.xml添加 resource，解决Mapper无法扫描的问题
    ```xml
        <build>
            <resources>
                <resource>
                    <directory>src/main/java</directory>
                    <filtering>false</filtering>
                    <includes>
                        <include>**/mapper/*.xml</include>
                    </includes>
                </resource>
                <resource>
                    <directory>src/main/resources</directory>
                    <includes>
                        <include>**/*</include>
                    </includes>
                </resource>
            </resources>
        </build>
    ```

3. [编写TenantLineHandler](./src/main/java/com/java/mp/config/MyTenantLineHandler.java)  
    实现TenantLineHandler接口

4. applicationContext.xml配置，添加tenantLineInnerInterceptor拦截器
```xml
<beans>
    <!-- TenantLineInnerInterceptor多租户拦截器插件 -->
    <bean id="myTenantLineHandler" class="com.java.mp.config.MyTenantLineHandler"/>
    <bean id="tenantLineInnerInterceptor" class="com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor">
        <property name="tenantLineHandler" ref="myTenantLineHandler"/>
    </bean>

    <!-- mybatis-plus 拦截器 -->
    <bean id="mybatisPlusInterceptor" class="com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor">
        <property name="interceptors">
            <list>
                <ref bean="tenantLineInnerInterceptor"/>
            </list>
        </property>
    </bean>
    
    <bean id="sqlSessionFactory" class="com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!-- configLocation: 指定MyBatis全局配置文件 -->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <!-- 别名处理 -->
        <property name="typeAliasesPackage" value="com.java.mp.bean"/>
        <!-- 注入MybatisPlus的全局策略配置 -->
        <property name="globalConfig" ref="globalConfig"/>

        <!-- 注册插件 -->
        <property name="plugins">
            <array>
                <ref bean="mybatisPlusInterceptor"/>
            </array>
        </property>
    </bean>
</beans>
```

5. [测试](src/test/java/com/java/mp/TenantTest.java)

