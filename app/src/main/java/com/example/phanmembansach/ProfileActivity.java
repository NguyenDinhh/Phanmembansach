package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ProfileActivity extends AppCompatActivity {
    private ImageView back;
    private EditText ten,email,sdt,diachi;
    private ImageView img;
    private Button ok;
    private DatabaseReference mdata = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ImageView buttonBack = findViewById(R.id.img_back);
        ten = findViewById(R.id.ten);
        diachi   = findViewById(R.id.diachi);
        email = findViewById(R.id.email);
        sdt = findViewById(R.id.sdt);
        img = findViewById(R.id.img);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, Home.class);
                intent.putExtra("fragment_cart", 3);
                startActivity(intent);
            }
        });
        App app = (App) getApplicationContext();
        mdata.child("TaiKhoans").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    try
                    {
                        TaiKhoan taiKhoan = dataSnapshot.getValue(TaiKhoan.class);
                        if(app.getUsername().equals(taiKhoan.getTenDangNhap()))
                        {
                            mdata.child("KhachHangs").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                        KhachHang khachHang= dataSnapshot.getValue(KhachHang.class);
                                        if (khachHang.getKhachHangID().equals(taiKhoan.getKhachHangID()))
                                        {
                                            ten.setText(khachHang.getTen());
                                            email.setText(khachHang.getEmail());
                                            diachi.setText(khachHang.getDiaChi());
                                            sdt.setText(khachHang.getDienThoai());
                                            if (taiKhoan.getAnh() != null) {
                                                int imageResId = getResources().getIdentifier(taiKhoan.getAnh(), "drawable",getPackageName());
                                                if (imageResId != 0) {
                                                    img.setImageResource(imageResId);
                                                } else {
                                                    img.setImageResource(R.drawable.andrzej_sapkowski);  // Ảnh mặc định
                                                }
                                            }
                                            break;
                                        }
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    // Lỗi khi đọc dữ liệu từ Firebase
                                    Toast.makeText(ProfileActivity.this , "Lỗi khi đọc dữ liệu: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
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
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdata.child("TaiKhoans").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot: snapshot.getChildren())
                        {
                            try
                            {
                                TaiKhoan taiKhoan = dataSnapshot.getValue(TaiKhoan.class);
                                if(app.getUsername().equals(taiKhoan.getTenDangNhap()))
                                {
                                    mdata.child("KhachHangs").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                                KhachHang khachHang= dataSnapshot.getValue(KhachHang.class);
                                                if (khachHang.getKhachHangID().equals(taiKhoan.getKhachHangID()))
                                                {
                                                    String key = dataSnapshot.getKey();
                                                    mdata.child("KhachHangs").child(key).child("Ten").setValue(ten.getText().toString())
                                                            .addOnSuccessListener(aVoid -> {
                                                            })
                                                            .addOnFailureListener(e -> {
                                                            });
                                                    mdata.child("KhachHangs").child(key).child("Email").setValue(email.getText().toString())
                                                            .addOnSuccessListener(aVoid -> {
                                                            })
                                                            .addOnFailureListener(e -> {
                                                            });
                                                    mdata.child("KhachHangs").child(key).child("DiaChi").setValue(diachi.getText().toString())
                                                            .addOnSuccessListener(aVoid -> {
                                                            })
                                                            .addOnFailureListener(e -> {
                                                            });
                                                    mdata.child("KhachHangs").child(key).child("DienThoai").setValue(ten.getText().toString())
                                                            .addOnSuccessListener(aVoid -> {
                                                            })
                                                            .addOnFailureListener(e -> {
                                                            });
                                                    break;
                                                }
                                            }
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            // Lỗi khi đọc dữ liệu từ Firebase
                                            Toast.makeText(ProfileActivity.this , "Lỗi khi đọc dữ liệu: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
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
        });
    }
}
