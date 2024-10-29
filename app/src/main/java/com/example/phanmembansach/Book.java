package com.example.phanmembansach;

import android.media.Image;
import android.widget.ImageView;

public class Book {
    private String name;
    private String describe;
    private String author;
    private double start;
    private double price;
    private Integer amount;
    private Integer sold;
    private String img;
    private  double sale;
    private  Integer diemthuong;

    public Book() {

    }

    public Book(String name, String describe, String author, double start, double price, Integer amount, Integer sold, String img, double sale, Integer diemthuong) {
        this.name = name;
        this.describe = describe;
        this.author = author;
        this.start = start;
        this.price = price;
        this.amount = amount;
        this.sold = sold;
        this.img = img;
        this.sale = sale;
        this.diemthuong = diemthuong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;

    }

    public Integer getDiemthuong() {
        return diemthuong;
    }

    public void setDiemthuong(Integer diemthuong) {
        this.diemthuong = diemthuong;
    }
}
