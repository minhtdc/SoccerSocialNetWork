package com.example.soccersocialnetwork.TranDuyHuynh.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.soccersocialnetwork.TranDuyHuynh.edit_profile_user;
import com.example.soccersocialnetwork.R;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.TranDuyHuynh.TimKiemUserActivity;
import com.example.soccersocialnetwork.TranDuyHuynh.edit_profile_user;
import com.example.soccersocialnetwork.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link menu_flagment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class menu_flagment extends Fragment {
     Dialog dialog ;
    LinearLayout lnUser;
    ImageView imageView_icHelps, imageView_Setting, imageView_user;
    LinearLayout ln_choose_Helps, ln_choose_Settings,lnDangXuat;
    TextView name_user_menu;
    ImageButton btnTimKiem;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public menu_flagment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment menu_flagment.
     */
    // TODO: Rename and change types and number of parameters
    public static menu_flagment newInstance(String param1, String param2) {
        menu_flagment fragment = new menu_flagment();
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


    int viewCount = 0;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.menu_flagment, container, false);

        imageView_icHelps = rootView.findViewById(R.id.imgDown_TroGiup);
        ln_choose_Helps = rootView.findViewById(R.id.ln_choose_Help);
        imageView_Setting = rootView.findViewById(R.id.img_Down_CaiDat);
        ln_choose_Settings = rootView.findViewById(R.id.ln_choose_Settings);
        imageView_user = rootView.findViewById(R.id.img_user_menu);
        lnUser = rootView.findViewById(R.id.ln_user);
        lnDangXuat = rootView.findViewById(R.id.lnDangXuat);
        name_user_menu = rootView.findViewById(R.id.name_user_menu);
        btnTimKiem = rootView.findViewById(R.id.btnTimKiem);

        //set ảnh đại diện
        //imageView_user.setImageResource(R.drawable.img_team5)
        Picasso.get().load(LoginActivity.USER_IMG_CURRENT).into(imageView_user);



        //set tên người dùng
        name_user_menu.setText(LoginActivity.USER_NAME_CURRENT);



        // click vào img down của button  trợ giúp sẽ hiện thị các item chức năng trợ giúp
        imageView_icHelps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewCount == 0) {
                    ln_choose_Helps.setVisibility(View.VISIBLE);
                    imageView_icHelps.setImageResource(R.drawable.ic_up);
                    imageView_Setting.setImageResource(R.drawable.ic_down);
                    if (ln_choose_Settings.getVisibility() == View.VISIBLE) {
                        ln_choose_Settings.setVisibility(View.GONE);
                        imageView_Setting.setImageResource(R.drawable.ic_down);
                        viewCount = 0;
                    } else
                        viewCount = 1;
                } else {
                    ln_choose_Helps.setVisibility(View.GONE);
                    imageView_icHelps.setImageResource(R.drawable.ic_down);
                    viewCount = 0;
                }
            }
        });

        // click vào img down của button  trợ giúp sẽ hiện thị các item chức năng cài đặt
        imageView_Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewCount == 0) {
                    ln_choose_Settings.setVisibility(View.VISIBLE);
                    imageView_Setting.setImageResource(R.drawable.ic_up);
                    if (ln_choose_Helps.getVisibility() == View.VISIBLE) {
                        ln_choose_Helps.setVisibility(View.GONE);
                        imageView_icHelps.setImageResource(R.drawable.ic_down);
                        viewCount = 0;
                    } else
                        viewCount = 1;
                } else {
                    ln_choose_Settings.setVisibility(View.GONE);
                    imageView_Setting.setImageResource(R.drawable.ic_down);
                    viewCount = 0;
                }
            }
        });
        lnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),edit_profile_user.class);
                startActivity(intent);
            }
        });

        // Click vao dang xuat hiện thị dialog thông báo đăng xuất cho người dùng
        lnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog_DangXuat();
            }
        });

        //btn Tim kiem
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TimKiemUserActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    public void showDialog_DangXuat() {
        dialog = new Dialog(getContext());
        dialog.setTitle("Đăng xuất");
        dialog.setCancelable(true); // khi bấm ra ngoài dialog thì dialog sẽ tự tắt đi
        dialog.setContentView(R.layout.dialog_sign_out);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        Button btnDongY = dialog.findViewById(R.id.btnDong_y);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.fAuth.signOut();
                LoginActivity.editor.clear();
                LoginActivity.editor.commit();
                LoginActivity.IS_LOGIN = false;
                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                Toast.makeText(getContext(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}