package com.nuwansudusinghe.appliccation01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.appliccation01.R;
import com.google.android.material.tabs.TabLayout;
//@Author NuwanSudusinghe.
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_bar_manu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.bar_menu) {
//            Toast.makeText(this, "Selected About", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainMenu.this,AboutPage.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}