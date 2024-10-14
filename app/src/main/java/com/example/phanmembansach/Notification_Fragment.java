package com.example.phanmembansach;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class Notification_Fragment extends Fragment {
    private ImageView back;
    private ImageView img_reload;
    private ImageView img_menu;
    public Notification_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.notification_fragment, container, false);
        img_reload = mView.findViewById(R.id.img_reload);
        img_menu = mView.findViewById(R.id.img_menu);
        back = mView.findViewById(R.id.img_back);



        back.setOnClickListener(view -> getActivity().finish());
        img_reload.setOnClickListener(view -> getActivity().recreate());

        img_menu.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(getActivity(), view);
            popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.option1) {
                    Toast.makeText(getContext(), "Bạn đã chọn Make all as reader", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.option2) {
                    Toast.makeText(getContext(), "Bạn đã chọn Make all as unreader", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            });
            popupMenu.show(); // Hiển thị menu
        });
        return mView;
    }
}