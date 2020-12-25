package com.example.soccersocialnetwork.TranDuyHuynh.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_FeedsDoi2;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Feeds;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.DoiActivity;
import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.Doi_ThongTinCaNhan;
import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.TaoDonActivity;
import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.adapter.Adapter_TestCLickTeam;
import com.example.soccersocialnetwork.TranDuyHuynh.adapter.CategoryAdapter_KhuVuc;
import com.example.soccersocialnetwork.TranDuyHuynh.adapter.information_findTeams_Adapter;
import com.example.soccersocialnetwork.TranDuyHuynh.adapter.information_listTeams_Adapter;
import com.example.soccersocialnetwork.TranDuyHuynh.models.Category_KhuVuc;
import com.example.soccersocialnetwork.TranDuyHuynh.models.information_listTeams;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link team_flagment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class team_flagment extends Fragment {

    // Khai báo spinner
    private Spinner spinner;
    private ImageView imgTaoDoi;
    private Button btn_list_your_team,btn_list_team;
    private CategoryAdapter_KhuVuc categoryAdapter_khuVuc;

    DatabaseReference mDatabase;

    // Khai báo đối tượng list view và danh sách các đối tượng đội
    private ListView listView;
    ArrayList<information_listTeams> list;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ArrayList<Team> listTeams = new ArrayList<>();
    ArrayAdapter adapterDoi;

    ArrayList<String> listTeams2 = new ArrayList<>();
    //  ArrayList<Team> listTeamDoiCuaBan = new ArrayList<>();
    ArrayAdapter adapterDoi2;

    //-------------new
    List<String> keyDoi = new ArrayList<>();
    ArrayList<Team> listTeamDoiCuaBan = new ArrayList<>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public team_flagment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment team_flagment.
     */
    // TODO: Rename and change types and number of parameters
    public static team_flagment newInstance(String param1, String param2) {
        team_flagment fragment = new team_flagment();
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
        return inflater.inflate(R.layout.team_flagment, container, false);

    }

    public static boolean kiemTraLayoutDoi = false;
    public static boolean kiemTraLayoutChuaDoi = false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner = getView().findViewById(R.id.spnKhuVuc_lstDoi);
        imgTaoDoi = getView().findViewById(R.id.imgTaoDoi);
        listView = (ListView) getView().findViewById(R.id.lstTeam);
        btn_list_your_team = getView().findViewById(R.id.btn_list_your_team);
        btn_list_team = getView().findViewById(R.id.btn_list_team);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //   setEvent();
//        categoryAdapter_khuVuc = new CategoryAdapter_KhuVuc(getContext(), R.layout.item_selected, createDataForSpn_KhuVuc());
//        spinner.setAdapter(categoryAdapter_khuVuc);
//

//        // gọi hàm tạo dữ liệu giả cho list view đội
//        createDataForLst();

        // setAdapter cho listView
//        information_listTeams_Adapter information_listTeams_adapter = new information_listTeams_Adapter(getContext(), R.layout.list_doi, list);
//        listView = (ListView) getView().findViewById(R.id.lstTeam);
//        listView.setAdapter(information_listTeams_adapter);
        readDoiFirebase();
        readUser();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final String key = listTeams.get(position).getIdDoi() + "";

                for (int i = 0; i < keyDoi.size(); i++) {
                    if (keyDoi.get(i).equals(key)) {
                        Intent intent = new Intent(getContext(), DoiActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("TaoDoi_IDDoi", key);
                        intent.putExtras(bundle);
                        //kiemTraLayoutDoi = true;
                        startActivity(intent);
                        break;
                    }
//                    if(keyDoi.get(i) != key){
//                        Intent intent = new Intent(getContext(), Doi_ThongTinCaNhan.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("Doi_ID", key + "");
//                        intent.putExtras(bundle);
//                        startActivity(intent);
//                        break;
//                    }
                }

                // mDatabase = FirebaseDatabase.getInstance().getReference("Team").child(key).child("listThanhVien");
              //  Toast.makeText(getContext(), key + "", Toast.LENGTH_SHORT).show();
//                mDatabase.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        listTeamUsers.clear();
//                        for (DataSnapshot dt :
//                                snapshot.getChildren()) {
//                            listTeamUsers.add(dt.getKey());
//                        }
//                        for (int i = 0; i < listTeamUsers.size(); i++) {
//                            if (LoginActivity.USER_ID_CURRENT.equals(listTeamUsers.get(i))) {
//                                if (kiemTraLayoutDoi == false) {
//                                    Intent intent = new Intent(getContext(), DoiActivity.class);
//                                    Bundle bundle = new Bundle();
//                                    bundle.putString("TaoDoi_IDDoi", key);
//                                    intent.putExtras(bundle);
//                                    kiemTraLayoutDoi = true;
//                                    startActivity(intent);
//                                }
//                                break;
//                            }
//                        }
//
////                        if(kiemTraLayoutChuaDoi==false && kiemTraLayoutDoi==false){
////                            Intent intent = new Intent(getContext(), Doi_ThongTinCaNhan.class);
////                        Bundle bundle = new Bundle();
////                        bundle.putString("Doi_ID",key+"");
////                        intent.putExtras(bundle);
////                        startActivity(intent);
////                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });

            }
        });
        imgTaoDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TaoDonActivity.class));
            }
        });
        btn_list_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readDoiFirebase();
            }
        });
        btn_list_your_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readDoiFirebaseCuaBan();
                //  insertUser();
            }
        });

    }


    // tạo dữ liệu giả cho spinner khu vực
    private List<Category_KhuVuc> createDataForSpn_KhuVuc() {
        List<Category_KhuVuc> list = new ArrayList<Category_KhuVuc>();
        list.add(new Category_KhuVuc("Khu vực"));
        list.add(new Category_KhuVuc("TP.HCM"));
        list.add(new Category_KhuVuc("Đà Nẵng"));
        list.add(new Category_KhuVuc("Hà Nội"));
        list.add(new Category_KhuVuc("Huế"));
        return list;
    }


//    private void insertUser() {
//        mDatabase = FirebaseDatabase.getInstance().getReference("users").child(LoginActivity.USER_ID_CURRENT).child("listDoi");
//        List<String> listUser = new ArrayList<>();
//        listUser.add("123");
//        listUser.add("321");
//        listUser.add("456");
//        listUser.add("654");
//
//        mDatabase.setValue(listUser);
//    }
//
//    private void readDoiCuaBan() {
//        for (int i = 0; i < listTeams2.size(); i++) {
//            mDatabase = FirebaseDatabase.getInstance().getReference().child("Team").child(listTeams2.get(i) + "");
//            mDatabase.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    for (DataSnapshot dt :
//                            snapshot.getChildren()) {
//                        String dttkey = dt.getKey().toString();
//                        Team team = dt.getValue(Team.class);
//                        listTeamDoiCuaBan.add(team);
//
//                    }
//
//
////                            listTeams2.add(getIDDOi + "");
////                       // Toast.makeText(getContext(), LoginActivity.USER_ID_CURRENT + "", Toast.LENGTH_SHORT).show();
////                        Toast.makeText(getContext(), dt.getKey() + "", Toast.LENGTH_SHORT).show();
//                }
//
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }
//
//    }
//
//    private void readDoiFirebase2() {
//        readDoiFirebase();
////        listTeams2.clear();
//        for (int i = 0; i < listTeams.size(); i++) {
//            final int getIDDOi = listTeams.get(i).getIdDoi();
//            mDatabase = FirebaseDatabase.getInstance().getReference().child("Team").child(listTeams.get(i).getIdDoi() + "").child("listThanhVien");
//            String dttkey = "";
//            mDatabase.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    for (DataSnapshot dt :
//                            snapshot.getChildren()) {
//                        String dttkey = dt.getKey().toString();
////                        if (LoginActivity.USER_ID_CURRENT.equals(dttkey)) {
////                            listTeams2.add(getIDDOi + "");
//////                            Toast.makeText(getContext(), listTeams2.size() + "", Toast.LENGTH_SHORT).show();
//////                              Toast.makeText(getContext(), dt.getKey() + "", Toast.LENGTH_SHORT).show();
////                            break;
////                        }
//
//                        // adapterDoi2 = new Adapter_TestCLickTeam(getContext(), R.layout.list_doi, listTeams2);
////                            listTeams2.add(getIDDOi + "");
////                        Toast.makeText(getContext(), LoginActivity.USER_ID_CURRENT + "", Toast.LENGTH_SHORT).show();
////                        Toast.makeText(getContext(), dt.getKey() + "", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//            listTeams2.add("123123");
//        }
//        //listTeams2.add("123123");
//        Toast.makeText(getContext(), listTeams2.size() + "", Toast.LENGTH_SHORT).show();
//    }

    private void readUser() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(LoginActivity.USER_ID_CURRENT).child("listDoi");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                keyDoi.clear();
                for (DataSnapshot dt :
                        snapshot.getChildren()) {
                    keyDoi.add(dt.getKey());


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void readDoiFirebaseCuaBan() {

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Team");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listTeams.clear();
                for (int i = 0; i < keyDoi.size(); i++) {
                    for (DataSnapshot dt : snapshot.getChildren()) {

                        if (keyDoi.get(i).equals(dt.getKey())) {
                            Team team = dt.getValue(Team.class);
                          //  Toast.makeText(getContext(), team.getGioiThieu() + "", Toast.LENGTH_SHORT).show();
                            listTeams.add(team);
                        }
                    }
//                        Team team = new Team();
//                        Map<String, Object> data = (Map<String, Object>) snapshot.getValue();
//                        team.setKhuVuc(data.get("khuVuc") + "");
//                        team.setHinhAnh(data.get("hinhAnh") + "");


                    // Toast.makeText(getContext(),listTeams.get(0).getIdDoi()+ "", Toast.LENGTH_SHORT).show();

                }
                    adapterDoi = new Adapter_TestCLickTeam(getContext(), R.layout.list_doi, listTeams);
                    listView.setAdapter(adapterDoi);
                    adapterDoi.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void readDoiFirebase() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Team");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listTeams.clear();
                for (DataSnapshot dt :
                        snapshot.getChildren()) {

                    Team team = dt.getValue(Team.class);
                    listTeams.add(team);

                }
                // Toast.makeText(getContext(),listTeams.get(0).getIdDoi()+ "", Toast.LENGTH_SHORT).show();
                adapterDoi = new Adapter_TestCLickTeam(getContext(), R.layout.list_doi, listTeams);
                listView.setAdapter(adapterDoi);
                adapterDoi.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    ArrayList<String> listTeamUsers = new ArrayList<>();


    //    private void loadData(){
//
//        mDatabase.child("Team").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                Team team = snapshot.getValue(Team.class);
//                listTeams.add(team);
//                adapterDoi.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
    private void createDataForLst() {
        list = new ArrayList<information_listTeams>();

        list.add(new information_listTeams(R.drawable.img_team2, "doi 2", "Hà Nội", "16", "CLB đến từ Hà Nội"));
        list.add(new information_listTeams(R.drawable.img_team3, "doi 3", "Đà Nẵng", "17", "CLB đến từ Đà Nẵng"));
        list.add(new information_listTeams(R.drawable.img_team4, "doi 4", "Nghệ An", "18", "CLB đến từ Nghệ An"));
        list.add(new information_listTeams(R.drawable.img_team5, "doi 5", "Vũng Tàu", "19", "CLB đến từ Vũng Tàu"));
    }

}