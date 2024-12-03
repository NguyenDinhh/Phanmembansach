package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class RegisterActivity extends AppCompatActivity {
    private DatabaseReference mdata;
    private TextView txt_login;
    private Button btn_register;
    private EditText ten;
    private EditText email;
    private EditText sdt;
    private EditText tendangnhap;
    private EditText matkhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txt_login = findViewById( R.id.txt_login);
        btn_register = findViewById(R.id.btn_create);
        ten = findViewById(R.id.yourname_input);
        sdt = findViewById(R.id.numberphone_input);
        email = findViewById(R.id.email_input);
        tendangnhap = findViewById(R.id.tendangnhap_input);
        matkhau = findViewById(R.id.password_input);
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
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    btn_register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Boolean register = true;
            Integer idlastkhachhang=0;
            for (KhachHang khachHang : arrkhachhang)
            {
                if(email.getText().toString().equals(khachHang.getEmail()))
                {
                    Toast.makeText(RegisterActivity.this, "Email đã được sử dụng", Toast.LENGTH_SHORT).show();
                    register = false;
                    break;
                }
                if(sdt.getText().toString().equals(khachHang.getDienThoai()))
                {
                    Toast.makeText(RegisterActivity.this, "Số điện thoại đã được sử dụng", Toast.LENGTH_SHORT).show();
                    register = false;
                    break;
                }
                idlastkhachhang= khachHang.getKhachHangID();
            }
            for (TaiKhoan taiKhoan: arrtaikhoan)
            {
                if(tendangnhap.getText().toString().equals(taiKhoan.getTenDangNhap()))
                {
                    Toast.makeText(RegisterActivity.this, "Đã tồn tại tên đăng nhập đã được sử dụng", Toast.LENGTH_SHORT).show();
                    register = false;
                    break;
                }
            }
            if(register)
            {
                KhachHang khachHang = new KhachHang(idlastkhachhang+1, ten.getText().toString(), email.getText().toString(), sdt.getText().toString(), "Da Nang", 0);
                TaiKhoan taiKhoan = new TaiKhoan(null, idlastkhachhang+1 ,tendangnhap.getText().toString(), matkhau.getText().toString(), null, "Đang hoạt động");
                mdata.child("KhachHangs").child(String.valueOf(idlastkhachhang)).setValue(khachHang);
                mdata.child("TaiKhoans").child(String.valueOf(idlastkhachhang+3)).setValue(taiKhoan);
                Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }

        }
    });
    }
}