package com.example.soccersocialnetwork.Set_Football_Pitches.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.Set_Football_Pitches.flagment.SetFootballPitchesInfoFragment;
import com.example.soccersocialnetwork.Set_Football_Pitches.flagment.SetListFreeTimeFragment;
import com.example.soccersocialnetwork.Set_Football_Pitches.model.SetFootballPitches;
import com.example.soccersocialnetwork.football_field_owner.activity.AddZoneActivity;
import com.example.soccersocialnetwork.football_field_owner.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SetFootballPitchesActivity extends AppCompatActivity {

    TextView tvNgay;
    ImageView imgNgay;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    String key = "";
    public static String ngayDat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_pitches);
        setControl();
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        setIcon();
        setEvent();
    }

    private void setEvent() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tvNgay.setText(simpleDateFormat.format(calendar.getTime()));
        ngayDat = tvNgay.getText().toString();
        imgNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog();
            }
        });

    }

    public void DatePickerDialog(){
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(SetFootballPitchesActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                tvNgay.setText(simpleDateFormat.format(calendar.getTime()));
                ngayDat = tvNgay.getText().toString();
            }
        }, year, month, day);
        datePickerDialog.show();
    }
    private void setControl() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tvNgay = findViewById(R.id.tvNgay);
        imgNgay = findViewById(R.id.imgNgay);
    }
    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SetFootballPitchesInfoFragment(), "");
        adapter.addFragment(new SetListFreeTimeFragment(), "");
        viewPager.setAdapter(adapter);

    }
    private void setIcon()
    {
        tabLayout.getTabAt(0).setIcon(R.drawable.icon_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_gio_trong);
    }
}