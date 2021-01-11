package com.example.soccersocialnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soccersocialnetwork.TranDuyHuynh.adapter.information_findTeams_Adapter;
import com.example.soccersocialnetwork.TranDuyHuynh.infomation_dangtintimtran;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ChiTietTranDauActivity extends AppCompatActivity {
    de.hdodenhof.circleimageview.CircleImageView detailIMGDoi;
    TextView detailTenDoi, detailDiaDiem, detailThoiGian, detailNgay, detailSan, detailThongTinThem;
    ListView detailListThanhVien;
    ArrayAdapter<Users> arrayAdapter;
    String idNguoiDangTin, idTranDau;
    Button detailButton;
    Boolean stateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chi_tiet_tran_dau_layout);
        setTitle("Chi tiết trận đấu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        detailIMGDoi = findViewById(R.id.detailIMGDoi);
        detailTenDoi = findViewById(R.id.detailTenDoi);
        detailDiaDiem = findViewById(R.id.detailDiaDiem);
        detailThoiGian = findViewById(R.id.detailThoiGian);
        detailNgay = findViewById(R.id.detailNgay);
        detailSan = findViewById(R.id.detailSan);
        detailButton = findViewById(R.id.detailButton);
        detailThongTinThem = findViewById(R.id.detailThongTinThem);
        detailListThanhVien = findViewById(R.id.detailListThanhVien);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Picasso.get().load(bundle.getString("anhDoi")).into(detailIMGDoi);
            detailTenDoi.setText(bundle.getString(("tenDoi")));
            detailDiaDiem.setText(bundle.getString(("diaDiem")));
            detailThoiGian.setText(bundle.getString(("thoiGian")));
            detailNgay.setText(bundle.getString(("ngay")));
            //detailSan.setText(bundle.getString(("")));
            detailThongTinThem.setText(bundle.getString(("thongTinThem")));
            idNguoiDangTin = bundle.getString("idNguoiDangTin");
            idTranDau = bundle.getString("idTranDau");
        }

        if (information_findTeams_Adapter.usersArrayList != null) {
            arrayAdapter = new ArrayAdapter(ChiTietTranDauActivity.this, android.R.layout.simple_list_item_1, information_findTeams_Adapter.usersArrayList);
            detailListThanhVien.setAdapter(arrayAdapter);
        }
        if(!LoginActivity.USER_ID_CURRENT.equals(idNguoiDangTin)){
            detailButton.setText("Gửi lời mời đấu");
            stateButton = false;
        }
        else {
            detailButton.setText("Xóa bài đăng");
            stateButton = true;
        }

        detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stateButton == true){
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(String.format("ThongTinTranDau/%s",idTranDau));
                    ref.removeValue();
                }
                else {
                    //
                }
            }
        });

    }
}