package com.example.soccersocialnetwork.Admin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.models.information_listStadium;
import com.example.soccersocialnetwork.football_field_owner.model.Zone;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterWaitingZone extends ArrayAdapter<information_listStadium>{
    DatabaseReference mFirebase;
    Context context;
    int resource;
    List<information_listStadium> objects;
    public AdapterWaitingZone(@NonNull Context context, int resource, @NonNull List<information_listStadium> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        mFirebase = FirebaseDatabase.getInstance().getReference();
        View row = convertView;
        Holder holder = null;
        if (row == null){
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            row = layoutInflater.inflate(resource,parent,false);

            holder = new Holder();
            holder.imageView = (ImageView) row.findViewById(R.id.imgLogo_Teams_list_stadiums);
            holder.txtTenSan = (TextView) row.findViewById(R.id.txttenSan_lstStadiums);
            holder.txtLoaiHinhSan = (TextView) row.findViewById(R.id.txtThongTinLoaiHinhSan);
            holder.txtLoaiSan = (TextView) row.findViewById(R.id.txtThongTinLoaiSan);
            holder.txtDiaChiSan = (TextView) row.findViewById(R.id.txtThongTinDiaChiSan);
            holder.imgXoa = row.findViewById(R.id.imgXoa);
            holder.lnButton = row.findViewById(R.id.lnButton);
            holder.imgDongY = row.findViewById(R.id.btnChon);
            holder.imgHuy = row.findViewById(R.id.btnHuy);
            row.setTag(holder);
        }
        else {
            holder = (Holder) row.getTag();
        }
        holder.imgXoa.setVisibility(View.GONE);
        holder.lnButton.setVisibility(View.VISIBLE);
        final information_listStadium listStadium = objects.get(position);
        Picasso.get().load(listStadium.getHinhSan()).fit().into(holder.imageView);
        holder.txtTenSan.setText(listStadium.getmTenSan());
        holder.txtLoaiHinhSan.setText(listStadium.getmLoaiHinhSan());
        holder.txtLoaiSan.setText(listStadium.getmLoaiSan());
        holder.txtDiaChiSan.setText(listStadium.getmDiaChiSan());
        holder.imgDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebase.child("ChoDuyetKhu").child(listStadium.getIdKhu()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        mFirebase.child("Khu").child(snapshot.getKey()).setValue(snapshot.getValue());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                mFirebase.child("ChoDuyetKhu").child(listStadium.getIdKhu()).removeValue();
                objects.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.imgHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebase.child("ChoDuyetKhu").child(listStadium.getIdKhu()).removeValue();
                objects.remove(position);
                notifyDataSetChanged();
            }
        });
        return row;
    }

    class Holder{
        ImageView imageView, imgXoa, imgDongY, imgHuy;
        TextView txtTenSan, txtLoaiHinhSan,txtLoaiSan,txtDiaChiSan;
        LinearLayout lnButton;
    }
}
