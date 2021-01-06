package com.example.soccersocialnetwork.TranDuyHuynh.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccersocialnetwork.DoanThanhTung.Models.Feeds;
import com.example.soccersocialnetwork.DoanThanhTung.Models.ListTeamUser;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.DoiActivity;
import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.Doi_ThongTinCaNhan;
import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.Doi_ThongTin_ChinhSua;
import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Adapter_TestCLickTeam extends ArrayAdapter {
    Context context;
    ArrayList<Team> data;
    int resource;
    DatabaseReference mDatabase;

    public Adapter_TestCLickTeam(@NonNull Context context, int resource, ArrayList<Team> data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
        this.resource = resource;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    static class Holder {
        ImageView imageView;
        TextView txtTenDoi, txtKhuVuc, txtThongtinGioiHieu;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Holder holder = new Holder();
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);

            holder.imageView = view.findViewById(R.id.imgLogo_Teams_list_teams);
            holder.txtTenDoi = view.findViewById(R.id.txtTendoi_list_teams);
            holder.txtKhuVuc = view.findViewById(R.id.txtThongTinKhuVuc_lstTeams);
            holder.txtThongtinGioiHieu = view.findViewById(R.id.txtThongTinGioiThieu_lstTeams);

            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final Team team = data.get(position);


     //   readuser(team.getIdDoi() + "");
        Picasso.get().load(team.getHinhAnh()).into(holder.imageView);
        holder.txtTenDoi.setText(team.getTenDoi());
        holder.txtKhuVuc.setText(team.getKhuVuc());
        holder.txtThongtinGioiHieu.setText(team.getGioiThieu());

        return view;
    }


}
