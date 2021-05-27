package com.java.springbootdatamybatis.controller;

import com.java.springbootdatamybatis.bean.Department;
import com.java.springbootdatamybatis.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentMapper departmentMapper;

    @GetMapping("/dep/{id}")
    public Department getDepartment(@PathVariable("id") Integer id) {
        return departmentMapper.getDepartmentById(id);
    }

    @GetMapping("dep")
    public Department insertDepartment(Department d) {
        departmentMapper.insertDepartment(d);
        return d;
    }

    @GetMapping("deps")
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments();
    }

    @GetMapping("/department")
    public String index() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Department</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h3>Department Opration</h3>\n" +
                "    <div>\n" +
                "        <li>\n" +
                "            <a href=\"/dep/2\">查询id为2的部门</a>\n" +
                "        </li>\n" +
                "        <li>\n" +
                "            <a href=\"/dep?departmentName=党支部\">插入一个部门：党支部</a>\n" +
                "        </li>\n" +
                "        <li>\n" +
                "            <a href=\"/deps\">列出所有部门</a>\n" +
                "        </li>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }
}
