package com.java.mp.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.java.mp.bean.Employee;

/**
 * Mepper接口
 * 基于Mybatis:
 *      在Mapper接口中定义CRUD相关的方法
 *      提供Mapper接口所对应的SQL映射文件、以及在SQL映入文件中编写对应的SQL语句
 * 基于MybatisPlus:
 *      XxxMapper接口继承BaseMapper接口即可，不需定义CRUD方法
 *      BaseMapper<T>：泛型就是当前Mapper接口所要操作的实体类型，即JavaBean
 *
 *
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
}
