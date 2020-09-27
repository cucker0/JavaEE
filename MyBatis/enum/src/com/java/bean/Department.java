package com.java.bean;

import java.io.Serializable;

public class Department {
    private Long id;
    private String depName;

    // 构造器
    public Department() {}

    public Department(Long id, String depName) {
        this.id = id;
        this.depName = depName;
    }

    // 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", depName='" + depName + '\'' +
                '}';
    }
}
