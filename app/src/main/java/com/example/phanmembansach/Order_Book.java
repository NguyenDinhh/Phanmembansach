package com.example.phanmembansach;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Order_Book extends AppCompatActivity {

    private TextView txt_back,ten,gia,giacu,giamgia,tacgia;
    ImageView img;
    private Button btn_order;
    DatabaseReference mdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_book);
        txt_back = findViewById(R.id.txt_tieude);
        btn_order = findViewById(R.id.btn_order);
        ten = findViewById(R.id.ten);
        gia = findViewById(R.id.gia);
        giacu   = findViewById(R.id.giacu);
        img = findViewById(R.id.img);
        tacgia  = findViewById(R.id.tacgia);
        mdata = FirebaseDatabase.getInstance().getReference();
        giacu.setPaintFlags(giacu.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        mdata.child("Sachs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    try
                    {
                        Book book = dataSnapshot.getValue(Book.class);
                        Integer tacgiaID = book.getTacgiaID();
                        mdata.child("TacGias").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    try
                                    {
                                        Author tacgia = dataSnapshot.getValue(Author.class);
                                        if(tacgiaID==tacgia.getTacGiaID())
                                            book.setTenTacGia(tacgia.getTen());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                // Xử lý khi truy vấn bị lỗi
                            }
                        });
                        if(book.getSachID()== getIntent().getIntExtra("SachID",0))
                        {
                            ten.setText(book.getTen());
                            tacgia.setText(book.getTenTacGia());
                            int imageResId = getResources().getIdentifier(book.getAnh(), "drawable", getPackageName());
                            if (imageResId != 0) {
                                img.setImageResource(imageResId);
                            } else {
                                img.setImageResource(R.drawable.andrzej_sapkowski);  // Mặc định nếu không tìm thấy ảnh
                            }
                            gia.setText(String.format("%.0f", (book.getGia() - book.getGia() * book.getSale())) + "đ");
                            giacu.setText(String.format("%.0f", book.getGia()) + "đ");
                            giamgia.setText("-" +String.format("%.0f", book.getSale() * 100) + "%");
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
        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        App app = (App) getApplicationContext();
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(app.isLoggedIn()==false)
                {
                    Toast.makeText(Order_Book.this,"Bạn cần đăng nhập",Toast.LENGTH_SHORT).show();
                }
                else {
                    mdata.child("GioHangs").addListenerForSingleValueEvent(new ValueEventListener()
                    {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Boolean tontai= false;
                            for (DataSnapshot dataSnapshot : snapshot.getChildren())
                            {
                                try {
                                    GioHang gioHang = dataSnapshot.getValue(GioHang.class);
                                    if(gioHang.getTenDangNhap().equals(app.getUsername()) && gioHang.getSachID()==getIntent().getIntExtra("SachID",0))
                                    {
                                        Intent intent = new Intent(Order_Book.this, Home.class);
                                        intent.putExtra("fragment_cart", 2);  // Truyền số trang bạn muốn
                                        startActivity(intent);
                                        tontai = true;
                                        break;
                                    }
                                } catch (Exception e) {
                                }
                            }
                            if(tontai == false)
                            {
                                mdata.child("Sachs").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                                        {
                                            try
                                            {
                                                Book book = dataSnapshot.getValue(Book.class);
                                                Integer tacgiaID = book.getTacgiaID();
                                                mdata.child("TacGias").addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                                            try
                                                            {
                                                                Author tacgia = dataSnapshot.getValue(Author.class);
                                                                if(tacgiaID==tacgia.getTacGiaID())
                                                                    book.setTenTacGia(tacgia.getTen());
                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    }
                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                        // Xử lý khi truy vấn bị lỗi
                                                    }
                                                });
                                                if(book.getSachID()== getIntent().getIntExtra("SachID",0))
                                                {
                                                    GioHang gioHang1 = new GioHang(app.getUsername(),book.getSachID(),book.getTen(),book.getAnh(), book.getSoLuong(), book.getGia(), 1,0);
                                                    mdata.child("GioHangs").push().setValue(gioHang1);
                                                    Intent intent = new Intent(Order_Book.this, Home.class);
                                                    intent.putExtra("fragment_cart", 2);  // Truyền số trang bạn muốn
                                                    startActivity(intent);
                                                    break;
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
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });
    }
}