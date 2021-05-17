package com.java.springboot.listener;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class MySpringApplicationRunListener implements SpringApplicationRunListener {
    private final SpringApplication application;
    private final String[] args;

    // 需要一个有参构造器
    public MySpringApplicationRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    // SpringApplicationRunListener 启动中
    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        System.out.println("SpringApplicationRunListener ... starting() ");
        System.out.println("bootstrapContext: " + bootstrapContext);
    }

    // SpringApplicationRunListener environment环境已经准备好
    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        Object o = environment.getSystemProperties().get("os.name");
        System.out.println("SpringApplicationRunListener ... environmentPrepared() ");
        System.out.println("environment.getSystemProperties().get(\"os.name\"): " + o);
    }

    // IOC容器准备完毕
    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener ... contextPrepared() ");
        System.out.println("ConfigurableApplicationContext: " + context);
    }

    // IOC容器加载完成
    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener ... contextLoaded() ");
    }

    // SpringApplicationRunListener启动完成
    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener ... started() ");
    }

    // SpringApplicationRunListener运行中
    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener ... running() ");
    }

    // SpringApplicationRunListener启动失败
    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("SpringApplicationRunListener ... failed() ");
    }

}
