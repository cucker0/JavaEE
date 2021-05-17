springboot-listener
==


## 常见问题


* 自定义SpringApplicationRunListener启动报错
    ```text
    Exception in thread "main" java.lang.IllegalArgumentException: Cannot instantiate interface org.springframework.boot.SpringApplicationRunListener : com.java.springboot.listener.MySpringApplicationRunListener
        at org.springframework.boot.SpringApplication.createSpringFactoriesInstances(SpringApplication.java:480)
        at org.springframework.boot.SpringApplication.getSpringFactoriesInstances(SpringApplication.java:462)
        at org.springframework.boot.SpringApplication.getRunListeners(SpringApplication.java:450)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:329)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1340)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1329)
        at com.java.springboot.SpringbootListenerApplication.main(SpringbootListenerApplication.java:10)
    Caused by: java.lang.NoSuchMethodException: com.java.springboot.listener.MySpringApplicationRunListener.<init>(org.springframework.boot.SpringApplication,[Ljava.lang.String;)
        at java.base/java.lang.Class.getConstructor0(Class.java:3355)
        at java.base/java.lang.Class.getDeclaredConstructor(Class.java:2559)
        at org.springframework.boot.SpringApplication.createSpringFactoriesInstances(SpringApplication.java:475)
        ... 6 more
    ```
    * 解决方案
        自定义的SpringApplicationRunListener类中，添加一个如下的构造器
        ```java
            private final SpringApplication application;
            private final String[] args;
        
            // 需要一个有参构造器
            public MySpringApplicationRunListener(SpringApplication application, String[] args) {
                this.application = application;
                this.args = args;
            }
        ```