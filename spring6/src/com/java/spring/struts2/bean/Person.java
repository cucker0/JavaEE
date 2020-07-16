package com.java.spring.struts2.bean;

public class Person {
    private String name;
    private int age;
    private int sex;

    public Person() {}

    public Person(String name, int age, int sex) {
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

    public void info() {
        String sexStr = sex == 1 ? "男" : "女";
        System.out.println(String.format("%s, %d岁, %s\n", name, age, sexStr));
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
