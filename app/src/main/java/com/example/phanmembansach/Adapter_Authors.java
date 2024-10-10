package com.example.phanmembansach;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter_Authors extends ArrayAdapter<Author> {
    private Context context;
    private int resource;
    private List<Author> arrAuthor;

    public Adapter_Authors(Context context, int resource, List<Author> arrAuthor) {
        super(context, resource, arrAuthor);
        this.arrAuthor = arrAuthor;
        this.resource = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        // Nếu convertView là null, tạo mới View và ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_authors, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvname = convertView.findViewById(R.id.tvname);
            viewHolder.tvjob = convertView.findViewById(R.id.tvjob);
            viewHolder.tvamount = convertView.findViewById(R.id.tvamount);
            viewHolder.img = convertView.findViewById(R.id.img);

            convertView.setTag(viewHolder);  // Lưu lại ViewHolder cho lần tái sử dụng sau
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Lấy Book hiện tại từ arrBook
        Author author = arrAuthor.get(position);

        if (author != null) {
            // Đảm bảo không bị null khi gán giá trị cho các TextView
            viewHolder.tvname.setText(author.getName() != null ? author.getName() : "Unknown Title");
            viewHolder.tvjob.setText(author.getJob() != null ? author.getJob() : "Unknown Job");
            viewHolder.tvamount.setText( author.getAmount() != null ? "Number of books: " +String.valueOf(author.getAmount()): "Unknown Amount");
            // Đặt hình ảnh cho ImageView, thay vì dùng setBackgroundResource, dùng setImageResource
            if (author.getImg() != null) {
                int imageResId = context.getResources().getIdentifier(author.getImg(), "drawable", context.getPackageName());
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
        TextView tvname,tvjob,tvamount;
        ImageView img;
    }
}
