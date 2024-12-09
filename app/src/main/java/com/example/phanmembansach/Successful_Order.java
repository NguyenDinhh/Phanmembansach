package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class Successful_Order extends AppCompatActivity {

    private Button btn_continue;
    private TextView diemthuongnhan;
    private DatabaseReference mdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_order);
        btn_continue = findViewById(R.id.btn_continue);
        diemthuongnhan = findViewById(R.id.txt_diemthuongnhan);
        diemthuongnhan.setText("Số điểm thưởng nhận: "+String.valueOf(getIntent().getIntExtra("diemthuongnhan",0)));
        App app = (App) getApplicationContext();
        mdata = FirebaseDatabase.getInstance().getReference();
        mdata.child("KhachHangs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    try
                    {
                        if(dataSnapshot.getValue(KhachHang.class).getKhachHangID()==app.getKhachHangID())
                        {
                            mdata.child("KhachHangs").child(dataSnapshot.getKey()).child("Diemthuong").setValue((app.getDiemThuong())).addOnSuccessListener(aVoid -> {
                                        Log.d("Firebase", "Cập nhật thành công!");
                                    })
                                    .addOnFailureListener(e -> {
                                        Log.e("Firebase", "Cập nhật thất bại", e);
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
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Successful_Order.this,Home.class);
                intent.putExtra("fragment_cart",0);
                startActivity(intent);
            }
        });

    }
}