package com.example.soccersocialnetwork.DoanThanhTung.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccersocialnetwork.R;

public class Doi_ViewHolder_2 extends RecyclerView.ViewHolder {
    public    TextView tv_HanGio, tv_ThanhPho, tv_Quan, tv_Ngay, tv_Gio, tv_ThongBao;

    public Doi_ViewHolder_2(@NonNull View itemView) {
        super(itemView);
        tv_HanGio = itemView.findViewById(R.id.tv_HanGio);
        tv_ThanhPho = itemView.findViewById(R.id.tv_ThanhPho);
        tv_Quan = itemView.findViewById(R.id.tv_Quan);
        tv_Ngay = itemView.findViewById(R.id.tv_Ngay);
        tv_Gio = itemView.findViewById(R.id.tv_Gio);
        tv_ThongBao = itemView.findViewById(R.id.tv_ThongBao);
    }


}
