package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import  com.example.phanmembansach.Home;


public class Home_Fragment extends Fragment {
    private TextView see_more;

    public Home_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View mView=  inflater.inflate(R.layout.home_fragment, container, false);
        see_more = mView.findViewById(R.id.txt_see_more);
        EditText txt_search = mView.findViewById(R.id.txt_search);
        ConstraintLayout sm_r = mView.findViewById(R.id.see_more_recommendation);
        ConstraintLayout sm_c = mView.findViewById(R.id.see_more_categories);
        ConstraintLayout sm_a = mView.findViewById(R.id.see_more_author);
        ConstraintLayout sm_f= mView.findViewById(R.id.see_more_fantasy);
        txt_search.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP ) {
                if (event.getRawX() <= (txt_search.getLeft() + txt_search.getCompoundDrawables()[0].getBounds().width())) {
                    ((Home) getActivity()).setCurrentPage(4);
                    Toast.makeText(getContext(), "You have just searched", Toast.LENGTH_SHORT).show();
                }
            }
            return false;
        });
        see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Home) getActivity()).setCurrentPage(4);
            }
        });
        sm_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Home) getActivity()).setCurrentPage(7);
            }
        });
        sm_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Home) getActivity()).setCurrentPage(6);
            }
        });
        sm_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Home) getActivity()).setCurrentPage(5);
            }
        });
        sm_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Home) getActivity()).setCurrentPage(7);
            }
        });
        int[] idbooks = {R.id.home_book_1, R.id.home_book_2, R.id.home_book_3, R.id.home_book_4, R.id.home_book_5, R.id.home_book_6, R.id.home_book_7, R.id.home_book_8, R.id.home_book_9, R.id.home_book_10, R.id.home_book_11, R.id.home_book_12, R.id.home_book_13, R.id.home_book_14, R.id.home_book_15,};
        int[] idauthors = {R.id.home_author_1, R.id.home_author_2, R.id.home_author_3, R.id.home_author_4, R.id.home_author_5};
        for (int id : idbooks) {
            ConstraintLayout c = mView.findViewById(id);
            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), Detail_book.class));
                }
            });
        }
        for (int id : idauthors) {
            ConstraintLayout c = mView.findViewById(id);
            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), Detail_Author.class));
                }
            });
        }
        return mView;
    }
}