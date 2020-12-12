package com.example.soccersocialnetwork.Foothball_Pitches.flagment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.soccersocialnetwork.Foothball_Pitches.adapter.CustomAdapterFreeTime;
import com.example.soccersocialnetwork.Foothball_Pitches.model.FreeTime;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.football_field_owner.adapter.CustomAdapterFootballPitches;
import com.example.soccersocialnetwork.football_field_owner.model.FootballPitches;

import java.util.ArrayList;

public class ListFreeTimeFragment extends Fragment {
    ListView lvGioTrong;
    ArrayList<FreeTime> data_FreeTimes = new ArrayList<>();
    ArrayAdapter adapter_FreeTimes;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_free_time, container, false);
        lvGioTrong = view.findViewById(R.id.lvGioTrong);
        setEvent();
        // Inflate the layout for this fragment
        return view;
    }

    private void setEvent() {
        KhoiTao();
        adapter_FreeTimes = new CustomAdapterFreeTime(getContext(), R.layout.item_listview_freetime, data_FreeTimes);
        lvGioTrong.setAdapter(adapter_FreeTimes);
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