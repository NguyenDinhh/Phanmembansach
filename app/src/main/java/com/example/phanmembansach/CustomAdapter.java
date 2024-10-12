package com.example.phanmembansach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<com.example.phanmembansach.Contact> {
    private Context context;
    private int resource;
    private ArrayList<com.example.phanmembansach.Contact> contacts;

    public CustomAdapter(Context context, int resource, ArrayList<com.example.phanmembansach.Contact> contacts) {
        super(context, resource, contacts);
        this.context = context;
        this.resource = resource;
        this.contacts = contacts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Kiểm tra xem convertView có null không
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        // Lấy đối tượng Contact cho vị trí hiện tại
        com.example.phanmembansach.Contact contact = contacts.get(position);

        // Lấy TextView và ImageView từ convertView
        TextView txtName = convertView.findViewById(R.id.txtName);
        ImageView icon = convertView.findViewById(R.id.icon);

        // Thiết lập tên cho TextView
        txtName.setText(contact.getName());

        // Thiết lập biểu tượng cho ImageView dựa trên tên
        switch (contact.getName()) {
            case "Home":
                icon.setImageResource(R.drawable.ic_home);
                break;
            case "Profile":
                icon.setImageResource(R.drawable.ic_profile);
                break;
            case "Privacy":
                icon.setImageResource(R.drawable.ic_privacy);
                break;
            case "Manage your password":
                icon.setImageResource(R.drawable.ic_password);
                break;
            case "Your favourite":
                icon.setImageResource(R.drawable.ic_favourite_2);
                break;
            case "History":
                icon.setImageResource(R.drawable.ic_history);
                break;
            case "Coming soon":
                icon.setImageResource(R.drawable.ic_comingsoon);
                break;
            case "Contact Us":
                icon.setImageResource(R.drawable.ic_contact);
                break;
            case "About Us":
                icon.setImageResource(R.drawable.ic_infor);
                break;
            case "Log out":
                icon.setImageResource(R.drawable.ic_logout);
                break;

        }

        return convertView;
    }}