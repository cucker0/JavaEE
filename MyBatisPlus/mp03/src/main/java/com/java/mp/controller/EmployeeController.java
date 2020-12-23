package com.java.mp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.java.mp.bean.Employee;
import com.java.mp.service.EmployeeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.BeanNameViewResolver;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hanxiao2100@qq.com
 * @since 2020-12-23
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    // 以下为手工写的代码  --start
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/login")
    public Boolean login(@Param(value = "id") Integer id, @Param(value = "lastName") String lastName) {
        Employee employee = employeeService.getOne(
                new QueryWrapper<Employee>()
                        .eq("id", id)
                        .eq("last_name", lastName)
        );
        return employee != null;
    }
    // 以下为手工写的代码 --end
}

