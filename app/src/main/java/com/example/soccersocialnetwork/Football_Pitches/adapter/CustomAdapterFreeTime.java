package com.example.soccersocialnetwork.Football_Pitches.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.soccersocialnetwork.Football_Pitches.model.FreeTime;
import com.example.soccersocialnetwork.R;

import java.util.ArrayList;

public class CustomAdapterFreeTime extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<FreeTime> data;
    public CustomAdapterFreeTime(Context context, int resource, ArrayList<FreeTime> data) {
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
        TextView tvGioTrong;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.tvGioTrong= view.findViewById(R.id.tvGioTrong);
            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final FreeTime freeTime = data.get(position);

        holder.tvGioTrong.setText(freeTime.getGioBatDau() + "-" + freeTime.getGioKetThuc());
        return  view;
    }
}
