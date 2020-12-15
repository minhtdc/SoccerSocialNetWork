package com.example.soccersocialnetwork.football_field_owner.flagment;

import android.Manifest;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.football_field_owner.activity.AddZoneActivity;
import com.example.soccersocialnetwork.football_field_owner.model.FootballPitches;
import com.example.soccersocialnetwork.football_field_owner.model.RushHour;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddFootballPitchesFragment extends Fragment {
    View view;
    EditText txtTenSan, txtGiaGioCD, txtGiaGioBT;
    Spinner spLoaiSan, spLoaiHinhSan;
    TextView tvGioBD, tvGioKT;
    ListView lvDSGioCD;
    Button btnGioCD, btnThemSan;
    DatabaseReference mFirebase;
    DatabaseReference mFirebaseHour;
    String idHour = "";
    int gioBD, phutBD, gioKT, phutKT;

    ArrayList<RushHour> data_rh = new ArrayList<>();
    ArrayList<String> data_ls = new ArrayList<>();
    ArrayList<String> data_lhs = new ArrayList<>();
    ArrayAdapter adapter_lvCD;
    ArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_football_pitches, container, false);
        mFirebase = FirebaseDatabase.getInstance().getReference();
        mFirebaseHour = FirebaseDatabase.getInstance().getReference().child("GioCaoDiem");
        setControl();
        setEvent();
        return view;
    }

    private void setEvent() {
        KhoiTao();

        setAdapterSpinner(data_ls, adapter, spLoaiSan);
        setAdapterSpinner(data_lhs, adapter, spLoaiHinhSan);

        lvDSGioCD.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    data_rh.remove(i);
                    adapter_lvCD.notifyDataSetChanged();
                return false;
            }
        });
        btnGioCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogGioCaoDiem();

            }
        });

        btnThemSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = mFirebase.child("San").push().getKey();
                FootballPitches footballPitches = getFootballPitches(key);
                mFirebase.child("San").child(key).setValue(footballPitches);
                mFirebaseHour.child(footballPitches.getId()).setValue(data_rh);

                txtTenSan.setText("");
                spLoaiSan.setSelection(0);
                spLoaiHinhSan.setSelection(0);
                txtGiaGioBT.setText("");
                txtGiaGioCD.setText("");
                data_rh.clear();
                adapter_lvCD.notifyDataSetChanged();
            }
        });
    }

    //Hiển thị dialog giờ cao điểm
    private void dialogGioCaoDiem() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_rush_hour, null);
        //ánh xạ
        tvGioBD = alertLayout.findViewById(R.id.tvGioBD);
        tvGioKT = alertLayout.findViewById(R.id.tvGioKT);
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
                                    Toast.makeText(getContext(), time+"", Toast.LENGTH_SHORT).show();
                                    tvGioBD.setText(time);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 00, true);
                timePickerDialog.setTitle("Thời gian");
                timePickerDialog.getWindow().setBackgroundDrawableResource(R.color.colorXam);
                timePickerDialog.updateTime(gioBD,phutBD);
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
                                    Toast.makeText(getContext(), time+"", Toast.LENGTH_SHORT).show();
                                    tvGioKT.setText(time);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 00, true);
                timePickerDialog.setTitle("Thời gian");
                timePickerDialog.getWindow().setBackgroundDrawableResource(R.color.colorXam);
                timePickerDialog.updateTime(gioKT,phutKT);
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
                idHour = String.valueOf(data_rh.size());
                RushHour rushHour = getRushHour(idHour);
                data_rh.add(rushHour);
                if (adapter_lvCD == null) {
                    adapter_lvCD = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,data_rh);
                    lvDSGioCD.setAdapter(adapter_lvCD);
                } else {
                    adapter_lvCD.notifyDataSetChanged();
                    lvDSGioCD.setSelection(adapter_lvCD.getCount() - 1);
                }
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    private RushHour getRushHour(String idHour) {
        RushHour rushHour = new RushHour();
        rushHour.setGioBD(gioBD);
        rushHour.setPhutBD(phutBD);
        rushHour.setGioKT(gioKT);
        rushHour.setPhutKT(phutKT);
        rushHour.setId(idHour);
        return rushHour;
    }

    private FootballPitches getFootballPitches(String id) {
        FootballPitches footballPitches = new FootballPitches();
        footballPitches.setTenSan(txtTenSan.getText().toString());
        footballPitches.setLoaiSan(spLoaiSan.getSelectedItem().toString());
        footballPitches.setLoaiHinhSan(spLoaiHinhSan.getSelectedItem().toString());
        footballPitches.setGiaBT(txtGiaGioBT.getText().toString());
        footballPitches.setGiaCD(txtGiaGioCD.getText().toString());
        footballPitches.setId(id);
        return footballPitches;
    }

    private void setAdapterSpinner(ArrayList data, ArrayAdapter adapter, Spinner spinner) {
        if (adapter == null) {
            adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, data);
            spinner.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
            spinner.setSelection(adapter.getCount() - 1);
        }
    }

    private void KhoiTao() {
        data_ls.add("Cỏ Tự Nhiên");
        data_ls.add("Cỏ Nhân Tạo");
        data_lhs.add("5 người");
        data_lhs.add("7 người");
        data_lhs.add("9 người");
    }

    private void setControl() {
        txtTenSan = view.findViewById(R.id.txtTenSan);
        txtGiaGioBT = view.findViewById(R.id.txtGiaGioBT);
        txtGiaGioCD = view.findViewById(R.id.txtGiaGioCaoDiem);
        spLoaiSan = view.findViewById(R.id.spLoaiSan);
        spLoaiHinhSan = view.findViewById(R.id.spLoaiHinhSan);
        lvDSGioCD = view.findViewById(R.id.lvDanhSach);
        btnGioCD = view.findViewById(R.id.btnThemGioCD);
        btnThemSan = view.findViewById(R.id.btnThemSan);
    }

}