package com.example.phanmembansach;

import android.app.Application;

public class App extends Application {
    private boolean isLoggedIn = false;
    private String username = "";
    private  int DiemThuong;
    private  int KhachHangID;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
    }

    // Lấy tên người dùng
    public String getUsername() {
        return username;
    }

    // Đặt tên người dùng
    public void setUsername(String username) {
        this.username = username;

    }

    public int getDiemThuong() {
        return DiemThuong;
    }

    public void setDiemThuong(int diemThuong) {
        DiemThuong = diemThuong;
    }

    public int getKhachHangID() {
        return KhachHangID;
    }

    public void setKhachHangID(int khachHangID) {
        KhachHangID = khachHangID;
    }
}
