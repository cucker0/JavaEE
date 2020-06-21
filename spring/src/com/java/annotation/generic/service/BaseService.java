package com.java.annotation.generic.service;

import com.java.annotation.generic.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService<T> {
    @Autowired
    private BaseDao<T> dao;

    public void add(T entity) {
        System.out.println("BaseService: add new by " + dao);
        dao.save(entity);
    }
}
