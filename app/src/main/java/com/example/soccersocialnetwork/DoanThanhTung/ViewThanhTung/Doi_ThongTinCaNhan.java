package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.soccersocialnetwork.R;
import com.squareup.picasso.Picasso;

public class Doi_ThongTinCaNhan extends AppCompatActivity {

    ImageView imgThongTinDoi;
    Spinner spKhuThongTinDoi;
    TextView tvTenDoiThongTinDoi, tvGioiThieuThongTinDoi, tvTieuChiThongTinDoi, tvSloganThongTinDoi, tvEmailThongTinDoi, tvSDTThongTinDoi;
    String idDoi, uriIMG, tenDoi, khuVuc, email, sdt, gioiThieu, tieuChi, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi__thong_tin_ca_nhan);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin đội");
        actionBar.setDisplayHomeAsUpEnabled(true);

        tenDoi = getIntent().getExtras().getString("Doi_TenDoi");
        uriIMG = getIntent().getExtras().getString("Doi_uriIMG");
        idDoi = getIntent().getExtras().getString("Doi_ID");
        khuVuc = getIntent().getExtras().getString("Doi_KhuVuc");
        email = getIntent().getExtras().getString("Doi_Email");
        sdt = getIntent().getExtras().getString("Doi_SDT");
        gioiThieu = getIntent().getExtras().getString("Doi_GioiThieu");
        tieuChi = getIntent().getExtras().getString("Doi_TieuChi");
        slogan = getIntent().getExtras().getString("Doi_Slogan");
        setControl();
        spKhuThongTinDoi.setEnabled(false);
        //Toast.makeText(this, fireBaseTeam.getListTeam().size() +"", Toast.LENGTH_SHORT).show();
        setEvent();

    }

    private void setEvent() {
        Picasso.get().load(uriIMG).into(imgThongTinDoi);

       String[] arrayKhuVucSpinner = getResources().getStringArray(R.array.listKhuVuc);


        for (int i = 0; i < arrayKhuVucSpinner.length; i++) {
            if (arrayKhuVucSpinner[i].equals(khuVuc) ) {
                spKhuThongTinDoi.setSelection(i);
                break;
            }
       }

        tvTenDoiThongTinDoi.setText(tenDoi);
        tvGioiThieuThongTinDoi.setText(gioiThieu);
        tvTieuChiThongTinDoi.setText(tieuChi);
        tvSloganThongTinDoi.setText(slogan);
        tvEmailThongTinDoi.setText(email);
        tvSDTThongTinDoi.setText(sdt);
    }

    private void setControl() {
        imgThongTinDoi = findViewById(R.id.imgThongTinDoi);
        spKhuThongTinDoi = findViewById(R.id.spKhuThongTinDoi);
        tvTenDoiThongTinDoi = findViewById(R.id.tvTenDoiThongTinDoi);
        tvGioiThieuThongTinDoi = findViewById(R.id.tvGioiThieuThongTinDoi);
        tvTieuChiThongTinDoi = findViewById(R.id.tvTieuChiThongTinDoi);
        tvSloganThongTinDoi = findViewById(R.id.tvSloganThongTinDoi);
        tvEmailThongTinDoi = findViewById(R.id.tvEmailThongTinDoi);
        tvSDTThongTinDoi = findViewById(R.id.tvSDTThongTinDoi);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

}
