package com.java.crudresful.dao;

import com.java.crudresful.bean.Department;
import com.java.crudresful.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.text.DateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = new HashMap<>();
    private static Integer initId = 1006;

    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees.put(1001, new Employee(1001, "Alix", 1, "aa@163.com", Date.valueOf("2000-01-01"), new Department(101, "D-AA")));
        employees.put(1002, new Employee(1002, "莱布尼茨", 1,"bb@163.com", Date.valueOf("2000-01-01"),new Department(102, "D-BB")));
        employees.put(1003, new Employee(1003, "牛顿", 0, "cc@163.com", Date.valueOf("2000-01-01"), new Department(103, "D-CC")));
        employees.put(1004, new Employee(1004, "爱因斯坦", 0,"dd@163.com", Date.valueOf("2000-01-01"), new Department(104, "D-DD")));
        employees.put(1005, new Employee(1005, "杨振宁", 1,"ee@163.com", Date.valueOf("2000-01-01"), new Department(105, "D-EE")));
    }

    public void save(Employee e) {
        if (e.getId() == null) {
            e.setId(initId);
        }
        e.setDepartment(departmentDao.getDepartmentById(e.getDepartment().getId()));
        employees.put(e.getId(), e);
    }

    public Collection<Employee> getAll() {
        return employees.values();
    }

    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    public void deleteEmployeeById(Integer id) {
        employees.remove(id);
    }
}
