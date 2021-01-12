package com.example.soccersocialnetwork.TranDuyHuynh.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.Set_Football_Pitches.activity.SetZoneActivity;
import com.example.soccersocialnetwork.TranDuyHuynh.adapter.CategoryAdapter_KhuVuc;
import com.example.soccersocialnetwork.TranDuyHuynh.adapter.information_listStadiums_Adapter;
import com.example.soccersocialnetwork.TranDuyHuynh.infomation_dangtintimtran;
import com.example.soccersocialnetwork.TranDuyHuynh.models.Category_KhuVuc;
import com.example.soccersocialnetwork.TranDuyHuynh.models.information_listStadium;
import com.example.soccersocialnetwork.football_field_owner.activity.AddZoneActivity;
import com.example.soccersocialnetwork.football_field_owner.activity.ListZone;
import com.example.soccersocialnetwork.football_field_owner.database.DataBaseHelper;
import com.example.soccersocialnetwork.football_field_owner.model.City;
import com.example.soccersocialnetwork.football_field_owner.model.FootballPitches;
import com.example.soccersocialnetwork.football_field_owner.model.Zone;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link stadium_flagment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class stadium_flagment extends Fragment {
    // Khai bao spinner
    private Spinner spinner;
    private CategoryAdapter_KhuVuc categoryAdapter_khuVuc;
    private Button btnSanCuaBan;
    public static String idKhu;

    ImageButton btnAdd;
    // Khai bao list view
    private ListView listView;
    ArrayList<information_listStadium> listStadiums;
    ArrayList<FootballPitches> data_listStadiums = new ArrayList<>();

    ArrayAdapter adapter_tp;

    DatabaseReference mFirebaseDatabase;
    ArrayAdapter information_listStadiums_adapter;
    ArrayList<City> data_tp = new ArrayList<>();
    String loaiSan, loaiHinhSan = "";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public stadium_flagment() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment stadium_flagment.
     */
    // TODO: Rename and change types and number of parameters
    public static stadium_flagment newInstance(String param1, String param2) {
        stadium_flagment fragment = new stadium_flagment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.stadium_flagment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
        dataBaseHelper.createDataBase();
        dataBaseHelper.openDataBase();

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        spinner = view.findViewById(R.id.spnKhuVuc_lstSan);
        listView = view.findViewById(R.id.lstStadiums);
        btnSanCuaBan = view.findViewById(R.id.btn_list_your_stadiums);
        btnAdd = view.findViewById(R.id.btnAdd);
        City city = new City();
        city.setName("Khu Vực");

        data_tp = dataBaseHelper.getAllCity();
        data_tp.add(0,city);
        setAdapterSpinner(data_tp, adapter_tp, spinner);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddZoneActivity.class);
                startActivity(intent);
            }
        });
        btnSanCuaBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ListZone.class);
                startActivity(intent);
            }
        });

        listStadiums = new ArrayList<>();


        information_listStadiums_adapter = new information_listStadiums_Adapter(getContext(), R.layout.list_stadiums, listStadiums);
        listView.setAdapter(information_listStadiums_adapter);
        loadData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), SetZoneActivity.class);
                idKhu = listStadiums.get(i).getIdKhu();
                infomation_dangtintimtran.timSan = "";
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        mFirebaseDatabase.child("Khu").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                final Zone zone = snapshot.getValue(Zone.class);
                if (zone.isCoTuNhien() == true) {
                    if (zone.isCoNhanTao() == true) {
                        loaiSan = "Cỏ Tự Nhiên, Cỏ Nhân Tạo";
                    } else {
                        loaiSan = "Cỏ Tự Nhiên";
                    }
                } else {
                    if (zone.isCoNhanTao() == true) {
                        loaiSan = "Cỏ Nhân Tạo";
                    } else {
                        loaiSan = "";
                    }
                }
                if (zone.isNamNguoi() == true) {
                    if (zone.isBayNguoi() == true) {
                        if (zone.isChinNguoi() == true) {
                            loaiHinhSan = "5 Người, 7 Người, 9 Người";
                        } else {
                            loaiHinhSan = "5 Người, 7 Người";
                        }
                    } else {
                        if (zone.isChinNguoi() == true) {
                            loaiHinhSan = "5 Người, 9 Người";
                        } else {
                            loaiHinhSan = "5 Người";
                        }
                    }
                } else {
                    if (zone.isBayNguoi() == true) {
                        if (zone.isChinNguoi() == true) {
                            loaiHinhSan = "7 Người, 9 Người";
                        } else {
                            loaiHinhSan = "7 Người";
                        }
                    } else {
                        if (zone.isChinNguoi() == true) {
                            loaiHinhSan = "9 Người";
                        } else {
                            loaiHinhSan = "";
                        }
                    }
                }
                if (!loaiSan.equals("") && !loaiHinhSan.equals("")) {
                    listStadiums.add(new information_listStadium(zone.getAnh(), zone.getPushId(), zone.getTenKhu(), loaiHinhSan, loaiSan, zone.getDiaChi() + zone.getQuan() + zone.getThanhPho()));
                    information_listStadiums_adapter.notifyDataSetChanged();
                }
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

    private void setAdapterSpinner(ArrayList data, ArrayAdapter adapter, Spinner spinner) {
        if (adapter == null) {
            adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, data);
            spinner.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            adapter.notifyDataSetChanged();
            spinner.setSelection(adapter.getCount() - 1);
        }
    }
}