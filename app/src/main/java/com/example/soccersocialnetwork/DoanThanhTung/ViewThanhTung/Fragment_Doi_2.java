package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_FeedsDoi;
import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_TranDau;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Feeds;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.models.thongTinTranDau;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment_Doi_2 extends Fragment {

    ListView lvTranDau;

    ArrayList<thongTinTranDau> listTranDau = new ArrayList<>();
    ArrayAdapter adapter_TranDau;
    //  Adapter_FeedsDoi adapterFeedsDoi;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = (ViewGroup) inflater.inflate((R.layout.fragment_trandau), container, false);
         lvTranDau = rootView.findViewById(R.id.lvTranDau);

        setEvent();
        return rootView;
    }


    private void setEvent() {

        DatabaseReference listThongTinTranDau = FirebaseDatabase.getInstance().getReference();
        listThongTinTranDau.child("ThongTinTranDau").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listTranDau.clear();
                for (DataSnapshot dt :
                        snapshot.getChildren()) {
                    thongTinTranDau trandau = dt.getValue(thongTinTranDau.class);
                    if(DoiActivity.idDoi.equals(trandau.getIdDoiDangTin())){
                        listTranDau.add(trandau);
                    }

                }

                adapter_TranDau = new Adapter_TranDau(getContext(),R.layout.doi_match_view,listTranDau);
                lvTranDau.setAdapter(adapter_TranDau);
                adapter_TranDau.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}
