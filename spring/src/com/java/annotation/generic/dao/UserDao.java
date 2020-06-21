package com.java.annotation.generic.dao;

import com.java.annotation.generic.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao<User> {

}
