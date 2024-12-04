package com.example.phanmembansach;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class Adapter_Home_Fragment_books extends RecyclerView.Adapter<Adapter_Home_Fragment_books.ViewHolder> {

    private Context context;
    private List<Book> arrBook;

    public Adapter_Home_Fragment_books(Context context, List<Book> arrBook) {
        this.context = context;
        this.arrBook = arrBook;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_home_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int currentPosition = holder.getAdapterPosition();  // Lấy vị trí hiện tại của item
        if (currentPosition != RecyclerView.NO_POSITION) {  // Kiểm tra nếu vị trí hợp lệ
            Book book = arrBook.get(currentPosition);  // Dùng vị trí này để lấy book
            if (book != null) {
                holder.tvname.setText(book.getTen() != null ? book.getTen() : "Không rõ tác phẩm");
                holder.tvprice.setText(String.format("%.0f", (book.getGia() - book.getGia() * book.getSale())) + "đ");
                holder.tvsold.setText("Đã bán: " + book.getDaBan());
                holder.tvoldprice.setText(String.format("%.0f", book.getGia())+"đ");
                 holder.tvoldprice.setPaintFlags(holder.tvoldprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.tvsale.setText("-" + book.getSale() * 100 + "%");
                holder.tvmota.setText(book.getMota());

                // Đặt hình ảnh cho ImageView
                if (book.getAnh() != null) {
                    int imageResId = context.getResources().getIdentifier(book.getAnh(), "drawable", context.getPackageName());
                    if (imageResId != 0) {
                        holder.img.setImageResource(imageResId);
                    } else {
                        holder.img.setImageResource(R.drawable.andrzej_sapkowski);  // Ảnh mặc định
                    }
                }

                // Thiết lập sự kiện click cho mỗi item
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, Detail_book.class);
                        Book book = arrBook.get(currentPosition);
                        intent.putExtra("SachID", book.getSachID());  // Truyền vị trí qua Intent
                        context.startActivity(intent);
                    }
                });
            }
        }
    }
    @Override
    public int getItemCount() {
        return arrBook.size();
    }

    // ViewHolder class dùng để tối ưu hóa hiệu suất
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvname, tvprice, tvsold, tvmota,tvoldprice,tvsale;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.ten);
            tvprice = itemView.findViewById(R.id.gia);
            img = itemView.findViewById(R.id.img1_Recommendation);
            tvsold = itemView.findViewById(R.id.daban);
            tvmota = itemView.findViewById(R.id.a);
            tvoldprice = itemView.findViewById(R.id.giacu);
            tvsale = itemView.findViewById(R.id.giamgia);
        }
    }
}
