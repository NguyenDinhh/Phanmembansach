package com.example.phanmembansach;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class YourfavouriteActivity extends AppCompatActivity {
    private ImageView back;
    ListView lv;
    Adapter_Categories_books adapterCategoriesBooks ;


    ArrayList<Book> arrbook = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseReference mdata = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_yourfavourite);
        lv = findViewById(R.id.lv);
        App app = (App) getApplicationContext();
        adapterCategoriesBooks = new Adapter_Categories_books(this,R.layout.row_categories_books,arrbook);
        lv.setAdapter(adapterCategoriesBooks);

        mdata.child("SachYeuThichs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                arrbook.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    try
                    {
                        SachYeuThich sachYeuThich = dataSnapshot.getValue(SachYeuThich.class);
                        if (app.getUsername().equals(sachYeuThich.getTenDangNhap()))
                        {
                            mdata.child("Sachs").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for(DataSnapshot dataSnapshot : snapshot.getChildren())
                                    {
                                        try {
                                            Book book = dataSnapshot.getValue(Book.class);
                                            if(book.getSachID()== sachYeuThich.getSachID())
                                            {
                                                arrbook.add(book);
                                            }
                                        }catch (Exception e)
                                        {
                                        }
                                    }
                                    adapterCategoriesBooks.notifyDataSetChanged();
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                }
                            });
                        }
                    }catch (Exception e)
                    {
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        back = findViewById(R.id.img_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YourfavouriteActivity.this, Home.class);
                intent.putExtra("fragment_cart", 3);
                startActivity(intent);
            }
        });

    }
}