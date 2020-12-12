package com.example.soccersocialnetwork.DoanThanhTung.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccersocialnetwork.R;

public class Doi_ViewHolder_1 extends RecyclerView.ViewHolder {
    public TextView trangThai;

    public Doi_ViewHolder_1(@NonNull View itemView) {
        super(itemView);
        trangThai = (TextView)itemView.findViewById(R.id.trangThai);
    }
}
