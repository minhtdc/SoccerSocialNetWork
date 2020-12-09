package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.soccersocialnetwork.R;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

public class Doi_ThongTinCaNhan extends AppCompatActivity {

    ImageView imgThongTinDoi;
    Spinner spKhuThongTinDoi;
    TextView tvTenDoiThongTinDoi, tvGioiThieuThongTinDoi, tvTieuChiThongTinDoi, tvSloganThongTinDoi, tvEmailThongTinDoi, tvSDTThongTinDoi;
    String idDoi, uriIMG, tenDoi, khuVuc, email, sdt, gioiThieu, tieuChi, slogan;

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi__thong_tin_ca_nhan);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin đội");
        actionBar.setDisplayHomeAsUpEnabled(true);


        setControl();
        spKhuThongTinDoi.setEnabled(false);
        //Toast.makeText(this, fireBaseTeam.getListTeam().size() +"", Toast.LENGTH_SHORT).show();
        setEvent();

    }
    private void takeData(){
        tenDoi = getIntent().getExtras().getString("Doi_TenDoi");
        uriIMG = getIntent().getExtras().getString("Doi_uriIMG");
        idDoi = getIntent().getExtras().getString("Doi_ID");
        khuVuc = getIntent().getExtras().getString("Doi_KhuVuc");
        email = getIntent().getExtras().getString("Doi_Email");
        sdt = getIntent().getExtras().getString("Doi_SDT");
        gioiThieu = getIntent().getExtras().getString("Doi_GioiThieu");
        tieuChi = getIntent().getExtras().getString("Doi_TieuChi");
        slogan = getIntent().getExtras().getString("Doi_Slogan");
    }
    private void setEvent() {
        takeData();
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

    private void dialogEditTeam(){
       Dialog dialog = new Dialog(this);
       // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setLayout(1000,500);
       // dialog.setContentView(R.layout.dialog_doi_chinhsua);
        //ánh xạ
        ImageView imgChinhSuaTeam = dialog.findViewById(R.id.imgChinhSuaTeam);
        EditText txtTenDoiChinhSua = dialog.findViewById(R.id.txtTenDoiChinhSua);
        Spinner spKhuVucChinhSua = dialog.findViewById(R.id.spKhuVucChinhSua);
        EditText txtEmailChinhSua = dialog.findViewById(R.id.txtEmailChinhSua);
        EditText txtSDTChinhSua = dialog.findViewById(R.id.txtSDTChinhSua);
        EditText txtGioiThieuChinhSua = dialog.findViewById(R.id.txtGioiThieuChinhSua);
        EditText txtTieuChiChinhSua = dialog.findViewById(R.id.txtTieuChiChinhSua);
        EditText txtSloganChinhSua = dialog.findViewById(R.id.txtSloganChinhSua);
        Button btnChinhSua = dialog.findViewById(R.id.btnChinhSua);

        dialog.show();
    }
    //add menu actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_doi_chinhsua,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //click actionbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.mnEdit:
                takeData();
                Intent intent = new Intent(Doi_ThongTinCaNhan.this,Doi_ThongTin_ChinhSua.class);
                Bundle bundle = new Bundle();
                bundle.putString("ID_DoiChinhSua",idDoi);
                bundle.putString("IMG_DoiChinhSua",uriIMG);
                bundle.putString("Ten_DoiChinhSua",tenDoi);
                bundle.putString("KhuVuc_DoiChinhSua",khuVuc);
                bundle.putString("Email_DoiChinhSua",email);
                bundle.putString("SDT_DoiChinhSua",sdt);
                bundle.putString("GioiThieu_DoiChinhSua",gioiThieu);
                bundle.putString("TieuChi_DoiChinhSua",tieuChi);
                bundle.putString("Slogan_DoiChinhSua",slogan);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
