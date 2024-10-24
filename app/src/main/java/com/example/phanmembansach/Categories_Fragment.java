package com.example.phanmembansach;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Categories_Fragment extends Fragment {
    public Categories_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.categories_fragment, container, false);
        ImageView back = mView.findViewById(R.id.img_back);
       ListView  lv = mView.findViewById(R.id.lvcategories);
        ArrayList<Category> arrCategories = new ArrayList<>();
        Category category1 = new Category("Fantasy", 134, "     Fantasy books transport readers to imaginary worlds filled with magic, supernatural creatures, and epic adventures.", 89, "fantasy_book",1);
        Category category2 = new Category("Comedy", 134, "      Comedy books are designed to entertain and amuse readers with humorous plots, witty dialogue, and lighthearted situations.", 89, "comedy_book",2);
        Category category3 = new Category("Cooking", 134, "     “Discover delicious recipes and culinary techniques to elevate your home cooking. Would you like any more help with this?", 89, "cooking_book",3);
        Category category4 = new Category("Mystery", 134, "     Adventure books are thrilling tales that take readers on exciting journeys, often featuring daring heroes, exotic locations, and high-stakes challenges.", 89, "mystery_book",4);
        Category category5 = new Category("Aventure", 134, "      Adventure books are thrilling tales that take readers on exciting journeys, often featuring daring heroes, exotic locations, and high-stakes challenges.", 89, "aventure_book",5);
        Category category6 = new Category("Education", 134, "     Educational Books: These books provide knowledge and insights on various subjects, aiming to educate and inform readers.", 89, "education_book",6);
        Category category7 = new Category("History", 134, "     Fantasy books transport readers to imaginary worlds filled with magic, supernatural creatures, and epic adventures.", 89, "history_book",7);
        Category category8 = new Category("Honnor", 134, "     Fantasy books transport readers to imaginary worlds filled with magic, supernatural creatures, and epic adventures.", 89, "honnor_book",8);
        Category category9 = new Category("Romance", 134, "     Fantasy books transport readers to imaginary worlds filled with magic, supernatural creatures, and epic adventures.", 89, "romance_book",9);
        Category category10 = new Category("Science", 134, "     Fantasy books transport readers to imaginary worlds filled with magic, supernatural creatures, and epic adventures.", 89, "science_book",10);
        arrCategories.add(category1);
        arrCategories.add(category2);
        arrCategories.add(category3);
        arrCategories.add(category4);
        arrCategories.add(category5);
        arrCategories.add(category6);
        arrCategories.add(category7);
        arrCategories.add(category8);
        arrCategories.add(category9);
        arrCategories.add(category10);
        Adapter_Categories adapter = new Adapter_Categories(getActivity(),R.layout.row_categories, arrCategories);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            ((Home) getActivity()).setCurrentPage(7);
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Home) getActivity()).setCurrentPage(0);
            }
        });
        return  mView;
    }
}