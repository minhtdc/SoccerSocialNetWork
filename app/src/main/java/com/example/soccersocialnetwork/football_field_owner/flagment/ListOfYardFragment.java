package com.example.soccersocialnetwork.football_field_owner.flagment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.football_field_owner.adapter.CustomAdapterFootballPitches;
import com.example.soccersocialnetwork.football_field_owner.adapter.CustomAdapterWaiting;
import com.example.soccersocialnetwork.football_field_owner.model.FootballPitches;
import com.example.soccersocialnetwork.football_field_owner.model.Waiting;

import java.util.ArrayList;

public class ListOfYardFragment extends Fragment {
    ListView lvFootballPitches;
    ArrayList<FootballPitches> data_FootballPitches = new ArrayList<>();
    ArrayAdapter adapter_FootballPitches;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_of_yard, container, false);
        lvFootballPitches = view.findViewById(R.id.lvFootballPitches);
        setEvent();
        return view;
    }

    private void setEvent() {
        KhoiTao();
        adapter_FootballPitches = new CustomAdapterFootballPitches(getContext(), R.layout.item_listview_yard, data_FootballPitches);
        lvFootballPitches.setAdapter(adapter_FootballPitches);
    }

    private void KhoiTao() {
        FootballPitches footballPitches = new FootballPitches("san 1", "200.000", "7 người","sân cỏ nhân tạo");
        FootballPitches footballPitches1= new FootballPitches("san 2", "150.000", "5 người","sân cỏ nhân tạo");
        FootballPitches footballPitches2 = new FootballPitches("san 3", "300.000", "7 người","sân cỏ tự nhiên");
        FootballPitches footballPitches3 = new FootballPitches("san 4", "250.000", "5 người","sân cỏ tự nhiên");
        data_FootballPitches.add(footballPitches);
        data_FootballPitches.add(footballPitches1);
        data_FootballPitches.add(footballPitches2);
        data_FootballPitches.add(footballPitches3);
    }
}