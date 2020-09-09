package com.java.bean;

public class EmployeeX {
    private Long id;
    private String lastName;
    // "0": female  "1": male
    private String gender;
    private String email;
    private Department department;

    // 构造器
    public EmployeeX() {
    }

    public EmployeeX(Long id, String lastName, String gender, String email) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }

    public EmployeeX(Long id, String lastName, String gender, String email, Department department) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
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

    @Override
    public String toString() {
        return "EmployeeX{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", department=" + department +
                '}';
    }
}
