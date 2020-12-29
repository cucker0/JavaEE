package com.java.mp.metaObjectHandler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * insert操作为空时，自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object fieldVal = getFieldValByName("age", metaObject);
        if (fieldVal == null) {
            System.out.println("== insert field is null, auto fill ==");
            // Employee年龄默认为 18
            setFieldValByName("age", 18, metaObject);
        }
    }

    /**
     * update操作为空时，自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Object fieldVal = getFieldValByName("age", metaObject);
        if (fieldVal == null) {
            System.out.println("== update field is null, auto fill ==");
            // Employee年龄默认为 18
            setFieldValByName("age", 18, metaObject);
        }
    }
}
