package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Doi_ThongTinCaNhan extends AppCompatActivity {

    ImageView imgThongTinDoi;

    TextView tvTenDoiThongTinDoi, tvGioiThieuThongTinDoi, tvTieuChiThongTinDoi, tvSloganThongTinDoi, tvEmailThongTinDoi, tvSDTThongTinDoi,tvKhuThongTinDoi;
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
       // spKhuThongTinDoi.setEnabled(false);
        //Toast.makeText(this, fireBaseTeam.getListTeam().size() +"", Toast.LENGTH_SHORT).show();
      //  setEvent();
        takeData();
        readTeam();


        imgThongTinDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Doi_ThongTinCaNhan.this, imageViewToByte(imgThongTinDoi) +"", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private byte[] imageViewToByte(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        OutputStream outputStream = new ByteArrayOutputStream();
        return bytes;

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
//        takeData();
//        Picasso.get().load(uriIMG).into(imgThongTinDoi);
//
//       String[] arrayKhuVucSpinner = getResources().getStringArray(R.array.listKhuVuc);
//
//
////        for (int i = 0; i < arrayKhuVucSpinner.length; i++) {
////            if (arrayKhuVucSpinner[i].equals(khuVuc) ) {
////                spKhuThongTinDoi.setSelection(i);
////                break;
////            }
////       }
//
//        tvKhuThongTinDoi.setText(khuVuc);
//        tvTenDoiThongTinDoi.setText(tenDoi);
//        tvGioiThieuThongTinDoi.setText(gioiThieu);
//        tvTieuChiThongTinDoi.setText(tieuChi);
//        tvSloganThongTinDoi.setText(slogan);
//        tvEmailThongTinDoi.setText(email);
//        tvSDTThongTinDoi.setText(sdt);
    }

    private void setControl() {
        imgThongTinDoi = findViewById(R.id.imgThongTinDoi);
        tvKhuThongTinDoi = findViewById(R.id.tvKhuThongTinDoi);
        tvTenDoiThongTinDoi = findViewById(R.id.tvTenDoiThongTinDoi);
        tvGioiThieuThongTinDoi = findViewById(R.id.tvGioiThieuThongTinDoi);
        tvTieuChiThongTinDoi = findViewById(R.id.tvTieuChiThongTinDoi);
        tvSloganThongTinDoi = findViewById(R.id.tvSloganThongTinDoi);
        tvEmailThongTinDoi = findViewById(R.id.tvEmailThongTinDoi);
        tvSDTThongTinDoi = findViewById(R.id.tvSDTThongTinDoi);
    }

    public void readTeam() {
        mDatabase = FirebaseDatabase.getInstance().getReference("Team");
        final List<String> keys = new ArrayList<>();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dt :
                        snapshot.getChildren()) {
                    keys.add(dt.getKey());
                    if(idDoi.equals(dt.getKey())){
                        Team team = dt.getValue(Team.class);
                        // listTeam.add(team);
                        Picasso.get().load(team.getHinhAnh()).into(imgThongTinDoi);
                        tvTenDoiThongTinDoi.setText(team.getTenDoi());
                        tvKhuThongTinDoi.setText(team.getKhuVuc());
                        tvEmailThongTinDoi.setText(team.getEmail());
                        tvSDTThongTinDoi.setText(team.getSdt());
                        tvGioiThieuThongTinDoi.setText(team.getGioiThieu());
                        tvTieuChiThongTinDoi.setText(team.getTieuChi());
                        tvSloganThongTinDoi.setText(team.getsLogan());
                        break;
                    }


                    // Toast.makeText(DoiActivity.this, listTeam.size() + "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(null, "loadPost:onCancelled", error.toException());
            }
        });

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

                bundle.putString("Ten_DoiChinhSua",tvTenDoiThongTinDoi.getText().toString());

                bundle.putString("KhuVuc_DoiChinhSua",tvKhuThongTinDoi.getText().toString());
                bundle.putString("Email_DoiChinhSua",tvEmailThongTinDoi.getText().toString());
                bundle.putString("SDT_DoiChinhSua",tvSDTThongTinDoi.getText().toString());
                bundle.putString("GioiThieu_DoiChinhSua",tvGioiThieuThongTinDoi.getText().toString());
                bundle.putString("TieuChi_DoiChinhSua",tvTieuChiThongTinDoi.getText().toString());
                bundle.putString("Slogan_DoiChinhSua",tvSloganThongTinDoi.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
