package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Home_Fragment extends Fragment {
    private TextView see_more;
    DatabaseReference mdata;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    RecyclerView recyclerView4;
    RecyclerView recyclerView5;
    RecyclerView recyclerView6;
    private  TextView txt_sieugiamgia;
    private  TextView txt_theloai;
    private  TextView txt_tacgia;
    Adapter_Home_Fragment_books adapter;
    Adapter_Home_Fragment_Categories adapter2;
    Adapter_Home_Fragment_Authors adapter3;
    Adapter_Home_Fragment_books adapter4;
    Adapter_Home_Fragment_books adapter5;
    Adapter_Home_Fragment_books adapter6;
    ArrayList<Book> arrBook = new ArrayList<>();
    ArrayList<Book> arrBooksieugiamgia = new ArrayList<>();
    ArrayList<Book> arrBookkiao = new ArrayList<>();
    ArrayList<Book> arrBookgiaoduc = new ArrayList<>();
    ArrayList<Book> arrBookhaikich = new ArrayList<>();
    ArrayList<Category> arrCategories = new ArrayList<>();
    ArrayList<Author> arrAuthor = new ArrayList<>();
    public Home_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.home_fragment, container, false);

        Toolbar toolbar = mView.findViewById(R.id.tool_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            activity.invalidateOptionsMenu();
        }
        requireActivity().invalidateOptionsMenu();
        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
                menu.clear();
                inflater.inflate(R.menu.menu_home, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.menu_home_new_product)
                {
                    ((Home) getActivity()).setCurrentPage(8);
                    return true;
                }else if(item.getItemId()==R.id.menu_home_bestseller)
                {
                    ((Home) getActivity()).setCurrentPage(4);
                    return true;
                }else if(item.getItemId()==R.id.menu_home_categories_book_1)
                {
                    ((Home) getActivity()).setCurrentPage(7);
                    return true;
                }else if(item.getItemId()==R.id.menu_home_categories_book_2)
                {
                    ((Home) getActivity()).setCurrentPage(7);
                    return true;
                }else if(item.getItemId()==R.id.menu_home_categories_book_3)
                {
                    ((Home) getActivity()).setCurrentPage(7);
                    return true;
                }else if(item.getItemId()==R.id.menu_home_categories_book_4)
                {
                    ((Home) getActivity()).setCurrentPage(7);
                    return true;
                }else if(item.getItemId()==R.id.menu_home_categories_book_5)
                {
                    ((Home) getActivity()).setCurrentPage(7);
                    return true;
                }else if(item.getItemId()==R.id.menu_home_categories_book_6)
                {
                    ((Home) getActivity()).setCurrentPage(7);
                    return true;
                }else if(item.getItemId()==R.id.menu_home_categories_book_7)
                {
                    ((Home) getActivity()).setCurrentPage(7);
                    return true;
                }else if(item.getItemId()==R.id.menu_home_categories_book_8)
                {
                    ((Home) getActivity()).setCurrentPage(7);
                    return true;
                }else if(item.getItemId()==R.id.menu_home_categories_book_9)
                {
                    ((Home) getActivity()).setCurrentPage(7);
                    return true;
                }else if(item.getItemId()==R.id.menu_home_categories_book_10)
                {
                    ((Home) getActivity()).setCurrentPage(7);
                    return true;
                }else if(item.getItemId()==R.id.menu_home_author_1)
                {
                    startActivity(new Intent(getActivity(),Detail_Author.class));
                    return true;
                }else if(item.getItemId()==R.id.menu_home_author_2)
                {
                    startActivity(new Intent(getActivity(),Detail_Author.class));
                    return true;
                }else if(item.getItemId()==R.id.menu_home_author_3)
                {
                    startActivity(new Intent(getActivity(),Detail_Author.class));
                    return true;
                }else if(item.getItemId()==R.id.menu_home_author_4)
                {
                    startActivity(new Intent(getActivity(),Detail_Author.class));
                    return true;
                }else if(item.getItemId()==R.id.menu_home_author_5)
                {
                    startActivity(new Intent(getActivity(),Detail_Author.class));
                    return true;
                }else if(item.getItemId()==R.id.menu_home_vouchers)
                {
                    return true;
                }else
                    return false;
            }
        }, getViewLifecycleOwner());
        see_more = mView.findViewById(R.id.txt_see_more);
        EditText txt_search = mView.findViewById(R.id.txt_search);

        txt_search.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP ) {
                if (event.getRawX() <= (txt_search.getLeft() + txt_search.getCompoundDrawables()[0].getBounds().width())) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("TheLoaiID", 0);
                    // Gọi phương thức setCurrentPage và truyền Bundle vào Home Activity
                    if (getContext() instanceof Home) {
                        Home homeActivity = (Home) getContext();
                        homeActivity.setCurrentPage(4, bundle);  // Chuyển đến All_Book_Fragment và truyền bundle
                    }
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

        ViewPager2 viewPager2 = mView.findViewById(R.id.slideviewpager);

        // Danh sách hình ảnh
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.slide_1);
        images.add(R.drawable.slide_2);
        images.add(R.drawable.slide_3);
        images.add(R.drawable.slide_4);
        Slide_Adapter a = new Slide_Adapter(requireActivity(), images);
        viewPager2.setAdapter(a);

        recyclerView = mView.findViewById(R.id.rv_sieugiamgia);
        recyclerView2 = mView.findViewById(R.id.rv_theloai);
        recyclerView3 = mView.findViewById(R.id.rv_tacgia);
        recyclerView4 = mView.findViewById(R.id.rv_kiao);
        recyclerView5 = mView.findViewById(R.id.rv_giaoduc);
        recyclerView6 = mView.findViewById(R.id.rv_haikich);


        // Sử dụng LinearLayoutManager với hướng cuộn ngang cho cả hai RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager5 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager6 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView3.setLayoutManager(layoutManager3);
        recyclerView4.setLayoutManager(layoutManager4);
        recyclerView5.setLayoutManager(layoutManager5);
        recyclerView6.setLayoutManager(layoutManager6);

        // Tạo Adapter và gắn vào RecyclerView
        adapter = new Adapter_Home_Fragment_books(getContext(), arrBooksieugiamgia);  // Adapter cho sách
        recyclerView.setAdapter(adapter);
        adapter2 = new Adapter_Home_Fragment_Categories(getContext(), arrCategories);  // Adapter cho thể loại
        recyclerView2.setAdapter(adapter2);
        adapter3 = new Adapter_Home_Fragment_Authors(getContext(), arrAuthor);  // Adapter cho thể loại
        recyclerView3.setAdapter(adapter3);
        adapter4 = new Adapter_Home_Fragment_books(getContext(), arrBookkiao);  // Adapter cho sách
        recyclerView4.setAdapter(adapter4);
        adapter5 = new Adapter_Home_Fragment_books(getContext(), arrBookgiaoduc);  // Adapter cho sách
        recyclerView5.setAdapter(adapter5);
        adapter6 = new Adapter_Home_Fragment_books(getContext(), arrBookhaikich);  // Adapter cho sách
        recyclerView6.setAdapter(adapter6);

        // Lấy dữ liệu từ Firebase cho sách
        mdata = FirebaseDatabase.getInstance().getReference();
        mdata.child("Sachs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrBook.clear(); // Xóa danh sách cũ trước khi thêm dữ liệu mới

                // Các danh sách phân loại
                arrBooksieugiamgia.clear();
                arrBookkiao.clear();
                arrBookgiaoduc.clear();
                arrBookhaikich.clear();

                // Duyệt qua các node con trong Firebase
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    try {
                        Book book = dataSnapshot.getValue(Book.class);
                        if (book != null) {
                            arrBook.add(book); // Thêm sách vào danh sách chung
                            // Phân loại sách theo các điều kiện
                            if (book.getSale() >= 0.4) {
                                arrBooksieugiamgia.add(book);
                            }
                            if (book.getTheLoaiID() == 1) {
                                arrBookkiao.add(book);
                            }
                            if (book.getTheLoaiID() == 6) {
                                arrBookgiaoduc.add(book);
                            }
                            if (book.getTheLoaiID() == 2) {
                                arrBookhaikich.add(book);
                            }
                        }
                    } catch (Exception e) {
                        Log.e("Firebase", "Error: " + e.getMessage());
                    }
                }

                // Cập nhật adapter sau khi phân loại xong
                adapter.notifyDataSetChanged();
                adapter4.notifyDataSetChanged();
                adapter5.notifyDataSetChanged();
                adapter6.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error: " + error.getMessage());
            }
        });

        // Lấy dữ liệu từ Firebase cho thể loại
        mdata.child("TheLoais").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrCategories.clear(); // Làm sạch danh sách thể loại trước khi thêm mới
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    try {
                        Category theloai = dataSnapshot.getValue(Category.class);
                        if (theloai != null) {
                            arrCategories.add(theloai);
                        }
                    } catch (Exception e) {
                        Log.e("Firebase", "Error: " + e.getMessage());
                    }
                }
                // Cập nhật adapter khi dữ liệu đã thay đổi
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error: " + error.getMessage());
            }
        });

        mdata.child("TacGias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    try {
                        Author tacgia = dataSnapshot.getValue(Author.class);
                        arrAuthor.add(tacgia);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                adapter3.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý khi truy vấn bị lỗi
            }
        });
        txt_sieugiamgia =mView.findViewById(R.id.txt_sieugiamgia);
        txt_tacgia = mView.findViewById(R.id.txt_tacgia);
        txt_theloai = mView.findViewById(R.id.txt_theloai);
        txt_sieugiamgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Home) getActivity()).setCurrentPage(4);
            }
        });
        txt_tacgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Home) getActivity()).setCurrentPage(5);
            }
        });
        txt_theloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Home) getActivity()).setCurrentPage(6);
            }
        });
        return mView;
    }
    }
