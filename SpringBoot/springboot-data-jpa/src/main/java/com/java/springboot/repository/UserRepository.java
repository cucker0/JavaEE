package com.java.springboot.repository;

import com.java.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Dao接口
 *
 * 继承JpaRepository接口，完成对数据的CRUD操作
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
