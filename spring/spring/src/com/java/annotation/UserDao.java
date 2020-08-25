package com.java.annotation;

import org.springframework.stereotype.Service;

@Service
public class UserDao {
    public void save() {
        System.out.println("UserDao: 保存新用户...");
    }
}
