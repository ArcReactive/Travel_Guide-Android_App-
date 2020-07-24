package com.example.appliccation01;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
//@Author NuwanSudusinghe.
public class PagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;


    public PagerAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        //choosing which one selected.
        switch (position){
            case 0:
                return new AllCountriesFragment();
            case 1:
                return new FavoriteFragment();
            default:
                return null;
        }

    }

    //return count.
    @Override
    public int getCount() {
        return numOfTabs;
    }
}
