package com.myapplication.mvvmsample.Demo;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.myapplication.mvvmsample.R;

public class ExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_main_activity);

        setUpViewPager();
    }

    private void setUpViewPager()
    {
        Log.d("tag", "set up view pager");
        ViewPager viewPager = findViewById(R.id.viewpager);
        TabLayout tabs = findViewById(R.id.tab);

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabs.setupWithViewPager(viewPager);
    }

}
