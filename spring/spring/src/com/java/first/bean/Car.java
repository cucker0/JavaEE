package com.java.first.bean;

public class Car {
    private String brand;
    // 总部所在城市
    private String headquarters;
    // 最大速度，单位：KM/H
    private int maxSpeed;
    // 价格，单位：元
    private float price;

    // 构造器
    public Car() {}

    public Car(String brand, String headquarters, float price) {
        this.brand = brand;
        this.headquarters = headquarters;
        this.price = price;
    }

    public Car(String brand, String headquarters, int maxSpeed) {
        this.brand = brand;
        this.headquarters = headquarters;
        this.maxSpeed = maxSpeed;
    }

    public Car(String brand, String headquarters, int maxSpeed, float price) {
        this.brand = brand;
        this.headquarters = headquarters;
        this.maxSpeed = maxSpeed;
        this.price = price;
    }

    // 方法
    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", headquarters='" + headquarters + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", price=" + price +
                '}';
    }
}
