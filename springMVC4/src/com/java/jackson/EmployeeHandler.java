package com.java.jackson;

import com.java.curd.bean.Employee;
import com.java.curd.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("jackSonEmployeeHandler")
public class EmployeeHandler {
    @Autowired
    private EmployeeDao employeeDao;

    @ResponseBody
    @RequestMapping("/testJson")
    public List<Employee> list() {
        return employeeDao.queryAllEmployees();
    }
}
