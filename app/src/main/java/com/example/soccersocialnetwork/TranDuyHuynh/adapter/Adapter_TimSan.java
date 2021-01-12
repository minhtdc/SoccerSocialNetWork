package com.example.soccersocialnetwork.TranDuyHuynh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.models.information_TimSan;
import com.example.soccersocialnetwork.football_field_owner.activity.ZoneInfoActivity;
import com.example.soccersocialnetwork.football_field_owner.flagment.WaitingListFragment;
import com.example.soccersocialnetwork.football_field_owner.model.Waiting;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_TimSan extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<information_TimSan> data;
    DatabaseReference mFirebase;
    public Adapter_TimSan(Context context, int resource, ArrayList<information_TimSan> data) {
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
        TextView tvTenKhu;
        TextView tvTenSan;
        TextView tvNgay;
        TextView tvGio;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        mFirebase = FirebaseDatabase.getInstance().getReference();
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.tvTenKhu= view.findViewById(R.id.tvTenKhu);
            holder.tvTenSan= view.findViewById(R.id.tvSan);
            holder.tvNgay= view.findViewById(R.id.tvNgay);
            holder.tvGio= view.findViewById(R.id.tvGio);
            holder.imgAnh= view.findViewById(R.id.imgAnh);
            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final information_TimSan timSan  = data.get(position);
        Picasso.get().load(timSan.getAnh()).fit().into(holder.imgAnh);
        holder.tvTenKhu.setText(timSan.getTenKhu());
        holder.tvTenSan.setText(timSan.getTenSan());
        holder.tvNgay.setText(timSan.getNgayDat());
        holder.tvGio.setText(timSan.getGioDat());

        return  view;
    }
}
