package com.example.soccersocialnetwork.TranDuyHuynh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapter_MoiThanhVien extends BaseAdapter {
    Context context;
    int resource;
    DatabaseReference mDatabase;
    ArrayList<Users> data;

    public adapter_MoiThanhVien(@NonNull Context context, int resource, ArrayList<Users> data) {

        this.context = context;
        this.data = data;
        this.resource = resource;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = view;
        adapter_MoiThanhVien.holder holder =  new adapter_MoiThanhVien.holder();
        if(view1 == null){
            holder = new adapter_MoiThanhVien.holder();
            view1 = LayoutInflater.from(context).inflate(resource, null);
            holder.tenThanhVien = view1.findViewById(R.id.tenThanhVien);
            holder.imageView = view1.findViewById(R.id.imgAnhThanhVien);
            holder.checkBox = view1.findViewById(R.id.chkThanhVien);
            view1.setTag(holder);
        }
        else {
            holder = (adapter_MoiThanhVien.holder) view1.getTag();
        }

         Users users = data.get(i);
        if (users.getUserImage().equals("")) {

        } else {
            Picasso.get().load(users.getUserImage()).into(holder.imageView);
        }
        holder.tenThanhVien.setText(users.getUserName().toString());
//
//        if (holder.checkBox.isChecked())
//        {
//
//        }
        return view1;

    }
    static class holder{
        ImageView imageView;
        TextView tenThanhVien;
        CheckBox checkBox;
    }
    @Override
    public int getCount() {
       return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


}
