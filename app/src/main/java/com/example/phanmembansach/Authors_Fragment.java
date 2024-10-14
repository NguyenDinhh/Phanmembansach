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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class Authors_Fragment extends Fragment {
    public Authors_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.authors_fragment, container, false);
        ImageView  back = mView.findViewById(R.id.img_back);
        EditText txt_search = mView.findViewById(R.id.txt_search);
        txt_search.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP ) {
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
        ListView lv = mView.findViewById(R.id.lvauthor);
        ArrayList<Author> arrAuthor = new ArrayList<>();
        Author author1= new Author("J. R. R. Tolkien","Writer, Philologist",10,"","j_r_r_tolkien");
        Author author2= new Author("Andrzej Sapkowski","Writer, Philologist",10,"","andrzej_sapkowski");
        Author author3= new Author("Stephen King","Writer, Philologist",10,"","stephen_king");
        Author author4= new Author("Sir Arthur conan doyle","Writer, Philologist",10,"","sir_arthur_conan_doyle");
        Author author5= new Author("J K Rowling","Writer, Philologist",10,"","j_k_rowling");

        arrAuthor.add(author1);
        arrAuthor.add(author2);
        arrAuthor.add(author3);
        arrAuthor.add(author4);
        arrAuthor.add(author5);
        Adapter_Authors adapter = new Adapter_Authors(getActivity(),R.layout.row_authors, arrAuthor);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            startActivity(new Intent(getActivity(), Detail_Author.class));
        });
        return  mView;
    }
}