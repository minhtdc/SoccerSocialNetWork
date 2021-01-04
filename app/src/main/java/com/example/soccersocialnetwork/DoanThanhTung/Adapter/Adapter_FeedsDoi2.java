package com.example.soccersocialnetwork.DoanThanhTung.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccersocialnetwork.DoanThanhTung.Models.Feeds;
import com.example.soccersocialnetwork.R;

import java.util.ArrayList;

public class Adapter_FeedsDoi2 extends RecyclerView.Adapter<Adapter_FeedsDoi2.Adapter_FeedsDoi2_ViewHoder> {

    Context context;

    public Adapter_FeedsDoi2(Context context, ArrayList<Feeds> data) {
        this.context = context;
        this.data = data;
    }
//    public Adapter_FeedsDoi2(Context context,ArrayList<Feeds> data) {
//        this.context = context;
//        this.data = data;
//    }

    private ArrayList<Feeds> data;


    @NonNull
    @Override
    public Adapter_FeedsDoi2_ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doi_feeds_view, parent, false);
        return new Adapter_FeedsDoi2_ViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_FeedsDoi2_ViewHoder holder, int position) {

        Feeds feeds = data.get(position);
        if(feeds == null){
            return;
        }
        holder.tv_Gio.setText(feeds.getGio() + "");
        holder.tv_HanGio.setText(feeds.getHanGio() + "");
        holder.tv_Ngay.setText(feeds.getNgay() + "");
        holder.tv_Quan.setText(feeds.getQuan() + "");
        holder.tv_ThanhPho.setText(feeds.getThanhPho() + "");
        holder.tv_ThongBao.setText(feeds.getThongBao() + "");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class Adapter_FeedsDoi2_ViewHoder extends RecyclerView.ViewHolder {
        TextView tv_HanGio, tv_ThanhPho, tv_Quan, tv_Ngay, tv_Gio, tv_ThongBao;

        public Adapter_FeedsDoi2_ViewHoder(@NonNull View itemView) {
            super(itemView);
            tv_HanGio = itemView.findViewById(R.id.tv_HanGio);
            tv_ThanhPho = itemView.findViewById(R.id.tv_ThanhPho);
            tv_Quan = itemView.findViewById(R.id.tv_Quan);
            tv_Ngay = itemView.findViewById(R.id.tv_Ngay);
            tv_Gio = itemView.findViewById(R.id.tv_Gio);
            tv_ThongBao = itemView.findViewById(R.id.tv_ThongBao);
        }


    }
}
