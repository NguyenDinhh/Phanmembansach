package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Account_Fragment extends Fragment {
    private ListView lvContact;
    private TextView ten;
    private  TextView diemthuong;
    private DatabaseReference mdata;
    public Account_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView=  inflater.inflate(R.layout.account_fragment, container, false);
        lvContact = mView.findViewById(R.id.lvContact);
        ten = mView.findViewById(R.id.ten);
        mdata = FirebaseDatabase.getInstance().getReference();
        diemthuong = mView.findViewById(R.id.diemthuong);
        App app = (App) getActivity().getApplicationContext();
        if(app.isLoggedIn())
        {
            mdata.child("TaiKhoans").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot: snapshot.getChildren())
                    {
                        try {
                            TaiKhoan taiKhoan = dataSnapshot.getValue(TaiKhoan.class);
                            if(taiKhoan.getTenDangNhap().equals(app.getUsername()))
                            {
                                mdata.child("KhachHangs").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot snapshot1 : snapshot.getChildren())
                                        {
                                            try
                                            {
                                                KhachHang khachHang = snapshot1.getValue(KhachHang.class);
                                                if(khachHang.getKhachHangID()== taiKhoan.getKhachHangID())
                                                {
                                                    ten.setText(khachHang.getTen().toString());
                                                    diemthuong.setText("Điểm thưởng: " + String.valueOf(khachHang.getDiemThuong()));
                                                }
                                            }catch (Exception e)
                                            {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        ArrayList<Contact> arrContact = new ArrayList<>();
        arrContact.add(new Contact("Trang chủ"));
        arrContact.add(new Contact("Trang cá nhân"));
        arrContact.add(new Contact("Kho voucher"));
        arrContact.add(new Contact("Địa chỉ giao hàng"));
        arrContact.add(new Contact("Riêng tư"));
        arrContact.add(new Contact("Thay đổi mật khẩu"));
        arrContact.add(new Contact("Yêu thích"));
        arrContact.add(new Contact("Lịch sử"));
        arrContact.add(new Contact("Sách sắp tới"));
        arrContact.add(new Contact("Liên hệ"));
        arrContact.add(new Contact("Về chúng tôi"));
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
                    case "Trang cá nhân":
                        intent = new Intent(getActivity(), ProfileActivity.class);
                        break;
                    case "Kho voucher":
                        intent = new Intent(getActivity(), Vouchers.class);
                        break;
                    case "Địa chỉ giao hàng":
                        intent = new Intent(getActivity(), AddressActivity.class);
                        break;
                    case "Riêng tư":
                        intent = new Intent(getActivity(), PrivacyActivity.class);
                        break;
                    case "Thay đổi mật khẩu":
                        intent = new Intent(getActivity(), PasswordActivity.class);
                        break;
                    case "Yêu thích":
                        intent = new Intent(getActivity(), YourfavouriteActivity.class);
                        break;
                    case "Lịch sử":
                        intent = new Intent(getActivity(),HistoryActivity.class);
                        break;
                    case "Sách sắp tới":
                        intent = new Intent(getActivity(), ComingsoonActivity.class);
                        break;
                    case "Liên hệ":
                        intent = new Intent(getActivity(), ContactusActivity.class);
                        break;
                    case "Về chúng tôi":
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