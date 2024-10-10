package com.example.phanmembansach;

import android.content.Intent;  // Thêm import này
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvContact = findViewById(R.id.lvContact);

        // Tạo ArrayList chứa các Contact
        ArrayList<Contact> arrContact = new ArrayList<>();
        arrContact.add(new Contact("Home"));
        arrContact.add(new Contact("Profile"));
        arrContact.add(new Contact("Privacy"));
        arrContact.add(new Contact("Manage your password"));
        arrContact.add(new Contact("Your favourite"));
        arrContact.add(new Contact("History"));
        arrContact.add(new Contact("Coming soon"));
        arrContact.add(new Contact("Contact Us"));
        arrContact.add(new Contact("About Us"));
        arrContact.add(new Contact("Log out"));

        // Thiết lập Adapter cho ListView
        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.row_listview, arrContact);
        lvContact.setAdapter(customAdapter);

        // Thiết lập OnItemClickListener cho ListView
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy tên Contact được chọn
                String selectedContact = arrContact.get(position).getName();

                // Tạo biến Intent
                Intent intent = null;

                // Thực hiện hành động dựa trên mục được chọn
                switch (selectedContact) {
                    case "Home":
                        // Xử lý hành động cho Home
                        break;
                    case "Profile":
                        // Mở ProfileActivity
                        intent = new Intent(MainActivity.this, ProfileActivity.class);
                        break;
                    case "Privacy":
                        intent = new Intent(MainActivity.this, PrivacyActivity.class);
                        break;
                    case "Manage your password":
                        intent = new Intent(MainActivity.this, PasswordActivity.class);
                        break;
                    case "Your favourite":
                        // Xử lý hành động cho Your favourite
                        break;
                    case "History":
                        // Xử lý hành động cho History
                        break;
                    case "Coming soon":
                        // Xử lý hành động cho Coming soon
                        break;
                    case "Contact Us":
                        // Xử lý hành động cho Contact Us
                        break;
                    case "About Us":
                        intent = new Intent(MainActivity.this, AboutusActivity.class);
                        break;
                    case "Log out":
                        // Xử lý hành động cho Log out
                        break;
                }

                // Nếu intent không null thì khởi chạy Activity
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
    }
}
