package com.java.crudresful.bean;

import java.util.Date;

public class Employee {
    private Integer id;
    private String name;
    private Integer gender;
    private String email;
    private Date birth;
    private Department department;

    public Employee() {
    }

    public Employee(Integer id, String name, Integer gender, String email, Date birth, Department department) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.birth = birth;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
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
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", department=" + department +
                '}';
    }
}
