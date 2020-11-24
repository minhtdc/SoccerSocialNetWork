package com.example.soccersocialnetwork.TranDuyHuynh.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.models.information_listStadium;

import java.util.List;

public class information_listStadiums_Adapter extends ArrayAdapter<information_listStadium> {
    Context context;
    int resource;
    List<information_listStadium> objects;
    public information_listStadiums_Adapter(@NonNull Context context, int resource, @NonNull List<information_listStadium> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
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

            row.setTag(holder);
        }
        else {
            holder = (Holder) row.getTag();
        }

        information_listStadium listStadium = objects.get(position);
        holder.imageView.setImageResource(listStadium.getHinhSan());
        holder.txtTenSan.setText(listStadium.getmTenSan());
        holder.txtLoaiHinhSan.setText(listStadium.getmLoaiHinhSan());
        holder.txtLoaiSan.setText(listStadium.getmLoaiSan());
        holder.txtDiaChiSan.setText(listStadium.getmDiaChiSan());

        return row;
    }

    class Holder{
        ImageView imageView;
        TextView txtTenSan, txtLoaiHinhSan,txtLoaiSan,txtDiaChiSan;
    }
}
