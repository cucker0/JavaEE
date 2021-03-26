package com.java.crudresful.controller;

import com.java.crudresful.bean.Department;
import com.java.crudresful.bean.Employee;
import com.java.crudresful.dao.DepartmentDao;
import com.java.crudresful.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    // 员工列表
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

    // 显示添加员工页面
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("deps", departments);
        return "emp/add";
    }

    /**
     * 添加员工
     *      SpringMVC自动将请求参数和入参对象的属性进行一一绑定；
     *      要求请求参数的名(name)和javaBean入参的对象里面的属性名是一样的
     * @param e
     * @return
     *      redirect: 表示重定向到一个地址.  / 代表当前项目路径
     *      forward: 表示转发到一个地址
     *
     */
    @PostMapping("/emp")
    public String addEmp(Employee e) {
        System.out.println("==== add employee: " + e);
        employeeDao.save(e);
        return "redirect:/emps";
    }

    // 删除指定id的员工
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id) {
        System.out.println("=== delete emp... emp id:" + id);
        employeeDao.deleteEmployeeById(id);
        return "redirect:/emps";
    }

    // 显示员工编辑页面
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Employee e = employeeDao.getEmployeeById(id);
        model.addAttribute("emp", e);
        model.addAttribute("deps", departmentDao.getDepartments());
        return "emp/edit";
    }

    // 更新员工信息
    @PutMapping("/emp")
    public String updateEmp(Employee e) {
        employeeDao.updateEmployee(e);
        return "redirect:/emps";
    }
}
