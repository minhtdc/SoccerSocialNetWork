package com.example.soccersocialnetwork.DoanThanhTung.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soccersocialnetwork.DoanThanhTung.Models.ThongBao;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.models.thongTinTranDau;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_TranDau extends ArrayAdapter {

    Context context;
    int resource;
    private ArrayList<thongTinTranDau> data;

    public Adapter_TranDau(Context context, int resource, ArrayList<thongTinTranDau> data) {
        super(context, resource, data);
        this.resource = resource;
        this.context = context;
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    static class Holder {
        TextView tvTenTeam,tvTenDoiThu,tvGioDa,tvNgayDa;
        ImageView imgTeam,imgDoiThu;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Adapter_TranDau.Holder holder = new Adapter_TranDau.Holder();
        if (view == null) {
            holder = new Adapter_TranDau.Holder();
            view = LayoutInflater.from(context).inflate(resource, null);


            holder.tvTenTeam = view.findViewById(R.id.tvTenTeam);

            holder.tvTenDoiThu = view.findViewById(R.id.tvTenDoiThu);

            holder.tvGioDa = view.findViewById(R.id.tvGioDa);
            holder.tvNgayDa = view.findViewById(R.id.tvNgayDa);
            holder.imgTeam = view.findViewById(R.id.imgTeam);

            holder.imgDoiThu = view.findViewById(R.id.imgDoiThu);



            // holder.txtNgay = view.findViewById(R.id.tv_Gio);

            view.setTag(holder);
        } else
            holder = (Adapter_TranDau.Holder) view.getTag();

        final thongTinTranDau thongTinTranDau = data.get(position);

        if (!thongTinTranDau.getAnhDoi().equals("")) {
            Picasso.get().load(thongTinTranDau.getAnhDoi()).into(holder.imgTeam);
        }
        // getUser(user.getUserEmail());

        holder.tvTenTeam.setText(thongTinTranDau.getTenDoi());
        holder.tvGioDa.setText(thongTinTranDau.getThoiGian());
        holder.tvNgayDa.setText(thongTinTranDau.getNgay());


        return view;
    }



}
