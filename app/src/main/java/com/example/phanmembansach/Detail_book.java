package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Detail_book extends AppCompatActivity {
    private TextView txt_back;
    private  ImageView img_favourite;
    private ImageView img_cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);
        txt_back = findViewById(R.id.txt_back);
        img_favourite = findViewById(R.id.ic_favourite);
        img_favourite.setImageResource(R.drawable.ic_favorite);
        img_favourite.setTag(R.drawable.ic_favorite);
       img_cart = findViewById(R.id.ic_cart);
       txt_back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });
       img_cart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(Detail_book.this, Home.class);
               intent.putExtra("fragment_cart", 2); // Chuyển tới fragment tại vị trí 0
               startActivity(intent);
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