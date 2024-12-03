package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class Forgetpassword2 extends AppCompatActivity {
    private TextView txt_ramdom;
    private EditText so1;
    private EditText so2;
    private EditText so3;
    private EditText so4;
    private EditText so5;
    private EditText so6;
    private Button btn_verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword2);
        txt_ramdom = findViewById(R.id.text_ramdom);
        so1 = findViewById(R.id.otp_digit_1);
        so2 = findViewById(R.id.otp_digit_2);
        so3 = findViewById(R.id.otp_digit_3);
        so4 = findViewById(R.id.otp_digit_4);
        so5 = findViewById(R.id.otp_digit_5);
        so6 = findViewById(R.id.otp_digit_6);
        btn_verify =findViewById(R.id.btn_verify);
        Intent intent = getIntent();
        String sdt = intent.getStringExtra("sdt");
        ArrayList<Integer> ramdoms = new ArrayList<>();
        Integer i =1;
        String m="";
        while(i<=6)
        {
            Random random = new Random();
            int randomNumber = random.nextInt(10);
            ramdoms.add(randomNumber);
            i=i+1;
            m=m+ String.valueOf(randomNumber)+"   ";
        }
        txt_ramdom.setText(m);
        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ramdoms.get(0)==Integer.valueOf(so1.getText().toString()) && ramdoms.get(1)==Integer.valueOf(so2.getText().toString())
                        && ramdoms.get(2)==Integer.valueOf(so3.getText().toString()) && ramdoms.get(3)==Integer.valueOf(so4.getText().toString())
                        && ramdoms.get(4)==Integer.valueOf(so5.getText().toString()) && ramdoms.get(5)==Integer.valueOf(so6.getText().toString()) && ramdoms.get(0)==Integer.valueOf(so1.getText().toString()))
                {
                    Intent intent1 = new Intent(Forgetpassword2.this, ForgetpasswordActivity.class);
                    intent1.putExtra("sdt",sdt);
                    startActivity(intent1);
                }
                else
                {
                    Toast.makeText(Forgetpassword2.this,"Sai mã xác minh",Toast.LENGTH_SHORT).show();
                    recreate();
                }
            }
        });
    }
}