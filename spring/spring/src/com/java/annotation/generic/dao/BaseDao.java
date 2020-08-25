package com.java.annotation.generic.dao;

public abstract class BaseDao<T> {

    public void save(T entity) {
        System.out.println("BaseDao: save " + entity);
    }
}
