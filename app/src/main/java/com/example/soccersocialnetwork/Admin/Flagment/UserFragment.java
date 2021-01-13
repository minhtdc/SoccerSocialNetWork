package com.example.soccersocialnetwork.Admin.Flagment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.soccersocialnetwork.Admin.Adapter.AdapterUser;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.Football_Pitches.activity.FootballPitchesActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.Admin.Adapter.Adapter_TestCLickTeam;
import com.example.soccersocialnetwork.data_models.Users;
import com.example.soccersocialnetwork.football_field_owner.flagment.ListOfYardFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserFragment extends Fragment {
    ListView lvDanhSach;
    DatabaseReference mFirebase;
    View view;
    ArrayList<Users> listUser = new ArrayList<>();
    ArrayAdapter adapterUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);
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
        mFirebase = FirebaseDatabase.getInstance().getReference().child("users");
        mFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listUser.clear();
                for (DataSnapshot dt :
                        snapshot.getChildren()) {
                    Users users = dt.getValue(Users.class);
                    listUser.add(users);
                }
                // Toast.makeText(getContext(),listTeams.get(0).getIdDoi()+ "", Toast.LENGTH_SHORT).show();
                adapterUser = new AdapterUser(getContext(), R.layout.item_admin_user, listUser);
                lvDanhSach.setAdapter(adapterUser);
                adapterUser.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}