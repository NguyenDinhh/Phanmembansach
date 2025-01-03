package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Diem_Thuong_Books_Fragment extends Fragment {

    public Diem_Thuong_Books_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView =  inflater.inflate(R.layout.diem__thuong__books_fragment, container, false);
        ListView lv = mView.findViewById(R.id.lvcategories_book);
        ImageView back = mView.findViewById(R.id.img_back);
        EditText txt_search = mView.findViewById(R.id.txt_search);
        txt_search.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() <= (txt_search.getLeft() + txt_search.getCompoundDrawables()[0].getBounds().width())) {
                    // Trong một phương thức như onClick
                    Toast.makeText(getContext(), "You have just searched", Toast.LENGTH_SHORT).show();

                }
            }
            return false;
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Home) getActivity()).setCurrentPage(0);
            }
        });
        ArrayList<Book> arrBook = new ArrayList<>();


        Adapter_Diem_Thuong_Books adapter = new Adapter_Diem_Thuong_Books(getActivity(), R.layout.row_diemthuong, arrBook);
        lv.setAdapter(adapter);

        return  mView;
    }
}