package com.java.ref.bean;

import com.java.first.bean.Car;
import com.java.first.bean.User;
import org.springframework.beans.factory.FactoryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 工厂方法注入 bean实例，很少用
 *
 * 实现FactoryBean接口
 */
public class UserBean implements FactoryBean<User> {

    /**
     * 返回bean实例
     *
     * @return
     * @throws Exception
     */
    @Override
    public User getObject() throws Exception {
        User user = new User("蒋大为", 21, 1);
        List<Car> list = new ArrayList<>();
        list.add(new Car("凯迪拉克", "美国", 220, 30.68F));
        list.add(new Car("小鹏", "广州", 200, 12.00F));
        user.setCars(list);
        return user;
    }

    /**
     * 返回bean的类型
     *
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    /**
     * 控制 返回的bean是否为 单例
     * @return
     */
    @Override
    public boolean isSingleton() {
        return false;
    }
}
