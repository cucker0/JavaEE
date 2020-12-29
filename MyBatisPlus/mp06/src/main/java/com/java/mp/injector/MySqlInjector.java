package com.java.mp.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.java.mp.methods.DeleteAll;

import java.util.List;

/**
 * Sql 注入器(自定义 SqlInjector)
 * 可定义全局操作，就如BaseMapper中通用的insert方法等
 */
public class MySqlInjector extends DefaultSqlInjector {

    /**
     * 如果只需增加方法，保留MP自带方法
     * 可以super.getMethodList(), 再add
     * @param mapperClass
     * @return
     */
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        // 注册自定义方法
        methodList.add(new DeleteAll());
        return methodList;
    }
}
