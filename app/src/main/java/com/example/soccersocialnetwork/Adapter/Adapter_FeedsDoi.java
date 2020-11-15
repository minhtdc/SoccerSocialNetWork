package com.example.soccersocialnetwork.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.data_models.Model_FeedsDoi_View;

import java.util.ArrayList;

public class Adapter_FeedsDoi extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Model_FeedsDoi_View> data;
    int count = 1;

    private static final int LAYOUT_ONE= 0;
    private static final int LAYOUT_TWO= 1;

    private ArrayList<View> views;

    public Adapter_FeedsDoi(Context context, ArrayList<Model_FeedsDoi_View> data) {
        this.context = context;
        this.data = data;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
//        if (viewType == LAYOUT_ONE) {
        if(count == 1){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doi_feeds_1, parent, false);
           // views.add(view);
            viewHolder = new Doi_ViewHolder_1(view);

            count = 2;
        }else {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doi_feeds_view, parent, false);

        viewHolder = new Doi_ViewHolder_2(view);


        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        if(count == 1 )
//        {
//
//        }else{
//            Doi_ViewHolder_2 viewHolder = (Doi_ViewHolder_2) holder;
//            final Model_FeedsDoi_View feedsDoi_viewModel = data.get(position);
//            viewHolder.tv_HanGio.setText(String.valueOf(feedsDoi_viewModel.getHanGio()));
//            viewHolder.tv_ThanhPho.setText(feedsDoi_viewModel.getThanhPho());
//            viewHolder.tv_Quan.setText(feedsDoi_viewModel.getQuan());
//            viewHolder.tv_Ngay.setText(feedsDoi_viewModel.getNgay());
//            viewHolder.tv_Gio.setText(feedsDoi_viewModel.getGio());
//            viewHolder.tv_ThongBao.setText(feedsDoi_viewModel.getThongBao());
//        }
    }

//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView tv_HanGio, tv_ThanhPho, tv_Quan, tv_Ngay, tv_Gio, tv_ThongBao;
//
//
////        TextView tv_DangBai;
////        ImageView img_DangBai;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tv_HanGio = itemView.findViewById(R.id.tv_HanGio);
//            tv_ThanhPho = itemView.findViewById(R.id.tv_ThanhPho);
//            tv_Quan = itemView.findViewById(R.id.tv_Quan);
//            tv_Ngay = itemView.findViewById(R.id.tv_Ngay);
//            tv_Gio = itemView.findViewById(R.id.tv_Gio);
//            tv_ThongBao = itemView.findViewById(R.id.tv_ThongBao);
//
//
////            tv_DangBai = itemView.findViewById(R.id.tv_DangBai);
////            img_DangBai = itemView.findViewById(R.id.img_DangBai);
//        }
//    }

//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View view = null;
//        RecyclerView.ViewHolder viewHolder = null;
//        if (count == 1) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feeds_doi_1, parent, false);
//            views.add(view);
//            viewHolder = new MyViewHolderOne(view);
//            return new OtherViewHolder(view);
//            count++;
//        }
////        }else {
////        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feeds_doi_view, parent, false);
//
//        MyViewHolder viewHolder = new MyViewHolder(view);
//        return viewHolder;
////        }
//    }


//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//
//        final Model_FeedsDoi_View feedsDoi_viewModel = data.get(position);
//        holder.tv_HanGio.setText(String.valueOf(feedsDoi_viewModel.getHanGio()));
//        holder.tv_ThanhPho.setText(feedsDoi_viewModel.getThanhPho());
//        holder.tv_Quan.setText(feedsDoi_viewModel.getQuan());
//        holder.tv_Ngay.setText(feedsDoi_viewModel.getNgay());
//        holder.tv_Gio.setText(feedsDoi_viewModel.getGio());
//        holder.tv_ThongBao.setText(feedsDoi_viewModel.getThongBao());
//
//
//    }

    @Override
    public int getItemCount() {

            return data.size() + 1;



    }


//    @Override
//    public int getItemViewType(int position) {
//        if(position == 0)
//        {
//            return LAYOUT_ONE;
//        }else{
//            return LAYOUT_TWO;
//        }
//    }
}



