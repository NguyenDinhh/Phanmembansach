package com.example.phanmembansach;

import android.app.Application;
import android.content.Intent;
import android.media.ExifInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Cart_Fragment extends Fragment {

    private ImageView back;
    private Button btn_checkout;
    private TextView select_voucher,select_address,tongtien;
    private  Button btn_cancel;
    private FrameLayout frame_voucher;
    private DatabaseReference mdata;
    private ListView lv;
    public Cart_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.cart_fagment, container, false);
        back = mView.findViewById(R.id.back);
        lv = mView.findViewById(R.id.lv);
        btn_checkout = mView.findViewById(R.id.btn_doi);
        mdata = FirebaseDatabase.getInstance().getReference();
        btn_cancel = mView.findViewById(R.id.btn_cancel);
        select_voucher = mView.findViewById(R.id.select_voucher);
        select_address = mView.findViewById(R.id.select_address);
        frame_voucher = mView.findViewById(R.id.frame_vouchers);
        tongtien = mView.findViewById(R.id.tongtien);
        App app = (App) getActivity().getApplicationContext();
        ArrayList<GioHang> arrgiohang = new ArrayList<>();
        Adapter_Cart_Item adapterCartItem = new Adapter_Cart_Item(getActivity(), R.layout.row_cart_item, arrgiohang);
        lv.setAdapter(adapterCartItem);
        int vouchers[] = {R.id.voucher_1, R.id.voucher_2, R.id.voucher_3, R.id.voucher_4};
        for (int voucher : vouchers) {
            LinearLayout l = mView.findViewById(voucher);
            l.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    frame_voucher.setVisibility(view.GONE);
                    Toast.makeText(getContext(), "Đã chọn voucher", Toast.LENGTH_SHORT).show();
                }
            });
        }

        select_voucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame_voucher.setVisibility(view.VISIBLE);
            }
        });
        select_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddressActivity.class));
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame_voucher.setVisibility(view.GONE);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        btn_checkout.setOnClickListener(view -> startActivity(new Intent(getActivity(), CheckoutActivity.class)));

        mdata.child("GioHangs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Double tien=0.0;
                arrgiohang.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    try {
                        GioHang gioHang = dataSnapshot.getValue(GioHang.class);
                        if(gioHang.getTenDangNhap().equals(app.getUsername()))
                        {
                            tien = tien+gioHang.getSoLuongMua()*gioHang.getGia();
                            arrgiohang.add(gioHang);
                        }
                    } catch (Exception e) {

                    }
                }
                adapterCartItem.notifyDataSetChanged();
                tongtien.setText("Tổng tiền: "+String.format("%.0f",tien)+" VND");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return mView;
    }
}