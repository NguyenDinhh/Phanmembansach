package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AddressActivity extends AppCompatActivity {

    private RecyclerView lv;
    private ImageView back;
    private Button btnAdd;
    private AddressAdapter adapter;
    private ArrayList<Address> arrAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        back = findViewById(R.id.img_back);
        btnAdd = findViewById(R.id.btn_add_address);
        lv = findViewById(R.id.recyclerView);

        // Set up RecyclerView
        lv.setLayoutManager(new LinearLayoutManager(this));
        arrAddress = new ArrayList<>();
        populateAddressList();

        adapter = new AddressAdapter(arrAddress);
        lv.setAdapter(adapter);

        // Back button functionality
        back.setOnClickListener(view -> finish());

        // Add address button functionality
        btnAdd.setOnClickListener(view ->
                startActivity(new Intent(AddressActivity.this, AddAddressActivity.class))
        );
    }

    private void populateAddressList() {
        // Sample addresses
        arrAddress.add(new Address("Nguyễn Văn Thế Dinh", "0123456789", "02 Thanh Sơn Thanh Bình Hải Châu Đà Nẵng"));
        arrAddress.add(new Address("Lưu Hồng Nhung", "0123456789", "134 Trần Cao Vân  Hải Châu Đà Nẵng"));
        arrAddress.add(new Address("Lê Đức Tuấn Anh", "0123456789", "12 Quang Trung Hải Châu Đà Nẵng"));
        arrAddress.add(new Address("Nguyễn Văn Thế Dinh", "0123456789", "02 Thanh Sơn Thanh Bình Hải Châu Đà Nẵng"));
    }
}
