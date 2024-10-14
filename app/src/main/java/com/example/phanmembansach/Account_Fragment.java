package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class Account_Fragment extends Fragment {
    private ListView lvContact;
    public Account_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView=  inflater.inflate(R.layout.account_fragment, container, false);
        lvContact = mView.findViewById(R.id.lvContact);
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

        CustomAdapter customAdapter = new CustomAdapter(getActivity(), R.layout.row_listview, arrContact);
        lvContact.setAdapter(customAdapter);
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedContact = arrContact.get(position).getName();
                Intent intent = null;
                switch (selectedContact) {
                    case "Home":
                        intent = new Intent(getActivity(), Home.class);
                        break;
                    case "Profile":
                        intent = new Intent(getActivity(), ProfileActivity.class);
                        break;
                    case "Privacy":
                        intent = new Intent(getActivity(), PrivacyActivity.class);
                        break;
                    case "Manage your password":
                        intent = new Intent(getActivity(), PasswordActivity.class);
                        break;
                    case "Your favourite":
                        intent = new Intent(getActivity(), YourfavouriteActivity.class);
                        break;
                    case "History":
                        intent = new Intent(getActivity(),HistoryActivity.class);
                        break;
                    case "Coming soon":
                        intent = new Intent(getActivity(), ComingsoonActivity.class);
                        break;
                    case "Contact Us":
                        intent = new Intent(getActivity(), ContactusActivity.class);
                        break;
                    case "About Us":
                        intent = new Intent(getActivity(), AboutusActivity.class);
                        break;
                    case "Log out":
                        intent = new Intent(getActivity(), LoginActivity.class);
                        break;
                }

                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
        return  mView;
    }
}