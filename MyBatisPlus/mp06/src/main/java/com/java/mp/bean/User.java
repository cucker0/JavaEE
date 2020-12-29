package com.java.mp.bean;

import com.baomidou.mybatisplus.annotation.TableLogic;

public class User {
    private Long id;
    private String username;
    private Integer gender;
    private String phone;
    // 逻辑删除标记，1:逻辑以删除，0:逻辑未删除
    @TableLogic
    private Integer deleted;

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
