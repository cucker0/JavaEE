package com.java.curd.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class Employee {
    private Long id;
    @NotEmpty(message = "用户名不能为空")
    private String lastName;
    // 0: female  1: male
    private int gender;
    @Email
    private String email;
    private double salary;
    @NotEmpty(message = "生日不能为空")
    private LocalDate birth;
    private Department department;

    public Employee() {}

    public Employee(Long id, String lastName, int gender, String email, double salary, LocalDate birth, Department department) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.salary = salary;
        this.birth = birth;
        this.department = department;
    }

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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", birth=" + birth +
                ", department=" + department +
                '}';
    }
}
