package com.java.customConversion;

import com.java.curd.bean.Employee;
import com.java.curd.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeConverterTest {
    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping("/customConversion")
    public String saveEmployee(@RequestParam("employee") Employee employee) {
        employeeDao.addEmployee(employee);
        return "redirect:/emps";
    }
}
