
// màn hình trang chủ khi người dùng đăng nhập vào ứng dụng
// Trần Duy Huynh
package com.example.soccersocialnetwork.TranDuyHuynh;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.fragments.home_flagment;
import com.example.soccersocialnetwork.TranDuyHuynh.fragments.menu_flagment;
import com.example.soccersocialnetwork.TranDuyHuynh.fragments.notification_flagment;
import com.example.soccersocialnetwork.TranDuyHuynh.fragments.stadium_flagment;
import com.example.soccersocialnetwork.TranDuyHuynh.fragments.team_flagment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class home_layout extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    // khai báo cái fragment
    private com.example.soccersocialnetwork.TranDuyHuynh.fragments.home_flagment home_flagment;
    private com.example.soccersocialnetwork.TranDuyHuynh.fragments.team_flagment team_flagment;
    private com.example.soccersocialnetwork.TranDuyHuynh.fragments.stadium_flagment stadium_flagment;
    private com.example.soccersocialnetwork.TranDuyHuynh.fragments.notification_flagment notification_flagment;
    private com.example.soccersocialnetwork.TranDuyHuynh.fragments.menu_flagment menu_flagment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);

        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        // khởi tạo các flagment
        home_flagment = new home_flagment();
        team_flagment = new team_flagment();
        stadium_flagment = new stadium_flagment();
        menu_flagment = new menu_flagment();
        notification_flagment  = new notification_flagment();

        // tạo tab yêu cầu cho từng trang, khi người dùng click vào 1 tab thì thay đổi các viewpage và hiển thị theo yêu cầu
        tabLayout.setupWithViewPager(viewPager);
        getViewPagerAdapter();
    }

    private void getViewPagerAdapter(){

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),0);
        viewPagerAdapter.addFragment(home_flagment);
        viewPagerAdapter.addFragment(team_flagment);
        viewPagerAdapter.addFragment(stadium_flagment);
        viewPagerAdapter.addFragment(notification_flagment);
        viewPagerAdapter.addFragment(menu_flagment);

        viewPager.setAdapter(viewPagerAdapter);
        // thêm icon vào mỗi item của tablayout
        tabLayout.getTabAt(0).setIcon(R.drawable.icon_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_team);
        tabLayout.getTabAt(2).setIcon(R.drawable.icon_stadium);
        tabLayout.getTabAt(3).setIcon(R.drawable.icon_notification);
        tabLayout.getTabAt(4).setIcon(R.drawable.icon_menu);
//        BadgeDrawable badgeDrawable = tabLayout.getTabAt(3).getOrCreateBadge();
//        badgeDrawable.setVisible(true);
//        badgeDrawable.setNumber(0);

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment){
            fragments.add(fragment);

        }

        @NonNull

        // lấy ra flagment tại vị trí được chọn
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        // lấy tổng flagment
        @Override
        public int getCount() {
            return fragments.size();
        }

    }

    public  void finish() {
        finish();
    }
}