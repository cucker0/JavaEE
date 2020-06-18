package com.java.ref.bean;

public class Dao {
    private String dataSource = "dbcp";

    // 构造器
    public Dao() {}

    // 方法
    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public void update() {
        System.out.println("update by " + dataSource);
    }
}
