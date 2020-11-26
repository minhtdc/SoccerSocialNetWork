package com.example.soccersocialnetwork.TranDuyHuynh.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.models.information_notifications;

import java.util.ArrayList;
import java.util.List;

public class information_notifications_Adapter extends ArrayAdapter<information_notifications> {
    private Context context;
    private int resource;
    List<information_notifications> objects;
    public information_notifications_Adapter(@NonNull Context context, int resource, @NonNull List<information_notifications> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        Holder holder = null;
        if (row == null)
        {
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            row = layoutInflater.inflate(resource,parent,false);

            holder = new Holder();
            holder.imageView = (ImageView) row.findViewById(R.id.img_Team_notification);
            holder.txtThongBao = (TextView) row.findViewById(R.id.txtChiThietThongBao);
            row.setTag(holder);
        }
        else {
            holder = (Holder) row.getTag();
        }

        information_notifications information_notifications = objects.get(position);
        holder.imageView.setImageResource(information_notifications.getmImage());
        holder.txtThongBao.setText(information_notifications.getmthongBao());

        return row;
    }
    class Holder{
        ImageView imageView;
        TextView txtThongBao;
    }
}
