package com.example.soccersocialnetwork.football_field_owner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.football_field_owner.model.FootballPitches;

import java.util.ArrayList;

public class CustomAdapterFootballPitches extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<FootballPitches> data;
    public CustomAdapterFootballPitches(Context context, int resource, ArrayList<FootballPitches> data) {
        super(context, resource);
        this.context=context;
        this.data= data;
        this.resource=resource;
    }

    @Override
    public int getCount() {
        return data.size();
    }
    private static class Holder {
        TextView tvTenSan;
        TextView tvGia;
        TextView tvLHS;
        TextView tvLS;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        CustomAdapterFootballPitches.Holder holder = null;
        if (view == null) {
            holder = new CustomAdapterFootballPitches.Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.tvTenSan= view.findViewById(R.id.tvTenSan);
            holder.tvGia= view.findViewById(R.id.tvGia);
            holder.tvLS= view.findViewById(R.id.tvLS);
            holder.tvLHS= view.findViewById(R.id.tvLHS);
            view.setTag(holder);
        } else
            holder = (CustomAdapterFootballPitches.Holder) view.getTag();

        final FootballPitches footballPitches = data.get(position);

        holder.tvTenSan.setText(footballPitches.getTenSan());
        holder.tvGia.setText(footballPitches.getGia());
        holder.tvLHS.setText(footballPitches.getLoaiHinhSan());
        holder.tvLS.setText(footballPitches.getLoaiSan());
        return  view;
    }
}
