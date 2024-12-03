package com.example.phanmembansach;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Detail_book extends AppCompatActivity {
    private TextView txt_back;
    private  ImageView img_favourite;
    private ImageView img_cart;
    private  TextView txtoldprice;
    private DatabaseReference mdata;
    private  ImageView anh;
    private  TextView ten;
    private  TextView tentacgia;
    private TextView gia;
    private TextView giacu;
    private TextView sale;
    private TextView daban;
    private TextView soluong;
    private  TextView mota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);
        txt_back = findViewById(R.id.txt_back);
        img_favourite = findViewById(R.id.ic_favourite);
        img_favourite.setImageResource(R.drawable.ic_favorite);
        img_favourite.setTag(R.drawable.ic_favorite);
       img_cart = findViewById(R.id.ic_cart);
       txtoldprice = findViewById(R.id.giacu);
       mdata = FirebaseDatabase.getInstance().getReference();
       txtoldprice.setPaintFlags(txtoldprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
       anh = findViewById(R.id.img);
       ten = findViewById(R.id.tvname);
       tentacgia = findViewById(R.id.tvauthor);
       gia = findViewById(R.id.gia);
       giacu     = findViewById( R.id.giacu);
       sale = findViewById(R.id.giamgia);
       daban = findViewById(R.id.tvsold);
       soluong =findViewById(R.id.tvsoluong);
       mota = findViewById(R.id.tv_mota);
        Intent intent = getIntent();
        Integer key = intent.getIntExtra("key", -1);
        if (key != -1) {
            mdata.child("Sachs").child(key.toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try {
                        Book book1 = dataSnapshot.getValue(Book.class);
                        ten.setText(book1.getTen());
                        tentacgia.setText(book1.getTenTacGia());
                        gia.setText( String.format("%.0f",(book1.getGia() - book1.getGia()*book1.getSale() ))+" đ");
                        giacu.setText(String.format("%.0f",book1.getGia())+ " đ");
                        sale.setText("-" + book1.getSale()*100 + "%");
                        daban.setText("Đã bán: "+ book1.getDaBan());
                        soluong.setText("Số lượng: "+book1.getSoLuong());
                        mota.setText(book1.getMota());
                        // Nếu có ảnh, hiển thị ảnh của sách
                        if (book1.getAnh() != null) {
                            int imageResId = getResources().getIdentifier(book1.getAnh(), "drawable", getPackageName());
                            if (imageResId != 0) {
                                anh.setImageResource(imageResId);
                            } else {
                                anh.setImageResource(R.drawable.andrzej_sapkowski);  // Mặc định nếu không tìm thấy ảnh
                            }
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
        } else {
            // Xử lý trường hợp không có SachID (ví dụ: hiển thị thông báo lỗi)
            Toast.makeText(this, "Không tìm thấy thông tin sách", Toast.LENGTH_SHORT).show();
        }
       txt_back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });
       img_cart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(Detail_book.this, Order_Book.class));
           }
       });
       img_favourite.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if( (Integer) img_favourite.getTag() ==R.drawable.ic_favorite )
               {
                   img_favourite.setImageResource(R.drawable.ic_favourite_2);
                   img_favourite.setTag(R.drawable.ic_favourite_2);
               }
               else
               {
                   img_favourite.setImageResource(R.drawable.ic_favorite);
                   img_favourite.setTag(R.drawable.ic_favorite);
               }
           }
       });
    }
}