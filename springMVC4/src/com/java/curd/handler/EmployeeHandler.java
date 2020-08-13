package com.java.curd.handler;

import com.java.curd.bean.Employee;
import com.java.curd.dao.DepartmentDao;
import com.java.curd.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class EmployeeHandler {
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Map<String, Object> map) {
        map.put("employees", employeeDao.queryAllEmployees());
        return "employeeList";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String addEmployee(Map<String, Object> map) {
        map.put("departments", departmentDao.queryAllDepartments());
        // 解决SpringMVC要求必须回显的问题
        map.put("employee", new Employee());
        return "addEmployee";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String saveEmployee(Employee employee) {
        System.out.println(employee);
        employeeDao.addEmployee(employee);
        return "redirect:/emps";
    }
}
