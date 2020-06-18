package com.java.first.bean;

public class User {
    private String name;
    private int age;
    // gende, 0: female, 1: male
    private int sex;

    // 构造器
    public User() {}

    public User(String name, int age, int sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    // 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String info() {
        String s = null;
        if (sex == 0) {
            s = "这是位美如天仙的女士";
        } else {
            s = "这是位气度不凡的绅士";
        }
        System.out.println(s);
        return s;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
