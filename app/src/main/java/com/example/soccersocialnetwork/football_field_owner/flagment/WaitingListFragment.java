package com.example.soccersocialnetwork.football_field_owner.flagment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.football_field_owner.adapter.CustomAdapterWaiting;
import com.example.soccersocialnetwork.football_field_owner.model.Waiting;

import java.util.ArrayList;

public class WaitingListFragment extends Fragment {
    ListView lvWaiting;
    ArrayList<Waiting> data_waiting = new ArrayList<>();
    ArrayAdapter adapter_waiting;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_waiting_list, container, false);
        lvWaiting = view.findViewById(R.id.lvWaiting);
        setEvent();
        return view;
    }

    private void setEvent() {
        KhoiTao();
        adapter_waiting = new CustomAdapterWaiting(getContext(), R.layout.item_listview_waiting, data_waiting);
        lvWaiting.setAdapter(adapter_waiting);
    }

    private void KhoiTao() {
        Waiting waiting = new Waiting("A", "san 1", "11/12/2020", "06:30");
        Waiting waiting1 = new Waiting("B", "san 2", "02/12/2020", "17:30");
        Waiting waiting2 = new Waiting("c", "san 3", "11/11/2020", "11:00");
        Waiting waiting3 = new Waiting("d", "san 2", "20/11/2020", "16:30");
        data_waiting.add(waiting);
        data_waiting.add(waiting1);
        data_waiting.add(waiting2);
        data_waiting.add(waiting3);
    }
}