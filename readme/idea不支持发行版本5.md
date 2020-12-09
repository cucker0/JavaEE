idea不支持发行版本5
==


## 问题
```
Error:java: 错误: 不支持发行版本 5

今天在maven项目里面运行的时候，出现error:java:不支持发行版本5报错，
原因是项目运行的时候，jdk版本与本地的版本不一致，我的本地jdk版本是12，
因此到IDEA--->File--->project structure，检查了一下project和moduels中jdk版本与本地对比，
发现不一致，于是修改为jdk12(你修改为与你本地的版本一致即可)
```

## 解决方法
* 方法1
    * Project Structru指设置
        * 指定Project SDK版本
            ![](../images/idea/java_error02.png)
        * modules->Sources->Language level指定版本
            ![](../images/idea/java_error03.png)
        * modules->Dependencies->Module SDK指定版本
            ![](../images/idea/java_error04.png)
    * Settings
        * Java Compiler指定版本
            ![](../images/idea/java_error05.png)

* 方法2
    
    在pom.xml文件的`<project></project>`块内添加如下内容
    ```
        <build>
            <plugins>
                <!-- 设置jdk版本 -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <configuration>
                        <source>12</source>
                        <target>12</target>
                        <encoding>utf-8</encoding>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    ```
    idea提示Maven projects need to be imported时，点击Import Changes
    