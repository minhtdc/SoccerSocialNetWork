package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_ThemThanhVien;
import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_ThemThanhVien_2;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ThemThanhVien extends AppCompatActivity {
    SearchView svThemThanhVien;
    Button btnEXIT;
    ListView lvThemThanhVien;
    ListView lvDanhSachDaThem;
    Button btnThemThanhVien;
    DatabaseReference mDatabase;
    public static ArrayList<Users> strings = new ArrayList<>();
    public static ArrayList<Users> listUser = new ArrayList<>();
    ArrayList<String> keyUser = new ArrayList<>();
    ArrayList<String> keyUserDaCo = new ArrayList<>();

    String idDoi;

     Adapter_ThemThanhVien_2 adapterThem;
     Adapter_ThemThanhVien adapterDanhSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_thanh_vien);
        setControl();
        idDoi = getIntent().getExtras().getString("Doi_ID");
        getUser();
        setEvent();
    }

    private void setEvent() {
        adapterThem = new Adapter_ThemThanhVien_2(this, R.layout.dialog_them_thanhvien_2, strings);
        lvThemThanhVien.setAdapter(adapterThem);
        adapterDanhSach = new Adapter_ThemThanhVien(this, R.layout.dialog_them_thanhvien_1, listUser);
        lvDanhSachDaThem.setAdapter(adapterDanhSach);
        svThemThanhVien.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterDanhSach.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                adapterDanhSach.getFilter().filter(newText);


                return false;
            }
        });

        btnThemThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference("Team").child(idDoi).child("listThanhVien").push();
                mDatabase.setValue("sdfsdf");
            }
        });

        btnEXIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
            }
        });
    }
    private void insertThanhVien() {

    }

    private void getUser() {
        listUser.clear();
        keyUser.clear();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                keyUser.add(snapshot.getKey());
                Users users = snapshot.getValue(Users.class);
                listUser.add(users);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void setControl() {
         svThemThanhVien = findViewById(R.id.svThemThanhVien);
         btnEXIT = findViewById(R.id.btnEXIT);
         lvThemThanhVien = findViewById(R.id.lvThemThanhVien);
         lvDanhSachDaThem = findViewById(R.id.lvDanhSachDaThem);
         btnThemThanhVien = findViewById(R.id.btnThemThanhVien);
    }
}