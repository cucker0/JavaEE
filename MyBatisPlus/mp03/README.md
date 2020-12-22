mp03
==

## Mybatis-plus代码自动生成
逆向工程，由数据库表生成java代码：

* 添加maven依赖

    pom.xml
    ```xml
    <project>
        <properties>
            <!-- define component version -->
            <!-- slf4j -->
            <slf4j.version>1.7.30</slf4j.version>
            <!-- velocity -->
            <velocity.version>2.2</velocity.version>
        </properties>
        
        <dependencies>
            ...
            <!-- Apache velocity -->
            <dependency>
                <groupId>org.apache.velocity</groupId>
                <artifactId>velocity-engine-core</artifactId>
                <version>${velocity.version}</version>
            </dependency>
            <dependency>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-log4j12</artifactId>
                <version>${slf4j.version}</version>
            </dependency>
            ...
        </dependencies>
    </project>
    ```
