package com.example.phanmembansach;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class AddressAdapter extends ArrayAdapter<Address> {
    private Context context;
    private int resource;
    private List<Address> arraddress;
    private DatabaseReference mdata;

    public AddressAdapter(Context context, int resource, List<Address> arraddress) {
        super(context, resource, arraddress);
        this.arraddress = arraddress;
        this.resource = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        // Nếu convertView là null, tạo mới View và ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_address, parent, false);
            viewHolder = new ViewHolder();

            mdata = FirebaseDatabase.getInstance().getReference();
            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            viewHolder.tvPhone = convertView.findViewById(R.id.tv_phone);
            viewHolder.tvAddress = convertView.findViewById(R.id.tv_address);
            viewHolder.btn_xoa= convertView.findViewById(R.id.btn_xoa);

            // Thiết lập listener cho sự kiện click vào mỗi item
            convertView.setTag(viewHolder);  // Lưu lại ViewHolder cho lần tái sử dụng sau
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Lấy Book hiện tại từ arrBook
        Address address = arraddress.get(position);
        if (address != null) {
            viewHolder.tvName.setText(address.getTen() != null ? address.getTen() : "Không rõ");
            viewHolder.tvPhone.setText(address.getDienThoai() != null ? address.getDienThoai() : "Không ro");
            viewHolder.tvAddress.setText(address.getDiaChi() != null ? address.getDiaChi() : "Không ro");

            // Thiết lập sự kiện click cho mỗi item
            viewHolder.btn_xoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            App app = (App) context.getApplicationContext();

        }
        viewHolder.btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arraddress.get(position);
                mdata.child("DiaChiNhanHangs").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            try {
                                Address address = dataSnapshot.getValue(Address.class);
                                if (address.getDiaChiNhanHangID()==arraddress.get(position).getDiaChiNhanHangID())
                                {
                                    mdata.child("DiaChiNhanHangs").child(dataSnapshot.getKey()).removeValue();
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            } catch (Exception e) {

                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        App app = (App) getContext().getApplicationContext();
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Đã chọn địa chỉ này", Toast.LENGTH_SHORT).show();
                mdata.child("GioHangs").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot: snapshot.getChildren())
                        {
                            try{
                                GioHang gioHang = dataSnapshot.getValue(GioHang.class);
                                if(app.getUsername().equals(gioHang.getTenDangNhap()))
                                {
                                    mdata.child("GioHangs").child(dataSnapshot.getKey()).child("diaChiNhanHangID").setValue(arraddress.get(position).getDiaChiNhanHangID())
                                            .addOnSuccessListener(aVoid -> {
                                                Log.d("Firebase", "Cập nhật thành công!");
                                            })
                                            .addOnFailureListener(e -> {
                                                Log.e("Firebase", "Cập nhật thất bại", e);
                                            });
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
                ((Activity) context).finish();
            }
        });
        return convertView;
    }
    // ViewHolder class dùng để tối ưu hóa hiệu suất
    public class ViewHolder {
        TextView tvName, tvPhone, tvAddress;
        Button btn_xoa;

    }
}
