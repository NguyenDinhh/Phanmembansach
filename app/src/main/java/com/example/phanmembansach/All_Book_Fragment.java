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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.TransferQueue;


public class All_Book_Fragment extends Fragment {

    TextView txt;
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
        txt = mView.findViewById(R.id.txt_tieude);
        ImageView back = mView.findViewById(R.id.img_back);
        EditText txt_search = mView.findViewById(R.id.txt_search);
        mdata = FirebaseDatabase.getInstance().getReference();
        ArrayList<Book> arrBook = new ArrayList<>();
        ArrayList<Author> arrauthor = new ArrayList<>();
        Adapter_Categories_books adapter = new Adapter_Categories_books(getActivity(), R.layout.row_categories_books, arrBook);
        mdata.child("Sachs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrBook.clear();
                Bundle arguments = getArguments();
                Integer theLoaiID = 0;
                if (arguments != null)
                {
                    theLoaiID = arguments.getInt("TheLoaiID",0);
                    txt.setText(arguments.getString("TenTheLoai","Tất cả sách"));
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    try {
                        Book book = dataSnapshot.getValue(Book.class);
                        Integer tacgiaID = book.getTacgiaID();
                        mdata.child("TacGias").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    try
                                    {
                                        Author tacgia = dataSnapshot.getValue(Author.class);
                                        if(tacgiaID==tacgia.getTacGiaID())
                                            book.setTenTacGia(tacgia.getTen());
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
                        if (theLoaiID != 0) {
                            // Nếu có TheLoaiID, chỉ thêm sách thuộc thể loại này
                            if (book.getTheLoaiID() == theLoaiID) {
                                arrBook.add(book);
                            }
                        }
                        else {
                            // Nếu không có TheLoaiID (hoặc là 0), thêm tất cả sách
                            arrBook.add(book);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                adapter.notifyDataSetChanged();  // Cập nhật adapter để hiển thị sách
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
                    mdata.child("Sachs").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            arrBook.clear();
                            Bundle arguments = getArguments();
                            Integer theLoaiID = 0;
                            if (arguments != null)
                            {
                                theLoaiID = arguments.getInt("TheLoaiID",0);
                                txt.setText(arguments.getString("TenTheLoai","Tất cả sách"));
                            }
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                try {
                                    Book book = dataSnapshot.getValue(Book.class);
                                    Integer tacgiaID = book.getTacgiaID();
                                    mdata.child("TacGias").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                                try
                                                {
                                                    Author tacgia = dataSnapshot.getValue(Author.class);
                                                    if(tacgiaID==tacgia.getTacGiaID())
                                                        book.setTenTacGia(tacgia.getTen());
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
                                    if (theLoaiID != 0 ) {
                                        // Nếu có TheLoaiID, chỉ thêm sách thuộc thể loại này
                                        if (book.getTheLoaiID() == theLoaiID && book.getTen().contains(txt_search.getText().toString()))
                                        {
                                            arrBook.add(book);
                                        }
                                    }
                                    else
                                    {
                                        if (book.getTen().contains(txt_search.getText().toString()))
                                        {
                                            arrBook.add(book);
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            adapter.notifyDataSetChanged();  // Cập nhật adapter để hiển thị sách
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Xử lý khi truy vấn bị lỗi
                        }
                    });

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