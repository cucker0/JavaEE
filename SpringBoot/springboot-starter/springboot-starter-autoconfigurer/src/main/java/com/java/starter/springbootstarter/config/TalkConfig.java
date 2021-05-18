package com.java.starter.springbootstarter.config;

import com.java.starter.springbootstarter.properties.TalkProperties;
import com.java.starter.springbootstarter.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TalkProperties.class)
@ConditionalOnWebApplication  // web应用才生效
@ConditionalOnProperty(
        prefix = "talk",
        name = "enable",
        havingValue = "true"
)
public class TalkConfig {
    @Autowired
    private TalkProperties demoProperties;

    @Bean
    public TalkService demoService() {
        TalkService service = new TalkService();
        service.setProperties(demoProperties);
        return service;
    }
}
