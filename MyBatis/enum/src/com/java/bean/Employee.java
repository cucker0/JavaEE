package com.java.bean;

import java.io.Serializable;

public class Employee implements Serializable {
    private Long id;
    private String lastName;
    // "0": female  "1": male
    private String gender;
    private String email;
    private EmployeeStatus status = EmployeeStatus.LOGOUT;
    private Department department;

    public Employee() {}

    public Employee(Long id, String lastName, String gender, String email, Department department) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.department = department;
    }

    public Employee(Long id, String lastName, String gender, String email, EmployeeStatus status, Department department) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.status = status;
        this.department = department;
    }

    // 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", department=" + department +
                '}';
    }
}
