package com.java.springbootdatamybatis.controller;

import com.java.springbootdatamybatis.bean.Employee;
import com.java.springbootdatamybatis.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id) {
        return employeeMapper.getEmployeeById(id);
    }

    @GetMapping("emps")
    public List<Employee> getAllEmployees() {
        return employeeMapper.getAllEmployees();
    }

    @GetMapping("/employee")
    public String index() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Employee</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h3>Employee Opration</h3>\n" +
                "    <div>\n" +
                "        <li>\n" +
                "            <a href=\"/emp/1\">查询id为1的员工</a>\n" +
                "        </li>\n" +
                "        <li>\n" +
                "            <a href=\"/emps\">列出所有员工信息</a>\n" +
                "        </li>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }
}
