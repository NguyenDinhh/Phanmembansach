package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ConstraintLayout sm_r = findViewById(R.id.see_more_recommendation);
        ConstraintLayout sm_c = findViewById(R.id.see_more_categories);
        ConstraintLayout sm_a = findViewById(R.id.see_more_author);
        ConstraintLayout sm_f= findViewById(R.id.see_more_fantasy);
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