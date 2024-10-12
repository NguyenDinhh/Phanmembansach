package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Notification extends AppCompatActivity {
    private ImageView back;
    private ImageView menu_home;
    private ImageView menu_notification;
    private ImageView menu_cart;
    private ImageView menu_account;
    private ImageView img_reload;
    private ImageView img_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        menu_home = findViewById(R.id.menu_home);
        menu_notification = findViewById(R.id.menu_notification);
        menu_cart = findViewById(R.id.menu_cart);
        menu_account = findViewById(R.id.menu_account);
        img_reload = findViewById(R.id.img_reload);
        img_menu = findViewById(R.id.img_menu);
        back = findViewById(R.id.img_back);

        menu_home.setOnClickListener(view -> startActivity(new Intent(Notification.this, Home.class)));
        menu_notification.setOnClickListener(view -> startActivity(new Intent(Notification.this, Notification.class)));
        menu_cart.setOnClickListener(view -> startActivity(new Intent(Notification.this, CartActivity.class)));
        menu_account.setOnClickListener(view -> startActivity(new Intent(Notification.this, MainActivity.class)));

        back.setOnClickListener(view -> finish());
        img_reload.setOnClickListener(view -> recreate());

        img_menu.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(Notification.this, view);
            popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.option1) {
                    Toast.makeText(Notification.this, "Bạn đã chọn Make all as reader", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.option2) {
                    Toast.makeText(Notification.this, "Bạn đã chọn Make all as unreader", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            });
            popupMenu.show(); // Hiển thị menu
        });
    }
}
