package com.java.curd.bean;

import java.time.LocalDate;

public class Employee {
    private Long id;
    private String last_name;
    private int gender;
    private String emial;
    private double salary;
    private LocalDate birth;
    private Long department_id;

    public Employee() {}

    public Employee(Long id, String last_name, int gender, String emial, double salary, LocalDate birth, Long department_id) {
        this.id = id;
        this.last_name = last_name;
        this.gender = gender;
        this.emial = emial;
        this.salary = salary;
        this.birth = birth;
        this.department_id = department_id;
    }

    // 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmial() {
        return emial;
    }

    public void setEmial(String emial) {
        this.emial = emial;
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

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", last_name='" + last_name + '\'' +
                ", gender=" + gender +
                ", emial='" + emial + '\'' +
                ", salary=" + salary +
                ", birth=" + birth +
                ", department_id=" + department_id +
                '}';
    }
}
