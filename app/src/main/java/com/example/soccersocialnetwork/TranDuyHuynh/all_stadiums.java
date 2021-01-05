package com.example.soccersocialnetwork.TranDuyHuynh;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.models.thongTinTranDau;

import java.util.ArrayList;

public class all_stadiums extends AppCompatActivity {
    // khai báo đối tượng cho list view
    private ListView listView;
    private ArrayList<thongTinTranDau> thongTinTranDau;
    ImageView imgBack;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_stadiums);
//        createDataForListView();
//
//        // setAdapter cho list view
//        information_findTeams_Adapter adapter = new information_findTeams_Adapter(all_stadiums.this,R.layout.listview_doi_dang_tim_tran,information_findTeams);
//        listView = (ListView) findViewById(R.id.lstAll_Stadiums);
//        listView.setAdapter(adapter);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    // thêm dữ liệu giả cho list view đội tìm trận
//    private void createDataForListView() {
//
//        thongTinTranDau = new ArrayList<thongTinTranDau>();
//        thongTinTranDau.add(new information_findTeams(R.drawable.img_team1, "Doi 1", "TP.HCM", "19h -> 20h", "20/12/2020"));
//        thongTinTranDau.add(new information_findTeams(R.drawable.img_team2, "Doi 2", "Hà Nội", "7h -> 10h", "21/12/2020"));
//        thongTinTranDau.add(new information_findTeams(R.drawable.img_team3, "Doi 3", "Hải Phòng", "14h -> 15h", "22/12/2020"));
//        thongTinTranDau.add(new information_findTeams(R.drawable.img_team4, "Doi 4", "Nghệ An", "18h -> 19h", "23/12/2020"));
//        thongTinTranDau.add(new information_findTeams(R.drawable.img_team5, "Doi 5", "Quảng Bình", "18h -> 20h", "21/12/2020"));
//        thongTinTranDau.add(new information_findTeams(R.drawable.img_team5, "Doi 5", "Quảng Bình", "18h -> 20h", "21/12/2020"));
//        thongTinTranDau.add(new information_findTeams(R.drawable.img_team5, "Doi 5", "Quảng Bình", "18h -> 20h", "21/12/2020"));
//    }
}
