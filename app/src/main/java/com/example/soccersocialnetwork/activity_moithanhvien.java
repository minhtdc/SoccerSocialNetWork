package com.example.soccersocialnetwork;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.soccersocialnetwork.TranDuyHuynh.adapter.adapter_MoiThanhVien;
import com.example.soccersocialnetwork.TranDuyHuynh.infomation_dangtintimtran;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activity_moithanhvien extends AppCompatActivity {

    ListView listView;
    TextView txtTenDoi;
    DatabaseReference databaseReference;
     ArrayList<Users> listUser = new ArrayList<>();
    String idDoi = infomation_dangtintimtran.idDoiDangTin;
    String tenDoi = infomation_dangtintimtran.tenDoi;
    adapter_MoiThanhVien adapter_moiThanhVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moithanhvien);
        listView =(ListView) findViewById(R.id.lstMoiThanhVien);
        txtTenDoi = (TextView) findViewById(R.id.txtDoi);
        txtTenDoi.setText(tenDoi);
        getUser();

    }
    private void getUser(){
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listUser.clear();
                for (DataSnapshot dt :
                        snapshot.getChildren()) {
                    Users users = dt.getValue(Users.class);
//                    Toast.makeText(activity_moithanhvien.this, dt.getValue()+"", Toast.LENGTH_SHORT).show();
                    for (DataSnapshot dtt :
                            dt.child("listDoi").getChildren()) {
                        if (dtt.getKey().equals(idDoi)) {
//                            Toast.makeText(activity_moithanhvien.this,users.getUserName(),Toast.LENGTH_LONG).show();
                            listUser.add(users);
                            adapter_moiThanhVien = new adapter_MoiThanhVien(activity_moithanhvien.this, R.layout.activity_list_moi_thanh_vien, listUser);
                            listView.setAdapter(adapter_moiThanhVien);
                            adapter_moiThanhVien.notifyDataSetChanged();
                        }

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}