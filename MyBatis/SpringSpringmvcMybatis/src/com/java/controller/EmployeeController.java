package com.java.controller;

import com.java.bean.Employee;
import com.java.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/emps")
    public String allEmps(Map<String, Object> map) {
        List<Employee> employees = employeeService.getAllEmps();
        map.put("emps", employees);
        return "employeeList";
    }
}
