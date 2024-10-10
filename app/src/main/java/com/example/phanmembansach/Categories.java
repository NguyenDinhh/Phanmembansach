package com.example.phanmembansach;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Categories extends AppCompatActivity {
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories2);
        lv = findViewById(R.id.lvcategories);
        ArrayList<Category> arrCategories = new ArrayList<>();
        Category category1 = new Category("Fantasy", 134, "     Fantasy books transport readers to imaginary worlds filled with magic, supernatural creatures, and epic adventures.", 89, "fantasy_book");
        Category category2 = new Category("Comedy", 134, "      Comedy books are designed to entertain and amuse readers with humorous plots, witty dialogue, and lighthearted situations.", 89, "comedy_book");
        Category category3 = new Category("Cooking", 134, "     “Discover delicious recipes and culinary techniques to elevate your home cooking. Would you like any more help with this?", 89, "cooking_book");
        Category category4 = new Category("Mystery", 134, "     Adventure books are thrilling tales that take readers on exciting journeys, often featuring daring heroes, exotic locations, and high-stakes challenges.", 89, "mystery_book");
        Category category5 = new Category("Aventure", 134, "      Adventure books are thrilling tales that take readers on exciting journeys, often featuring daring heroes, exotic locations, and high-stakes challenges.", 89, "aventure_book");
        Category category6 = new Category("Education", 134, "     Educational Books: These books provide knowledge and insights on various subjects, aiming to educate and inform readers.", 89, "education_book");
        Category category7 = new Category("History", 134, "     Fantasy books transport readers to imaginary worlds filled with magic, supernatural creatures, and epic adventures.", 89, "history_book");
        Category category8 = new Category("Honnor", 134, "     Fantasy books transport readers to imaginary worlds filled with magic, supernatural creatures, and epic adventures.", 89, "honnor_book");
        Category category9 = new Category("Romance", 134, "     Fantasy books transport readers to imaginary worlds filled with magic, supernatural creatures, and epic adventures.", 89, "romance_book");
        Category category10 = new Category("Science", 134, "     Fantasy books transport readers to imaginary worlds filled with magic, supernatural creatures, and epic adventures.", 89, "science_book");
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
        Adapter_Categories adapter = new Adapter_Categories(this,R.layout.row_categories, arrCategories);
        lv.setAdapter(adapter);
    }
}