package com.example.phanmembansach;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Order_Book extends AppCompatActivity {

    private TextView txt_back;
    private Button btn_order;
    private  TextView txt_oldprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_book);
        txt_back = findViewById(R.id.txt_tieude);
        btn_order = findViewById(R.id.btn_order);
        txt_oldprice = findViewById(R.id.giacu);
        txt_oldprice.setPaintFlags(txt_oldprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Order_Book.this, Home.class);
                intent.putExtra("fragment_cart", 2);
                startActivity(intent);
            }
        });
    }
}