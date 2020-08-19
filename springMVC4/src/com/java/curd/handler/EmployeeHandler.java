package com.java.curd.handler;

import com.java.curd.bean.Department;
import com.java.curd.bean.Employee;
import com.java.curd.dao.DepartmentDao;
import com.java.curd.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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

    // 新增员工页面
    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String addEmployeePage(Map<String, Object> map) {
        map.put("departments", departmentDao.queryAllDepartments());
        // 解决SpringMVC要求必须回显的问题
        map.put("employee", new Employee());
        return "addEmployee";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    // public String saveEmployee(Employee employee) {
    public String saveEmployee(@Valid Employee employee, BindingResult result, Map<String, Object> map) {  // @Valid：使用hibernate validato进行验证
                                                                                                            // Map<String, Object> map：为了在添加员工出错时，回显部门数据
        map.put("departments", departmentDao.queryAllDepartments());
        System.out.println(employee);
        // 出错了
        if (result.getErrorCount() > 0) {
            System.out.println("出错了!");
            for (FieldError error: result.getFieldErrors()) {
                System.out.println(error.getField() + ": " + error.getDefaultMessage());
            }
            // 继续显示 添加员工页面
            return "addEmployee";
        }
        employeeDao.addEmployee(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeDao.deleteEmployeeById(id);
        return "redirect:/emps";
    }

    // 编辑用户页
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String editEmployee(@PathVariable("id") Long id, Map<String, Object> map) {
        Employee employee = employeeDao.queryEmployeeById(id);
        List<Department> departments = departmentDao.queryAllDepartments();
        map.put("employee", employee);
        map.put("departments", departments);
        return "editEmployee";
    }

    // 其他方法在执行前都会先执行此方法
    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id", required = false) Long id, Map<String, Object> map) {
        if (id != null) {
            map.put("employee", employeeDao.queryEmployeeById(id));
        }
    }

    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public String updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
        return "redirect:/emps";
    }
}
