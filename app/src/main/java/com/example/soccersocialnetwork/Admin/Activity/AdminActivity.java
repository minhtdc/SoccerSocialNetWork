package com.example.soccersocialnetwork.Admin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.soccersocialnetwork.Admin.Flagment.TeamFragment;
import com.example.soccersocialnetwork.Admin.Flagment.UserFragment;
import com.example.soccersocialnetwork.Admin.Flagment.WaitingZoneFragment;
import com.example.soccersocialnetwork.Admin.Flagment.ZoneFragment;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.football_field_owner.adapter.ViewPagerAdapter;
import com.example.soccersocialnetwork.football_field_owner.flagment.AddFootballPitchesFragment;
import com.example.soccersocialnetwork.football_field_owner.flagment.ListOfYardFragment;
import com.example.soccersocialnetwork.football_field_owner.flagment.WaitingListFragment;
import com.example.soccersocialnetwork.football_field_owner.flagment.ZoneInfoFragment;
import com.google.android.material.tabs.TabLayout;

public class AdminActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setControl();
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        setIcon();
    }

    private void setControl() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
    }
    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new UserFragment(), "");
        adapter.addFragment(new TeamFragment(), "");
        adapter.addFragment(new ZoneFragment(), "");
        adapter.addFragment(new WaitingZoneFragment(), "");
        viewPager.setAdapter(adapter);

    }
    private void setIcon()
    {
        tabLayout.getTabAt(0).setIcon(R.drawable.icon_user1);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_team);
        tabLayout.getTabAt(2).setIcon(R.drawable.iconsanbong);
        tabLayout.getTabAt(3).setIcon(R.drawable.icon_duyet);
    }
}