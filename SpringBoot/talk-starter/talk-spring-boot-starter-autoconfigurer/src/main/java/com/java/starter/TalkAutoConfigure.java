package com.java.starter;

import com.java.starter.properties.TalkProperties;
import com.java.starter.service.TalkService;
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
public class TalkAutoConfigure {
    @Autowired
    private TalkProperties talkProperties;

    @Bean
    public TalkService talkService() {
        TalkService service = new TalkService();
        service.setProperties(talkProperties);
        return service;
    }
}
