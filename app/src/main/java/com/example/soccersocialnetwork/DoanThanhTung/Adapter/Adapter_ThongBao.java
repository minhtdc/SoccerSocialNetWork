package com.example.soccersocialnetwork.DoanThanhTung.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soccersocialnetwork.DoanThanhTung.Models.ListCMT;
import com.example.soccersocialnetwork.DoanThanhTung.Models.ThongBao;
import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.DoiActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_ThongBao extends ArrayAdapter {

    Context context;
    int resource;
    private ArrayList<ThongBao> data;

    public Adapter_ThongBao(Context context, int resource, ArrayList<ThongBao> data) {
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
        TextView txtChiThietThongBao;
        ImageView img_Team_notification;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Adapter_ThongBao.Holder holder = new Adapter_ThongBao.Holder();
        if (view == null) {
            holder = new Adapter_ThongBao.Holder();
            view = LayoutInflater.from(context).inflate(resource, null);


            holder.txtChiThietThongBao = view.findViewById(R.id.txtChiThietThongBao);
            holder.img_Team_notification = view.findViewById(R.id.img_Team_notification);



            // holder.txtNgay = view.findViewById(R.id.tv_Gio);

            view.setTag(holder);
        } else
            holder = (Adapter_ThongBao.Holder) view.getTag();

        final ThongBao thongBao = data.get(position);

        if (!thongBao.getImg().equals("")) {
            Picasso.get().load(thongBao.getImg()).into(holder.img_Team_notification);
        } else {

        }
        // getUser(user.getUserEmail());

        holder.txtChiThietThongBao.setText(thongBao.getNoiDung());


        return view;
    }



}
