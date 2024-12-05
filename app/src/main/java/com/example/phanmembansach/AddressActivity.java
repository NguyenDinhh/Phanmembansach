package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddressActivity extends AppCompatActivity {

    private DatabaseReference mdata;
    private ListView lv;
    private ImageView back;
    private Button btnAdd;
    private AddressAdapter adapter;
    private ArrayList<Address> arrAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        mdata = FirebaseDatabase.getInstance().getReference();
        back = findViewById(R.id.img_back);
        btnAdd = findViewById(R.id.ok);
        lv = findViewById(R.id.lv);
        App app = (App) getApplicationContext();
        arrAddress = new ArrayList<>();

        adapter = new AddressAdapter(this,R.layout.item_address, arrAddress);
        lv.setAdapter(adapter);

        mdata.child("DiaChiNhanHangs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrAddress.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    try {
                        Address address = dataSnapshot.getValue(Address.class);
                        if(address.getTenDangNhap().equals(app.getUsername()))
                        {
                            arrAddress.add(address);
                        }
                    }catch (Exception e)
                    {

                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // Back button functionality
        back.setOnClickListener(view -> finish());

        // Add address button functionality
        btnAdd.setOnClickListener(view ->
                startActivity(new Intent(AddressActivity.this, AddAddressActivity.class))
        );
    }

}
