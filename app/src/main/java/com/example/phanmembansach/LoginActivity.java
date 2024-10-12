package com.example.phanmembansach;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    TextView textViewForgotPassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Kích hoạt chế độ Edge-to-Edge
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Thiết lập padding để xử lý các thanh hệ thống (system bars)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Liên kết TextView với phần tử "Forgotten password?" trong layout
        textViewForgotPassword = findViewById(R.id.textview_forgot_password);

        // Sự kiện khi nhấn vào "Forgotten password?" để chuyển sang ForgetPasswordActivity
        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang ForgetPasswordActivity
                Intent intent = new Intent(LoginActivity.this, ForgetpasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
