package com.example.soccersocialnetwork.football_field_owner.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.football_field_owner.adapter.ViewPagerAdapter;
import com.example.soccersocialnetwork.football_field_owner.flagment.AddFootballPitchesFragment;
import com.example.soccersocialnetwork.football_field_owner.flagment.ListOfYardFragment;
import com.example.soccersocialnetwork.football_field_owner.flagment.WaitingListFragment;
import com.example.soccersocialnetwork.football_field_owner.flagment.ZoneInfoFragment;
import com.google.android.material.tabs.TabLayout;

public class ZoneInfoActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static String idKhu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone_info);
        setControl();
        setupViewPager();
        Intent intent = getIntent();
        idKhu = intent.getStringExtra("idKhu");
        tabLayout.setupWithViewPager(viewPager);
        setIcon();
    }

    private void setControl() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
    }
    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ZoneInfoFragment(), "");
        adapter.addFragment(new ListOfYardFragment(), "");
        adapter.addFragment(new WaitingListFragment(), "");
        adapter.addFragment(new AddFootballPitchesFragment(), "");
        viewPager.setAdapter(adapter);

    }
    private void setIcon()
    {
        tabLayout.getTabAt(0).setIcon(R.drawable.icon_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsanbong);
        tabLayout.getTabAt(2).setIcon(R.drawable.icon_duyet);
        tabLayout.getTabAt(3).setIcon(R.drawable.icon_add);
    }
}