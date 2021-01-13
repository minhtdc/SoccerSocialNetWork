package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_FeedsDoi2;
import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_FeedsDoi_4_1;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Feed;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Feeds;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.DoanThanhTung.Models.ThongBao;
import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class Fragment_Doi extends Fragment {

    DatabaseReference mDatabase;

    RecyclerView recyclerView;
    LinearLayout imgDangBai;
    TextView tvTrangThai, tvChuaCoBai;

    TextView tvGioStart;
    TextView tvGioEnd;
    TextView tvNgay;
    TextView txtThongBao;
    ValueEventListener mListener;
    Spinner spHanGio, spThanhPho, spQuan;

    ArrayList<Feeds> listFeeds = new ArrayList<>();
    ArrayList<Feed> listFirebaseDangBai = new ArrayList<>();
    Adapter_FeedsDoi_4_1 adapter_feedsDoi_4_1;
    Adapter_FeedsDoi2 adapter2;

    String userIMG, userName;
    String idDoi;
    int iHourStart, iMinuteStart;
    int iHourEnd, iMinuteEnd;

    int date, thang, nam;
    int hanGio;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = (ViewGroup) inflater.inflate((R.layout.doi_feeds), container, false);
        recyclerView = rootView.findViewById(R.id.rcv_Feed);
        imgDangBai = rootView.findViewById(R.id.imgDangBai);
        tvTrangThai = rootView.findViewById(R.id.tvTrangThai);
        tvChuaCoBai = rootView.findViewById(R.id.tvChuaCoBai);

        // tvTime = rootView.findViewById(R.id.tvTime);

//        imgDangBai.setVisibility(View.GONE);
        // tvTrangThai.setVisibility(View.GONE);
        idDoi = getArguments().getString("Doi_ID");

        getUser();
        readFirebaseDangBai();

        setEvent();

        return rootView;
    }


    public void insertFirebaseDangBai(Feed feed) {
        final ProgressDialog progreDiaglogLoadding = new ProgressDialog(getContext());
        progreDiaglogLoadding.setTitle("Tải dữ liệu lên trang chủ");
        progreDiaglogLoadding.setMessage("Đang tải dữ liệu");
        progreDiaglogLoadding.show();

        String keyFeed = "";
        //  mDatabase = FirebaseDatabase.getInstance().getReference("Feeds").child(idDoi);
        mDatabase = FirebaseDatabase.getInstance().getReference("Team").child(idDoi).child("listFeeds");
        keyFeed = mDatabase.push().getKey();
        //listFeedTest.add(feeds);
        feed.setId(keyFeed);
        mDatabase.child(keyFeed).setValue(feed).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                progreDiaglogLoadding.dismiss();

            }
        });
    }

    public Feeds getFeeds() {
        Feeds feeds = new Feeds();
        String hanGio = spHanGio.getSelectedItem().toString();
        feeds.setGio(tvGioStart.getText() + "-> " + tvGioEnd.getText());
        feeds.setHanGio(hanGio.codePointAt(0) + hanGio.codePointAt(1));
        feeds.setNgay(tvNgay.getText() + "");
        feeds.setQuan(spHanGio.getSelectedItem() + "");
        feeds.setThanhPho(spThanhPho.getSelectedItem() + "");
        feeds.setThongBao(txtThongBao.getText() + "");
        feeds.setQuan(spQuan.getSelectedItem() + "");

        return feeds;
    }


    public void readFirebaseDangBai() {
//        final ProgressDialog progreDiaglog = new ProgressDialog(getActivity());
//        progreDiaglog.setTitle("Tải dữ liệu");
//        progreDiaglog.setMessage("Đang tải dữ liệu");
//        progreDiaglog.show();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Team").child(idDoi).child("listFeeds");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listFirebaseDangBai.clear();
                for (DataSnapshot dt :
                        snapshot.getChildren()) {
                    Feed feed = dt.getValue(Feed.class);


//                    Map<String, Object> data = (Map<String, Object>) dt.getValue();
                    //Toast.makeText(getContext(), dt.getKey()+"", Toast.LENGTH_SHORT).show();
//                    feeds.setQuan(data.get("quan") + "");
//                    feeds.setThongBao(data.get("thongBao") + "");
//                    feeds.setThanhPho(data.get("thanhPho") + "");
//                    feeds.setNgay(data.get("ngay") + "");
//                    feeds.setGio(data.get("gio") + "");
//                    feeds.setHanGio(1);
                    listFirebaseDangBai.add(feed);
                }
                if (listFirebaseDangBai.size() == 0) {
                    tvChuaCoBai.setVisibility(View.VISIBLE);

                } else {
                    tvChuaCoBai.setVisibility(View.GONE);
                }
                adapter_feedsDoi_4_1 = new Adapter_FeedsDoi_4_1(getContext(), listFirebaseDangBai);
                recyclerView.setAdapter(adapter_feedsDoi_4_1);
                adapter_feedsDoi_4_1.notifyDataSetChanged();
//                adapter2 = new Adapter_FeedsDoi2(getContext(), listFirebaseDangBai);
//                recyclerView.setAdapter(adapter2);
//                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setEvent() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        // adapter2 = new Adapter_FeedsDoi2(getContext(),listFeeds);
        //  adapterFeedsDoi.setSetLayout(false);
        //listFeeds.clear();
//        ThemBaiVietDaBong();
//        adapter2 = new Adapter_FeedsDoi2(getContext(), listFeeds);
////        adapterFeedsDoi = new Adapter_FeedsDoi(getContext());
////        adapterFeedsDoi.setData(listFeeds);
//        recyclerView.setAdapter(adapter2);

        imgDangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullscreenDangBai();
                // fullscreenDialog();
            }
        });

    }

    private void fullscreenDangBai() {
        final Dialog dialogFullScreen = new Dialog(getContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialogFullScreen.getWindow().setBackgroundDrawableResource(R.color.colorWhite);
        dialogFullScreen.setContentView(R.layout.dialog_doi_dangbaiviet);
//        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ImageView btnBackDangBai = dialogFullScreen.findViewById(R.id.btnBackDangBai);
        ImageView imgAvatar = dialogFullScreen.findViewById(R.id.imgAvatar);
        TextView tvNameUser = dialogFullScreen.findViewById(R.id.tvNameUser);
        final EditText txtSTT = dialogFullScreen.findViewById(R.id.txtSTT);
        Button btnDangBai = dialogFullScreen.findViewById(R.id.btnDangBai);


        if (userIMG.equals("") || userIMG == null) {

        } else {
            Picasso.get().load(userIMG).into(imgAvatar);
        }
        tvNameUser.setText(userName);


        btnDangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Feed feed = new Feed();
//                feed.setImgUser(userIMG);
//                feed.setNameUser(userName);
                feed.setUid(LoginActivity.USER_ID_CURRENT);
                feed.setSTT(txtSTT.getText() + "");
                // Toast.makeText(getContext(), txtSTT.getText()+"", Toast.LENGTH_SHORT).show();
                insertFirebaseDangBai(feed);
                thongBao();
                dialogFullScreen.dismiss();

//                DatabaseReference thongbao = FirebaseDatabase.getInstance().getReference();
//                String idThongBao = thongbao.push().getKey();
//                ThongBao thongBao = new ThongBao();
//                thongBao.setIdDoi(DoiActivity.idDoi);
//                thongBao.setNoiDung("Chủ đội "+DoiActivity.tenDoi + " có thông báo");
//
//                thongBao.setIdThongBao(idThongBao);
//                thongBao.setImg("https://firebasestorage.googleapis.com/v0/b/soccersocialnetwork-733b3.appspot.com/o/imgTeam%2FIDTeam_IMG%3A%203%2Ffdd47dd6-3846-4eed-ad68-0d5246d3f823?alt=media&token=bcd53626-ac23-4b31-b2bb-0d10925b03be");
//
//                //them 1 cái list all thành viên, dê biết các thành viên đó nhận được thông báo
//                thongbao.child("ThongBao").child(idThongBao).setValue(thongBao);

            }
        });
        btnBackDangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFullScreen.dismiss();
            }
        });
        dialogFullScreen.show();
    }

    boolean kiemtraTB = true;

    private void thongBao() {
        //        thongBao.
        final String idThongBao;
        final ThongBao thongBao = new ThongBao();

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference();

        idThongBao = databaseReference.push().getKey();

        thongBao.setIdDoi(DoiActivity.idDoi);

        thongBao.setIdThongBao(idThongBao);
        thongBao.setNoiDung("Chủ đội " + DoiActivity.tenDoi + " có thông báo");


        databaseReference2.child("Team").child(DoiActivity.idDoi).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();
                final Team team = snapshot.getValue(Team.class);
                mListener = databaseReference1.child("users").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot dt :
                                snapshot.getChildren()) {
                            Users users = dt.getValue(Users.class);

                            for (DataSnapshot dtt :
                                    dt.child("listDoi").getChildren()) {
                                if (team.getIdDoiTruong().equals(LoginActivity.USER_ID_CURRENT)) {
                                    if (dtt.getKey().equals(DoiActivity.idDoi) && !dtt.getValue().equals("Admin")) {
                                        final DatabaseReference user = FirebaseDatabase.getInstance().getReference();
                                        user.child("users").child(dt.getKey()).child("listThongBao").child(idThongBao).setValue("Đội trưởng có thông báo " + DoiActivity.tenDoi).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                thongBao.setImg(team.getHinhAnh());
                                                databaseReference.child("ThongBao").child(idThongBao).setValue(thongBao);
                                                databaseReference1.removeEventListener(mListener);
                                            }
                                        });
                                    }
                                }
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

    private void getUser() {
        mDatabase = FirebaseDatabase.getInstance().getReference("users").child(LoginActivity.USER_ID_CURRENT);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);
                userIMG = users.getUserImage();
                userName = users.getUserName();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setControl() {


    }


}
