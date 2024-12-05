package com.example.phanmembansach;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Adapter_Cart_Item extends ArrayAdapter<GioHang> {
    private Context context;
    private int resource;
    private List<GioHang> arrgiohang;

    public Adapter_Cart_Item(Context context, int resource, List<GioHang> arrgiohang) {
        super(context, resource, arrgiohang);
        this.arrgiohang = arrgiohang;
        this.resource = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Adapter_Cart_Item.ViewHolder viewHolder;

        // Nếu convertView là null, tạo mới View và ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_cart_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ten = convertView.findViewById(R.id.ten);
            viewHolder.gia = convertView.findViewById(R.id.gia);
            viewHolder.soluongmua = convertView.findViewById(R.id.soluong);
            viewHolder.img = convertView.findViewById(R.id.img);
            viewHolder.cong = convertView.findViewById(R.id.cong);
            viewHolder.tru = convertView.findViewById(R.id.tru);
            viewHolder.go = convertView.findViewById(R.id.go);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Adapter_Cart_Item.ViewHolder) convertView.getTag();
        }

        // Lấy Book hiện tại từ arrBook
        GioHang gioHang = arrgiohang.get(position);

        if (gioHang != null) {
            // Đảm bảo không bị null khi gán giá trị cho các TextView
            viewHolder.ten.setText(gioHang.getTen() != null ? gioHang.getTen() : "Unknown Title");
            viewHolder.gia.setText(String.valueOf(gioHang.getGia()) != null ? "Giá: "+String.format("%.0f",gioHang.getGia())+ " VND" : "Unknown");
            viewHolder.soluongmua.setText(String.valueOf(gioHang.getSoLuongMua()) != null ? String.valueOf(gioHang.getSoLuongMua()) : "Unknown");
            // Đặt hình ảnh cho ImageView, thay vì dùng setBackgroundResource, dùng setImageResource
            if (gioHang.getAnh() != null) {
                int imageResId = context.getResources().getIdentifier(gioHang.getAnh(), "drawable", context.getPackageName());
                if (imageResId != 0) {  // Kiểm tra nếu resource ID hợp lệ
                    viewHolder.img.setImageResource(imageResId);  // Đặt hình ảnh
                } else {
                    viewHolder.img.setImageResource(R.drawable.andrzej_sapkowski);  // Nếu không tìm thấy hình, đặt ảnh mặc định
                }
            }
        }
        viewHolder.tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer soluong = Integer.valueOf(viewHolder.soluongmua.getText().toString());
                if(soluong>0)
                    viewHolder.soluongmua.setText(String.valueOf(soluong-1));
                else
                    Toast.makeText(context, "Số lượng sách phải lớn hơn 0",Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer soluong = Integer.valueOf(viewHolder.soluongmua.getText().toString());
                if(soluong<arrgiohang.get(position).getSoLuong())
                    viewHolder.soluongmua.setText(String.valueOf(soluong+1));
                else
                    Toast.makeText(context, "Số lượng sách đã tối đa",Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mData = FirebaseDatabase.getInstance().getReference("GioHangs");

                // Sử dụng addListenerForSingleValueEvent để chỉ nhận một lần dữ liệu
                mData.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot gioHangSnapshot : snapshot.getChildren()) {
                            GioHang gioHang = gioHangSnapshot.getValue(GioHang.class);
                            // Kiểm tra nếu SachID và TenDangNhap trùng khớp
                            if (gioHang != null && gioHang.getSachID().equals(arrgiohang.get(position).getSachID()) &&
                                    gioHang.getTenDangNhap().equals(arrgiohang.get(position).getTenDangNhap())) {
                                String key = gioHangSnapshot.getKey(); // Lấy key của node GioHang
                                // Xóa node GioHang dựa trên key
                                mData.child(key).removeValue()
                                        .addOnSuccessListener(aVoid -> {
                                            // Xóa thành công
                                            Toast.makeText(getContext(), "Đã xóa giỏ hàng", Toast.LENGTH_SHORT).show();
                                        })
                                        .addOnFailureListener(e -> {
                                            // Lỗi khi xóa
                                            Toast.makeText(getContext(), "Lỗi khi xóa: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        });
                                break; // Chỉ xóa 1 phần tử
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Lỗi khi đọc dữ liệu từ Firebase
                        Toast.makeText(getContext(), "Lỗi khi đọc dữ liệu: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        return convertView;
    }

    // ViewHolder class dùng để tối ưu hóa hiệu suất
    public class ViewHolder {
        TextView ten,gia,soluongmua;
        ImageView img,go;
        Button cong,tru;
    }
}
