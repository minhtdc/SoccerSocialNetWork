package com.example.soccersocialnetwork.football_field_owner.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.soccersocialnetwork.Football_Pitches.activity.FootballPitchesActivity;
import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.football_field_owner.adapter.CustomAdapterListZone;
import com.example.soccersocialnetwork.football_field_owner.model.Zone;
import com.google.firebase.FirebaseCommonRegistrar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListZone extends AppCompatActivity {
    ListView lvDanhSach;
    ArrayList<Zone> data = new ArrayList<>();
    CustomAdapterListZone adapterZone = null;
    DatabaseReference mFirebaseDatabase;
    String idUser = LoginActivity.USER_ID_CURRENT;
    public static String idKhu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_zone);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        setControl();
        setEvent();
    }

    private void setEvent() {

        adapterZone = new CustomAdapterListZone(this, R.layout.item_listview_zone, data);
        lvDanhSach.setAdapter(adapterZone);
        loadData();
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListZone.this, ZoneInfoActivity.class);
                idKhu = data.get(i).getPushId();
                startActivity(intent);
            }
        });

    }

    private void loadData() {
        mFirebaseDatabase.child("Khu").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Zone zone = snapshot.getValue(Zone.class);
                if (zone.getIdUser().equals(idUser))
                {
                    data.add(new Zone(zone.getTenKhu(), zone.getAnh(), zone.getThanhPho(),
                            zone.getQuan(), zone.getDiaChi(), zone.getPushId(), zone.getIdUser(), zone.getGioMo(), zone.getPhutMo(),
                            zone.getGioDong(), zone.getPhutDong()));
                    adapterZone.notifyDataSetChanged();
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

    private void setControl() {
        lvDanhSach = findViewById(R.id.lvDanhSach);
    }
}