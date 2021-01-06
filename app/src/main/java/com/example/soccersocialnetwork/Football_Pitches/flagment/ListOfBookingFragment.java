package com.example.soccersocialnetwork.Football_Pitches.flagment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

import java.util.ArrayList;

public class ListOfBookingFragment extends Fragment {
    ListView lvDaDat;
    ArrayList<Book> data_Books = new ArrayList<>();
    ArrayAdapter adapter_Book;
    DatabaseReference mFirebase;
    String idSan = ListOfYardFragment.idSan;

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
    }
    private void loadData() {
        mFirebase.child("SanDaDat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable final String previousChildName) {
                final SetFootballPitches setFootballPitches = snapshot.getValue(SetFootballPitches.class);
                final Book book = new Book();
                mFirebase.child("San").child(setFootballPitches.getIdSanDat()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final FootballPitches footballPitches = snapshot.getValue(FootballPitches.class);

                        mFirebase.child("Team").child(setFootballPitches.getIdDoiDat()).child("tenDoi").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (footballPitches.getId().equals(idSan)) {
                                    book.setTenDoi(snapshot.getValue().toString());
                                    book.setGioBatDau(setFootballPitches.getGioBatDau() + ":"
                                            + setFootballPitches.getPhutBatDau());
                                    book.setGioKetThuc(setFootballPitches.getGioKetThuc() + ":"
                                            + setFootballPitches.getPhutKetThuc());
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