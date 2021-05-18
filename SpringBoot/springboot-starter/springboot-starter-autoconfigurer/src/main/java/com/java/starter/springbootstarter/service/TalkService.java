package com.java.starter.springbootstarter.service;

import com.java.starter.springbootstarter.properties.TalkProperties;

public class TalkService {
    TalkProperties properties;

    public TalkService() {}

    public TalkProperties getProperties() {
        return properties;
    }

    public void setProperties(TalkProperties properties) {
        this.properties = properties;
    }

    public String say() {
        return String.format("'%s' to: %s", properties.getSayWhat(), properties.getToWho());
    }
}
