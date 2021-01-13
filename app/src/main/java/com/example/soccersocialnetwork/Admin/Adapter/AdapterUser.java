package com.example.soccersocialnetwork.Admin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.data_models.Users;
import com.example.soccersocialnetwork.football_field_owner.model.FootballPitches;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterUser extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<Users> data;

    public AdapterUser(Context context, int resource, ArrayList<Users> data) {
        super(context, resource);
        this.context = context;
        this.data = data;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    private static class Holder {
        ImageView imgAnhUser;
        TextView tvTen;
        TextView tvGmail;
        TextView tvQueQuan;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.tvTen = view.findViewById(R.id.tvTen);
            holder.tvGmail = view.findViewById(R.id.tvGmail);
            holder.tvQueQuan = view.findViewById(R.id.tvQueQuan);
            holder.imgAnhUser = view.findViewById(R.id.imgAnhUser);
            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final Users users = data.get(position);

        holder.tvTen.setText(users.getUserName());
        holder.tvGmail.setText(users.getUserEmail());
        holder.tvQueQuan.setText(users.getUserQueQuan());
        Picasso.get().load(users.getUserImage()).fit().into(holder.imgAnhUser);
        return view;
    }
}
