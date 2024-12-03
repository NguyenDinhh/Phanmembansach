package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter_Categories extends ArrayAdapter<Category> {
    private Context context;
    private int resource;
    private List<Category> arrCategories;

    public Adapter_Categories(Context context, int resource, List<Category> arrCategories) {
        super(context, resource, arrCategories);
        this.arrCategories = arrCategories;
        this.resource = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;


        // Nếu convertView là null, tạo mới View và ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_categories, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.tvname = convertView.findViewById(R.id.tvname);
            viewHolder.tvdescribe = convertView.findViewById(R.id.tvdescribe);
            viewHolder.img = convertView.findViewById(R.id.img);

            convertView.setTag(viewHolder);  // Lưu lại ViewHolder cho lần tái sử dụng sau
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        // Lấy Book hiện tại từ arrBook
        Category category = arrCategories.get(position);

        if (category != null) {
            // Đảm bảo không bị null khi gán giá trị cho các TextView
            viewHolder.tvname.setText(category.getTenTheLoai() != null ? category.getTenTheLoai() : "Unknown Title");
            viewHolder.tvdescribe.setText(category.getMoTa() != null ? category.getMoTa() : "Unknown Describe");
            // Đặt hình ảnh cho ImageView, thay vì dùng setBackgroundResource, dùng setImageResource
            if (category.getAnh() != null) {
                int imageResId = context.getResources().getIdentifier(category.getAnh(), "drawable", context.getPackageName());
                if (imageResId != 0) {  // Kiểm tra nếu resource ID hợp lệ
                    viewHolder.img.setImageResource(imageResId);  // Đặt hình ảnh
                } else {
                    viewHolder.img.setImageResource(R.drawable.adventure_book);  // Nếu không tìm thấy hình, đặt ảnh mặc định
                }
            }
        }

        return convertView;
    }

    // ViewHolder class dùng để tối ưu hóa hiệu suất
    public class ViewHolder {
        TextView tvname, tvdescribe;
        ImageView img;
    }
}
