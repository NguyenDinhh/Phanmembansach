package com.example.phanmembansach;

import android.content.Context;
import android.content.Intent;
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
            case "Trang chủ":
                icon.setImageResource(R.drawable.ic_home);
                break;
            case "Trang cá nhân":
                icon.setImageResource(R.drawable.ic_profile);
                break;
            case "Kho voucher":
                icon.setImageResource(R.drawable.ic_voucher);
                break;
            case "Địa chỉ giao hàng":
                icon.setImageResource(R.drawable.ic_home);
                break;
            case "Riêng tư":
                icon.setImageResource(R.drawable.ic_privacy);
                break;
            case "Thay đổi mật khẩu":
                icon.setImageResource(R.drawable.ic_password);
                break;
            case "Yêu thích":
                icon.setImageResource(R.drawable.ic_favourite_2);
                break;
            case "Lịch sử":
                icon.setImageResource(R.drawable.ic_history);
                break;
            case "Sách sắp tới":
                icon.setImageResource(R.drawable.ic_history);
                break;
            case "Liên hệ":
                icon.setImageResource(R.drawable.ic_contact);
                break;
            case "Về chúng tôi":
                icon.setImageResource(R.drawable.ic_infor);
                break;
            case "Đăng xuất":
                icon.setImageResource(R.drawable.ic_logout);
                break;
        }

        return convertView;
    }}