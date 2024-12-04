package com.example.phanmembansach;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private String loginStatus; // Dữ liệu cần truyền vào các Fragment
    private Bundle bundle;
    // Constructor nhận FragmentManager, behavior và loginStatus
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior, String loginStatus) {
        super(fm, behavior);
        this.loginStatus = loginStatus;
    }
    // Phương thức để set Bundle (dữ liệu cần truyền vào Fragment)
    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new Home_Fragment();
                break;
            case 1:
                fragment = new Notification_Fragment();
                break;
            case 2:
                fragment = new Cart_Fragment();
                break;
            case 3:
                fragment = new Account_Fragment();
                break;
            case 4:
                fragment = new All_Book_Fragment();
                break;
            case 5:
                fragment = new Authors_Fragment();
                break;
            case 6:
                fragment = new Categories_Fragment();
                break;
            case 7:
                fragment = new Categories_Books_Fragment();
                break;
            case 8:
                fragment = new Diem_Thuong_Books_Fragment();
                break;
            default:
                fragment = new Home_Fragment();
                break;
        }

        // Truyền Bundle vào Fragment nếu có
        if (fragment != null && bundle != null) {
            fragment.setArguments(bundle);  // Đặt Bundle vào Fragment
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 9;  // Số lượng Fragment trong ViewPager
    }
}
