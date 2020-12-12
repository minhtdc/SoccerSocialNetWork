package com.example.soccersocialnetwork.football_field_owner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.football_field_owner.model.Waiting;

import java.util.ArrayList;

public class CustomAdapterWaiting extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Waiting> data;
    public CustomAdapterWaiting(Context context, int resource, ArrayList<Waiting> data) {
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
        ImageView imgAnh;
        TextView tvTenDoi;
        TextView tvsan;
        TextView tvNgay;
        TextView tvGio;
        Button btnChon;
        Button btnHuy;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.tvTenDoi= view.findViewById(R.id.tvTenDoi);
            holder.tvsan= view.findViewById(R.id.tvSan);
            holder.tvNgay= view.findViewById(R.id.tvNgay);
            holder.tvGio= view.findViewById(R.id.tvGio);
            holder.imgAnh= view.findViewById(R.id.imgAnh);
            holder.btnChon= view.findViewById(R.id.btnChon);
            holder.btnHuy= view.findViewById(R.id.btnHuy);
            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final Waiting waiting = data.get(position);

        holder.tvTenDoi.setText(waiting.getTenDoi());
        holder.tvsan.setText(waiting.getSan());
        holder.tvNgay.setText(waiting.getNgay());
        holder.tvGio.setText(waiting.getGio());
        holder.imgAnh.setImageResource(R.drawable.sanbong);

        return  view;
    }
}
