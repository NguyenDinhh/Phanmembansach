package com.example.phanmembansach;

import android.app.Application;

public class App extends Application {
    private boolean isLoggedIn = false;
    private String username = "";
    @Override
    public void onCreate() {
        super.onCreate();
        // Khởi tạo trạng thái đăng nhập khi ứng dụng bắt đầu
        // Bạn có thể kiểm tra từ SharedPreferences hoặc Firebase ở đây
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
}
