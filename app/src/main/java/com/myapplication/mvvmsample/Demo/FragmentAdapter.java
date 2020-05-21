package com.myapplication.mvvmsample.Demo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {


    private String[] title = {"Works", "Equipment"};

    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
        Log.d("tag", "fragment adapter");
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:

                return new WorkFragment();

            case 1:

                return new EquipmentFragment();

            default: return null;
        }

    }

    @Override
    public int getCount() {
        return title.length;
    }
}
