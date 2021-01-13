package com.example.soccersocialnetwork.Admin.Flagment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.Football_Pitches.activity.FootballPitchesActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.Admin.Adapter.Adapter_TestCLickTeam;
import com.example.soccersocialnetwork.football_field_owner.flagment.ListOfYardFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeamFragment extends Fragment {
    ListView lvDanhSach;
    DatabaseReference mFirebase;
    View view;
    ArrayList<Team> listTeams = new ArrayList<>();
    Adapter_TestCLickTeam adapterDoi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_team, container, false);
        setControl();
        setEvent();
        return view;
    }

    private void setEvent() {
        readDoiFirebase();
    }
    private void setControl() {
        lvDanhSach = view.findViewById(R.id.lvDanhSach);
    }
    private void readDoiFirebase() {
        mFirebase = FirebaseDatabase.getInstance().getReference().child("Team");
        mFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listTeams.clear();
                for (DataSnapshot dt :
                        snapshot.getChildren()) {
                    Team team = dt.getValue(Team.class);
                    listTeams.add(team);
                }
                // Toast.makeText(getContext(),listTeams.get(0).getIdDoi()+ "", Toast.LENGTH_SHORT).show();
                adapterDoi = new Adapter_TestCLickTeam(getContext(), R.layout.list_doi, listTeams);
                lvDanhSach.setAdapter(adapterDoi);
                adapterDoi.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}