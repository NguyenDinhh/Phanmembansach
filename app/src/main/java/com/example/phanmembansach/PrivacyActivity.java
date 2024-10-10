package com.example.phanmembansach;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView; // Thêm import cho ImageView
import androidx.appcompat.app.AppCompatActivity;

public class PrivacyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy); // Layout cho PasswordActivity

        // Khai báo nút "Back"
        ImageView buttonBack = findViewById(R.id.buttonBack);

        // Thiết lập sự kiện nhấp chuột cho nút "Back"
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Quay về MainActivity
            }
        });
    }
}
