package com.example.phanmembansach;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddAddressActivity extends AppCompatActivity {
    private EditText etName, etPhone, etAddress;
    private Button btnSave;
    private DatabaseReference mdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        etName = findViewById(R.id.ten);
        etPhone = findViewById(R.id.sdt);
        etAddress = findViewById(R.id.diachi);
        btnSave = findViewById(R.id.btn_save);
        mdata = FirebaseDatabase.getInstance().getReference();
        App app = (App) getApplicationContext();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdata.child("DiaChiNhanHangs").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Address address = new Address(etName.getText().toString(),etPhone.getText().toString(), (int)snapshot.getChildrenCount()+1,etAddress.getText().toString(),app.getUsername());
                        mdata.child("DiaChiNhanHangs").push().setValue(address);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                    Toast.makeText(AddAddressActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
