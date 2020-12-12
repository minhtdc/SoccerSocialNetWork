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
import com.example.soccersocialnetwork.TranDuyHuynh.models.information_findTeams;

import java.util.List;

public class information_findTeams_Adapter extends ArrayAdapter<information_findTeams> {
    Context context;
    int resource;
    List<information_findTeams> object;

    public information_findTeams_Adapter(@NonNull Context context, int resource, @NonNull List<information_findTeams> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.object = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        information_findTeams_Holder information_findTeams_holder = null;
        if( row == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(resource,parent,false);
            information_findTeams_holder = new information_findTeams_Holder();
            information_findTeams_holder.imageView = (ImageView)row.findViewById(R.id.imgLogo_find_teams);
            information_findTeams_holder.txtTenDoi = (TextView)row.findViewById(R.id.txtTenDoi);
            information_findTeams_holder.txtDiaDiem = (TextView)row.findViewById(R.id.txtThongTinDiaDiem);
            information_findTeams_holder.txtThoigian = (TextView)row.findViewById(R.id.txtThongTinGio);
            information_findTeams_holder.txtNgay= (TextView)row.findViewById(R.id.txtThongTinNgay);
            row.setTag(information_findTeams_holder);
        }
        else{
            information_findTeams_holder = (information_findTeams_Holder)row.getTag();
        }

        information_findTeams information_findTeams = object.get(position);
        information_findTeams_holder.imageView.setImageResource(information_findTeams.getmIcon());
        information_findTeams_holder.txtTenDoi.setText(information_findTeams.getmTenDoi());
        information_findTeams_holder.txtDiaDiem.setText(information_findTeams.getmDiaDiem());
        information_findTeams_holder.txtNgay.setText(information_findTeams.getmNgay());
        information_findTeams_holder.txtThoigian.setText(information_findTeams.getmThoiGian());

        return  row;
    }
    class information_findTeams_Holder{
        ImageView imageView;
        TextView txtTenDoi, txtDiaDiem, txtThoigian, txtNgay;
    }
}
