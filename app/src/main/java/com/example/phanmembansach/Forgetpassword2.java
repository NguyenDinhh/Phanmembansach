package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Forgetpassword2 extends AppCompatActivity {

    private Button btn_verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword2);
        btn_verify =findViewById(R.id.btn_verify);
        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Forgetpassword2.this, ForgetpasswordActivity.class));
            }
        });
    }
}