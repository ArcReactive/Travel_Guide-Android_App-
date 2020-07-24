package com.example.appliccation01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.tabs.TabLayout;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Get tabs.
        TabLayout tabLayout = findViewById(R.id.tabBar1);
        //TabLayout allcountries = findViewById(R.id.tabAllCountries);
        //TabLayout favorite = findViewById(R.id.tabFavorite);
        final ViewPager viewpager = findViewById(R.id.viewPager);

        //Set PagerAdapter.
        PagerAdapter pagerAdapter = new PagerAdapter(
                getSupportFragmentManager(),
                tabLayout.getTabCount());

        viewpager.setAdapter(pagerAdapter);

        //Set tab view which was clicked.
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}