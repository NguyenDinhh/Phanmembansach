package com.example.phanmembansach;

import android.os.Bundle;
import android.util.Log;
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

public class connection extends AppCompatActivity {
    DatabaseReference mdata;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    Adapter_Home_Fragment_books adapter;
    Adapter_Home_Fragment_Categories adapter2;
    Adapter_Home_Fragment_Authors adapter3;
    ArrayList<Book> arrBook = new ArrayList<>();
    ArrayList<Category> arrCategories = new ArrayList<>();
    ArrayList<Author> arrAuthor = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        recyclerView = findViewById(R.id.rv_sieugiamgia);
        recyclerView2 = findViewById(R.id.rv2);
        recyclerView3 = findViewById(R.id.rv3);

        // Sử dụng LinearLayoutManager với hướng cuộn ngang cho cả hai RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView3.setLayoutManager(layoutManager3);

        // Tạo Adapter và gắn vào RecyclerView
        adapter = new Adapter_Home_Fragment_books(this, arrBook);  // Adapter cho sách
        recyclerView.setAdapter(adapter);

        adapter2 = new Adapter_Home_Fragment_Categories(this, arrCategories);  // Adapter cho thể loại
        recyclerView2.setAdapter(adapter2);

        adapter3 = new Adapter_Home_Fragment_Authors(this, arrAuthor);  // Adapter cho thể loại
        recyclerView3.setAdapter(adapter3);
        // Lấy dữ liệu từ Firebase cho sách
        mdata = FirebaseDatabase.getInstance().getReference();
        mdata.child("Sachs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrBook.clear(); // Xóa danh sách cũ trước khi thêm dữ liệu mới
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    try {
                        Book book = dataSnapshot.getValue(Book.class);
                        if (book != null) {
                            arrBook.add(book);
                        }
                    } catch (Exception e) {
                        Log.e("Firebase", "Error: " + e.getMessage());
                    }
                }
                // Cập nhật adapter khi dữ liệu đã thay đổi
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error: " + error.getMessage());
            }
        });

        // Lấy dữ liệu từ Firebase cho thể loại
        mdata.child("TheLoais").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrCategories.clear(); // Làm sạch danh sách thể loại trước khi thêm mới
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    try {
                        Category theloai = dataSnapshot.getValue(Category.class);
                        if (theloai != null) {
                            arrCategories.add(theloai);
                        }
                    } catch (Exception e) {
                        Log.e("Firebase", "Error: " + e.getMessage());
                    }
                }
                // Cập nhật adapter khi dữ liệu đã thay đổi
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error: " + error.getMessage());
            }
        });

        mdata.child("TacGias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    try {
                        Author tacgia = dataSnapshot.getValue(Author.class);
                        arrAuthor.add(tacgia);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                adapter3.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý khi truy vấn bị lỗi
            }
        });
    }
}
