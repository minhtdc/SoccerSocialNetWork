package com.example.soccersocialnetwork.football_field_owner.flagment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.soccersocialnetwork.Football_Pitches.activity.FootballPitchesActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.football_field_owner.activity.ListZone;
import com.example.soccersocialnetwork.football_field_owner.activity.ZoneInfoActivity;
import com.example.soccersocialnetwork.football_field_owner.adapter.CustomAdapterFootballPitches;
import com.example.soccersocialnetwork.football_field_owner.model.FootballPitches;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListOfYardFragment extends Fragment {
    ListView lvFootballPitches;
    ArrayList<FootballPitches> data_FootballPitches;
    ArrayAdapter adapter_FootballPitches;
    DatabaseReference mFirebaseDatabase;
    String idKhu ;
    public static String idSan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_of_yard, container, false);
        lvFootballPitches = view.findViewById(R.id.lvFootballPitches);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        idKhu = ListZone.idKhu;
        setEvent();
        return view;
    }

    private void setEvent() {
        loadData();
        adapter_FootballPitches = new CustomAdapterFootballPitches(getContext(), R.layout.item_listview_yard, data_FootballPitches);
        lvFootballPitches.setAdapter(adapter_FootballPitches);
        lvFootballPitches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FootballPitches footballPitches = data_FootballPitches.get(i);
                idSan = footballPitches.getId();
                Intent intent = new Intent(getContext(), FootballPitchesActivity.class);

                startActivity(intent);
            }
        });
    }

    private void loadData() {
        data_FootballPitches = new ArrayList<>();
        mFirebaseDatabase.child("San").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                FootballPitches footballPitches = snapshot.getValue(FootballPitches.class);
                if (footballPitches.getIdKhu().equals(idKhu)) {
                    data_FootballPitches.add(new FootballPitches(footballPitches.getTenSan(),
                            footballPitches.getLoaiHinhSan(), footballPitches.getLoaiSan(),
                            footballPitches.getGiaBT(), footballPitches.getGiaCD(), footballPitches.getId(), "aaa"));
                    adapter_FootballPitches.notifyDataSetChanged();
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