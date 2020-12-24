package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.example.soccersocialnetwork.DoanThanhTung.Models.Feeds;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.slider.Slider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

public class Fragment_Doi extends Fragment {

    DatabaseReference mDatabase;

    RecyclerView recyclerView;
    ImageView imgDangBai;
    TextView tvTrangThai,tvChuaCoBai;

    TextView tvGioStart;
    TextView tvGioEnd;
    TextView tvNgay;
    TextView txtThongBao;
    Button btnDangBai, btnBackTeam;
    Spinner spHanGio, spThanhPho, spQuan;

    ArrayList<Feeds> listFeeds = new ArrayList<>();
    ArrayList<Feeds> listFeedTest = new ArrayList<>();
    Adapter_FeedsDoi2 adapter2;

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


        readFirebaseDangBai();

        setEvent();

        return rootView;
    }

    TimePickerDialog timePickerDialogGioStart;
    TimePickerDialog timePickerDialogGioEnd;

    private void fullscreenDialog() {
        iHourStart = 0;
        iMinuteStart = 0;
        iHourEnd = 0;
        iMinuteEnd = 0;
        final Dialog dialogFullScreen = new Dialog(getContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialogFullScreen.getWindow().setBackgroundDrawableResource(R.color.colorWhite);
//        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//
        dialogFullScreen.setContentView(R.layout.dialog_doi_dangbai);
//
        //ánh xạ
        tvGioStart = dialogFullScreen.findViewById(R.id.tvGioStart);
        tvGioEnd = dialogFullScreen.findViewById(R.id.tvGioEnd);
        tvNgay = dialogFullScreen.findViewById(R.id.tvNgay);
        txtThongBao = dialogFullScreen.findViewById(R.id.txtThongBao);
        btnDangBai = dialogFullScreen.findViewById(R.id.btnDangBai);
        spHanGio = dialogFullScreen.findViewById(R.id.spHanGio);
        spThanhPho = dialogFullScreen.findViewById(R.id.spThanhPho);
        spQuan = dialogFullScreen.findViewById(R.id.spQuan);
        btnBackTeam = dialogFullScreen.findViewById(R.id.btnBackTeam);

        // btnDangBai.setVisibility(View.INVISIBLE);
        final Calendar calendar = Calendar.getInstance();
        final int ihours = calendar.get(Calendar.HOUR);
        final int iminute = calendar.get(Calendar.MINUTE);
        tvGioStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialogGioStart = new TimePickerDialog(
                        getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        iHourStart = hourOfDay;
                        iMinuteStart = minute;
                        Calendar calendar = Calendar.getInstance();
                        //
                        calendar.set(0, 0, 0, iHourStart, iMinuteStart);

                        //set selected
                        tvGioStart.setText(iHourStart + ":" + iMinuteStart);
                    }
                }, ihours, iminute, true
                );
                timePickerDialogGioStart.updateTime(iHourStart, iMinuteStart);
                timePickerDialogGioStart.show();

            }
        });


        tvGioEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                if (iHourStart == 0) {
                    builder.setTitle("Thời gian");
                    builder.setMessage("Hãy chọn thời gian bắt đầu");
                    builder.setNegativeButton("Xác nhận", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else {
                    timePickerDialogGioEnd = new TimePickerDialog(
                            getActivity(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                            iHourEnd = hourOfDay;
                            iMinuteEnd = minute;
                            Calendar calendar = Calendar.getInstance();
                            //
                            calendar.set(0, 0, 0, iHourEnd, iMinuteEnd);

                            //set selected
                            tvGioEnd.setText(iHourEnd + ":" + iMinuteEnd);
                        }
                    }, iHourStart, iMinuteStart, true
                    );
                    timePickerDialogGioEnd.updateTime(iHourEnd, iHourEnd);
                    timePickerDialogGioEnd.show();

                }


            }
        });

        tvNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int idate = calendar.get(Calendar.DATE);
                int imonth = calendar.get(Calendar.MONTH);
                int iyear = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date = dayOfMonth;
                        thang = month;
                        nam = year;
                        String day = date + "/" + thang + "/" + nam;
                        tvNgay.setText(day);
                    }
                }, iyear, imonth, idate);
                datePickerDialog.show();
            }
        });

        btnDangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Xác nhận đăng bài");
                builder.setMessage("Bạn có muốn đăng bài này lên ?");

                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        insertFirebaseDangBai(getFeeds());
                        dialogFullScreen.dismiss();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        btnBackTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogFullScreen.dismiss();
            }
        });
        //them adapter hinh anh

        dialogFullScreen.show();
    }

    public void insertFirebaseDangBai(Feeds feeds) {
        final ProgressDialog progreDiaglogLoadding = new ProgressDialog(getContext());
        progreDiaglogLoadding.setTitle("Tải dữ liệu lên trang chủ");
        progreDiaglogLoadding.setMessage("Đang tải dữ liệu");
        progreDiaglogLoadding.show();
        //  mDatabase = FirebaseDatabase.getInstance().getReference("Feeds").child(idDoi);
        mDatabase = FirebaseDatabase.getInstance().getReference("Team").child(idDoi).child("listFeeds").push();
        //listFeedTest.add(feeds);

        mDatabase.setValue(feeds).addOnSuccessListener(new OnSuccessListener<Void>() {
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
        feeds.setThongBao(txtThongBao.getText() +"");
        feeds.setQuan(spQuan.getSelectedItem() + "");

        return feeds;
    }

    ArrayList<Feeds> listFirebaseDangBai = new ArrayList<>();

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
                    Feeds feeds =new Feeds();
                    Map<String, Object> data = (Map<String, Object>) dt.getValue();
                    //Toast.makeText(getContext(), dt.getKey()+"", Toast.LENGTH_SHORT).show();
                    feeds.setQuan(data.get("quan") +"");
                    feeds.setThongBao(data.get("thongBao") +"");
                    feeds.setThanhPho(data.get("thanhPho") +"");
                    feeds.setNgay(data.get("ngay") +"");
                    feeds.setGio(data.get("gio") +"");
                    feeds.setHanGio(1);
                    listFirebaseDangBai.add(feeds);
                }
                if(listFirebaseDangBai.size() == 0){
                    tvChuaCoBai.setVisibility(View.VISIBLE);

                }else{
                    tvChuaCoBai.setVisibility(View.GONE);
                }

                adapter2 = new Adapter_FeedsDoi2(getContext(),listFirebaseDangBai);
                recyclerView.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();
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

                fullscreenDialog();
            }
        });
        tvTrangThai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), LoginActivity.USER_ID_CURRENT+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ProgressDialog progressDialogLoading(){
        final ProgressDialog progreDiaglog = new ProgressDialog(getActivity());
        progreDiaglog.setTitle("Tải dữ liệu");
        progreDiaglog.setMessage("Đang tải dữ liệu");
        progreDiaglog.show();
        return progreDiaglog;

    }

    private void setControl() {


    }






}
