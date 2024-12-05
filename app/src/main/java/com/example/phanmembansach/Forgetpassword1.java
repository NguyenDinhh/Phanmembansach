package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Forgetpassword1 extends AppCompatActivity {
    private TextView txt_login;
    private Button btn_ok;
    private EditText sdt;
    DatabaseReference mdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword1);
        txt_login = findViewById(R.id.txt_login);
        btn_ok = findViewById(R.id.btn_reset_password);
        sdt = findViewById(R.id.numberphone_input);
        mdata = FirebaseDatabase.getInstance().getReference();
        ArrayList<TaiKhoan> arrtaikhoan = new ArrayList<>();
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
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Forgetpassword1.this, LoginActivity.class));
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdata.child("KhachHangs").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Boolean isexist =false;
                        for (DataSnapshot snapshot1 : snapshot.getChildren())
                        {
                            try
                            {
                                KhachHang khachHang = snapshot1.getValue(KhachHang.class);
                                if(khachHang.getDienThoai().equals(sdt.getText().toString()))
                                {
                                    Intent intent = new Intent(Forgetpassword1.this, Forgetpassword2.class);
                                    intent.putExtra("sdt", sdt.getText().toString());
                                    isexist =true;
                                    startActivity(intent);
                                    break;
                                }
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                        if (isexist==false)
                            Toast.makeText(Forgetpassword1.this, "Sai số điện thoại",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}