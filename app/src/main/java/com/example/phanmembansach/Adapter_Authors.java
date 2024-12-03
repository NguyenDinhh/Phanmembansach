package com.example.phanmembansach;

import android.content.Intent;
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
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Lấy SachID của cuốn sách hiện tại
                    Author author = arrAuthor.get(position);
                    if (author != null) {
                        Integer AuthorID =author.getTacGiaID();
                        Intent intent = new Intent(context, Detail_Author.class);
                        intent.putExtra("TacGiaID", AuthorID);
                        context.startActivity(intent);
                    }
                }
            });
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Lấy Book hiện tại từ arrBook
        Author author = arrAuthor.get(position);

        if (author != null) {
            // Đảm bảo không bị null khi gán giá trị cho các TextView
            viewHolder.tvname.setText(author.getTen() != null ? author.getTen() : "Unknown Title");
            viewHolder.tvjob.setText(author.getCongViec() != null ? author.getCongViec() : "Unknown Job");
            viewHolder.tvamount.setText("Fan: 782 người");
            // Đặt hình ảnh cho ImageView, thay vì dùng setBackgroundResource, dùng setImageResource
            if (author.getAnh() != null) {
                int imageResId = context.getResources().getIdentifier(author.getAnh(), "drawable", context.getPackageName());
                if (imageResId != 0) {  // Kiểm tra nếu resource ID hợp lệ
                    viewHolder.img.setImageResource(imageResId);  // Đặt hình ảnh
                } else {
                    viewHolder.img.setImageResource(R.drawable.andrzej_sapkowski);  // Nếu không tìm thấy hình, đặt ảnh mặc định
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
