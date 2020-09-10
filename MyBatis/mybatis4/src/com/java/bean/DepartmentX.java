package com.java.bean;

import java.util.List;

public class DepartmentX {
    private Long id;
    private String depName;
    // 该部门的员工
    private List<EmployeeX> employeeList;

    // 构造器
    public DepartmentX() {}

    public DepartmentX(Long id, String depName) {
        this.id = id;
        this.depName = depName;
    }

    public DepartmentX(Long id, String depName, List<EmployeeX> employeeList) {
        this.id = id;
        this.depName = depName;
        this.employeeList = employeeList;
    }

    // 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public List<EmployeeX> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeX> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "DepartmentX{" +
                "id=" + id +
                ", depName='" + depName + '\'' +
                '}';
    }
}
