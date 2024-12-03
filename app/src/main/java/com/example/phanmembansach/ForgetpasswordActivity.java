package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ForgetpasswordActivity extends AppCompatActivity {
    private DatabaseReference mdata;
    private EditText password1;
    private  EditText password2;
    private Button btn_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        btn_ok = findViewById(R.id.ok_btn);
        password1 =findViewById(R.id.newpassword_input);
        password2 =findViewById(R.id.repeatnewpassword_input);
        Intent intent = getIntent();
        mdata = FirebaseDatabase.getInstance().getReference();
        ArrayList<KhachHang> arrkhachhang = new ArrayList<>();
        ArrayList<TaiKhoan> arrtaikhoan = new ArrayList<>();
        mdata.child("KhachHangs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    try
                    {
                        KhachHang khachHang = snapshot1.getValue(KhachHang.class);
                        arrkhachhang.add(khachHang);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mdata.child("TaiKhoans").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    try {
                        TaiKhoan taiKhoan = dataSnapshot.getValue(TaiKhoan.class);
                        arrtaikhoan.add(taiKhoan);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer key =0;
                if(password1.getText().toString().equals(password2.getText().toString()))
                {
                    for (KhachHang khachHang: arrkhachhang)
                    {
                        if(khachHang.getDienThoai().equals(intent.getStringExtra("sdt")))
                        {
                            for (TaiKhoan taiKhoan: arrtaikhoan)
                            {
                                if(taiKhoan.getKhachHangID().equals(khachHang.getKhachHangID()))
                                {
                                    mdata.child("TaiKhoans").child(String.valueOf(key)).child("MatKhau").setValue(password1.getText().toString())
                                            .addOnSuccessListener(aVoid -> {
                                                Log.d("Firebase", "Mật khẩu đã được cập nhật thành công!");
                                            })
                                            .addOnFailureListener(e -> {
                                                Log.e("Firebase", "Cập nhật mật khẩu thất bại", e);
                                            });

                                    startActivity(new Intent(ForgetpasswordActivity.this, LoginActivity.class));
                                }
                                key++;
                            }
                        }
                    }
                }
                else
                {
                    Toast.makeText(ForgetpasswordActivity.this, "Nhập lại mật khẩu không đúng!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}