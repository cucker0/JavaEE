package com.java.service;


import com.java.properties.TalkProperties;

public class TalkService {
    private TalkProperties properties;

    public TalkService() {}

    public TalkProperties getProperties() {
        return properties;
    }

    public void setProperties(TalkProperties properties) {
        this.properties = properties;
    }

    public String say() {
        return String.format("'%s' to [ %s ]", properties.getSayWhat(), properties.getToWho());
    }
}
