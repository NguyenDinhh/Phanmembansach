package com.example.phanmembansach;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Detail_Author extends AppCompatActivity {
    private DatabaseReference mdata;
    private TextView txt_back;
    private TextView ten;
    private  TextView congviec;
    private  TextView mota;
    private ImageView img;
    private RecyclerView recyclerView;
    private  Adapter_Home_Fragment_books adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_author);
        ArrayList<Book> arrbook = new ArrayList<>();
        txt_back = findViewById(R.id.txt_tieude);
        ten = findViewById(R.id.ten);
        mota = findViewById(R.id.mota);
        congviec   = findViewById(R.id.congviec);
        img = findViewById(R.id.img);
        recyclerView = findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter_Home_Fragment_books(this, arrbook);  // Adapter cho sách
        recyclerView.setAdapter(adapter);
        mdata = FirebaseDatabase.getInstance().getReference();
        mdata.child("TacGias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                Integer tacgiaid = getIntent().getIntExtra("TacGiaID", 0);
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    try {
                        Author author = dataSnapshot.getValue(Author.class);
                        if (author.getTacGiaID() == tacgiaid)
                        {
                            ten.setText(author.getTen());
                            congviec.setText(author.getCongViec());
                            mota.setText(author.getMoTa());
                            // Nếu có ảnh, hiển thị ảnh của sách
                            if (author.getAnh() != null) {
                                int imageResId = getResources().getIdentifier(author.getAnh(), "drawable", getPackageName());
                                if (imageResId != 0) {
                                    img.setImageResource(imageResId);
                                } else {
                                    img.setImageResource(R.drawable.andrzej_sapkowski);  // Mặc định nếu không tìm thấy ảnh
                                }
                            }
                            mdata.child("Sachs").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    arrbook.clear();
                                    for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                        try {
                                            Book book = dataSnapshot.getValue(Book.class);
                                            if (tacgiaid == book.getTacgiaID())
                                            {
                                                arrbook.add(book);
                                            }
                                        } catch (Exception e) {
                                            Log.e("Firebase", "Error: " + e.getMessage());
                                        }
                                    }
                                    adapter.notifyDataSetChanged();
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Log.e("Firebase", "Error loading data: " + error.getMessage());
                                }
                            });
                            break;
                        }
                    } catch (Exception e) {
                        Log.e("Firebase", "Error: " + e.getMessage());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error loading data: " + error.getMessage());
            }
        });
        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}