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
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_ThemThanhVien;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Feeds;
import com.example.soccersocialnetwork.DoanThanhTung.Models.ListTeamUser;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.DoiActivity;
import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.Doi_ThongTinCaNhan;
import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.Doi_ThongTin_ChinhSua;
import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.data_models.Users;
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

public class Adapter_TestCLickTeam extends BaseAdapter implements Filterable {
    Context context;
    ArrayList<Team> data;
    ArrayList<Team> datafull;

    Adapter_TestCLickTeam.ValueFilter valueFilter;
    int resource;
    DatabaseReference mDatabase;

    public Adapter_TestCLickTeam(@NonNull Context context, int resource, ArrayList<Team> data) {

        this.context = context;
        this.data = data;
        this.resource = resource;
        this.datafull = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class Holder {
        ImageView imageView;
        TextView txtTenDoi, txtKhuVuc, txtThongtinGioiHieu, txtThongTinSL_CauThu_lstTeam;

    }

    int soCauThu = 0;
    ValueEventListener valueEventListener;
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
            holder.txtThongTinSL_CauThu_lstTeam = view.findViewById(R.id.txtThongTinSL_CauThu_lstTeam);

            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final Team team = data.get(position);


        //   readuser(team.getIdDoi() + "");
        Picasso.get().load(team.getHinhAnh()).into(holder.imageView);
        holder.txtTenDoi.setText(team.getTenDoi());
        holder.txtKhuVuc.setText(team.getKhuVuc());
        holder.txtThongtinGioiHieu.setText(team.getGioiThieu());

        final DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();
        final Holder finalHolder = holder;

        valueEventListener = databaseReference1.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                soCauThu = 0;
                for (DataSnapshot dt :
                        snapshot.getChildren()) {
                    for (DataSnapshot dtt :
                            dt.child("listDoi").getChildren()) {
                        if (dtt.getKey().equals(team.getIdDoi() + "")) {
                            soCauThu++;
                        }
                    }
                }
                finalHolder.txtThongTinSL_CauThu_lstTeam.setText(soCauThu+"");
                databaseReference1.removeEventListener(valueEventListener);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {

        if (valueFilter == null) {
            valueFilter = new Adapter_TestCLickTeam.ValueFilter();
        }
        return valueFilter;
    }

    //---------search
    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Team> strings = new ArrayList<>();
            if (constraint.toString().isEmpty()) {
                strings.addAll(datafull);
            } else {
                for (Team movie : datafull) {
                    if (movie.getTenDoi().toLowerCase().contains(constraint.toString().toLowerCase().trim())) {
                        strings.add(movie);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = strings;
            return filterResults;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            data = (ArrayList<Team>) results.values;

            notifyDataSetChanged();
        }
    }



}
