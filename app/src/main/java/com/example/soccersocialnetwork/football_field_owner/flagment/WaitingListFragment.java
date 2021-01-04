package com.example.soccersocialnetwork.football_field_owner.flagment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.Set_Football_Pitches.model.SetFootballPitches;
import com.example.soccersocialnetwork.Set_Football_Pitches.model.SetTeam;
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

import java.util.ArrayList;

public class WaitingListFragment extends Fragment {
    ListView lvWaiting;
    ArrayList<SetFootballPitches> data_SetFootballPitches = new ArrayList<>();
    ArrayList<Waiting> data_waiting = new ArrayList<>();

    ArrayAdapter adapter_waiting;

    DatabaseReference mFirebase;
    String idKhu = ListZone.idKhu;

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
    }

    private void loadData() {
        mFirebase.child("ChoDuyetDatSan").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                final SetFootballPitches setFootballPitches = snapshot.getValue(SetFootballPitches.class);
                final Waiting waiting = new Waiting();
                mFirebase.child("San").child(setFootballPitches.getIdSanDat()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final FootballPitches footballPitches = snapshot.getValue(FootballPitches.class);
                        mFirebase.child("Team").child(setFootballPitches.getIdDoiDat()).child("tenDoi").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (footballPitches.getIdKhu().equals(idKhu)) {
                                    data_SetFootballPitches.add(setFootballPitches);
                                    waiting.setSan(footballPitches.getTenSan());
                                    waiting.setTenDoi(snapshot.getValue().toString());
                                    waiting.setGio(setFootballPitches.getGioBatDau() + ":"
                                            + setFootballPitches.getPhutBatDau() + " - "
                                            + setFootballPitches.getGioKetThuc() + ":"
                                            + setFootballPitches.getPhutKetThuc());
                                    waiting.setNgay(setFootballPitches.getNgayDat());
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