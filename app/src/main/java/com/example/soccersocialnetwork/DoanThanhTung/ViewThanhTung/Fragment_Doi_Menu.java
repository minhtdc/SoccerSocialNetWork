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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_ChoDuyetThanhVien;
import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_ThanhVien;
import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_ThemThanhVien;
import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_ThemThanhVien_2;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Doi_Menu extends Fragment {


    public static ArrayList<Users> listUser = new ArrayList<>();
    String adminOrUser;

    ArrayList<String> keyUser = new ArrayList<>();


    ArrayList<Team> listTeam = new ArrayList<>();
    DatabaseReference mDatabase;
    private ValueEventListener mListener;
    List<String> keyUserChoDuyet = new ArrayList<>();
    ArrayList<Users> listChoDuyet = new ArrayList<>();
    LinearLayout llThongTinDoi, llThemThanhVien, llThanhVien, llChoDuyet, llRoiDoi;
    TextView tvThongBao123;
    String idDoi, uriIMG, tenDoi, khuVuc, email, sdt, gioiThieu, tieuChi, slogan;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = (ViewGroup) inflater.inflate((R.layout.doi_menu_1), container, false);
        llThongTinDoi = rootView.findViewById(R.id.llThongTinDoi);
        llThemThanhVien = rootView.findViewById(R.id.llThemThanhVien);
        llThanhVien = rootView.findViewById(R.id.llThanhVien);
        llChoDuyet = rootView.findViewById(R.id.llChoDuyet);
        tvThongBao123 = rootView.findViewById(R.id.tvThongBao123);
        llRoiDoi = rootView.findViewById(R.id.llRoiDoi);


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

        readUserOrAdmin();
        getUserDaCo();

        readChoDuyet();


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
                // getUserDaCo();
//                Toast.makeText(getContext(), adminOrUser + "", Toast.LENGTH_SHORT).show();
                dialogThemThanh();

            }
        });

        llThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    getUserDaCo();
                Intent intent = new Intent(getContext(), ThemThanhVien.class);
                Bundle bundle = new Bundle();
                bundle.putString("Doi_ID", idDoi);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        llChoDuyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogChoDuyet();
            }
        });

        llRoiDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Rời đội");
                builder.setMessage("Bạn có muốn rời đội này?");
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (adminOrUser.equals("Admin")) {
                            Toast.makeText(getContext(), "Bạn không thể rồi team của mình", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                            roiTeam();
                            getActivity().finish();
                        }
                    }
                });
                builder.show();
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
        mDatabase.getDatabase().goOnline();

        final Adapter_ThemThanhVien adapterDanhSach;


        final Dialog dialogThemThanhVien = new Dialog(getContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialogThemThanhVien.getWindow().setBackgroundDrawableResource(R.color.colorWhite);
        dialogThemThanhVien.setContentView(R.layout.dialog_them_thanhvien);
        //dialogThemThanhVien.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //ánh xạ
        SearchView svThemThanhVien = dialogThemThanhVien.findViewById(R.id.svThemThanhVien);
        Button btnEXIT = dialogThemThanhVien.findViewById(R.id.btnEXIT);

//        ListView lvThemThanhVien = dialogThemThanhVien.findViewById(R.id.lvThemThanhVien);
        ListView lvDanhSachDaThem = dialogThemThanhVien.findViewById(R.id.lvDanhSachDaThem);


        strings.add(new Users("0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "https://firebasestorage.googleapis.com/v0/b/soccersocialnetwork-733b3.appspot.com/o/imgUser%2FQY2wgkB3OtNCmcSb2gfDpc8S9Kj2%2Fa8fe8517-fbc4-47d4-ba6f-1fe00586ff81?alt=media&token=e8c0696c-2196-41be-a047-b3153c9efdb8"));

//        adapterThem = new Adapter_ThemThanhVien_2(getContext(), R.layout.dialog_them_thanhvien_2, strings);
//        lvThemThanhVien.setAdapter(adapterThem);

        adapterDanhSach = new Adapter_ThemThanhVien(getContext(), R.layout.dialog_them_thanhvien_1, listUser);
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


        btnEXIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strings.clear();
                dialogThemThanhVien.cancel();
            }
        });
        //them adapter hinh anh

//        lvDanhSachDaThem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(),listUser.get(position).getUserName()+ "", Toast.LENGTH_SHORT).show();
//            }
//        });
        dialogThemThanhVien.show();
    }


    private void dialogChoDuyet() {


        Adapter_ChoDuyetThanhVien adapterDanhSachChoDuyet;
        final Dialog dialogChoDuyet = new Dialog(getContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialogChoDuyet.setTitle("!@#");
        dialogChoDuyet.getWindow().setBackgroundDrawableResource(R.color.colorWhite);
        dialogChoDuyet.setContentView(R.layout.dialog_choduyet);
//        dialogChoDuyet.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //  ánh xạ
        Button btnEXIT = dialogChoDuyet.findViewById(R.id.btnEXIT);

        final ListView lvDanhSachChoDuyet = dialogChoDuyet.findViewById(R.id.lvChoDuyet);


        //  strings.add(new Users("0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "https://firebasestorage.googleapis.com/v0/b/soccersocialnetwork-733b3.appspot.com/o/imgUser%2FQY2wgkB3OtNCmcSb2gfDpc8S9Kj2%2Fa8fe8517-fbc4-47d4-ba6f-1fe00586ff81?alt=media&token=e8c0696c-2196-41be-a047-b3153c9efdb8"));

//        adapterThem = new Adapter_ThemThanhVien_2(getContext(), R.layout.dialog_them_thanhvien_2, strings);
//        lvThemThanhVien.setAdapter(adapterThem);
        adapterDanhSachChoDuyet = new Adapter_ChoDuyetThanhVien(getContext(), R.layout.dialog_them_thanhvien_1, listChoDuyet);
        lvDanhSachChoDuyet.setAdapter(adapterDanhSachChoDuyet);

        adapterDanhSachChoDuyet.notifyDataSetChanged();

        btnEXIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogChoDuyet.cancel();

            }
        });

        dialogChoDuyet.show();

    }

    private void readChoDuyet() {
        final DatabaseReference mDataUser = FirebaseDatabase.getInstance().getReference("users");
        mDatabase = FirebaseDatabase.getInstance().getReference("Team").child(idDoi).child("ChoDuyet");
        mListener = mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                keyUserChoDuyet.clear();
                listChoDuyet.clear();
                for (final DataSnapshot dt :
                        snapshot.getChildren()) {
                    keyUserChoDuyet.add(dt.getKey());
                    mDataUser.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dtt:
                            snapshot.getChildren()){
                                Users users = dtt.getValue(Users.class);
                                if(dtt.getKey().equals(dt.getKey())){
                                    listChoDuyet.add(users);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                if (keyUserChoDuyet.size() == 0 || keyUserChoDuyet == null) {
                    tvThongBao123.setVisibility(View.GONE);
                } else {
                    tvThongBao123.setText(keyUserChoDuyet.size() + "");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //------------------------------Fixx danh sach có thành viên
    private void getUserDaCo() {
        final ArrayList<String> strings = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listUser.clear();

                for (DataSnapshot dt :
                        snapshot.getChildren()) {
                    //    boolean kiemTra = true;
                    Users allUsers = dt.getValue(Users.class);
                    Users usersDaCo = new Users("0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");

                    strings.add(dt.getKey());
                    for (DataSnapshot dtt :
                            dt.child("listDoi").getChildren()) {
                        String key = dtt.getKey();
                        if (key.equals(idDoi)) {
//                            Toast.makeText(getContext(), dt.getKey()+"", Toast.LENGTH_SHORT).show();
                            usersDaCo = dt.getValue(Users.class);
                        }
                    }
                    if (allUsers.getUserEmail().equals(usersDaCo.getUserEmail())) {
                    } else {
                        listUser.add(allUsers);
                    }
                    // Toast.makeText(getContext(), strings.size()+"", Toast.LENGTH_SHORT).show();
//                    if (kiemTra != false) {
//
//                        listUser.add(allUsers);
//                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void roiTeam(){
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        mDatabase.child(LoginActivity.USER_ID_CURRENT).child("listDoi").child(idDoi).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(), "Bạn đã rời đội thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void readUserOrAdmin() {
        adminOrUser = "khongnull";
        mDatabase = FirebaseDatabase.getInstance().getReference("users").child(LoginActivity.USER_ID_CURRENT).child("listDoi").child(idDoi);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adminOrUser = snapshot.getValue(String.class);

                if (adminOrUser.equals("Admin")) {
                    llThemThanhVien.setVisibility(View.VISIBLE);
                    llChoDuyet.setVisibility(View.VISIBLE);
                } else {
                    llThemThanhVien.setVisibility(View.GONE);
                    llChoDuyet.setVisibility(View.GONE);
                }
                // Toast.makeText(Doi_ThongTinCaNhan.this,snapshot.getValue()+ "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
