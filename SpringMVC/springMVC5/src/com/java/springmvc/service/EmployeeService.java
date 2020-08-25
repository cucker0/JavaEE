package com.java.springmvc.service;

import com.java.springmvc.bean.Employee;
import com.java.springmvc.dao.EmployeeDao;
import com.java.springmvc.handler.EmployeeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 注意：Spring IOC中的Bean不能引用SpringMVC IOC中的Bean，这与Bean的初始化先后顺序有关
     *
     */
    // @Autowired
    // private EmployeeHandler employeeHandler;

    public EmployeeService() {
        System.out.println("EmployeeService creating ...");
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.queryAllEmployees();
    }
}
