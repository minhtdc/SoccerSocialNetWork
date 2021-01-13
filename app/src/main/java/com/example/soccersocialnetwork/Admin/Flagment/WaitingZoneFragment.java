package com.example.soccersocialnetwork.Admin.Flagment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.soccersocialnetwork.Admin.Adapter.AdapterWaitingZone;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.adapter.information_listStadiums_Adapter;
import com.example.soccersocialnetwork.TranDuyHuynh.models.information_listStadium;
import com.example.soccersocialnetwork.football_field_owner.model.Zone;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class WaitingZoneFragment extends Fragment {
    ListView lvDanhSach;
    DatabaseReference mFirebase;
    View view;
    ArrayList<information_listStadium> listStadiums;
    ArrayAdapter adapterZone;
    String loaiSan, loaiHinhSan = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_waiting_zone, container, false);
        mFirebase = FirebaseDatabase.getInstance().getReference();
        setControl();
        setEvent();
        return view;
    }

    private void setEvent() {
        listStadiums = new ArrayList<>();
        adapterZone = new AdapterWaitingZone(getContext(), R.layout.list_stadiums, listStadiums);
        lvDanhSach.setAdapter(adapterZone);
        loadData();
    }

    private void setControl() {
        lvDanhSach = view.findViewById(R.id.lvDanhSach);
    }

    private void loadData() {
        listStadiums.clear();
        mFirebase.child("ChoDuyetKhu").addChildEventListener(new ChildEventListener() {

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
                listStadiums.add(new information_listStadium(zone.getAnh(), zone.getPushId(), zone.getTenKhu(), loaiHinhSan, loaiSan, zone.getDiaChi() + zone.getQuan() + zone.getThanhPho()));
                adapterZone.notifyDataSetChanged();
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

