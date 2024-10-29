package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
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

public class Bestseller extends AppCompatActivity {

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestseller);
        ImageView back = findViewById(R.id.img_back);
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
        ArrayList<Book> arrBook = new ArrayList<>();
        Book book1 = new Book("Chúa nhẫn 1", "", "J. R. R. Tolkien", 0, 15.00, 1000, 13, "chuanhan1",0.1,12000);
        Book book2 = new Book("Ash and steel: A soul stones story", "", "T.L. Branson", 0, 20.99, 440, 47, "fantasy_book_2",0.2,20000);
        Book book3 = new Book("As fire is to gold", "", "Mark McCabe", 0, 27.09, 80, 8, "fantasy_book_3",0.23,25000);
        Book book4 = new Book("The wicher", "", "T.L. Branson", 0, 20.99, 1000, 13, "fantasy_book_1",0.34,23000);
        Book book5 = new Book("Ash and steel: A soul stones story", "", "T.L. Branson", 0, 20.99, 1000, 13, "fantasy_book_4",0.12,34000);
        Book book6 = new Book("Hero", "", "T.L. Branson", 0, 20.99, 1000, 13, "fantasy_book_5",0.13,27000);
        Book book7 = new Book("Nguoi Hobbit", "", "T.L. Branson", 0, 20.99, 1000, 13, "chuanhan2",0.24,18000);
        Book book8 = new Book("Chua nhan 3", "", "T.L. Branson", 0, 20.99, 1000, 13, "chuanhan33",0.05,24000);


        arrBook.add(book5);
        arrBook.add(book6);
        arrBook.add(book7);
        arrBook.add(book8);
        arrBook.add(book1);
        arrBook.add(book2);
        arrBook.add(book3);
        arrBook.add(book4);
        Adapter_Categories_books adapter = new Adapter_Categories_books(Bestseller.this, R.layout.row_categories_books, arrBook);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            startActivity(new Intent(Bestseller.this, Detail_book.class));
        });
    }
}