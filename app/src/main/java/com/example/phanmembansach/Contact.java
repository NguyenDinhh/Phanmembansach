package com.example.phanmembansach;

public class Contact {
    private String name;

    // Constructor với một tham số
    public Contact(String name) {
        this.name = name;
    }

    // Getter cho tên
    public String getName() {
        return name;
    }

    // Setter cho tên (nếu cần thiết)
    public void setName(String name) {
        this.name = name;
    }
}
