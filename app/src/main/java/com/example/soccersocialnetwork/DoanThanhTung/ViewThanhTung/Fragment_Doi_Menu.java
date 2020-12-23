package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_ThemThanhVien;
import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_ThemThanhVien_2;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment_Doi_Menu extends Fragment {


    public static ArrayList<Users> listUser = new ArrayList<>();
    ArrayList<Users> listUserDaCo = new ArrayList<>();

    ArrayList<String> keyUser = new ArrayList<>();
    ArrayList<String> keyUserDaCo = new ArrayList<>();

    ArrayList<Team> listTeam = new ArrayList<>();
    DatabaseReference mDatabase;

    LinearLayout llThongTinDoi, llThemThanhVien, llThanhVien;
    String idDoi, uriIMG, tenDoi, khuVuc, email, sdt, gioiThieu, tieuChi, slogan;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = (ViewGroup) inflater.inflate((R.layout.doi_menu_1), container, false);
        llThongTinDoi = rootView.findViewById(R.id.llThongTinDoi);
        llThemThanhVien = rootView.findViewById(R.id.llThemThanhVien);
        llThanhVien = rootView.findViewById(R.id.llThanhVien);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        //lay id bên trang chủ của đội
        idDoi = getArguments().getString("Doi_ID");
        uriIMG = getArguments().getString("Doi_uriIMG");
        tenDoi = getArguments().getString("Doi_TenDoi");
        khuVuc = getArguments().getString("Doi_KhuVuc");
        email = getArguments().getString("Doi_Email");
        sdt = getArguments().getString("Doi_SDT");
        gioiThieu = getArguments().getString("Doi_GioiThieu");
        tieuChi = getArguments().getString("Doi_TieuChi");
        slogan = getArguments().getString("Doi_Slogan");

        getUserDaCo();
        getUser();

        setEvent();


        return rootView;
    }


    private void setEvent() {


        llThongTinDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), Doi_ThongTinCaNhan.class);
                Bundle bundle_menu = new Bundle();
                bundle_menu.putString("Doi_ID", idDoi);
                bundle_menu.putString("Doi_uriIMG", uriIMG);
                bundle_menu.putString("Doi_TenDoi", tenDoi);
                bundle_menu.putString("Doi_KhuVuc", khuVuc);
                bundle_menu.putString("Doi_Email", email);
                bundle_menu.putString("Doi_SDT", sdt);
                bundle_menu.putString("Doi_GioiThieu", gioiThieu);
                bundle_menu.putString("Doi_TieuChi", tieuChi);
                bundle_menu.putString("Doi_Slogan", slogan);
                intent.putExtras(bundle_menu);
                startActivity(intent);
            }
        });

        llThemThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogThemThanh();

            }
        });

        llThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), listUser.get(0).getUserID() + "", Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), keyUserDaCo.get(0) + "", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void dialogFullScreenThanhVien() {
        final Dialog dialogFullScreen = new Dialog(getContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialogFullScreen.getWindow().setBackgroundDrawableResource(R.color.colorWhite);


        dialogFullScreen.show();
    }

    public static ArrayList<Users> strings = new ArrayList<>();

    private void dialogThemThanh() {

        final Adapter_ThemThanhVien_2 adapterThem;
        final Adapter_ThemThanhVien adapterDanhSach;


        final Dialog dialogThemThanhVien = new Dialog(getContext());
        dialogThemThanhVien.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogThemThanhVien.setContentView(R.layout.dialog_them_thanhvien);
        dialogThemThanhVien.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //ánh xạ
        SearchView svThemThanhVien = dialogThemThanhVien.findViewById(R.id.svThemThanhVien);
        Button btnEXIT = dialogThemThanhVien.findViewById(R.id.btnEXIT);
        ListView lvThemThanhVien = dialogThemThanhVien.findViewById(R.id.lvThemThanhVien);
        ListView lvDanhSachDaThem = dialogThemThanhVien.findViewById(R.id.lvDanhSachDaThem);
        Button btnThemThanhVien = dialogThemThanhVien.findViewById(R.id.btnThemThanhVien);


        adapterThem = new Adapter_ThemThanhVien_2(getContext(), R.layout.dialog_them_thanhvien_2, strings);
        lvThemThanhVien.setAdapter(adapterThem);
        adapterDanhSach = new Adapter_ThemThanhVien(getContext(), R.layout.dialog_them_thanhvien_1, listUser);
//
//        for(int i = 0; i<keyUserDaCo.size();i++){
//            for(int j = 0; j<listUser.size();j++){
//                if(keyUserDaCo.get(i).equals(keyUser.get(j))){
//                    listUser.remove(j);
//                }
//            }
//        }
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

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Xác thực");
                builder.setMessage("Bạn có thực sự muốn thêm một/các thành viên này?");
                builder.setNegativeButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        getUser(user.getUserEmail());
                        insertThanhVien();
                        dialog.cancel();
                        dialogThemThanhVien.cancel();
                    }
                });
                builder.setPositiveButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                // Toast.makeText(getContext(), strings.get(1).getUserID()+"", Toast.LENGTH_SHORT).show();
            }
        });

        btnEXIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogThemThanhVien.cancel();
            }
        });
        //them adapter hinh anh
        dialogThemThanhVien.show();
    }

    private void insertThanhVien() {
        for (int i = 0; i < strings.size(); i++) {
            mDatabase.child("Team").child(idDoi).child("listThanhVien").child(strings.get(i).getUserID()).setValue("User");
        }

    }

    private Dialog dialogSearchThanhVien() {

        final ArrayAdapter adapter;
        ArrayList<String> strings = new ArrayList<>();

        Dialog dialogSearchThanhVien = new Dialog(getContext());
        dialogSearchThanhVien = new Dialog(getContext());

        dialogSearchThanhVien.setContentView(R.layout.dialog_them_thanhvien_1);
        dialogSearchThanhVien.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //anhxa


        return dialogSearchThanhVien;
    }


    private void getUserDaCo() {
        keyUserDaCo.clear();
        mDatabase.child("Team").child(idDoi).child("listThanhVien").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                keyUserDaCo.add(snapshot.getKey());
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

                ;
            }
        });
    }

    private void getUser() {
        listUser.clear();
        keyUser.clear();
        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dt :
                        snapshot.getChildren()) {
                    if(LoginActivity.USER_ID_CURRENT != dt.getKey()){
                        keyUser.add(dt.getKey());

                        Users users = dt.getValue(Users.class);
                        listUser.add(users);
                    }



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
