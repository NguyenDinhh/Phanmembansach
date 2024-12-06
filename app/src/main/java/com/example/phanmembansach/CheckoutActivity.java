package com.example.phanmembansach;

import android.content.Intent;
import android.media.ExifInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
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

public class CheckoutActivity extends AppCompatActivity {

    ImageView img_exit;
    TextView tien;
    Button btn_order;
    private DatabaseReference mdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        img_exit = findViewById(R.id.img);
        btn_order = findViewById(R.id.btn_order);
        tien = findViewById(R.id.tien);
        mdata = FirebaseDatabase.getInstance().getReference();
        Intent intent = getIntent();
        tien.setText(intent.getStringExtra("tien"));
        App app = (App) getApplicationContext();
        img_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdata.child("DonHangs").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final Integer[] madonhang = {0};
                        final Integer[] madiachi = {0};
                        final Integer[] machitietdonhang = {0};

                        // Lấy madonhang
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            try {
                                madonhang[0] = dataSnapshot.getValue(DonHang.class).getDonHangID();
                            } catch (Exception e) {
                                // Handle exception
                            }
                        }

                        // Sau khi lấy madonhang, tiếp tục lấy giá trị từ GioHangs
                        mdata.child("GioHangs").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                // Lấy madiachi
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    try {
                                        if(app.getUsername().equals(dataSnapshot.getValue(GioHang.class).getTenDangNhap()))
                                            madiachi[0] = dataSnapshot.getValue(GioHang.class).getDiaChiNhanHangID();
                                         } catch (Exception e) {
                                        // Handle exception
                                    }
                                }
                             // Tạo đối tượng DonHang
                                DonHang donHang = new DonHang(madiachi[0], madonhang[0] + 1, app.getUsername(), "Đang xử lý");
                                mdata.child("DonHangs").push().setValue(donHang);

                                // Tiếp tục lấy ChiTietDonHangs
                                mdata.child("ChiTietDonHangs").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        // Lấy machitietdonhang
                                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                            try {
                                                machitietdonhang[0] = dataSnapshot.getValue(Chi_Tiet_Don_Hang.class).getChiTietDonHangID();
                                            } catch (Exception e) {
                                                // Handle exception
                                            }
                                        }

                                        // Sau khi có đủ các giá trị cần thiết, tiếp tục xử lý GioHangs và tạo ChiTietDonHang
                                        mdata.child("GioHangs").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                                    try {
                                                        GioHang gioHang = dataSnapshot.getValue(GioHang.class);
                                                        if (gioHang.getTenDangNhap().equals(app.getUsername())) {
                                                            // Tạo ChiTietDonHang
                                                            Chi_Tiet_Don_Hang chiTietDonHang = new Chi_Tiet_Don_Hang(machitietdonhang[0] + 1, madonhang[0] + 1, gioHang.getGia(), gioHang.getSoLuongMua(), gioHang.getSachID());
                                                            mdata.child("ChiTietDonHangs").push().setValue(chiTietDonHang);
                                                            machitietdonhang[0]++;
                                                            // Xóa mục trong GioHangs
                                                            mdata.child("GioHangs").child(dataSnapshot.getKey()).removeValue()
                                                                    .addOnSuccessListener(aVoid -> {
                                                                        Log.d("Firebase", "Xóa thành công!");
                                                                    })
                                                                    .addOnFailureListener(e -> {
                                                                        Log.e("Firebase", "Xóa thất bại", e);
                                                                    });
                                                        }
                                                    } catch (Exception e) {
                                                        // Handle exception
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                Log.e("Firebase", "Lỗi khi đọc GioHangs", error.toException());
                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Log.e("Firebase", "Lỗi khi đọc ChiTietDonHangs", error.toException());
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.e("Firebase", "Lỗi khi đọc GioHangs", error.toException());
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("Firebase", "Lỗi khi đọc DonHangs", error.toException());
                    }
                });
        startActivity(new Intent(CheckoutActivity.this, Successful_Order.class));
            }
        });

}
}