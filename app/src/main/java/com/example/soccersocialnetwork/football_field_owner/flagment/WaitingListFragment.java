package com.example.soccersocialnetwork.football_field_owner.flagment;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.Set_Football_Pitches.activity.SetFootballPitchesActivity;
import com.example.soccersocialnetwork.Set_Football_Pitches.model.SetFootballPitches;
import com.example.soccersocialnetwork.Set_Football_Pitches.model.SetTeam;
import com.example.soccersocialnetwork.TranDuyHuynh.infomation_dangtintimtran;
import com.example.soccersocialnetwork.TranDuyHuynh.models.information_TimSan;
import com.example.soccersocialnetwork.football_field_owner.activity.ListZone;

import com.example.soccersocialnetwork.football_field_owner.adapter.CustomAdapterWaiting;
import com.example.soccersocialnetwork.football_field_owner.model.FootballPitches;
import com.example.soccersocialnetwork.football_field_owner.model.Waiting;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WaitingListFragment extends Fragment {

    ImageView imgTeam,imgThoat;
    TextView tvChuCLB, tvTenNguoiDat, tvSDT, tvSan, tvNgay, tvGio, tvTongTien;
    Button btnChapNhan,btnHuy;
    ListView lvWaiting;
    ArrayList<SetFootballPitches> data_SetFootballPitches = new ArrayList<>();
    ArrayList<Waiting> data_waiting = new ArrayList<>();
    CustomAdapterWaiting adapter_waiting;

    DatabaseReference mFirebase;
    String idKhu = ListZone.idKhu;
    String idDatSan;
    String tenChuCLB;
    Waiting waitings = new Waiting();
    int index = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_waiting_list, container, false);
        lvWaiting = view.findViewById(R.id.lvWaiting);
        mFirebase = FirebaseDatabase.getInstance().getReference();

        setEvent();
        return view;
    }

    private void setEvent() {

        adapter_waiting = new CustomAdapterWaiting(getContext(), R.layout.item_listview_waiting, data_waiting);
        lvWaiting.setAdapter(adapter_waiting);
        loadData();

        lvWaiting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                idDatSan = data_waiting.get(i).getIdDuyet();
                waitings = data_waiting.get(i);
                index = i;
                dialogDatSan();
                Toast.makeText(getContext(), idDatSan, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void dialogDatSan() {
        final LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_waiting, null);
        //ánh xạ
        tvChuCLB = alertLayout.findViewById(R.id.tvChuCLB);
        tvNgay = alertLayout.findViewById(R.id.tvNgay);
        tvSan = alertLayout.findViewById(R.id.tvSan);
        tvTenNguoiDat = alertLayout.findViewById(R.id.tvTenNguoiDat);
        tvSDT = alertLayout.findViewById(R.id.tvSDT);
        tvTongTien = alertLayout.findViewById(R.id.tvTongTien);
        tvGio = alertLayout.findViewById(R.id.tvGio);
        imgTeam = alertLayout.findViewById(R.id.imgTeam);
        imgThoat = alertLayout.findViewById(R.id.imgThoat);
        btnHuy = alertLayout.findViewById(R.id.btnHuy);
        btnChapNhan = alertLayout.findViewById(R.id.btnChapNhan);
        //khởi tạo dữ liệu
        tvSan.setText(waitings.getSan());
        tvNgay.setText(waitings.getNgay());
        tvGio.setText(waitings.getGio());
        loadAdmin();
        loadUser();
        //set title dialog
        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(alertLayout);
        alert.setCancelable(false);
        final AlertDialog dialog = alert.create();

        imgThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnChapNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebase.child("ChoDuyetDatSan").child(waitings.getIdDuyet()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        mFirebase.child("SanDaDat").child(snapshot.getKey()).setValue(snapshot.getValue());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                mFirebase.child("ChoDuyetDatSan").child(waitings.getIdDuyet()).removeValue();
                data_waiting.remove(index);
                adapter_waiting.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebase.child("ChoDuyetDatSan").child(waitings.getIdDuyet()).removeValue();
                data_waiting.remove(index);
                adapter_waiting.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void loadUser(){
        mFirebase.child("ChoDuyetDatSan").child(idDatSan).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final SetFootballPitches setFootballPitches = snapshot.getValue(SetFootballPitches.class);
                mFirebase.child("users").child(setFootballPitches.getIdNguoiDat()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        tvTenNguoiDat.setText(snapshot.child("userName").getValue().toString());
                        tvTongTien.setText(setFootballPitches.getTongTien());

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadAdmin(){
        mFirebase.child("ChoDuyetDatSan").child(idDatSan).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final SetFootballPitches setFootballPitches = snapshot.getValue(SetFootballPitches.class);
                mFirebase.child("Team").child(setFootballPitches.getIdDoiDat()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final String image = snapshot.child("hinhAnh").getValue().toString();
                        for (DataSnapshot data : snapshot.child("listThanhVien").getChildren()){
                            if (data.getValue().equals("Admin")){
                                mFirebase.child("users").child(data.getKey()).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        tvChuCLB.setText(snapshot.getValue().toString());
                                        Picasso.get().load(image).fit().into(imgTeam);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadData() {
        data_waiting.clear();
        mFirebase.child("ChoDuyetDatSan").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable final String previousChildName) {
                final SetFootballPitches setFootballPitches = snapshot.getValue(SetFootballPitches.class);
                final Waiting waiting = new Waiting();
                final String idDuyet = snapshot.getKey();
                mFirebase.child("San").child(setFootballPitches.getIdSanDat()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final FootballPitches footballPitches = snapshot.getValue(FootballPitches.class);

                        mFirebase.child("Team").child(setFootballPitches.getIdDoiDat()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (footballPitches.getIdKhu().equals(idKhu)) {
                                    data_SetFootballPitches.add(setFootballPitches);
                                    waiting.setIdDuyet(idDuyet);
                                    waiting.setSan(footballPitches.getTenSan());
                                    waiting.setTenDoi(snapshot.child("tenDoi").getValue().toString());
                                    waiting.setGio(setFootballPitches.getGioBatDau() + ":"
                                            + setFootballPitches.getPhutBatDau() + " - "
                                            + setFootballPitches.getGioKetThuc() + ":"
                                            + setFootballPitches.getPhutKetThuc());
                                    waiting.setNgay(setFootballPitches.getNgayDat());
                                    waiting.setAnhDoi(snapshot.child("hinhAnh").getValue().toString());
                                    data_waiting.add(waiting);
                                    adapter_waiting.notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

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