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
        arrContact.add(new Contact("Trang chủ"));
        arrContact.add(new Contact("Hồ sơ"));
        arrContact.add(new Contact("Quyền riên tư"));
        arrContact.add(new Contact("Quản lý mật khẩu của bạn"));
        arrContact.add(new Contact("Yêu thích"));
        arrContact.add(new Contact("Địa chỉ giao hàng"));
        arrContact.add(new Contact("Lịch sử"));
        arrContact.add(new Contact("Sắp ra mắt"));
        arrContact.add(new Contact("Liên hệ với chúng tôi"));
        arrContact.add(new Contact("Về phía chúng tôi"));
        arrContact.add(new Contact("Đăng xuất"));

        CustomAdapter customAdapter = new CustomAdapter(getActivity(), R.layout.row_listview, arrContact);
        lvContact.setAdapter(customAdapter);
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedContact = arrContact.get(position).getName();
                Intent intent = null;
                switch (selectedContact) {
                    case "Trang chủ":
                        intent = new Intent(getActivity(), Home.class);
                        break;
                    case "Hồ sơ":
                        intent = new Intent(getActivity(), ProfileActivity.class);
                        break;
                    case "Quyền riên tư":
                        intent = new Intent(getActivity(), PrivacyActivity.class);
                        break;
                    case "Quản lý mật khẩu của bạn":
                        intent = new Intent(getActivity(), PasswordActivity.class);
                        break;
                    case "Yêu thích":
                        intent = new Intent(getActivity(), YourfavouriteActivity.class);
                        break;
                    case "Địa chỉ giao hàng":
                        intent = new Intent(getActivity(), AddressActivity.class);
                        break;
                    case "Lịch sử":
                        intent = new Intent(getActivity(),HistoryActivity.class);
                        break;
                    case "Sắp ra mắt":
                        intent = new Intent(getActivity(), ComingsoonActivity.class);
                        break;
                    case "Liên hệ với chúng tôi":
                        intent = new Intent(getActivity(), ContactusActivity.class);
                        break;
                    case "Về phía chúng tôi":
                        intent = new Intent(getActivity(), AboutusActivity.class);
                        break;
                    case "Đăng xuất":
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