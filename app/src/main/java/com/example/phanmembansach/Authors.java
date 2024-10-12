package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Authors extends AppCompatActivity {
    private ListView lv;
    private ImageView back;
    private ImageView menu_home;
    private ImageView menu_notification;
    private ImageView menu_cart;
    private ImageView menu_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //EdgeToEdge.enable(this);   toàn màn hình
        setContentView(R.layout.activity_authors);
        menu_home = findViewById(R.id.menu_home);
        menu_notification = findViewById(R.id.menu_notification);
        menu_cart = findViewById(R.id.menu_cart);
        menu_account= findViewById(R.id.menu_account);
        back = findViewById(R.id.img_back);
        EditText txt_search = findViewById(R.id.txt_search);
        txt_search.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP ) {
                if (event.getRawX() <= (txt_search.getLeft() + txt_search.getCompoundDrawables()[0].getBounds().width())) {
                    // Trong một phương thức như onClick
                    Toast.makeText(this, "You have just searched", Toast.LENGTH_SHORT).show();

                }
            }
            return false;
        });
        menu_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Authors.this, Home.class));

            }
        });
        menu_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Authors.this, Notification.class));
            }
        });
        menu_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Authors.this, CartActivity.class));
            }
        });
        menu_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Authors.this, MainActivity.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Authors.this, Home.class));
            }
        });
        lv = findViewById(R.id.lvauthor);
        ArrayList<Author> arrAuthor = new ArrayList<>();
        Author author1= new Author("J. R. R. Tolkien","Writer, Philologist",10,"","j_r_r_tolkien");
        Author author2= new Author("Andrzej Sapkowski","Writer, Philologist",10,"","andrzej_sapkowski");
        Author author3= new Author("Stephen King","Writer, Philologist",10,"","stephen_king");
        Author author4= new Author("Sir Arthur conan doyle","Writer, Philologist",10,"","sir_arthur_conan_doyle");
        Author author5= new Author("J K Rowling","Writer, Philologist",10,"","j_k_rowling");

        arrAuthor.add(author1);
        arrAuthor.add(author2);
        arrAuthor.add(author3);
        arrAuthor.add(author4);
        arrAuthor.add(author5);
        Adapter_Authors adapter = new Adapter_Authors(this,R.layout.row_authors, arrAuthor);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            startActivity(new Intent(Authors.this, Detail_Author.class));
        });
    }
}