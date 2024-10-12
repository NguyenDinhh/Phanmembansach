package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class ContactusActivity extends AppCompatActivity {
    private ImageView back;
    private ImageView menu_home;
    private ImageView menu_notification;
    private ImageView menu_cart;
    private ImageView menu_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        menu_home = findViewById(R.id.menu_home);
        menu_notification = findViewById(R.id.menu_notification);
        menu_cart = findViewById(R.id.menu_cart);
        menu_account= findViewById(R.id.menu_account);
        back = findViewById(R.id.img_back);
        menu_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContactusActivity.this, Home.class));

            }
        });
        menu_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContactusActivity.this, Notification.class));
            }
        });
        menu_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContactusActivity.this, CartActivity.class));
            }
        });
        menu_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContactusActivity.this, MainActivity.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
