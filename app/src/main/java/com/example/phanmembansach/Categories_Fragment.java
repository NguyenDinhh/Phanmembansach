package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Categories_Fragment extends Fragment {
    DatabaseReference mdata;
    Adapter_Categories adapter ;
    public Categories_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.categories_fragment, container, false);
        ImageView back = mView.findViewById(R.id.img_back);
       ListView  lv = mView.findViewById(R.id.lvcategories);
       mdata = FirebaseDatabase.getInstance().getReference();
        ArrayList<Category> arrCategories = new ArrayList<>();
       mdata.child("TheLoais").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                   try {
                       Category theloai= dataSnapshot.getValue(Category.class);
                       arrCategories.add(theloai);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
               adapter.notifyDataSetChanged();
           }
           @Override
           public void onCancelled(@NonNull DatabaseError error)
           {
           }
       });
        adapter = new Adapter_Categories(getActivity(),R.layout.row_categories, arrCategories);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            ((Home) getActivity()).setCurrentPage(7);
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Home) getActivity()).setCurrentPage(0);
            }
        });
        return  mView;
    }
}