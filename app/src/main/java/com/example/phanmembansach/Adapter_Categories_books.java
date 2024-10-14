package com.example.phanmembansach;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import java.util.List;

public class Adapter_Categories_books extends ArrayAdapter<Book> {
    private Context context;
    private int resource;
    private List<Book> arrBook;

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

            viewHolder.tvname = convertView.findViewById(R.id.tvname);
            viewHolder.tvauthor = convertView.findViewById(R.id.tvauthor);
            viewHolder.tvprice = convertView.findViewById(R.id.tvprice);
            viewHolder.img = convertView.findViewById(R.id.img);
            viewHolder.tvsold = convertView.findViewById(R.id.tvsold);
            viewHolder.img_favourite = convertView.findViewById(R.id.ic_favourite);
            viewHolder.img_cart = convertView.findViewById(R.id.ic_cart);

            convertView.setTag(viewHolder);  // Lưu lại ViewHolder cho lần tái sử dụng sau
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Lấy Book hiện tại từ arrBook
        Book book = arrBook.get(position);

        if (book != null) {
            // Đảm bảo không bị null khi gán giá trị cho các TextView
            viewHolder.tvname.setText(book.getName() != null ? book.getName() : "Unknown Title");
            viewHolder.tvauthor.setText(book.getAuthor() != null ? book.getAuthor() : "Unknown Author");
            viewHolder.tvprice.setText("$" + book.getPrice());
            viewHolder.tvsold.setText("Sold: " + book.getSold()); // Thêm tiền tố "Sold: "

            // Đặt hình ảnh cho ImageView, thay vì dùng setBackgroundResource, dùng setImageResource
            if (book.getImg() != null) {
                int imageResId = context.getResources().getIdentifier(book.getImg(), "drawable", context.getPackageName());
                if (imageResId != 0) {  // Kiểm tra nếu resource ID hợp lệ
                    viewHolder.img.setImageResource(imageResId);  // Đặt hình ảnh
                } else {
                    viewHolder.img.setImageResource(R.drawable.adventure_book);  // Nếu không tìm thấy hình, đặt ảnh mặc định
                }
            }
        }

        if (viewHolder.img_favourite !=null && viewHolder.img_cart !=null)
        {
            viewHolder.img_favourite.setTag(R.drawable.ic_favorite);
            viewHolder.img_favourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if( (Integer) viewHolder.img_favourite.getTag() ==R.drawable.ic_favorite )
                    {
                        viewHolder.img_favourite.setImageResource(R.drawable.ic_favourite_2);
                        viewHolder.img_favourite.setTag(R.drawable.ic_favourite_2);
                    }
                    else
                    {
                        viewHolder.img_favourite.setImageResource(R.drawable.ic_favorite);
                        viewHolder.img_favourite.setTag(R.drawable.ic_favorite);
                    }

                }
            });
            viewHolder.img_cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, Cart_fagment.class));
                }
            });
        }


        return convertView;
    }

    // ViewHolder class dùng để tối ưu hóa hiệu suất
    public class ViewHolder {
        TextView tvname, tvauthor, tvprice, tvsold;
        ImageView img,img_favourite,img_cart;
    }
}
