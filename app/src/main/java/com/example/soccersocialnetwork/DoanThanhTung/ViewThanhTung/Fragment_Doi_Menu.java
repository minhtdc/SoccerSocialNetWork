package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.DialogTitle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_FeedsDoi;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Feeds;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.R;

import java.util.ArrayList;

public class Fragment_Doi_Menu extends Fragment {


    ArrayList<Team> listTeam = new ArrayList<>();


    LinearLayout llThongTinDoi, llThemThanhVien;
    String idDoi, uriIMG, tenDoi, khuVuc, email, sdt, gioiThieu, tieuChi, slogan;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = (ViewGroup) inflater.inflate((R.layout.doi_menu_1), container, false);
        llThongTinDoi = rootView.findViewById(R.id.llThongTinDoi);
        llThemThanhVien = rootView.findViewById(R.id.llThemThanhVien);

        //lay id bên trang chủ của đội
        idDoi = getArguments().getString("Doi_ID");
        uriIMG = getArguments().getString("Doi_uriIMG");
        tenDoi = getArguments().getString("Doi_TenDoi");
        khuVuc = getArguments().getString("Doi_KhuVuc");
        email = getArguments().getString("Doi_Email");
        sdt = getArguments().getString("Doi_SDT");
        gioiThieu = getArguments().getString("Doi_GioiThieu");
        tieuChi = getArguments().getString("Doi_TieuChi");
        slogan = getArguments().getString("Doi_Slogan");

        setEvent();

        return rootView;
    }


    private void setEvent() {


        llThongTinDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), Doi_ThongTinCaNhan.class);
                Bundle bundle_menu = new Bundle();
                bundle_menu.putString("Doi_ID", idDoi);
                bundle_menu.putString("Doi_uriIMG", uriIMG);
                bundle_menu.putString("Doi_TenDoi", tenDoi);
                bundle_menu.putString("Doi_KhuVuc", khuVuc);
                bundle_menu.putString("Doi_Email", email);
                bundle_menu.putString("Doi_SDT", sdt);
                bundle_menu.putString("Doi_GioiThieu", gioiThieu);
                bundle_menu.putString("Doi_TieuChi", tieuChi);
                bundle_menu.putString("Doi_Slogan", slogan);
                intent.putExtras(bundle_menu);
                startActivity(intent);
            }
        });

        llThemThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogThemThanh();
            }
        });


    }


    private void dialogThemThanh() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_thanhvien);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //ánh xạ


        //them adapter hinh anh
        dialog.show();
    }

}
