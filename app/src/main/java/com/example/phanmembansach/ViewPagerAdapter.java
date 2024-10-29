package com.example.phanmembansach;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return  new Home_Fragment();
            case 1:
                return  new Notification_Fragment();
            case 2:
                return  new Cart_Fragment();
            case 3:
                return  new Account_Fragment();
            case 4:
                return  new All_Book_Fragment();
            case 5:
                return  new Authors_Fragment();
            case 6:
                return  new Categories_Fragment();
            case 7:
                return  new Categories_Books_Fragment();
            case 8:
                return  new Diem_Thuong_Books_Fragment();
            default:
                return  new Home_Fragment();
        }
    }

    @Override
    public int getCount() {
        return 9;
    }
}

