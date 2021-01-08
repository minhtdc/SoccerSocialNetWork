package com.example.soccersocialnetwork.Set_Football_Pitches.flagment;

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
import com.example.soccersocialnetwork.Football_Pitches.model.FreeTime;
import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.Set_Football_Pitches.activity.SetFootballPitchesActivity;
import com.example.soccersocialnetwork.Set_Football_Pitches.model.SetFootballPitches;
import com.example.soccersocialnetwork.Set_Football_Pitches.model.SetTeam;
import com.example.soccersocialnetwork.TranDuyHuynh.fragments.stadium_flagment;
import com.example.soccersocialnetwork.data_models.Users;
import com.example.soccersocialnetwork.football_field_owner.model.FootballPitches;
import com.example.soccersocialnetwork.football_field_owner.model.RushHour;
import com.example.soccersocialnetwork.football_field_owner.model.Zone;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SetListFreeTimeFragment extends Fragment {
    TextView tvTenKhu, tvDiaChi, tvSan, tvTenNguoiDat, tvSDT, tvGioBD, tvGioKT, tvTongTien;
    Spinner spDoi;
    int gioBD, phutBD, gioKT, phutKT;
    String idKhu = stadium_flagment.idKhu;
    String idSan = SetListOfYardFragment.idSan;
    String idUser = LoginActivity.USER_ID_CURRENT;
    ListView lvGioTrong;
    ArrayList<FreeTime> data_FreeTimes = new ArrayList<>();
    ArrayList<SetTeam> dataDoi = new ArrayList<>();
    ArrayAdapter adapter_FreeTimes;
    ArrayAdapter adapter_tenDoi;
    DatabaseReference mFirebaseDatabase;
    SetFootballPitchesActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_free_time, container, false);
        lvGioTrong = view.findViewById(R.id.lvGioTrong);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

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
        mActivity = (SetFootballPitchesActivity) getActivity();
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
        loadKhu();
        loadSan();
        loadUser();
        adapter_tenDoi = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, dataDoi);
        spDoi.setAdapter(adapter_tenDoi);
        loadDoi();
        //set title dialog
        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
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
                                    tinhTongTien();
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
                mActivity.recreate();
                Toast.makeText(getContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //thêm
        alert.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mFirebaseDatabase.child("ChoDuyetDatSan").push().setValue(getSetFootballPitches());
                mActivity.recreate();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    private SetFootballPitches getSetFootballPitches() {
        SetFootballPitches setFootballPitches = new SetFootballPitches();
        setFootballPitches.setIdDoiDat(dataDoi.get(spDoi.getSelectedItemPosition()).getIdDoi());
        setFootballPitches.setIdNguoiDat(idUser);
        setFootballPitches.setIdSanDat(idSan);
        setFootballPitches.setGioBatDau(gioBD);
        setFootballPitches.setPhutBatDau(phutBD);
        setFootballPitches.setGioKetThuc(gioKT);
        setFootballPitches.setPhutKetThuc(phutKT);
        setFootballPitches.setTongTien(tvTongTien.getText().toString());
        setFootballPitches.setNgayDat(SetFootballPitchesActivity.ngayDat);
        return setFootballPitches;
    }

    private void tinhTongTien() {
        final int batDau = gioBD * 60 + phutBD;
        final int ketThuc = gioKT * 60 + phutKT;

        mFirebaseDatabase.child("San").child(idSan).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final FootballPitches footballPitches = snapshot.getValue(FootballPitches.class);
                mFirebaseDatabase.child("GioCaoDiem").child(idSan).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot data : snapshot.getChildren()) {
                            RushHour rushHour = data.getValue(RushHour.class);
                            int tongTien;
                            int batDauCD = Integer.valueOf(rushHour.getGioBD()) * 60 + Integer.valueOf(rushHour.getPhutBD());
                            int ketThucCD = Integer.valueOf(rushHour.getGioKT()) * 60 + Integer.valueOf(rushHour.getPhutKT());
                            if (batDau >= batDauCD && ketThuc <= ketThucCD) {
                                tongTien = ((ketThuc - batDau)  * Integer.valueOf(footballPitches.getGiaCD())/ 60);
                                tvTongTien.setText(String.valueOf(tongTien));
                                break;
                            } else {
                                if (batDau >= batDauCD && batDau <= ketThucCD) {
                                    tongTien = (((ketThucCD - batDau)  * Integer.valueOf(footballPitches.getGiaCD()))/ 60) + (((ketThuc - ketThucCD) * Integer.valueOf(footballPitches.getGiaBT()))/ 60);
                                    tvTongTien.setText(String.valueOf(tongTien));
                                    break;
                                } else if (ketThuc >= batDauCD && ketThuc <= ketThucCD) {
                                    tongTien = (((ketThuc - batDauCD)  * Integer.valueOf(footballPitches.getGiaCD()))/ 60) + (((batDauCD - batDau)  * Integer.valueOf(footballPitches.getGiaBT()))/ 60);
                                    tvTongTien.setText(String.valueOf(tongTien));
                                    break;
                                } else {
                                    tongTien = 0;
                                    tongTien = ((ketThuc - batDau)  * Integer.valueOf(footballPitches.getGiaBT())/ 60);
                                    tvTongTien.setText(String.valueOf(tongTien));
                                }
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

    private void loadDoi() {
        mFirebaseDatabase.child("users").child(idUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dt :
                        snapshot.child("listDoi").getChildren()) {
                    final String key = dt.getKey();
                    mFirebaseDatabase.child("Team").child(key).child("tenDoi").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            SetTeam setTeam = new SetTeam();
                            setTeam.setIdDoi(key);
                            setTeam.setTenDoi(snapshot.getValue().toString());
                            dataDoi.add(setTeam);
                            adapter_tenDoi.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadUser() {
        mFirebaseDatabase.child("users").child(idUser).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String users = snapshot.getValue().toString();
                tvTenNguoiDat.setText(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadSan() {
        mFirebaseDatabase.child("San").child(idSan).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FootballPitches footballPitches = snapshot.getValue(FootballPitches.class);
                tvSan.setText(footballPitches.getTenSan());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadKhu() {
        mFirebaseDatabase.child("Khu").child(idKhu).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Zone zone = snapshot.getValue(Zone.class);
                tvTenKhu.setText(zone.getTenKhu());
                tvDiaChi.setText(zone.getDiaChi() + ", " + zone.getQuan() + ", " + zone.getThanhPho());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

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