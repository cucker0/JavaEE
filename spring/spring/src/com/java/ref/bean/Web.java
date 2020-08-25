package com.java.ref.bean;

public class Web {
    private Service service;

    // 方法
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void addUser() {
        System.out.println("添加用户");
        // 获取用户名、sex、age等...
        service.save();
    }
}
