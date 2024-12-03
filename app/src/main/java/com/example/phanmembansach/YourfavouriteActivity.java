package com.example.phanmembansach;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class YourfavouriteActivity extends AppCompatActivity {
    private ImageView back;
    private TextView tvoldprice;
    private TextView tvoldprice2;
    ImageView img_cart_1;
    ImageView img_cart_2;
    LinearLayout a;
    LinearLayout b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourfavourite);
        tvoldprice = findViewById(R.id.giacu);
        tvoldprice2 =findViewById(R.id.tvoldprice2);
        tvoldprice.setPaintFlags(tvoldprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tvoldprice2.setPaintFlags(tvoldprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        img_cart_1 = findViewById(R.id.ic_cart_1);
        img_cart_2 = findViewById(R.id.ic_cart_2);
        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        back = findViewById(R.id.img_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YourfavouriteActivity.this, Home.class);
                intent.putExtra("fragment_cart", 3);
                startActivity(intent);
            }
        });
        img_cart_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YourfavouriteActivity.this, Home.class);
                intent.putExtra("fragment_cart", 2);
                startActivity(intent);
            }
        });
        img_cart_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YourfavouriteActivity.this, Home.class);
                intent.putExtra("fragment_cart", 2);
                startActivity(intent);
            }
        });
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(YourfavouriteActivity.this, Detail_book.class));
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(YourfavouriteActivity.this, Detail_book.class));
            }
        });
    }
}
