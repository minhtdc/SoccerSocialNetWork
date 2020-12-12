package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_FeedsDoi2;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Feeds;
import com.example.soccersocialnetwork.R;

import java.util.ArrayList;

public class Fragment_Doi extends Fragment {

    RecyclerView recyclerView;
    ImageView imgDangBai;
    ArrayList<Feeds> listFeeds = new ArrayList<>();

    Adapter_FeedsDoi2 adapter2;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = (ViewGroup) inflater.inflate((R.layout.doi_feeds), container, false);
        recyclerView = rootView.findViewById(R.id.rcv_Feed);
        imgDangBai = rootView.findViewById(R.id.imgDangBai);

        setEvent();
        return  rootView;
    }


    private void setEvent() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
       // adapter2 = new Adapter_FeedsDoi2(getContext(),listFeeds);
      //  adapterFeedsDoi.setSetLayout(false);
        listFeeds.clear();
        ThemBaiVietDaBong();
        adapter2 = new Adapter_FeedsDoi2(getContext(),listFeeds);
//        adapterFeedsDoi = new Adapter_FeedsDoi(getContext());
//        adapterFeedsDoi.setData(listFeeds);
       recyclerView.setAdapter(adapter2);

       imgDangBai.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(getContext(), "sdfsdfsdf", Toast.LENGTH_SHORT).show();
           }
       });


//

    }



    private void setControl() {


    }

    private void ThemBaiVietDaBong() {
        Feeds model_feedsDoi_view = new Feeds(24, "Hà Nội", "Cái Làn", "14/11/2020", "13h - 16h", "Ae mình zui vẽ");
        Feeds model_feedsDoi_view1 = new Feeds(72,"xxx","Cái Làn","14/11/2020","13h - 16h","Ae mình zui vẽ");
        Feeds model_feedsDoi_view2 = new Feeds(123,"x","Cái Làn","14/11/2020","13h - 16h","Ae mình zui vẽ");
        Feeds model_feedsDoi_view3 = new Feeds(234,"Hà Nội","Cái Làn","14/11/2020","13h - 16h","Ae mình zui vẽ");
        Feeds model_feedsDoi_view4 = new Feeds(234,"Hà Nội","Cái Làn","14/11/2020","13h - 16h","Ae mình zui vẽ");
        listFeeds.add(model_feedsDoi_view);
        listFeeds.add(model_feedsDoi_view1);
        listFeeds.add(model_feedsDoi_view2);
        listFeeds.add(model_feedsDoi_view3);
        listFeeds.add(model_feedsDoi_view4);
//       adapter2.notifyDataSetChanged();
    }
}
