package com.example.phanmembansach;

public class Author {
    private  String name;
    private  String job;
    private  Integer amount;
    private  String dedescribe;

    public Author(String name, String job, Integer amount, String dedescribe) {
        this.name = name;
        this.job = job;
        this.amount = amount;
        this.dedescribe = dedescribe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDedescribe() {
        return dedescribe;
    }

    public void setDedescribe(String dedescribe) {
        this.dedescribe = dedescribe;
    }
}
