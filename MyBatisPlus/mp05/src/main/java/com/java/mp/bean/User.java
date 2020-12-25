package com.java.mp.bean;

import com.baomidou.mybatisplus.annotation.TableField;

public class User {
    private Long id;
    // 租户ID
    private Long tenantId;
    private String name;
    @TableField(exist = false)
    private String address;

    public void User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", tenantId=" + tenantId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
