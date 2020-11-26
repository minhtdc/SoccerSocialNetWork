package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_FeedsDoi;
import com.example.soccersocialnetwork.DoanThanhTung.Adapter.ViewPagerAdapter;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Feeds;
import com.example.soccersocialnetwork.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class DoiActivity extends AppCompatActivity {

    //tao thanh nang, luot qua luot lai
    private TabLayout tabLayout;
    private ViewPager viewPager;

    RecyclerView recyclerView;

    ArrayList<Feeds> listFeeds = new ArrayList<>();
    Adapter_FeedsDoi adapterFeedsDoi;

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    ArrayList<Fragment> fragmentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi);
        setControl();
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        setIcon();
        // setEvent();
//
//        fragmentList.add(new Fragment_Doi());
//        fragmentList.add(new Fragment_Doi_2());
//        fragmentList.add(new Fragment_Doi_Menu());
//
//        pager = findViewById(R.id.pagerMain);
//        pagerAdapter = new Fragment_SildePager(getSupportFragmentManager(), fragmentList);
//        pager.setAdapter(pagerAdapter);

    }

    private void setEvent() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        adapterFeedsDoi = new Adapter_FeedsDoi(this, listFeeds);
        recyclerView.setAdapter(adapterFeedsDoi);


//

    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_Doi(), "");
        adapter.addFragment(new Fragment_Doi_2(), "");
        adapter.addFragment(new Fragment_Doi_Menu(), "");

        viewPager.setAdapter(adapter);

    }

    private void setIcon() {
        tabLayout.getTabAt(0).setIcon(R.drawable.icon_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_delicious);
        tabLayout.getTabAt(2).setIcon(R.drawable.icon_bars);

    }
    private void setControl() {
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabs);

    }

}