package com.example.phanmembansach;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
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

public class LoginActivity extends AppCompatActivity {
    DatabaseReference mdata;
    TextView textViewForgotPassword;
    private  Button btn_login;
    private  TextView txt_register;
    private EditText username;
    private  EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Liên kết TextView với phần tử "Forgotten password?" trong layout
        textViewForgotPassword = findViewById(R.id.textview_forgot_password);
        txt_register = findViewById(R.id.txt_register);
        username = findViewById(R.id.username_input);
        password = findViewById(R.id.password_input);
        ArrayList<TaiKhoan> arrtaikhoan = new ArrayList<>();
        mdata = FirebaseDatabase.getInstance().getReference();
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
        for (TaiKhoan taiKhoan: arrtaikhoan)
        {
                if(taiKhoan.getNhanVienID()  ==  null)
                    arrtaikhoan.remove(taiKhoan);
        }
        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang ForgetPasswordActivity
                Intent intent = new Intent(LoginActivity.this, Forgetpassword1.class);
                startActivity(intent);
            }
        });
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int m=0;
                for (TaiKhoan taiKhoan : arrtaikhoan)
                {
                    if(username.getText().toString().equals(taiKhoan.getTenDangNhap()))
                    {
                        if(password.getText().toString().equals(taiKhoan.getMatKhau()) && taiKhoan.getTinhTrang().equals("Đang hoạt động"))
                        {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            m=1;
                            Intent intent = new Intent(LoginActivity.this, Home.class);
                            App app = (App) getApplicationContext();
                            app.setLoggedIn(true);
                            app.setUsername(taiKhoan.getTenDangNhap());
                            app.setKhachHangID(taiKhoan.getKhachHangID());
                            mdata.child("KhachHangs").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for(DataSnapshot dataSnapshot : snapshot.getChildren())
                                    {
                                        try
                                        {
                                            if(taiKhoan.getKhachHangID().equals(dataSnapshot.getValue(KhachHang.class).getKhachHangID()))
                                            {
                                                app.setDiemThuong(dataSnapshot.getValue(KhachHang.class).getDiemThuong());
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
                            startActivity(intent);
                        }else
                        {
                            m= -1;
                            Toast.makeText(LoginActivity.this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }
                if(m==0)
                    Toast.makeText(LoginActivity.this, "Sai tên đăng nhập", Toast.LENGTH_SHORT).show();
            }
        });
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}
