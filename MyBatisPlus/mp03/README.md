mp03
==

## Mybatis-plus代码自动生成
逆向工程，由数据库表生成java代码

* 添加maven依赖

    pom.xml
    ```xml
    <project>
        <properties>
            <!-- define component version -->
            <!-- mybatis-plus -->
            <mybatis-plus.version>3.4.1</mybatis-plus.version>
            <!-- log4j2 -->
            <log4j2.version>2.13.3</log4j2.version>
            <!-- velocity -->
            <velocity.version>2.2</velocity.version>
        </properties>
        
        <dependencies>
            ...
            <!-- mybatis-plus-generator -->
                <dependency>
                    <groupId>com.baomidou</groupId>
                    <artifactId>mybatis-plus-generator</artifactId>
                    <version>${mybatis-plus.version}</version>
                </dependency>
            <!-- Apache velocity -->
            <dependency>
                <groupId>org.apache.velocity</groupId>
                <artifactId>velocity-engine-core</artifactId>
                <version>${velocity.version}</version>
            </dependency>
            <dependency>
                <groupId>org.apache.logging.log4j</groupId>
                <artifactId>log4j-slf4j-impl</artifactId>
                <version>${log4j2.version}</version>
            </dependency>
            ...
        </dependencies>
    </project>
    ```
