package com.example.phanmembansach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Slide_Adapter extends RecyclerView .Adapter<Slide_Adapter.SlideViewHolder>{
    private Context context;
    private List<Integer> imageList;
    public Slide_Adapter(Context context, List<Integer> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slide, parent, false);
        return new SlideViewHolder(view);
    }

    @NonNull


    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {
        holder.imageView.setImageResource(imageList.get(position)); // Gán hình ảnh
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static class SlideViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
