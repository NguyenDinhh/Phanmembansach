package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryActivity extends AppCompatActivity {
    private ImageView back;
    private TextView tvoldprice;
    private TextView tvoldprice2;
    private Button btn_buy_1;
    private Button btn_buy_2;
    private Button btn_danhgia1;
    private Button btn_danhgia2;
    LinearLayout a;
    LinearLayout b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        back = findViewById(R.id.img_back);
        tvoldprice = findViewById(R.id.giacu);
        tvoldprice2 = findViewById(R.id.tvoldprice2);
        btn_buy_1 =findViewById(R.id.btn_buy_1);
        btn_buy_2 = findViewById(R.id.btn_buy_2);
        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        btn_danhgia1 = findViewById(R.id.btn_danhgia1);
        btn_danhgia2 = findViewById(R.id.btn_danhgia2);
        back = findViewById(R.id.img_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this, Home.class);
                intent.putExtra("fragment_cart", 3);
                startActivity(intent);
            }
        });
        btn_buy_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this, Home.class);
                intent.putExtra("fragment_cart", 2);
                startActivity(intent);
            }
        });
        btn_buy_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this, Home.class);
                intent.putExtra("fragment_cart", 2);
                startActivity(intent);
            }
        });
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HistoryActivity.this, Detail_book.class));
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HistoryActivity.this, Detail_book.class));
            }
        });
        btn_danhgia1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HistoryActivity.this, Danh_gia.class));
            }
        });
        btn_danhgia2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HistoryActivity.this, Danh_gia.class));
            }
        });
    }
}
