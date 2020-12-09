package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.InputStream;

public class Doi_ThongTin_ChinhSua extends AppCompatActivity {
    ImageView imgChinhSuaTeam;
    Spinner spKhuVucChinhSua;
    EditText txtEmailChinhSua, txtSDTChinhSua, txtGioiThieuChinhSua, txtTieuChiChinhSua, txtSloganChinhSua, txtTenDoiChinhSua;
    Button btnChinhSua;

    DatabaseReference mDatabase;

    final int REQUEST_CODE = 999;
    Uri uri;

    String idDoi, uriIMG, tenDoi, khuVuc, email, sdt, gioiThieu, tieuChi, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi__thong_tin__chinh_sua);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Chỉnh sửa thông tin đội");
        actionBar.setDisplayHomeAsUpEnabled(true);

        setControl();
        //nhận dữ liệu
        takeData();

        //------set hình ảnh edittext...
        Picasso.get().load(uriIMG).into(imgChinhSuaTeam);
        txtTenDoiChinhSua.setText(tenDoi + "");
        //spiner
        String[] arrayKhuVucSpinner = getResources().getStringArray(R.array.listKhuVuc);
        for (int i = 0; i < arrayKhuVucSpinner.length; i++) {
            if (arrayKhuVucSpinner[i].equals(khuVuc)) {
                spKhuVucChinhSua.setSelection(i);
                break;
            }
        }
        txtEmailChinhSua.setText(email);
        txtSDTChinhSua.setText(sdt);
        txtGioiThieuChinhSua.setText(gioiThieu);
        txtTieuChiChinhSua.setText(tieuChi);
        txtSloganChinhSua.setText(slogan);

        setEvent();

    }

    private void takeData() {
        idDoi = getIntent().getExtras().getString("ID_DoiChinhSua");
        uriIMG = getIntent().getExtras().getString("IMG_DoiChinhSua");
        tenDoi = getIntent().getExtras().getString("Ten_DoiChinhSua");
        khuVuc = getIntent().getExtras().getString("KhuVuc_DoiChinhSua");
        email = getIntent().getExtras().getString("Email_DoiChinhSua");
        sdt = getIntent().getExtras().getString("SDT_DoiChinhSua");
        gioiThieu = getIntent().getExtras().getString("GioiThieu_DoiChinhSua");
        tieuChi = getIntent().getExtras().getString("TieuChi_DoiChinhSua");
        slogan = getIntent().getExtras().getString("Slogan_DoiChinhSua");
    }

    private void setEvent() {
        btnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Doi_ThongTin_ChinhSua.this);
                builder.setTitle("Chỉnh sửa thông tin đội");
                builder.setMessage("Bạn có chắc về sự thay đổi của mình?");
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateFirebase(getTeam());
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
//                AlertDialog.Builder builder = new AlertDialog.Builder(Doi_ThongTin_ChinhSua.this);
//                builder.setTitle("Thiếu thông tin");
//                builder.setMessage("Vui lòng thêm hình ảnh");
//                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//                builder.show();
            }
        });

        imgChinhSuaTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(Doi_ThongTin_ChinhSua.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        });
    }

    private Team getTeam() {
        Team team = new Team();
        team.setIdDoi(Integer.parseInt(idDoi));

        team.setTenDoi(txtTenDoiChinhSua.getText().toString());
        team.setKhuVuc(spKhuVucChinhSua.getSelectedItem().toString());
        team.setEmail(txtEmailChinhSua.getText().toString());
        team.setSdt(txtSDTChinhSua.getText().toString());
        team.setGioiThieu(txtGioiThieuChinhSua.getText().toString());
        team.setTieuChi(txtTieuChiChinhSua.getText().toString());
        team.setsLogan(txtSloganChinhSua.getText().toString());

        if (uri != null) {
            team.setHinhAnh(uri.toString());

        } else {
            team.setHinhAnh(uriIMG);
        }

        return team;
    }

    private void updateFirebase(Team team) {

        mDatabase = FirebaseDatabase.getInstance().getReference("Team").child(idDoi);
        //----------cach 1 update
        // mDatabase.updateChildren(team.toMap());

        //----------cach 2 update có hiển thị
        mDatabase.setValue(team).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Doi_ThongTin_ChinhSua.this, "update thành công", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Doi_ThongTin_ChinhSua.this, "update thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setControl() {
        imgChinhSuaTeam = findViewById(R.id.imgChinhSuaTeam);
        txtTenDoiChinhSua = findViewById(R.id.txtTenDoiChinhSua);
        spKhuVucChinhSua = findViewById(R.id.spKhuVucChinhSua);
        txtEmailChinhSua = findViewById(R.id.txtEmailChinhSua);
        txtSDTChinhSua = findViewById(R.id.txtSDTChinhSua);
        txtGioiThieuChinhSua = findViewById(R.id.txtGioiThieuChinhSua);
        txtTieuChiChinhSua = findViewById(R.id.txtTieuChiChinhSua);
        txtSloganChinhSua = findViewById(R.id.txtSloganChinhSua);
        btnChinhSua = findViewById(R.id.btnChinhSua);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE);
            } else {
                Toast.makeText(this, "Chưa cấp quyền truy cập!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgChinhSuaTeam.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgChinhSuaTeam.setImageBitmap(bitmap);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}