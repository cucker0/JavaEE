package com.java.annotation.generic.www;

import com.java.annotation.generic.bean.Role;
import com.java.annotation.generic.bean.User;
import com.java.annotation.generic.service.RoleService;
import com.java.annotation.generic.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private ApplicationContext ctx = new ClassPathXmlApplicationContext("annotation-generic.xml");

    @Test
    public void test() {
        RoleService roleService = (RoleService) ctx.getBean("roleService");
        List<Integer> permissions = new ArrayList<>();
        permissions.add(11);
        permissions.add(12);
        permissions.add(15);
        roleService.add(new Role("admin", permissions));
        System.out.println("");

        UserService userService = (UserService) ctx.getBean("userService");
        userService.add(new User("刘彻", 70));
        /*
BaseService: add new by com.java.annotation.generic.dao.RoleDao@f0da945
BaseDao: save Role{name='admin', permissions=[11, 12, 15]}
BaseService: add new by com.java.annotation.generic.dao.UserDao@550dbc7a
BaseDao: save User{userName='刘彻', age=70}
         */
    }
}
