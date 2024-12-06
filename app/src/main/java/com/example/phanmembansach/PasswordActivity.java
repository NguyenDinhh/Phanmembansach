package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PasswordActivity extends AppCompatActivity {
    private ImageView back;
    private EditText oldp, newp, newp2;
    private Button ok;
    private DatabaseReference mdata = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        back = findViewById(R.id.img_back);
        oldp = findViewById(R.id.edtOldPassword);
        newp = findViewById(R.id.edtNewPassword);
        newp2 = findViewById(R.id.edtConfirmPassword);
        ok = findViewById(R.id.btnUpdatePassword);
        App app = (App) getApplicationContext();
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
                               if(taiKhoan.getTenDangNhap().equals(app.getUsername()))
                               {
                                    if(oldp.getText().toString().equals(taiKhoan.getMatKhau()))
                                    {
                                        if(newp.getText().toString().equals(newp2.getText().toString())==false)
                                        {
                                            Toast.makeText(PasswordActivity.this, "Nhap lai mat khau khong dung",Toast.LENGTH_SHORT).show();
                                            recreate();
                                            break;
                                        }
                                        else
                                        {
                                            String key = dataSnapshot.getKey();
                                            mdata.child("TaiKhoans").child(key).child("MatKhau").setValue(newp.getText().toString())
                                                    .addOnSuccessListener(aVoid -> {
                                                    })
                                                    .addOnFailureListener(e -> {
                                                    });
                                            Toast.makeText(PasswordActivity.this, "Cap nhat thanh cong",Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                    }else
                                    {
                                        Toast.makeText(PasswordActivity.this, "Sai mat khau",Toast.LENGTH_SHORT).show();
                                        recreate();
                                        break;
                                    }
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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
