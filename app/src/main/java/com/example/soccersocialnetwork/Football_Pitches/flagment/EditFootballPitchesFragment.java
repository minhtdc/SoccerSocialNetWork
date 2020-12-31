package com.example.soccersocialnetwork.Football_Pitches.flagment;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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

import com.example.soccersocialnetwork.Football_Pitches.activity.FootballPitchesActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.football_field_owner.flagment.ListOfYardFragment;
import com.example.soccersocialnetwork.football_field_owner.model.FootballPitches;
import com.example.soccersocialnetwork.football_field_owner.model.RushHour;
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

public class EditFootballPitchesFragment extends Fragment {
    View view;
    EditText txtTenSan, txtGiaGioCD, txtGiaGioBT;
    Spinner spLoaiSan, spLoaiHinhSan;
    TextView tvGioBD, tvGioKT;
    ListView lvDSGioCD;
    Button btnGioCD, btnSuaSan;
    DatabaseReference mFirebase;
    DatabaseReference mDataGioCD;
    DatabaseReference mFirebaseHour;
    String idHour = "";
    int gioBD, phutBD, gioKT, phutKT;

    ArrayList<RushHour> data_rh;
    ArrayList<String> data_ls = new ArrayList<>();
    ArrayList<String> data_lhs = new ArrayList<>();
    ArrayAdapter adapter_lvCD;
    ArrayAdapter adapter;


    String key = "";
    private FootballPitchesActivity mPitchesActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_edit_football_pitches, container, false);
        mPitchesActivity = (FootballPitchesActivity) getActivity();
        key = ListOfYardFragment.idSan;
        setControl();
        setEvent();
        return view;
    }

    private void setEvent() {
        data_rh = new ArrayList<>();
        mFirebase = FirebaseDatabase.getInstance().getReference().child("San").child(key);
        mFirebaseHour = FirebaseDatabase.getInstance().getReference().child("GioCaoDiem").child(key);

        mFirebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                FootballPitches footballPitches = snapshot.getValue(FootballPitches.class);
                txtTenSan.setText(footballPitches.getTenSan());
                spLoaiHinhSan.setSelection(data_lhs.indexOf(footballPitches.getLoaiHinhSan()));
                spLoaiSan.setSelection(data_ls.indexOf(footballPitches.getLoaiSan()));
                txtGiaGioCD.setText(footballPitches.getGiaCD());
                txtGiaGioBT.setText(footballPitches.getGiaBT());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDataGioCD = FirebaseDatabase.getInstance().getReference().child("GioCaoDiem").child(key);
        mDataGioCD.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                RushHour rushHour = snapshot.getValue(RushHour.class);
                data_rh.add(new RushHour(rushHour.getGioBD(), rushHour.getPhutBD(),
                        rushHour.getGioKT(), rushHour.getPhutKT(), rushHour.getId()));
                if (adapter_lvCD == null) {
                    adapter_lvCD = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,data_rh);
                    lvDSGioCD.setAdapter(adapter_lvCD);
                } else {
                    adapter_lvCD.notifyDataSetChanged();
                    lvDSGioCD.setSelection(adapter_lvCD.getCount() - 1);
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
        KhoiTao();

        setAdapterSpinner(data_ls, adapter, spLoaiSan);
        setAdapterSpinner(data_lhs, adapter, spLoaiHinhSan);

        lvDSGioCD.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                data_rh.remove(i);
                adapter_lvCD.notifyDataSetChanged();
                for (int k = 0; k < data_rh.size(); k++) {
                    RushHour rushHour = data_rh.get(k);
                    rushHour.setId(String.valueOf(k));
                }
                return false;
            }
        });
        btnGioCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogGioCaoDiem();
            }
        });

        btnSuaSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FootballPitches footballPitches = getFootballPitches(key);
                mFirebase.setValue(footballPitches);
                mFirebaseHour.setValue(data_rh);
                mPitchesActivity.finish();
                startActivity(mPitchesActivity.getIntent());
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
        btnSuaSan = view.findViewById(R.id.btnSuaSan);
    }
}