package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AddressMainActivity extends AppCompatActivity {
    private static final int ADD_ADDRESS_REQUEST = 1;
    private List<Address> addressList;
    private AddressAdapter addressAdapter;
    private RecyclerView recyclerView;
    private Button btnAddAddress;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        recyclerView = findViewById(R.id.recyclerView);
        btnAddAddress = findViewById(R.id.btn_add_address);
        imgBack = findViewById(R.id.img_back);

        addressList = new ArrayList<>();
        addressAdapter = new AddressAdapter(addressList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(addressAdapter);

        btnAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressMainActivity.this, AddAddressActivity.class);
                startActivityForResult(intent, ADD_ADDRESS_REQUEST);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ADDRESS_REQUEST && resultCode == RESULT_OK && data != null) {
            String name = data.getStringExtra("NAME");
            String phone = data.getStringExtra("PHONE");
            String address = data.getStringExtra("ADDRESS");
            Address newAddress = new Address(name, phone, address);
            addressList.add(newAddress);
            addressAdapter.notifyItemInserted(addressList.size() - 1);
        }
    }
}
