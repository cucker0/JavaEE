package com.java.ref.bean;

public class Person {
    private String name;
    private String fatherName;

    public Person() {}

    // 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void init() {
        System.out.println("init method execute... ");
    }

    public void destroy() {
        System.out.println("destroy method execute... ");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", fatherName='" + fatherName + '\'' +
                '}';
    }
}
