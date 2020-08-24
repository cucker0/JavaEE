package com.java.springmvc.handler;

import com.java.springmvc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller("/employee")
public class EmployeeHandler {
    /**
     * SpringMVC IOC中的Bean可以引用Spring IOC中的Bean
     *
     */
    @Autowired
    private EmployeeService employeeService;

    public EmployeeHandler() {
        System.out.println("EmployeeHandler creating ...");
    }

    @RequestMapping("/emps")
    public String list(Map<String, Object> map) {
        map.put("employees", employeeService.getAllEmployees());
        return "employee-list";
    }
}
