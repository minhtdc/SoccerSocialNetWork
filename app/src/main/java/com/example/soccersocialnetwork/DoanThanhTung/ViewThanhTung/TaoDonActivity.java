package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.soccersocialnetwork.R;

public class TaoDonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tao_doi__layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }
}