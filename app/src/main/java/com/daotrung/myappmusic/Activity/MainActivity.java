package com.daotrung.myappmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.daotrung.myappmusic.Adapter.MainViewPaperAdapter;
import com.daotrung.myappmusic.Fragment.FragmentTimKiem;
import com.daotrung.myappmusic.Fragment.FragmentTrangChu;
import com.daotrung.myappmusic.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        init();
    }

    public void init() {
        MainViewPaperAdapter mainViewPagerAdapter = new MainViewPaperAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new FragmentTrangChu(), "Trang Chủ");
        mainViewPagerAdapter.addFragment(new FragmentTimKiem(), "Tìm Kiếm");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
    }

    public void anhXa(){
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}