package com.example.soccersocialnetwork.Football_Pitches.flagment;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.soccersocialnetwork.Football_Pitches.adapter.CustomAdapterFreeTime;
import com.example.soccersocialnetwork.Football_Pitches.model.Book;
import com.example.soccersocialnetwork.Football_Pitches.model.FreeTime;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.Set_Football_Pitches.model.SetFootballPitches;
import com.example.soccersocialnetwork.football_field_owner.model.FootballPitches;
import com.example.soccersocialnetwork.football_field_owner.model.RushHour;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.soccersocialnetwork.Football_Pitches.model.FreeTime;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.football_field_owner.model.RushHour;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListFreeTimeFragment extends Fragment {
    TextView tvTenKhu, tvDiaChi, tvSan, tvTenNguoiDat, tvSDT, tvGioBD, tvGioKT, tvTongTien;
    Spinner spDoi;
    int gioBD, phutBD, gioKT, phutKT;

    ListView lvGioTrong;
    ArrayList<FreeTime> data_FreeTimes = new ArrayList<>();
    ArrayAdapter adapter_FreeTimes;
    DatabaseReference mFirebase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_free_time, container, false);
        lvGioTrong = view.findViewById(R.id.lvGioTrong);
        mFirebase = FirebaseDatabase.getInstance().getReference();
        setEvent();
        // Inflate the layout for this fragment
        return view;
    }
    private void setEvent() {
        KhoiTao();
        adapter_FreeTimes = new CustomAdapterFreeTime(getContext(), R.layout.item_listview_freetime, data_FreeTimes);
        lvGioTrong.setAdapter(adapter_FreeTimes);
        lvGioTrong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialogDatSan();
            }
        });
    }
    private void dialogDatSan() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_set_football_pitches, null);
        //ánh xạ
        tvTenKhu = alertLayout.findViewById(R.id.tvTenKhu);
        tvDiaChi = alertLayout.findViewById(R.id.tvDiaChi);
        tvSan = alertLayout.findViewById(R.id.tvSan);
        tvTenNguoiDat = alertLayout.findViewById(R.id.tvTenNguoiDat);
        tvSDT = alertLayout.findViewById(R.id.tvSDT);
        tvTongTien = alertLayout.findViewById(R.id.tvTongTien);
        tvGioBD = alertLayout.findViewById(R.id.tvGioBD);
        tvGioKT = alertLayout.findViewById(R.id.tvGioKT);
        spDoi = alertLayout.findViewById(R.id.spDoi);
        //khởi tạo dữ liệu
        KhoiTao();
        //set title dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Giờ Cao Điểm");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        tvGioBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                gioBD = hourOfDay;
                                phutBD = minute;
                                String time = gioBD + ":" + phutBD;

                                try {
                                    SimpleDateFormat f24hours = new SimpleDateFormat("HH:mm");
                                    Date date = f24hours.parse(time);
                                    Toast.makeText(getContext(), time + "", Toast.LENGTH_SHORT).show();
                                    tvGioBD.setText(time);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 00, true);
                timePickerDialog.setTitle("Thời gian");
                timePickerDialog.getWindow().setBackgroundDrawableResource(R.color.colorXam);
                timePickerDialog.updateTime(gioBD, phutBD);
                timePickerDialog.show();
            }
        });
        tvGioKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                gioKT = hourOfDay;
                                phutKT = minute;
                                String time = gioKT + ":" + phutKT;
                                SimpleDateFormat f24hours = new SimpleDateFormat("hh:mm");
                                try {
                                    Date date = f24hours.parse(time);
                                    Toast.makeText(getContext(), time + "", Toast.LENGTH_SHORT).show();
                                    tvGioKT.setText(time);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 00, true);
                timePickerDialog.setTitle("Thời gian");
                timePickerDialog.getWindow().setBackgroundDrawableResource(R.color.colorXam);
                timePickerDialog.updateTime(gioKT, phutKT);
                timePickerDialog.show();
            }
        });
        //cancel
        alert.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //thêm
        alert.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /*private void loadData(){
        mFirebase.child("SanDaDat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable final String previousChildName) {
                final SetFootballPitches setFootballPitches = snapshot.getValue(SetFootballPitches.class);
                final FreeTime freeTime = new FreeTime();
                mFirebase.child("San").child(setFootballPitches.getIdSanDat()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final FootballPitches footballPitches = snapshot.getValue(FootballPitches.class);

                        mFirebase.child("Khu").child(footballPitches.getIdKhu()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (footballPitches.getId().equals(idSan)) {
                                    book.setTenDoi(snapshot.getValue().toString());
                                    book.setGioBatDau(setFootballPitches.getGioBatDau() + ":"
                                            + setFootballPitches.getPhutBatDau());
                                    book.setGioKetThuc(setFootballPitches.getGioKetThuc() + ":"
                                            + setFootballPitches.getPhutKetThuc());
                                    data_Books.add(book);
                                    adapter_Book.notifyDataSetChanged();
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
    }*/

    private void KhoiTao() {
        FreeTime freeTime = new FreeTime("06h30", "09h00");
        FreeTime freeTime1 = new FreeTime("12h30", "14h00");
        FreeTime freeTime2 = new FreeTime("16h30", "17h30");
        FreeTime freeTime3 = new FreeTime("20h30", "21h00");
        data_FreeTimes.add(freeTime);
        data_FreeTimes.add(freeTime1);
        data_FreeTimes.add(freeTime2);
        data_FreeTimes.add(freeTime3);
    }
}