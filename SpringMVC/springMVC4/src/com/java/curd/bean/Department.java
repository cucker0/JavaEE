package com.java.curd.bean;

public class Department {
    private Long id;
    private String departmentName;

    public Department() {}

    public Department(Long id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    // 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
