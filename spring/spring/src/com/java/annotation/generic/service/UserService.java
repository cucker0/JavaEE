package com.java.annotation.generic.service;

import com.java.annotation.generic.bean.User;
import org.springframework.stereotype.Service;


/*
* 若注解没有指定 bean 的 id, 则类名第一个字母小写即为 bean 的 id,
* 指定 bean id方法： @Service("us01")
* */

@Service
public class UserService extends BaseService<User> {

}
