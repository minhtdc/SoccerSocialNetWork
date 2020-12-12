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
import com.example.soccersocialnetwork.TranDuyHuynh.models.Category_LoaiHinhSan;

import java.util.List;

public class CategoryAdapter_LoaiHinhSan extends ArrayAdapter<Category_LoaiHinhSan> {

    public CategoryAdapter_LoaiHinhSan(@NonNull Context context, int resource, @NonNull List<Category_LoaiHinhSan> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected, parent, false);
        TextView textView = convertView.findViewById(R.id.txtSelected);

        Category_LoaiHinhSan category_loaiHinhSan = this.getItem(position);

        if (category_loaiHinhSan != null) {
            textView.setText(category_loaiHinhSan.getmLoaiHinhSan());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        TextView textView = convertView.findViewById(R.id.txtCategory);

        Category_LoaiHinhSan category_loaiHinhSan = this.getItem(position);

        if (category_loaiHinhSan != null) {
            textView.setText(category_loaiHinhSan.getmLoaiHinhSan());
        }
        return convertView;
    }
}
