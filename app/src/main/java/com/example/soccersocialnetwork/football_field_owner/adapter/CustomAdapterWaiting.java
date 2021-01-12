package com.example.soccersocialnetwork.football_field_owner.adapter;

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

public class CustomAdapterWaiting extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Waiting> data;
    DatabaseReference mFirebase;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        mFirebase = FirebaseDatabase.getInstance().getReference();
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
        Picasso.get().load(waiting.getAnhDoi()).fit().into(holder.imgAnh);
        holder.tvTenDoi.setText(waiting.getTenDoi());
        holder.tvsan.setText(waiting.getSan());
        holder.tvNgay.setText(waiting.getNgay());
        holder.tvGio.setText(waiting.getGio());
        holder.btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebase.child("ChoDuyetDatSan").child(waiting.getIdDuyet()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        mFirebase.child("SanDaDat").child(snapshot.getKey()).setValue(snapshot.getValue());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                mFirebase.child("ChoDuyetDatSan").child(waiting.getIdDuyet()).removeValue();
                data.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebase.child("ChoDuyetDatSan").child(waiting.getIdDuyet()).removeValue();
                data.remove(position);
                notifyDataSetChanged();
            }
        });
        return  view;
    }
}
