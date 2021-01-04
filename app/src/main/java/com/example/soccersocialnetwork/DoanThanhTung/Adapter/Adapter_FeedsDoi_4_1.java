package com.example.soccersocialnetwork.DoanThanhTung.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccersocialnetwork.DoanThanhTung.Models.Feed;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Feeds;
import com.example.soccersocialnetwork.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_FeedsDoi_4_1 extends RecyclerView.Adapter<Adapter_FeedsDoi_4_1.Adapter_FeedsDoi2_ViewHoder> {

    Context context;
    private ArrayList<Feed> data;
    public Adapter_FeedsDoi_4_1(Context context, ArrayList<Feed> data) {
        this.context = context;
        this.data = data;
    }
//    public Adapter_FeedsDoi2(Context context,ArrayList<Feeds> data) {
//        this.context = context;
//        this.data = data;
//    }




    @NonNull
    @Override
    public Adapter_FeedsDoi2_ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doi_feeds_view_4_1, parent, false);
        return new Adapter_FeedsDoi2_ViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_FeedsDoi2_ViewHoder holder, int position) {

        Feed feed = data.get(position);
        if (feed == null) {
            return;
        }
        holder.tvNameUser.setText(feed.getNameUser());
        holder.tvSTT.setText(feed.getSTT());
        if (feed.getImgUser().equals("")) {

        } else {
            Picasso.get().load(feed.getImgUser()).into(holder.imgAvatar);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class Adapter_FeedsDoi2_ViewHoder extends RecyclerView.ViewHolder {
        TextView tvNameUser,tvSTT;
        ImageView imgAvatar;
        Button btnBinhLuan;

        public Adapter_FeedsDoi2_ViewHoder(@NonNull View itemView) {
            super(itemView);
            tvNameUser = itemView.findViewById(R.id.tvNameUser);
            tvSTT = itemView.findViewById(R.id.tvSTT);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            btnBinhLuan = itemView.findViewById(R.id.btnBinhLuan);

        }


    }
}
