package com.example.phanmembansach;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Categories_books extends AppCompatActivity {
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_books);
        lv = findViewById(R.id.lvcategories_book);
        ArrayList<Book> arrBook = new ArrayList<>();
        Book book1 = new Book("Chúa nhẫn 1","", "J. R. R. Tolkien", 0, 15.00, 1000, 13,"chuanhan1");
        Book book2 = new Book("Ash and steel: A soul stones story","", "T.L. Branson", 0, 20.99, 1000, 13,"fantasy_book_2");
        Book book3 = new Book("Ash and steel: A soul stones story","", "T.L. Branson", 0, 20.99, 1000, 13,"fantasy_book_2");
        arrBook.add(book3);
        Book book4 = new Book("Ash and steel: A soul stones story","", "T.L. Branson", 0, 20.99, 1000, 13,"fantasy_book_2");
        arrBook.add(book4);
        arrBook.add(book1);
        arrBook.add(book2);
        Adapter_Categories_books adapter = new Adapter_Categories_books(this,R.layout.row_categories_books, arrBook);
        lv.setAdapter(adapter);    }
}