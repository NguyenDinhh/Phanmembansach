package com.example.phanmembansach;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class Adapter_Home_Fragment_Authors extends RecyclerView.Adapter<Adapter_Home_Fragment_Authors.ViewHolder> {

    private Context context;
    private List<Author> arrauthors;

    public Adapter_Home_Fragment_Authors(Context context, List<Author> arrauthors) {
        this.context = context;
        this.arrauthors = arrauthors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_home_author, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int currentPosition = holder.getAdapterPosition();  // Lấy vị trí hiện tại của item
        if (currentPosition != RecyclerView.NO_POSITION) {  // Kiểm tra nếu vị trí hợp lệ
            Author author = arrauthors.get(currentPosition);  // Dùng vị trí này để lấy book
            if (author != null) {
                holder.ten.setText(author.getTen() != null ? author.getTen() : "Không rõ");
                holder.mota.setText(author.getMoTa() != null ? author.getMoTa() : "Không rõ");
                holder.congviec.setText(author.getCongViec() != null ? author.getCongViec(): "Không rõ");

                // Đặt hình ảnh cho ImageView
                if (author.getAnh() != null) {
                    int imageResId = context.getResources().getIdentifier(author.getAnh(), "drawable", context.getPackageName());
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
                        Intent intent = new Intent(context, Detail_Author.class);
                        Author author1 = arrauthors.get(currentPosition);
                        intent.putExtra("TacGiaID", author1.getTacGiaID());  // Truyền vị trí qua Intent
                        context.startActivity(intent);
                    }
                });
            }
        }
    }


    @Override
    public int getItemCount() {
        return arrauthors.size();
    }

    // ViewHolder class dùng để tối ưu hóa hiệu suất
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ten,mota,congviec;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.ten);
            mota = itemView.findViewById(R.id.a);
            img = itemView.findViewById(R.id.img);
            congviec = itemView.findViewById(R.id.congviec);
        }
    }
}
