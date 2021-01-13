package com.example.soccersocialnetwork.Football_Pitches.flagment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soccersocialnetwork.Football_Pitches.adapter.CustomAdapterBooked;
import com.example.soccersocialnetwork.Football_Pitches.model.Book;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.Set_Football_Pitches.model.SetFootballPitches;
import com.example.soccersocialnetwork.football_field_owner.activity.ListZone;
import com.example.soccersocialnetwork.football_field_owner.flagment.ListOfYardFragment;
import com.example.soccersocialnetwork.football_field_owner.model.FootballPitches;
import com.example.soccersocialnetwork.football_field_owner.model.Waiting;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListOfBookingFragment extends Fragment {
    ImageView imgTeam,imgThoat;
    TextView tvChuCLB, tvTenNguoiDat, tvSDT, tvNgay, tvGio, tvTongTien;
    Button btnChapNhan,btnHuy;
    LinearLayout lnSan;
    ListView lvDaDat;
    ArrayList<Book> data_Books = new ArrayList<>();
    ArrayAdapter adapter_Book;
    DatabaseReference mFirebase;
    String idSan = ListOfYardFragment.idSan;
    String idDatSan;
    Book books = new Book();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_of_booking, container, false);
        lvDaDat = view.findViewById(R.id.lvDaDat);
        mFirebase = FirebaseDatabase.getInstance().getReference();
        setEvent();

        // Inflate the layout for this fragment
        return view;
    }

    private void setEvent() {
        adapter_Book = new CustomAdapterBooked(getContext(), R.layout.item_listview_booked, data_Books);
        lvDaDat.setAdapter(adapter_Book);
        loadData();
        lvDaDat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                idDatSan = data_Books.get(i).getIdDaDat();
                books = data_Books.get(i);
                dialogDatSan();
            }
        });

    }
    private void dialogDatSan() {
        final LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_waiting, null);
        //ánh xạ
        tvChuCLB = alertLayout.findViewById(R.id.tvChuCLB);
        tvNgay = alertLayout.findViewById(R.id.tvNgay);
        tvTenNguoiDat = alertLayout.findViewById(R.id.tvTenNguoiDat);
        tvSDT = alertLayout.findViewById(R.id.tvSDT);
        tvTongTien = alertLayout.findViewById(R.id.tvTongTien);
        tvGio = alertLayout.findViewById(R.id.tvGio);
        imgTeam = alertLayout.findViewById(R.id.imgTeam);
        imgThoat = alertLayout.findViewById(R.id.imgThoat);
        btnHuy = alertLayout.findViewById(R.id.btnHuy);
        btnChapNhan = alertLayout.findViewById(R.id.btnChapNhan);
        lnSan = alertLayout.findViewById(R.id.lnSan);

        btnChapNhan.setVisibility(View.GONE);
        btnHuy.setVisibility(View.GONE);
        lnSan.setVisibility(View.GONE);

        //khởi tạo dữ liệu

        tvGio.setText(books.getGioBatDau() + " - " + books.getGioKetThuc());
        loadAdmin();
        loadUser();
        //set title dialog
        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(alertLayout);
        alert.setCancelable(false);
        final AlertDialog dialog = alert.create();

        imgThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void loadUser(){
        mFirebase.child("SanDaDat").child(idDatSan).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final SetFootballPitches setFootballPitches = snapshot.getValue(SetFootballPitches.class);
                mFirebase.child("users").child(setFootballPitches.getIdNguoiDat()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        tvTenNguoiDat.setText(snapshot.child("userName").getValue().toString());
                        tvTongTien.setText(setFootballPitches.getTongTien());
                        tvNgay.setText(setFootballPitches.getNgayDat());
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadAdmin(){
        mFirebase.child("SanDaDat").child(idDatSan).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final SetFootballPitches setFootballPitches = snapshot.getValue(SetFootballPitches.class);
                mFirebase.child("Team").child(setFootballPitches.getIdDoiDat()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final String image = snapshot.child("hinhAnh").getValue().toString();
                        for (DataSnapshot data : snapshot.child("listThanhVien").getChildren()){
                            if (data.getValue().equals("Admin")){
                                mFirebase.child("users").child(data.getKey()).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        tvChuCLB.setText(snapshot.getValue().toString());
                                        Picasso.get().load(image).fit().into(imgTeam);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadData() {
        mFirebase.child("SanDaDat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable final String previousChildName) {
                final SetFootballPitches setFootballPitches = snapshot.getValue(SetFootballPitches.class);
                final String idDaDat = snapshot.getKey();
                final Book book = new Book();
                mFirebase.child("San").child(setFootballPitches.getIdSanDat()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final FootballPitches footballPitches = snapshot.getValue(FootballPitches.class);

                        mFirebase.child("Team").child(setFootballPitches.getIdDoiDat()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (footballPitches.getId().equals(idSan)) {
                                    book.setIdDaDat(idDaDat);
                                    book.setTenDoi(snapshot.child("tenDoi").getValue().toString());
                                    book.setGioBatDau(setFootballPitches.getGioBatDau() + ":"
                                            + setFootballPitches.getPhutBatDau());
                                    book.setGioKetThuc(setFootballPitches.getGioKetThuc() + ":"
                                            + setFootballPitches.getPhutKetThuc());
                                    book.setAnhDoi(snapshot.child("hinhAnh").getValue().toString());
                                    data_Books.add(book);
                                    adapter_Book.notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

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
}