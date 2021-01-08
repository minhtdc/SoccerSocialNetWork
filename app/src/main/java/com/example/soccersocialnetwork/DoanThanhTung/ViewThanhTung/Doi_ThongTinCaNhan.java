package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.DoanThanhTung.Models.ThongBao;
import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Doi_ThongTinCaNhan extends AppCompatActivity {

    ImageView imgThongTinDoi;
    MenuItem mnEdit;
    Button btnThamGiaDoi;
    TextView tvTenDoiThongTinDoi, tvGioiThieuThongTinDoi, tvTieuChiThongTinDoi, tvSloganThongTinDoi, tvEmailThongTinDoi, tvSDTThongTinDoi, tvKhuThongTinDoi;
    String idDoi, uriIMG, khuVuc, email, sdt, gioiThieu, tieuChi, slogan;
    String tenDoi;
    ArrayList<String> listTeamUsers = new ArrayList<>();

    String adminOrUser = "";
    private DatabaseReference mDatabase;
    private ValueEventListener mListener;

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
        readUser();

        readTeamChoDuyet();
        readUserTeam();

        setEvent();

    }


    private void takeData() {

        idDoi = getIntent().getExtras().getString("Doi_ID");
//       tenDoi = getIntent().getExtras().getString("Doi_TenDoi");
//        uriIMG = getIntent().getExtras().getString("Doi_uriIMG");
//        khuVuc = getIntent().getExtras().getString("Doi_KhuVuc");
//        email = getIntent().getExtras().getString("Doi_Email");
//        sdt = getIntent().getExtras().getString("Doi_SDT");
//        gioiThieu = getIntent().getExtras().getString("Doi_GioiThieu");
//        tieuChi = getIntent().getExtras().getString("Doi_TieuChi");
//        slogan = getIntent().getExtras().getString("Doi_Slogan");
    }

    private void setEvent() {
        imgThongTinDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Doi_ThongTinCaNhan.this, adminOrUser + "", Toast.LENGTH_SHORT).show();
            }
        });


        btnThamGiaDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choDuyet();
            }
        });
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

        btnThamGiaDoi = findViewById(R.id.btnThamGiaDoi);
    }


    private void choDuyet(){
        final Calendar calendar = Calendar.getInstance();
        int idate = calendar.get(Calendar.DATE);
        int imonth = calendar.get(Calendar.MONTH);
        int iyear = calendar.get(Calendar.YEAR);
        int ihours = calendar.get(Calendar.HOUR);
        int iminute = calendar.get(Calendar.MINUTE);
        int isecond = calendar.get(Calendar.SECOND);
        mDatabase = FirebaseDatabase.getInstance().getReference("Team").child(idDoi).child("ChoDuyet");
        mDatabase.child(LoginActivity.USER_ID_CURRENT).setValue(idate + "/" + imonth +"/" + iyear + " Time-> " +ihours +":" + iminute +":" +isecond).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                btnThamGiaDoi.setEnabled(false);
                btnThamGiaDoi.setBackgroundColor(Color.GREEN);
                btnThamGiaDoi.setText("Chờ duyệt");

                //        thongBao.
                String idThongBao;
                ThongBao thongBao = new ThongBao();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                idThongBao = databaseReference.push().getKey();

                thongBao.setIdDoi(idDoi);
                thongBao.setImg(uriIMG);
                thongBao.setIdThongBao(idThongBao);
                thongBao.setNoiDung(LoginActivity.USER_NAME_CURRENT +" xin vào đội "+ tenDoi);

                databaseReference.child("ThongBao").child(idThongBao).setValue(thongBao);
            }
        });

    }

    private void readTeamChoDuyet(){
        mDatabase = FirebaseDatabase.getInstance().getReference("Team").child(idDoi).child("ChoDuyet");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dt:
                snapshot.getChildren()){
                    if(dt.getKey().equals(LoginActivity.USER_ID_CURRENT)){
                        btnThamGiaDoi.setEnabled(false);
                        btnThamGiaDoi.setBackgroundColor(Color.GREEN);
                        btnThamGiaDoi.setText("Chờ duyệt");
                    }
                    //Toast.makeText(Doi_ThongTinCaNhan.this,dt.getKey()+ "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
                    if (idDoi.equals(dt.getKey())) {
                        Team team = dt.getValue(Team.class);
                        // listTeam.add(team);
                        tenDoi = team.getTenDoi();
                        uriIMG = team.getHinhAnh();
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

    public void readUserTeam() {
        btnThamGiaDoi.setVisibility(View.VISIBLE);
        mDatabase = FirebaseDatabase.getInstance().getReference("users").child(LoginActivity.USER_ID_CURRENT).child("listDoi");
        mListener = mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dt :
                        snapshot.getChildren()) {
                    if (dt.getKey().equals(idDoi)) {
                        btnThamGiaDoi.setVisibility(View.GONE);
                        mDatabase.removeEventListener(mListener);
                        break;
                    } else {
                        btnThamGiaDoi.setVisibility(View.VISIBLE);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void readUser() {
        adminOrUser = "khongnull";
        mDatabase = FirebaseDatabase.getInstance().getReference("users").child(LoginActivity.USER_ID_CURRENT).child("listDoi").child(idDoi);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adminOrUser = snapshot.getValue(String.class);

                // Toast.makeText(Doi_ThongTinCaNhan.this,snapshot.getValue()+ "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //add menu actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_doi_chinhsua, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mnEdit = menu.findItem(R.id.mnEdit);
        mnEdit.setVisible(false);
        if (adminOrUser == null) {
            adminOrUser = "null";
        }
        if (adminOrUser.equals("Admin")) {
            mnEdit.setVisible(true);

        }

        return true;
    }

    //click actionbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
            case R.id.mnEdit:
                takeData();
                Intent intent = new Intent(Doi_ThongTinCaNhan.this, Doi_ThongTin_ChinhSua.class);
                Bundle bundle = new Bundle();
                bundle.putString("ID_DoiChinhSua", idDoi);
                bundle.putString("IMG_DoiChinhSua", uriIMG);

                bundle.putString("Ten_DoiChinhSua", tvTenDoiThongTinDoi.getText().toString());

                bundle.putString("KhuVuc_DoiChinhSua", tvKhuThongTinDoi.getText().toString());
                bundle.putString("Email_DoiChinhSua", tvEmailThongTinDoi.getText().toString());
                bundle.putString("SDT_DoiChinhSua", tvSDTThongTinDoi.getText().toString());
                bundle.putString("GioiThieu_DoiChinhSua", tvGioiThieuThongTinDoi.getText().toString());
                bundle.putString("TieuChi_DoiChinhSua", tvTieuChiThongTinDoi.getText().toString());
                bundle.putString("Slogan_DoiChinhSua", tvSloganThongTinDoi.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
