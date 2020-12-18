package com.example.soccersocialnetwork.TranDuyHuynh.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccersocialnetwork.DoanThanhTung.Models.Feeds;
import com.example.soccersocialnetwork.DoanThanhTung.Models.ListTeamUser;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
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
        TextView txtTenDoi, txtKhuVuc, txtThongtinGioiHieu, txtNgay;
//
//
////        TextView tv_DangBai;
////        ImageView img_DangBai;
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
           // holder.txtNgay = view.findViewById(R.id.tv_Gio);

            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final Team team = data.get(position);

        readuser(team.idDoi+"");


        Picasso.get().load(team.getHinhAnh()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i<listTeamUsers.size();i++){
                    if(LoginActivity.USER_ID_CURRENT.equals(listTeamUsers.get(i)) ){
                        Toast.makeText(context, "win", Toast.LENGTH_SHORT).show();
                        break;
                    }else{

                    Toast.makeText(context, "thua", Toast.LENGTH_SHORT).show();

                    }
                }


            }
        });
        holder.txtTenDoi.setText(team.getTenDoi());
        holder.txtKhuVuc.setText(team.getKhuVuc());
        holder.txtThongtinGioiHieu.setText(team.getGioiThieu());
//        holder.imgDoiAdapter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, team.getTenDoi(), Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }

    ArrayList<String> listTeamUsers = new ArrayList<>();
    public void readuser(String key){
       mDatabase = FirebaseDatabase.getInstance().getReference("Team").child(key).child("listThanhVien");
       mDatabase.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               listTeamUsers.clear();
               for (DataSnapshot dt:
                    snapshot.getChildren()) {
                   listTeamUsers.add(dt.getKey());
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
    }
}
