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
 * @since 2020-12-24
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
