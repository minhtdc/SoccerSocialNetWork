package com.example.soccersocialnetwork.TranDuyHuynh.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.adapter.CategoryAdapter_KhuVuc;
import com.example.soccersocialnetwork.TranDuyHuynh.adapter.information_listStadiums_Adapter;
import com.example.soccersocialnetwork.TranDuyHuynh.models.Category_KhuVuc;
import com.example.soccersocialnetwork.TranDuyHuynh.models.information_listStadium;

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

   // Khai bao list view
    private ListView listView;
    ArrayList<information_listStadium> listStadiums;

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
        spinner = view.findViewById(R.id.spnKhuVuc_lstSan);
       createDataForSpn_KhuVuc();
        categoryAdapter_khuVuc = new CategoryAdapter_KhuVuc(getContext(),R.layout.item_selected,createDataForSpn_KhuVuc());
        spinner.setAdapter(categoryAdapter_khuVuc);

        createDataForListStadiums();
        information_listStadiums_Adapter information_listStadiums_adapter = new information_listStadiums_Adapter(getContext(),R.layout.list_stadiums,listStadiums);
        listView = view.findViewById(R.id.lstStadiums);
        listView.setAdapter(information_listStadiums_adapter);

    }

    // tạo dữ liệu giả cho spinner khu vực
    private List<Category_KhuVuc> createDataForSpn_KhuVuc(){
        List<Category_KhuVuc> list = new ArrayList<Category_KhuVuc>();
        list.add(new Category_KhuVuc("Khu vực"));
        list.add(new Category_KhuVuc("TP.HCM"));
        list.add(new Category_KhuVuc("Đà Nẵng"));
        list.add(new Category_KhuVuc("Hà Nội"));
        list.add(new Category_KhuVuc("Huế"));
        return list;
    }

    // tao du lieu gia cho lst san
    private void createDataForListStadiums(){
        listStadiums = new ArrayList<>();
        listStadiums.add(new information_listStadium(R.drawable.img_team1,"San 1","San 5 , San 7","San nhan tao","204 duong so 8, khu pho 3, TP.HCM"));
        listStadiums.add(new information_listStadium(R.drawable.img_team2,"San 2","San 5 , San 7","San nhan tao","205 duong so 9, khu pho 3, Da Nang"));
        listStadiums.add(new information_listStadium(R.drawable.img_team3,"San 3","San 5 , San 7","San nhan tao","206 duong so 7, khu pho 3, Hue"));
        listStadiums.add(new information_listStadium(R.drawable.img_team4,"San 4","San 5 , San 7","San nhan tao","207 duong so 5, khu pho 3, Hai Phong"));
        listStadiums.add(new information_listStadium(R.drawable.img_team5,"San 5","San 5 , San 7","San nhan tao","208 duong so 3, khu pho 3, Vung Tau"));
        listStadiums.add(new information_listStadium(R.drawable.img_team6,"San 6","San 5 , San 7","San nhan tao","2049duong so 2, khu pho 3, TP.HCM"));
    }
}