package com.java.bean;

public class Employee {
    private Long id;
    private String lastName;
    // "0": female  "1": male
    private String gender;
    private String email;

    public Employee() {
    }

    public Employee(Long id, String lastName, String gender, String email) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
