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

public class All_Book extends AppCompatActivity {
    private ListView lv;
    private ImageView back;
    private ImageView menu_home;
    private ImageView menu_notification;
    private ImageView menu_cart;
    private ImageView menu_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_book);
        lv = findViewById(R.id.lvcategories_book);
        menu_home = findViewById(R.id.menu_home);
        menu_notification = findViewById(R.id.menu_notification);
        menu_cart = findViewById(R.id.menu_cart);
        menu_account = findViewById(R.id.menu_account);
        back = findViewById(R.id.img_back);
        EditText txt_search = findViewById(R.id.txt_search);
        txt_search.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
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
                startActivity(new Intent(All_Book.this, Home.class));

            }
        });
        menu_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(All_Book.this, Notification.class));
            }
        });
        menu_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(All_Book.this, CartActivity.class));
            }
        });
        menu_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(All_Book.this, MainActivity.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ArrayList<Book> arrBook = new ArrayList<>();
        Book book1 = new Book("Chúa nhẫn 1", "", "J. R. R. Tolkien", 0, 15.00, 1000, 13, "chuanhan1");
        Book book2 = new Book("Ash and steel: A soul stones story", "", "T.L. Branson", 0, 20.99, 440, 47, "fantasy_book_2");
        Book book3 = new Book("As fire is to gold", "", "Mark McCabe", 0, 27.09, 80, 8, "fantasy_book_3");
        Book book4 = new Book("The wicher", "", "T.L. Branson", 0, 20.99, 1000, 13, "fantasy_book_1");
        Book book5 = new Book("Ash and steel: A soul stones story", "", "T.L. Branson", 0, 20.99, 1000, 13, "fantasy_book_4");
        Book book6 = new Book("Hero", "", "T.L. Branson", 0, 20.99, 1000, 13, "fantasy_book_5");
        Book book7 = new Book("Nguoi Hobbit", "", "T.L. Branson", 0, 20.99, 1000, 13, "chuanhan2");
        Book book8 = new Book("Chua nhan 3", "", "T.L. Branson", 0, 20.99, 1000, 13, "chuanhan33");

        arrBook.add(book5);
        arrBook.add(book6);
        arrBook.add(book7);
        arrBook.add(book8);
        arrBook.add(book1);
        arrBook.add(book2);
        arrBook.add(book3);
        arrBook.add(book4);
        Adapter_Categories_books adapter = new Adapter_Categories_books(this, R.layout.row_categories_books, arrBook);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            startActivity(new Intent(All_Book.this, Detail_book.class));
        });
    }
}