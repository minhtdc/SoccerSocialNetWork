package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_FeedsDoi;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Feeds;
import com.example.soccersocialnetwork.R;

import java.util.ArrayList;

public class Fragment_Doi_2 extends Fragment {

    RecyclerView recyclerView;

    ArrayList<Feeds> listFeeds = new ArrayList<>();
  //  Adapter_FeedsDoi adapterFeedsDoi;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = (ViewGroup) inflater.inflate((R.layout.fragment_trandau), container, false);
        //setEvent();
        return  rootView;
    }


    private void setEvent() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

//
//        adapterFeedsDoi = new Adapter_FeedsDoi(getContext());
//        recyclerView.setAdapter(adapterFeedsDoi);

       // ThemBaiVietDaBong();
//

    }



    private void setControl() {


    }


}
