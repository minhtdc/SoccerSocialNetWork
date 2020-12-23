package com.example.soccersocialnetwork.football_field_owner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.football_field_owner.model.FootballPitches;
import com.example.soccersocialnetwork.football_field_owner.model.Zone;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapterListZone extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Zone> data;

    public CustomAdapterListZone(Context context, int resource, ArrayList<Zone> data) {
        super(context, resource);
        this.context = context;
        this.data = data;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class Holder {
        ImageView imgAnh;
        TextView tvTenKhu;
        TextView tvViTri;
        TextView tvGioMo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.imgAnh = view.findViewById(R.id.imgView);
            holder.tvTenKhu = view.findViewById(R.id.tvTenKhu);
            holder.tvViTri = view.findViewById(R.id.tvViTri);
            holder.tvGioMo = view.findViewById(R.id.tvGioMocua);
            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final Zone zone = data.get(position);
        Picasso.get().load(zone.getAnh()).fit().into(holder.imgAnh);
        holder.tvTenKhu.setText(zone.getTenKhu());
        holder.tvViTri.setText(zone.getDiaChi());
        holder.tvGioMo.setText(zone.getGioMo() + ":"
                + zone.getPhutMo() + " - "
                + zone.getGioDong() + ":"
                + zone.getPhutDong());
        return view;
    }
}
