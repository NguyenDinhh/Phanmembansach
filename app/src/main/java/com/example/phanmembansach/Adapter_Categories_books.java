package com.example.phanmembansach;

import static android.app.PendingIntent.getActivity;

import android.graphics.Paint;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Adapter_Categories_books extends ArrayAdapter<Book> {
    private Context context;
    private int resource;
    private List<Book> arrBook;
    private DatabaseReference mdata;

    public Adapter_Categories_books(Context context, int resource, List<Book> arrBook) {
        super(context, resource, arrBook);
        this.arrBook = arrBook;
        this.resource = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        // Nếu convertView là null, tạo mới View và ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_categories_books, parent, false);
            viewHolder = new ViewHolder();

            mdata = FirebaseDatabase.getInstance().getReference();
            viewHolder.tvname = convertView.findViewById(R.id.ten);
            viewHolder.tvauthor = convertView.findViewById(R.id.tentacgia);
            viewHolder.tvprice = convertView.findViewById(R.id.gia);
            viewHolder.img = convertView.findViewById(R.id.img);
            viewHolder.tvsold = convertView.findViewById(R.id.tvsold);
            viewHolder.img_favourite = convertView.findViewById(R.id.ic_favourite);
            viewHolder.img_cart = convertView.findViewById(R.id.ic_cart);
            viewHolder.tvoldprice = convertView.findViewById(R.id.giacu);
            viewHolder.tvsale = convertView.findViewById(R.id.giamgia);

            // Thiết lập listener cho sự kiện click vào mỗi item
            convertView.setTag(viewHolder);  // Lưu lại ViewHolder cho lần tái sử dụng sau
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Lấy Book hiện tại từ arrBook
        Book book = arrBook.get(position);
        if (book != null) {
            viewHolder.tvname.setText(book.getTen() != null ? book.getTen() : "Không rõ tác phẩm");
            viewHolder.tvauthor.setText(book.getTenTacGia() != null ? book.getTenTacGia() : "Không rõ tác giả");
            viewHolder.tvprice.setText(String.format("%.0f", (book.getGia() - book.getGia() * book.getSale())) + "đ");
            viewHolder.tvsold.setText("Đã bán: " + book.getDaBan());
            viewHolder.tvoldprice.setText(String.format("%.0f", book.getGia()) + "đ");
            viewHolder.tvoldprice.setPaintFlags(viewHolder.tvoldprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.tvsale.setText("-" + book.getSale() * 100 + "%");

            // Đặt hình ảnh cho ImageView
            if (book.getAnh() != null) {
                int imageResId = context.getResources().getIdentifier(book.getAnh(), "drawable", context.getPackageName());
                if (imageResId != 0) {
                    viewHolder.img.setImageResource(imageResId);
                } else {
                    viewHolder.img.setImageResource(R.drawable.andrzej_sapkowski);  // Ảnh mặc định
                }
            }

            // Thiết lập sự kiện click cho mỗi item
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Detail_book.class);
                    Book book = arrBook.get(position);
                    intent.putExtra("SachID", book.getSachID());  // Truyền vị trí qua Intent
                    context.startActivity(intent);
                }
            });
            App app = (App) context.getApplicationContext();
                if (viewHolder.img_favourite != null && viewHolder.img_cart != null)
                {
                    mdata.child("SachYeuThichs").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dataSnapshot : snapshot.getChildren())
                            {
                                try
                                {
                                    SachYeuThich sachYeuThich = dataSnapshot.getValue(SachYeuThich.class);
                                    if(app.getUsername().equals( sachYeuThich.getTenDangNhap()) && sachYeuThich.getSachID() == arrBook.get(position).getSachID())
                                    {

                                        viewHolder.img_favourite.setImageResource(R.drawable.ic_favourite_2);
                                        viewHolder.img_favourite.setTag(R.drawable.ic_favourite_2);
                                        break;
                                    }
                                    else
                                    {
                                        viewHolder.img_favourite.setImageResource(R.drawable.ic_favorite);
                                        viewHolder.img_favourite.setTag(R.drawable.ic_favorite);
                                    }
                                }catch (Exception e)
                                {

                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    // su kien click them hoac huy sach yeu thich
                   viewHolder.img_favourite.setOnClickListener(view -> {
                        if(app.isLoggedIn()==false)
                            Toast.makeText(context,"Bạn cần đăng nhập",Toast.LENGTH_SHORT).show();
                        else
                        {
                            if ((Integer) viewHolder.img_favourite.getTag() == R.drawable.ic_favorite) {
                                mdata.child("SachYeuThichs").push().setValue(new SachYeuThich(arrBook.get(position).getSachID() ,app.getUsername()));
                                viewHolder.img_favourite.setImageResource(R.drawable.ic_favourite_2);
                                viewHolder.img_favourite.setTag(R.drawable.ic_favourite_2);
                            } else {
                                mdata.child("SachYeuThichs").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for(DataSnapshot dataSnapshot: snapshot.getChildren())
                                        {
                                            try
                                            {
                                                SachYeuThich sachYeuThich = dataSnapshot.getValue(SachYeuThich.class);
                                                if(app.getUsername().equals( sachYeuThich.getTenDangNhap()) && sachYeuThich.getSachID() == arrBook.get(position).getSachID())
                                                {
                                                    mdata.child("SachYeuThichs").child(dataSnapshot.getKey()).removeValue()
                                                            .addOnSuccessListener(aVoid -> {
                                                                Log.d("Firebase", "Xóa thành công!");
                                                            })
                                                            .addOnFailureListener(e -> {
                                                                Log.e("Firebase", "Xóa thất bại", e);
                                                            });
                                                    break;
                                                }

                                            }catch (Exception e)
                                            {

                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                                viewHolder.img_favourite.setImageResource(R.drawable.ic_favorite);
                                viewHolder.img_favourite.setTag(R.drawable.ic_favorite);
                            }
                        }
                    });
                viewHolder.img_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(app.isLoggedIn()==false)
                        {
                            Toast.makeText(getContext(),"Bạn cần đăng nhập",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Book book = arrBook.get(position);
                            if (book != null)
                            {
                                mdata.child("GioHangs").addListenerForSingleValueEvent(new ValueEventListener()
                                {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        Boolean tontai= false;
                                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                                        {
                                            try {
                                                GioHang gioHang = dataSnapshot.getValue(GioHang.class);
                                                if(gioHang.getTenDangNhap().equals(app.getUsername()) && gioHang.getSachID()==book.getSachID())
                                                {
                                                    Toast.makeText(context,"Sach da co trong gio hang",Toast.LENGTH_SHORT).show();
                                                    tontai =true;
                                                    break;
                                                }
                                            } catch (Exception e) {
                                            }
                                        }
                                        if(tontai == false)
                                        {
                                            Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                                            GioHang gioHang1 = new GioHang(app.getUsername(),book.getSachID(),book.getTen(),book.getAnh(), book.getSoLuong(), book.getGia(), 1,0);
                                            mdata.child("GioHangs").push().setValue(gioHang1);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }
                        }
                    }
                });
            }
        }
        return convertView;
    }
    // ViewHolder class dùng để tối ưu hóa hiệu suất
    public class ViewHolder {
        TextView tvname, tvauthor, tvprice, tvsold, tvoldprice, tvsale;
        ImageView img, img_favourite, img_cart;
    }
}
