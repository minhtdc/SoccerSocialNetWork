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
import com.example.soccersocialnetwork.TranDuyHuynh.models.information_listTeams;

import java.util.List;

public class information_listTeams_Adapter extends ArrayAdapter<information_listTeams> {
    Context context;
    int resource;
    List<information_listTeams> object;
    public information_listTeams_Adapter(@NonNull Context context, int resource, @NonNull List<information_listTeams> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.object = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        holder holder = null;
        if (row == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(resource,parent,false);

            holder = new holder();
            holder.imageView = (ImageView) row.findViewById(R.id.imgLogo_Teams_list_teams);
            holder.txtTenDoi = (TextView) row.findViewById(R.id.txtTendoi_list_teams);
            holder.txtKhuVuc = (TextView) row.findViewById(R.id.txtThongTinKhuVuc_lstTeams);
            holder.txtSLCauThu = (TextView) row.findViewById(R.id.txtThongTinSL_CauThu_lstTeam);
            holder.txtGioiThieu = (TextView) row.findViewById(R.id.txtThongTinGioiThieu_lstTeams);

            row.setTag(holder);
        }
        else {
            holder = (holder) row.getTag();
        }

        information_listTeams information_listTeams = object.get(position);
        holder.imageView.setImageResource(information_listTeams.getmIcon());
        holder.txtTenDoi.setText(information_listTeams.getmTenDoi());
        holder.txtKhuVuc.setText(information_listTeams.getmKhuVuc());
        holder.txtSLCauThu.setText(information_listTeams.getmSl_CauThu());
        holder.txtGioiThieu.setText(information_listTeams.getmGioiThieu());

        return row;
    }

    class holder{
        ImageView imageView;
        TextView txtTenDoi, txtKhuVuc, txtSLCauThu,txtGioiThieu;
    }
}
