package com.example.soccersocialnetwork.ViewDoi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.soccersocialnetwork.Adapter.Adapter_FeedsDoi;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.data_models.Model_FeedsDoi_View;

import java.util.ArrayList;

public class DoiActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<Model_FeedsDoi_View> listFeeds = new ArrayList<>();
    Adapter_FeedsDoi adapterFeedsDoi;

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    ArrayList<Fragment> fragmentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi);
        //setControl();

       // setEvent();
//
       fragmentList.add(new Fragment_Doi());
        fragmentList.add(new Fragment_Doi_2());

        pager = findViewById(R.id.pagerMain);
        pagerAdapter = new Fragment_SildePager(getSupportFragmentManager(),fragmentList);
        pager.setAdapter(pagerAdapter);

    }

    private void setEvent() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        adapterFeedsDoi = new Adapter_FeedsDoi(this, listFeeds);
        recyclerView.setAdapter(adapterFeedsDoi);

       ThemBaiVietDaBong();
//

    }



    private void setControl() {
        recyclerView = findViewById(R.id.recyclerView);

    }

    private void ThemBaiVietDaBong() {
        Model_FeedsDoi_View model_feedsDoi_view = new Model_FeedsDoi_View(24, "Hà Nội", "Cái Làn", "14/11/2020", "13h - 16h", "Ae mình zui vẽ");
        Model_FeedsDoi_View model_feedsDoi_view1 = new Model_FeedsDoi_View(72,"xxx","Cái Làn","14/11/2020","13h - 16h","Ae mình zui vẽ");
        Model_FeedsDoi_View model_feedsDoi_view2 = new Model_FeedsDoi_View(123,"x","Cái Làn","14/11/2020","13h - 16h","Ae mình zui vẽ");
        Model_FeedsDoi_View model_feedsDoi_view3 = new Model_FeedsDoi_View(234,"Hà Nội","Cái Làn","14/11/2020","13h - 16h","Ae mình zui vẽ");
        Model_FeedsDoi_View model_feedsDoi_view4 = new Model_FeedsDoi_View(234,"Hà Nội","Cái Làn","14/11/2020","13h - 16h","Ae mình zui vẽ");
        listFeeds.add(model_feedsDoi_view);
        listFeeds.add(model_feedsDoi_view1);
        listFeeds.add(model_feedsDoi_view2);
        listFeeds.add(model_feedsDoi_view3);
        listFeeds.add(model_feedsDoi_view4);
        adapterFeedsDoi.notifyDataSetChanged();
    }
}