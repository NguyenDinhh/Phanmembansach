package com.example.phanmembansach;

public class Category {
    private  String name;
    private  Integer amount;
    private String describe;
    private Integer sold;
    private  String img;
    private Integer id;


    public Category(String name, Integer amount, String describe, Integer sold, String img, Integer id) {
        this.name = name;
        this.amount = amount;
        this.describe = describe;
        this.sold = sold;
        this.img = img;
        this.id = id;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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
}
