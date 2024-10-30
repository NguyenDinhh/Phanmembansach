package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Cart_Fragment extends Fragment {

    private ImageView back;
    private Button btn_checkout;
    private TextView select_voucher,select_address;
    private  Button btn_cancel;
    private FrameLayout frame_voucher;
    public Cart_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.cart_fagment, container, false);
        back = mView.findViewById(R.id.back);
        btn_checkout = mView.findViewById(R.id.btn_doi);
        btn_cancel = mView.findViewById(R.id.btn_cancel);
        select_voucher = mView.findViewById(R.id.select_voucher);
        select_address = mView.findViewById(R.id.select_address);
        frame_voucher  = mView.findViewById(R.id.frame_vouchers);
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
                startActivity(new Intent(getActivity(),AddressActivity.class));
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
        return  mView;
    }
}