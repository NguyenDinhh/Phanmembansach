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
import android.widget.TextView;


public class Cart_Fragment extends Fragment {

    private ImageView back;
    private Button btn_checkout;
    private TextView select_voucher;
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
        btn_checkout = mView.findViewById(R.id.btn_checkout);
        btn_cancel = mView.findViewById(R.id.btn_cancel);
        select_voucher = mView.findViewById(R.id.select_voucher);
        frame_voucher  = mView.findViewById(R.id.frame_vouchers);
        select_voucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame_voucher.setVisibility(view.VISIBLE);
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame_voucher.setVisibility(view.GONE);
            }
        });
        back.setOnClickListener(view -> ((Home) getActivity()).setCurrentPage(0));
        btn_checkout.setOnClickListener(view -> startActivity(new Intent(getActivity(), CheckoutActivity.class)));
        return  mView;
    }
}