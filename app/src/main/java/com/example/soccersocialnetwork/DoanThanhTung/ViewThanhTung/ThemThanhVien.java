package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_ThanhVien;
import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_ThemThanhVien;
import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_ThemThanhVien_2;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ThemThanhVien extends AppCompatActivity {

    ImageView imgKichKhoiDoi;
    DatabaseReference mDatabase;
    ListView lvAllThanhVien;
    public static ArrayList<Users> strings = new ArrayList<>();
    public static ArrayList<Users> listUser = new ArrayList<>();
    ArrayList<String> keyUser = new ArrayList<>();
    ArrayList<String> keyUserDaCo = new ArrayList<>();

    String idDoi;

    Adapter_ThemThanhVien_2 adapterThem;
    Adapter_ThanhVien adapter_thanhVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_thanh_vien);
        setControl();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Danh sách thành viên");

        actionBar.setDisplayHomeAsUpEnabled(true);

        idDoi = getIntent().getExtras().getString("Doi_ID");

         // Toast.makeText(this, idDoi+"", Toast.LENGTH_SHORT).show();
        getUser();
        setEvent();
    }

    private void setEvent() {
    lvAllThanhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          //  Toast.makeText(ThemThanhVien.this, listUser.get(position).getUserEmail()+"", Toast.LENGTH_SHORT).show();
            getUserChuanBiKich(listUser.get(position).getUserEmail());

        }
    });
//        lvAllThanhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                getUserChuanBiKich(listUser.get(position).getUserEmail());
//                return false;
//            }
//        });
    }

    String keyUserChuanBiKich;
    private void getUserChuanBiKich(final String key){

     //   Toast.makeText(ThemThanhVien.this, users.getUserEmail()+"", Toast.LENGTH_SHORT).show();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dt:
                snapshot.getChildren()){

                    Users users = dt.getValue(Users.class);

                    if(key.equals(users.getUserEmail())){
                       // Toast.makeText(ThemThanhVien.this, key+"", Toast.LENGTH_SHORT).show();
                        for (DataSnapshot dtt:
                        dt.child("listDoi").getChildren()){
                            if(idDoi.equals(dtt.getKey())){
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
                                databaseReference.child(dt.getKey()).child("listDoi").child(idDoi).setValue(null);
                         //   Toast.makeText(ThemThanhVien.this, dtt.getValue()+"", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getUser() {
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listUser.clear();
                for (DataSnapshot dt :
                        snapshot.getChildren()) {
                    Users users = dt.getValue(Users.class);
                    for (DataSnapshot dtt :
                            dt.child("listDoi").getChildren()) {
                        if (dtt.getKey().equals(idDoi) ) {
                            listUser.add(users);
                            adapter_thanhVien = new Adapter_ThanhVien(getBaseContext(),R.layout.adapter_thanhviendoi,listUser);
                            lvAllThanhVien.setAdapter(adapter_thanhVien);
                            adapter_thanhVien.notifyDataSetChanged();
                         //   Toast.makeText(ThemThanhVien.this, listUser.size() + "", Toast.LENGTH_SHORT).show();
                        }

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setControl() {

        lvAllThanhVien = findViewById(R.id.lvAllThanhVien);
        imgKichKhoiDoi = findViewById(R.id.imgKichKhoiDoi);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}