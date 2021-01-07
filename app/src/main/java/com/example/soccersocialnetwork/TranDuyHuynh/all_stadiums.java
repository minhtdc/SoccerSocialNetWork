package com.example.soccersocialnetwork.TranDuyHuynh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.Set_Football_Pitches.activity.SetZoneActivity;
import com.example.soccersocialnetwork.TranDuyHuynh.adapter.information_listStadiums_Adapter;
import com.example.soccersocialnetwork.TranDuyHuynh.fragments.stadium_flagment;
import com.example.soccersocialnetwork.TranDuyHuynh.models.information_listStadium;
import com.example.soccersocialnetwork.TranDuyHuynh.models.thongTinTranDau;
import com.example.soccersocialnetwork.football_field_owner.model.FootballPitches;
import com.example.soccersocialnetwork.football_field_owner.model.Zone;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class all_stadiums extends AppCompatActivity {
    // khai báo đối tượng cho list view

    private ListView listView;
    private ArrayList<thongTinTranDau> thongTinTranDau;
    ImageView imgBack;

    ArrayList<information_listStadium> listStadiums;
    ArrayList<FootballPitches> data_listStadiums = new ArrayList<>();
    DatabaseReference mFirebaseDatabase;
    ArrayAdapter information_listStadiums_adapter;
    String loaiSan, loaiHinhSan = "";
    public static String idKhu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_stadiums);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        listView = (ListView) findViewById(R.id.lstAll_Stadiums);
        stadium_flagment.idKhu = "";
        listStadiums = new ArrayList<>();
        information_listStadiums_adapter = new information_listStadiums_Adapter(all_stadiums.this, R.layout.list_stadiums, listStadiums);
        listView.setAdapter(information_listStadiums_adapter);
        loadData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(all_stadiums.this, SetZoneActivity.class);
                idKhu = listStadiums.get(i).getIdKhu();
                startActivity(intent);
            }
        });
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

    private void loadData() {
        mFirebaseDatabase.child("Khu").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                final Zone zone = snapshot.getValue(Zone.class);
                if (zone.isCoTuNhien() == true) {
                    if (zone.isCoNhanTao() == true) {
                        loaiSan = "Cỏ Tự Nhiên, Cỏ Nhân Tạo";
                    } else {
                        loaiSan = "Cỏ Tự Nhiên";
                    }
                } else {
                    if (zone.isCoNhanTao() == true) {
                        loaiSan = "Cỏ Nhân Tạo";
                    } else {
                        loaiSan = "";
                    }
                }
                if (zone.isNamNguoi() == true) {
                    if (zone.isBayNguoi() == true) {
                        if (zone.isChinNguoi() == true) {
                            loaiHinhSan = "5 Người, 7 Người, 9 Người";
                        } else {
                            loaiHinhSan = "5 Người, 7 Người";
                        }
                    } else {
                        if (zone.isChinNguoi() == true) {
                            loaiHinhSan = "5 Người, 9 Người";
                        } else {
                            loaiHinhSan = "5 Người";
                        }
                    }
                } else {
                    if (zone.isBayNguoi() == true) {
                        if (zone.isChinNguoi() == true) {
                            loaiHinhSan = "7 Người, 9 Người";
                        } else {
                            loaiHinhSan = "7 Người";
                        }
                    } else {
                        if (zone.isChinNguoi() == true) {
                            loaiHinhSan = "9 Người";
                        } else {
                            loaiHinhSan = "";
                        }
                    }
                }
                if (!loaiSan.equals("") && !loaiHinhSan.equals("")) {
                    listStadiums.add(new information_listStadium(zone.getAnh(), zone.getPushId(), zone.getTenKhu(), loaiHinhSan, loaiSan, zone.getDiaChi() + zone.getQuan() + zone.getThanhPho()));
                    information_listStadiums_adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
