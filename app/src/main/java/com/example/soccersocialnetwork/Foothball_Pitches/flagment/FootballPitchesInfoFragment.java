package com.example.soccersocialnetwork.Foothball_Pitches.flagment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soccersocialnetwork.Foothball_Pitches.activity.FootballPitchesActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.football_field_owner.model.FootballPitches;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FootballPitchesInfoFragment extends Fragment {
    View view;
    TextView tvTenSan, tvLoaiSan, tvLoaiHinhSan, tvGia;
    DatabaseReference mFirebase;
    String key = "";
private FootballPitchesActivity mPitchesActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_football_pitches_info, container, false);
        mPitchesActivity = (FootballPitchesActivity) getActivity();
        key = mPitchesActivity.getKey();
        setControl();
        setEvent();
        return view;
    }

    private void setEvent() {
        mFirebase = FirebaseDatabase.getInstance().getReference().child("San").child(key);
        mFirebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                FootballPitches footballPitches = snapshot.getValue(FootballPitches.class);
                tvTenSan.setText(footballPitches.getTenSan());
                tvLoaiHinhSan.setText(footballPitches.getLoaiHinhSan());
                tvLoaiSan.setText(footballPitches.getLoaiSan());
                tvGia.setText(footballPitches.getGiaBT() + " - " + footballPitches.getGiaCD());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setControl() {
        tvTenSan = view.findViewById(R.id.tvTenSan);
        tvLoaiHinhSan = view.findViewById(R.id.tvLoaiHinhSan);
        tvLoaiSan = view.findViewById(R.id.tvLoaiSan);
        tvGia = view.findViewById(R.id.tvGia);
    }
}