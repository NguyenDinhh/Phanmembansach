package com.example.phanmembansach;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class All_Book_Fragment extends Fragment {

    DatabaseReference mdata;
    public All_Book_Fragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView =  inflater.inflate(R.layout.all__book_fragment, container, false);
        ListView lv = mView.findViewById(R.id.lvcategories_book);
        ImageView back = mView.findViewById(R.id.img_back);
        EditText txt_search = mView.findViewById(R.id.txt_search);
        mdata = FirebaseDatabase.getInstance().getReference();
        ArrayList<Book> arrBook = new ArrayList<>();
        Adapter_Categories_books adapter = new Adapter_Categories_books(getActivity(), R.layout.row_categories_books, arrBook);
        Bundle arguments = getArguments();
        if (arguments != null) {
            Integer key = arguments.getInt("key", -1); // Lấy dữ liệu từ Bundle
        }
        mdata.child("Sachs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrBook.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    try {
                        Book book = dataSnapshot.getValue(Book.class);
                        Integer tacgiaID = book.getTacgiaID();
                        mdata.child("TacGias").child(tacgiaID.toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                try {
                                    Author tacGia = dataSnapshot.getValue(Author.class);
                                    if (tacGia != null) {
                                        book.setTenTacGia(tacGia.getTen());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                // Xử lý khi truy vấn bị lỗi
                            }
                        });
                        arrBook.add(book);
                        adapter.notifyDataSetChanged(); // Cập nhật giao diện khi tất cả sách đã được tải
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý khi truy vấn bị lỗi
            }
        });

        lv.setAdapter(adapter);
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


        return  mView;
    }
}