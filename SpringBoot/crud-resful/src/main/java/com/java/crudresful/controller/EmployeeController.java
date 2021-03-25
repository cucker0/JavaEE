package com.java.crudresful.controller;

import com.java.crudresful.bean.Employee;
import com.java.crudresful.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        /*
        ThymeleafProperties
        private String prefix = "classpath:/templates/";  // 前缀
        private String suffix = ".html";  // 后缀
         */
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage() {
        return "emp/add";
    }
}
