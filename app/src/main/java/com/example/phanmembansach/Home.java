package com.example.phanmembansach;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Home extends AppCompatActivity {

    private ImageView menu_home;
    private ImageView menu_notification;
    private ImageView menu_cart;
    private ImageView menu_account;
    private TextView see_more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ConstraintLayout sm_r = findViewById(R.id.see_more_recommendation);
        ConstraintLayout sm_c = findViewById(R.id.see_more_categories);
        ConstraintLayout sm_a = findViewById(R.id.see_more_author);
        ConstraintLayout sm_f= findViewById(R.id.see_more_fantasy);
        see_more = findViewById(R.id.txt_see_more);
        menu_home = findViewById(R.id.menu_home);
        menu_notification = findViewById(R.id.menu_notification);
        menu_cart = findViewById(R.id.menu_cart);
        menu_account= findViewById(R.id.menu_account);
        EditText txt_search = findViewById(R.id.txt_search);
        txt_search.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP ) {
                if (event.getRawX() <= (txt_search.getLeft() + txt_search.getCompoundDrawables()[0].getBounds().width())) {
                    startActivity(new Intent(Home.this, All_Book.class));
                    Toast.makeText(this, "You have just searched", Toast.LENGTH_SHORT).show();
                }
            }
            return false;
        });
        see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, All_Book.class));
            }
        });
        menu_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });
        menu_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, Notification.class));
            }
        });
        menu_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, CartActivity.class));
            }
        });
        menu_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, MainActivity.class));
            }
        });
        sm_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, Categories_books.class));
            }
        });
        sm_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, Categories.class));
            }
        });
        sm_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, Authors.class));
            }
        });
        sm_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, Categories_books.class));
            }
        });
        int[] idbooks = {R.id.home_book_1, R.id.home_book_2, R.id.home_book_3, R.id.home_book_4, R.id.home_book_5, R.id.home_book_6, R.id.home_book_7, R.id.home_book_8, R.id.home_book_9, R.id.home_book_10, R.id.home_book_11, R.id.home_book_12, R.id.home_book_13, R.id.home_book_14, R.id.home_book_15,};
        int[] idauthors = {R.id.home_author_1, R.id.home_author_2, R.id.home_author_3, R.id.home_author_4, R.id.home_author_5};
        for (int id : idbooks) {
            ConstraintLayout c = findViewById(id);
            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        startActivity(new Intent(Home.this, Detail_book.class));
                }
            });
        }
        for (int id : idauthors) {
            ConstraintLayout c = findViewById(id);
            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Home.this, Detail_Author.class));
                }
            });
        }
    }
}