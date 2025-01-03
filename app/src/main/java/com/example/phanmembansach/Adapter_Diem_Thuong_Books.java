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

import java.util.List;

public class Adapter_Diem_Thuong_Books  extends ArrayAdapter<Book> {
    private Context context;
    private int resource;
    private List<Book> arrBook;

    public Adapter_Diem_Thuong_Books(Context context, int resource, List<Book> arrBook) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.row_diemthuong, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.tvname = convertView.findViewById(R.id.ten);
            viewHolder.tvauthor = convertView.findViewById(R.id.tentacgia);
            viewHolder.tvprice = convertView.findViewById(R.id.gia);
            viewHolder.img = convertView.findViewById(R.id.img);
            viewHolder.tvsold = convertView.findViewById(R.id.tvsold);
            viewHolder.btn_doi = convertView.findViewById(R.id.btn_buy_2);
            convertView.setTag(viewHolder);  // Lưu lại ViewHolder cho lần tái sử dụng sau
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Lấy Book hiện tại từ arrBook
        Book book = arrBook.get(position);

        if (book != null) {
            viewHolder.tvname.setText(book.getTen() != null ? book.getTen() : "Unknown Title");
            viewHolder.tvauthor.setText(book.getTacgiaID().toString() != null ? book.getTacgiaID().toString() : "Unknown Author");
            viewHolder.tvprice.setText("Điểm thưởng: " + book.getDiemThuong());
            viewHolder.tvsold.setText("Đã bán: " + book.getDaBan());

            // Đặt hình ảnh cho ImageView, thay vì dùng setBackgroundResource, dùng setImageResource
            if (book.getAnh() != null) {
                int imageResId = context.getResources().getIdentifier(book.getAnh(), "drawable", context.getPackageName());
                if (imageResId != 0) {  // Kiểm tra nếu resource ID hợp lệ
                    viewHolder.img.setImageResource(imageResId);  // Đặt hình ảnh
                } else {
                    viewHolder.img.setImageResource(R.drawable.adventure_book);  // Nếu không tìm thấy hình, đặt ảnh mặc định
                }
            }
        }
        viewHolder.btn_doi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,Detail_book_diem_thuong.class));
            }
        });
        convertView.setOnClickListener(v -> {
                context.startActivity(new Intent(context, Detail_book_diem_thuong.class));

        });

        return convertView;
    }

    // ViewHolder class dùng để tối ưu hóa hiệu suất
    public class ViewHolder {
        TextView tvname, tvauthor, tvprice, tvsold;
        Button btn_doi;
        ImageView img;
    }
}