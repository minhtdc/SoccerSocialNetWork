package com.example.soccersocialnetwork.Football_Pitches.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.soccersocialnetwork.Football_Pitches.model.Book;
import com.example.soccersocialnetwork.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapterBooked extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Book> data;
    public CustomAdapterBooked(Context context, int resource, ArrayList<Book> data) {
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
        TextView tvGioDaDat;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.imgAnh = view.findViewById(R.id.imgAnhDoiDat);
            holder.tvTenDoi= view.findViewById(R.id.tvTenDoi1);
            holder.tvGioDaDat= view.findViewById(R.id.tvGioDaDat);;
            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final Book book = data.get(position);
        Picasso.get().load(book.getAnhDoi()).fit().into(holder.imgAnh);
        holder.imgAnh.setImageResource(R.drawable.sanbong);
        holder.tvTenDoi.setText(book.getTenDoi());
        holder.tvGioDaDat.setText("Giờ: " + book.getGioBatDau() + "-" + book.getGioKetThuc());
        return  view;
    }
}
