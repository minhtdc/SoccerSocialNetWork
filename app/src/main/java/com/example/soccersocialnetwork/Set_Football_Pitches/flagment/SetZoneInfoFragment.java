package com.example.soccersocialnetwork.Set_Football_Pitches.flagment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.fragments.stadium_flagment;
import com.example.soccersocialnetwork.football_field_owner.activity.ListZone;
import com.example.soccersocialnetwork.football_field_owner.activity.ZoneInfoActivity;
import com.example.soccersocialnetwork.football_field_owner.model.Zone;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SetZoneInfoFragment extends Fragment {
    DatabaseReference mData;
    TextView txtTenkhu,txtDiachi,txtLoaihinhsan,txtLoaisan,txtGio;
    ArrayList<Zone> data = new ArrayList<>();
    String idKhu = stadium_flagment.idKhu;
    String loaiSan, loaiHinhSan;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zone_info, container, false);
        txtTenkhu = view.findViewById(R.id.txtTenKhu);
        txtDiachi = view.findViewById(R.id.txtDiaChi);
        txtLoaihinhsan = view.findViewById(R.id.txtLoaiHinhSan);
        txtLoaisan = view.findViewById(R.id.txtLoaiSan);
        txtGio = view.findViewById(R.id.txtGio);

        loadData();

//        mData = FirebaseDatabase.getInstance().getReference();
        return view;
    }
    private void loadData() {
        mData = FirebaseDatabase.getInstance().getReference().child("Khu").child(idKhu);
        mData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Zone zone = snapshot.getValue(Zone.class);
                //data.add(new Zone(zone.getTenKhu(), zone.getAnh(), zone.getThanhPho(),
                //       zone.getQuan(), zone.getDiaChi(), zone.getGioMo(), zone.getPhutMo(),
                //       zone.getGioDong(), zone.getPhutDong(), zone.getPushId()));
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
                txtTenkhu.setText(zone.getTenKhu());
                txtDiachi.setText(zone.getDiaChi());
                txtLoaisan.setText(loaiSan);
                txtLoaihinhsan.setText(loaiHinhSan);
                txtGio.setText(zone.getGioMo() +":"+ zone.getGioDong() + " ~ " + zone.getGioDong() +":"+ zone.getPhutDong());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
