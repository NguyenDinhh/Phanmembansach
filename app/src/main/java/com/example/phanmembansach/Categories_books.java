package com.example.phanmembansach;

import android.content.Intent;
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
        Book book2 = new Book("Ash and steel: A soul stones story","", "T.L. Branson", 0, 20.99, 440, 47,"fantasy_book_2");
        Book book3 = new Book("As fire is to gold","", "Mark McCabe", 0, 27.09, 80, 8,"fantasy_book_3");
        Book book4 = new Book("The wicher","", "T.L. Branson", 0, 20.99, 1000, 13,"fantasy_book_1");
        Book book5 = new Book("Ash and steel: A soul stones story","", "T.L. Branson", 0, 20.99, 1000, 13,"fantasy_book_4");
        Book book6 = new Book("Hero","", "T.L. Branson", 0, 20.99, 1000, 13,"fantasy_book_5");
        Book book7 = new Book("Nguoi Hobbit","", "T.L. Branson", 0, 20.99, 1000, 13,"chuanhan2");
        Book book8 = new Book("Chua nhan 3","", "T.L. Branson", 0, 20.99, 1000, 13,"chuanhan33");
        arrBook.add(book1);
        arrBook.add(book2);
        arrBook.add(book3);
        arrBook.add(book4);
        arrBook.add(book5);
        arrBook.add(book6);
        arrBook.add(book7);
        arrBook.add(book8);
        Adapter_Categories_books adapter = new Adapter_Categories_books(this,R.layout.row_categories_books, arrBook);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            startActivity(new Intent(Categories_books.this, Detail_book.class));
        });
    }
}