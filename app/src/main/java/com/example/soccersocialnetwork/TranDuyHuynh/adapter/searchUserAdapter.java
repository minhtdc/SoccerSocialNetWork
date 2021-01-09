package com.example.soccersocialnetwork.TranDuyHuynh.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.edit_profile_user;
import com.example.soccersocialnetwork.TranDuyHuynh.models.information_findTeams;
import com.example.soccersocialnetwork.data_models.Users;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class searchUserAdapter extends ArrayAdapter<Users> {
    Activity context;
    int resource;
    ArrayList<Users> object;
    public static String clickUserID = LoginActivity.USER_ID_CURRENT;

    //define view holder
    static class ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView searchUserImage;
        TextView searchUserName;
        String searchUserID;
    }

    public searchUserAdapter(Activity context, int resource, ArrayList<Users> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.object = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = context.getLayoutInflater().inflate(resource, parent, false);
            viewHolder.searchUserImage = convertView.findViewById(R.id.searchUserImage);
            viewHolder.searchUserName = convertView.findViewById(R.id.searchUserName);

            //binging the view in convertView coresponding
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Users users = object.get(position);

        viewHolder.searchUserName.setText(users.getUserName());
        viewHolder.searchUserID = users.getUserID();
        if (users.getUserImage().equalsIgnoreCase("")) {
            Picasso.get().load(R.drawable.img_user).into(viewHolder.searchUserImage);
        } else {
            Picasso.get().load(users.getUserImage()).into(viewHolder.searchUserImage);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickUserID = getItem(position).getUserID();
                Intent intent = new Intent(getContext(), edit_profile_user.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                context.startActivity(intent);

                //Toast.makeText(getContext(), getItem(position).getUserID(), Toast.LENGTH_SHORT).show();

            }
        });

        return convertView;
    }
}
