package com.java.crudresful.dao;

import com.java.crudresful.bean.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDao {
    private static Map<Integer, Department> departments = new HashMap<>();

    static {
        departments.put(101, new Department(101, "行政部"));
        departments.put(102, new Department(102, "财务部"));
        departments.put(103, new Department(103, "IT部"));
        departments.put(104, new Department(104, "销售部"));
        departments.put(105, new Department(105, "客服部"));
    }

    public Collection<Department> getDepartments() {
        return departments.values();
    }

    public static Department getDepartmentById(Integer id) {
        return departments.get(id);
    }
}
