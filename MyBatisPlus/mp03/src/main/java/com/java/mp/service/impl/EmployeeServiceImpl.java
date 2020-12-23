package com.java.mp.service.impl;

import com.java.mp.bean.Employee;
import com.java.mp.mapper.EmployeeMapper;
import com.java.mp.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hanxiao2100@qq.com
 * @since 2020-12-23
 *
 *
 * 不用进行mapper注入
 * 继承 ServiceImpl 后，
 * 1. 在ServiceImpl中已经完成Mapper对象的注入,直接在EmployeeServiceImpl中进行使用
 * 2. 在ServiceImpl中也帮我们提供了常用的CRUD方法， 基本的一些CRUD方法在Service中不需要我们自己定义
 *
 *
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
