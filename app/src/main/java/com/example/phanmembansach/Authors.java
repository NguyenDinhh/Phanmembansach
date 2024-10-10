package com.example.phanmembansach;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Authors extends AppCompatActivity {
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //EdgeToEdge.enable(this);   toàn màn hình
        setContentView(R.layout.activity_authors);
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
    }
}