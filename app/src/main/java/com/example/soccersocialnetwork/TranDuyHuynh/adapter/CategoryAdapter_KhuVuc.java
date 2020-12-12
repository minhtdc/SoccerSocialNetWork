package com.example.soccersocialnetwork.TranDuyHuynh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.models.Category_KhuVuc;

import java.util.List;

public class CategoryAdapter_KhuVuc extends ArrayAdapter<Category_KhuVuc> {

    // hàm khởi tạo contructer
    public CategoryAdapter_KhuVuc(@NonNull Context context, int resource, @NonNull List<Category_KhuVuc> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected,parent,false);
        TextView tvSelected = convertView.findViewById(R.id.txtSelected);
        Category_KhuVuc category_khuVuc = this.getItem(position);

        if(category_khuVuc != null){
            tvSelected.setText(category_khuVuc.getKhuVuc());

        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
        TextView tvCategory = convertView.findViewById(R.id.txtCategory);
        Category_KhuVuc category_khuVuc = this.getItem(position);

        if(category_khuVuc != null){
            tvCategory.setText(category_khuVuc.getKhuVuc());

        }
        return convertView;
    }
}
