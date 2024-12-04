package com.example.phanmembansach;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String loginStatus = getIntent().getStringExtra("login");
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        viewPager = findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,loginStatus);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menu_notification).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.menu_cart).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.menu_account).setChecked(true);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.menu_home)
                {
                    viewPager.setCurrentItem(0);
                    return true;
                }
                else  if(item.getItemId() == R.id.menu_notification)
                {
                    viewPager.setCurrentItem(1);
                    return true;
                }
                else  if(item.getItemId() == R.id.menu_cart)
                {
                    viewPager.setCurrentItem(2);
                    return true;
                }
                else  if(item.getItemId() == R.id.menu_account)
                {
                    viewPager.setCurrentItem(3);
                    return true;
                }
                else
                    return  false;
            }
        });
        int position = getIntent().getIntExtra("fragment_cart", 0);
        viewPager.setCurrentItem(position);
    }

    public void setCurrentPage(int i) {
        // Chuyển đến trang trong ViewPager
        viewPager.setCurrentItem(i);
    }
    public void setCurrentPage(int i, Bundle bundle) {
        // Cập nhật bundle cho adapter
        ViewPagerAdapter adapter = (ViewPagerAdapter) viewPager.getAdapter();
        if (adapter != null) {
            adapter.setBundle(bundle);  // Truyền bundle vào adapter
        }

        // Sau đó, chuyển đến trang i trong ViewPager
        viewPager.setCurrentItem(i);
    }


}