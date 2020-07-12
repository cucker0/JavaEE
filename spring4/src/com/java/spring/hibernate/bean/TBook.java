package com.java.spring.hibernate.bean;

public class TBook {
    private Integer id;
    private String sn;
    private String bookName;
    private double price;
    // 库存
    private int stock;

    public TBook() {}

    public TBook(Integer id, String sn, String bookName, double price, int stock) {
        this.id = id;
        this.sn = sn;
        this.bookName = bookName;
        this.price = price;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "TBook{" +
                "id=" + id +
                ", sn='" + sn + '\'' +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
