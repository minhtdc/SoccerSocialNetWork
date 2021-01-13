package com.example.soccersocialnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.TranDuyHuynh.adapter.information_findTeams_Adapter;
import com.example.soccersocialnetwork.TranDuyHuynh.home_layout;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ChiTietTranDauActivity extends AppCompatActivity {
    de.hdodenhof.circleimageview.CircleImageView detailIMGDoi;
    TextView detailTenDoi, detailDiaDiem, detailThoiGian, detailNgay, detailSan, detailThongTinThem;
    ListView detailListThanhVien;
    ArrayAdapter<Users> arrayAdapter;
    String idNguoiDangTin, idTranDau, idDoiDangTin;
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
            detailSan.setText(bundle.getString("san"));
            //detailSan.setText(bundle.getString(("")));
            detailThongTinThem.setText(bundle.getString(("thongTinThem")));
            idNguoiDangTin = bundle.getString("idNguoiDangTin");
            idTranDau = bundle.getString("idTranDau");
            idDoiDangTin = bundle.getString("idDoiDangTin");
        }

        if (information_findTeams_Adapter.usersArrayList != null) {
            arrayAdapter = new ArrayAdapter(ChiTietTranDauActivity.this, android.R.layout.simple_list_item_1, information_findTeams_Adapter.usersArrayList);
            detailListThanhVien.setAdapter(arrayAdapter);
        }
        if (!LoginActivity.USER_ID_CURRENT.equals(idNguoiDangTin)) {
            detailButton.setText("Liên hệ");
            stateButton = false;
        } else {
            detailButton.setText("Xóa bài đăng");
            stateButton = true;
        }

        detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stateButton == true) {
                    final Dialog dialog;
                    dialog = new Dialog(ChiTietTranDauActivity.this);
                    dialog.setTitle("Đăng xuất");
                    dialog.setCancelable(true); // khi bấm ra ngoài dialog thì dialog sẽ tự tắt đi
                    dialog.setContentView(R.layout.dialog_sign_out);
                    Button btnHuy = dialog.findViewById(R.id.btnHuy);
                    Button btnDongY = dialog.findViewById(R.id.btnDong_y);
                    TextView dialogTitle = dialog.findViewById(R.id.dialogTitle);
                    dialogTitle.setText("Bạn có muốn xóa bài đăng không");
                    btnHuy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    btnDongY.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(String.format("ThongTinTranDau/%s", idTranDau));
                            ref.removeValue();
                            Intent intent1 = new Intent(ChiTietTranDauActivity.this, home_layout.class);
                            startActivity(intent1);
                            finish();
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else {
                    //hiện thông tin liên hệ
                    final Dialog dialog;
                    dialog = new Dialog(ChiTietTranDauActivity.this);
                    dialog.setTitle("Đăng xuất");
                    dialog.setCancelable(true); // khi bấm ra ngoài dialog thì dialog sẽ tự tắt đi
                    dialog.setContentView(R.layout.dialog_thongtinlienhe_nhom);
                    Button btnOK = dialog.findViewById(R.id.dialogBtnOK);
                    Button btnChiTiet = dialog.findViewById(R.id.dialogBtnChiTietDoi);
                    final TextView dialogTenDoi = dialog.findViewById(R.id.dialogTenDoi);
                    final TextView dialogEmail = dialog.findViewById(R.id.dialogEmail);
                    final TextView dialogSDT = dialog.findViewById(R.id.dialogSDT);
                    //dialogTitle.setText("Bạn có muốn xóa bài đăng không");

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(String.format("Team/%s/", idDoiDangTin));
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Team team = snapshot.getValue(Team.class);
                            try {
                                dialogTenDoi.setText(team.getTenDoi());
                                dialogEmail.setText(team.getEmail());
                                dialogSDT.setText(team.getSdt());
                            }catch (Exception ex){

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            }
        });

    }
}