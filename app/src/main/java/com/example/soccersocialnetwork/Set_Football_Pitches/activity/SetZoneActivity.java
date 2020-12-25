package com.example.soccersocialnetwork.Set_Football_Pitches.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.Set_Football_Pitches.flagment.SetListOfYardFragment;
import com.example.soccersocialnetwork.Set_Football_Pitches.flagment.SetZoneInfoFragment;
import com.example.soccersocialnetwork.football_field_owner.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class SetZoneActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    String idKhu = "";
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

    public String getIdKhu() {
        return idKhu;
    }

    private void setControl() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
    }
    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SetZoneInfoFragment(), "");
        adapter.addFragment(new SetListOfYardFragment(), "");
        viewPager.setAdapter(adapter);

    }
    private void setIcon()
    {
        tabLayout.getTabAt(0).setIcon(R.drawable.icon_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsanbong);
    }
}