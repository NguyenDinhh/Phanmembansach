package com.example.phanmembansach;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Danh_gia extends AppCompatActivity {
    private TextView txt_back;
    private ImageView img_favourite;
    private ImageView img_cart;
    private  TextView txtoldprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia);
        Button btn = findViewById(R.id.submit_review_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Danh_gia.this, "Đã bình luận", Toast.LENGTH_SHORT).show();
            }
        });
        img_favourite = findViewById(R.id.ic_favourite);
        img_favourite.setImageResource(R.drawable.ic_favorite);
        img_favourite.setTag(R.drawable.ic_favorite);
        img_cart = findViewById(R.id.ic_cart);
        txtoldprice = findViewById(R.id.tvoldprice);
        txtoldprice.setPaintFlags(txtoldprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        txt_back = findViewById(R.id.txt_back);
        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Danh_gia.this, Order_Book.class));
            }
        });
        img_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( (Integer) img_favourite.getTag() ==R.drawable.ic_favorite )
                {
                    img_favourite.setImageResource(R.drawable.ic_favourite_2);
                    img_favourite.setTag(R.drawable.ic_favourite_2);
                }
                else
                {
                    img_favourite.setImageResource(R.drawable.ic_favorite);
                    img_favourite.setTag(R.drawable.ic_favorite);
                }
            }
        });
    }
}