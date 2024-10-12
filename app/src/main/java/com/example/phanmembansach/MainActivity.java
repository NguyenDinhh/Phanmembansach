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


        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.row_listview, arrContact);
        lvContact.setAdapter(customAdapter);


        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedContact = arrContact.get(position).getName();

                Intent intent = null;


                switch (selectedContact) {
                    case "Home":
                        intent = new Intent(MainActivity.this,Home.class);
                        break;
                    case "Profile":
                        intent = new Intent(MainActivity.this, ProfileActivity.class);
                        break;
                    case "Privacy":
                        intent = new Intent(MainActivity.this, PrivacyActivity.class);
                        break;
                    case "Manage your password":
                        intent = new Intent(MainActivity.this, PasswordActivity.class);
                        break;
                    case "Your favourite":
                        intent = new Intent(MainActivity.this, YourfavouriteActivity.class);
                        break;
                    case "History":
                        intent = new Intent(MainActivity.this, HistoryActivity.class);
                        break;
                    case "Coming soon":
                        intent = new Intent(MainActivity.this, ComingsoonActivity.class);
                        break;
                    case "Contact Us":
                        intent = new Intent(MainActivity.this, ContactusActivity.class);
                        break;
                    case "About Us":
                        intent = new Intent(MainActivity.this, AboutusActivity.class);
                        break;
                    case "Log out":
                        intent = new Intent(MainActivity.this, LoginActivity.class);
                        break;
                }


                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
    }
}
