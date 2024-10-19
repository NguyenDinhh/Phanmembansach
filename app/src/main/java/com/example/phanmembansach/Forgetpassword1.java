package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Forgetpassword1 extends AppCompatActivity {
    private TextView txt_login;
    private Button btn_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword1);
        txt_login = findViewById(R.id.txt_login);
        btn_ok = findViewById(R.id.btn_reset_password);
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Forgetpassword1.this, LoginActivity.class));
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Forgetpassword1.this, Forgetpassword2.class));
            }
        });
    }
}