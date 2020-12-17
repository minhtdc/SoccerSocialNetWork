package com.example.soccersocialnetwork.Football_Pitches.flagment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.soccersocialnetwork.Football_Pitches.adapter.CustomAdapterBooked;
import com.example.soccersocialnetwork.Football_Pitches.model.Book;
import com.example.soccersocialnetwork.R;

import java.util.ArrayList;

public class ListOfBookingFragment extends Fragment {
    ListView lvDaDat;
    ArrayList<Book> data_Books = new ArrayList<>();
    ArrayAdapter adapter_Book;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_of_booking, container, false);
        lvDaDat = view.findViewById(R.id.lvDaDat);
        setEvent();
        // Inflate the layout for this fragment
        return view;
    }

    private void setEvent() {
        KhoiTao();
        adapter_Book = new CustomAdapterBooked(getContext(), R.layout.item_listview_booked, data_Books);
        lvDaDat.setAdapter(adapter_Book);
    }

    private void KhoiTao() {
        Book book = new Book("A", "09h00", "12h30");
        Book book1 = new Book("B", "14h00", "16h00");
        Book book2 = new Book("C", "09h00", "12h30");
        Book book3 = new Book("D", "09h00", "12h30");
        data_Books.add(book);
        data_Books.add(book1);
        data_Books.add(book2);
        data_Books.add(book3);
    }
}