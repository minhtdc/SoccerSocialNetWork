package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
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
    TextView tvTrangThai;

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

        // tvTime = rootView.findViewById(R.id.tvTime);

//        imgDangBai.setVisibility(View.GONE);
//        tvTrangThai.setVisibility(View.GONE);
        readFirebaseDangBai();
        idDoi = getArguments().getString("Doi_ID");
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


                        timePickerDialogGioStart.dismiss();
                        dialog.dismiss();
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
        mDatabase = FirebaseDatabase.getInstance().getReference("Feeds").child(idDoi);

        listFeedTest.add(feeds);

        mDatabase.setValue(listFeedTest);
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
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Feeds").child("10");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
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
        listFeeds.clear();
//        ThemBaiVietDaBong();
//        adapter2 = new Adapter_FeedsDoi2(getContext(), listFeeds);
////        adapterFeedsDoi = new Adapter_FeedsDoi(getContext());
////        adapterFeedsDoi.setData(listFeeds);
//        recyclerView.setAdapter(adapter2);
        tvTrangThai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFirebaseDangBai();
            }
        });
        imgDangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fullscreenDialog();
            }
        });


//

    }


    private void setControl() {


    }

    private void ThemBaiVietDaBong() {
        Feeds model_feedsDoi_view = new Feeds(24, "Hà Nội", "Cái Làn", "14/11/2020", "13h - 16h", "Ae mình zui vẽ");
        Feeds model_feedsDoi_view1 = new Feeds(72, "xxx", "Cái Làn", "14/11/2020", "13h - 16h", "Ae mình zui vẽ");
        Feeds model_feedsDoi_view2 = new Feeds(123, "x", "Cái Làn", "14/11/2020", "13h - 16h", "Ae mình zui vẽ");
        Feeds model_feedsDoi_view3 = new Feeds(234, "Hà Nội", "Cái Làn", "14/11/2020", "13h - 16h", "Ae mình zui vẽ");
        Feeds model_feedsDoi_view4 = new Feeds(234, "Hà Nội", "Cái Làn", "14/11/2020", "13h - 16h", "Ae mình zui vẽ");
        listFeeds.add(model_feedsDoi_view);
        listFeeds.add(model_feedsDoi_view1);
        listFeeds.add(model_feedsDoi_view2);
        listFeeds.add(model_feedsDoi_view3);
        listFeeds.add(model_feedsDoi_view4);
//       adapter2.notifyDataSetChanged();
    }


}
