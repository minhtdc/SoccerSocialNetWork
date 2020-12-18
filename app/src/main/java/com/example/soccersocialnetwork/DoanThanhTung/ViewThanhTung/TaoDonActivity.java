package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.soccersocialnetwork.DoanThanhTung.FireBaseTeam;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Feeds;
import com.example.soccersocialnetwork.DoanThanhTung.Models.ListTeamUser;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaoDonActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseDatabase database;
    //img
    private StorageReference storegaRef;
    private FirebaseStorage storage;
    Button btnTaoDoi;
    EditText txtTenDoi, txtEmail, txtSDT, txtGioiThieu, txtTieuChi, txtSlogan;
    Spinner spKhuVuc;
    ImageView imgTaoDoi;


    ArrayList<Team> listTeam = new ArrayList<>();
    FireBaseTeam fireBaseTeam = new FireBaseTeam();

    Uri uri = null;
    String tenDoi;

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    final int REQUEST_CODE = 999;

    //test save show firebase

    // FireBaseHelp helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tao_doi__layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Tạo dội");

        actionBar.setDisplayHomeAsUpEnabled(true);

        setControl();
        setEvent();
    }

    private void setControl() {
        btnTaoDoi = findViewById(R.id.btnTaoDoi);

        txtTenDoi = findViewById(R.id.txtTenDoi);
        txtEmail = findViewById(R.id.txtEmail);
        txtSDT = findViewById(R.id.txtSDT);
        txtGioiThieu = findViewById(R.id.txtGioiThieu);
        txtTieuChi = findViewById(R.id.txtTieuChi);
        txtSlogan = findViewById(R.id.txtSlogan);
        spKhuVuc = findViewById(R.id.spKhuVuc);

        imgTaoDoi = findViewById(R.id.imgTaoDoi);

    }

    private void setEvent() {
        readTeam();
        final ProgressDialog progreDiaglog = new ProgressDialog(this);

        btnTaoDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progreDiaglog.setCancelable(false);
                progreDiaglog.setTitle("Đang trong quá trình tạo");
                progreDiaglog.show();
                if (uri == null) {
                    progreDiaglog.cancel();
                    AlertDialog.Builder builder = new AlertDialog.Builder(TaoDonActivity.this);
                    builder.setTitle("Thiếu thông tin");
                    builder.setMessage("Vui lòng thêm hình ảnh");
                    builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else {

                    uploadImage(imgTaoDoi, progreDiaglog, getTeam());


//                    // chuyển qua layout khác và dữ liệu
//                    Intent intent = new Intent(TaoDonActivity.this, DoiActivity.class);
//
//                    Bundle bundle = new Bundle();
//                    bundle.putString("TaoDoi_IDDoi", IDLatter() + "");
//                    bundle.putString("TaoDoi_IMGDoi", uri.toString());
//                    bundle.putString("TaoDoi_TenDoi", txtTenDoi.getText().toString());
//                    bundle.putString("TaoDoi_KhuVuc", spKhuVuc.getSelectedItem().toString());
//                    bundle.putString("TaoDoi_Email", txtEmail.getText().toString());
//                    bundle.putString("TaoDoi_SDT", txtSDT.getText().toString());
//                    bundle.putString("TaoDoi_GioiThieu", txtGioiThieu.getText().toString());
//                    bundle.putString("TaoDoi_TieuChi", txtTieuChi.getText().toString());
//                    bundle.putString("TaoDoi_Slogan", txtSlogan.getText().toString());
//                    intent.putExtras(bundle);
//                    startActivity(intent);
                    // Toast.makeText(TaoDonActivity.this, "Không có mạng!", Toast.LENGTH_SHORT).show();

                }
            }

        });


        //  deleteTeam(124);
        imgTaoDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(TaoDonActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        });
    }

    public void readTeam() {
        mDatabase = FirebaseDatabase.getInstance().getReference("Team");
        //  List<String> keys = new ArrayList<>();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dt :
                        snapshot.getChildren()) {
                    //   keys.add(dt.getKey());
                    Team team = dt.getValue(Team.class);
//                    team.setTenDoi(dt.child("tenDoi").getValue().toString());
//                    team.setEmail(dt.child("email").getValue().toString());
//                    team.setGioiThieu(dt.child("gioiThieu").getValue().toString());
//                    team.setIdDoi(Integer.parseInt(dt.child("idDoi").getValue().toString()));
//                    team.setsLogan(dt.child("sLogan").getValue().toString());
//                    team.setSdt(dt.child("sdt").getValue().toString());
//                    team.setTieuChi(dt.child("tieuChi").getValue().toString());
                    listTeam.add(team);

                    //   Toast.makeText(TaoDonActivity.this, listTeam.size() + "", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(null, "loadPost:onCancelled", error.toException());
            }
        });
    }

    private Team getTeam() {
        Team team = new Team();

        team.setIdDoi(IDLatter());
        team.setTenDoi(txtTenDoi.getText().toString());
        team.setKhuVuc(spKhuVuc.getSelectedItem().toString());
        team.setEmail(txtEmail.getText().toString());
        team.setSdt(txtSDT.getText().toString());
        team.setGioiThieu(txtGioiThieu.getText().toString());
        team.setTieuChi(txtTieuChi.getText().toString());
        team.setsLogan(txtSlogan.getText().toString());
        team.setHinhAnh(uri.toString());

      //  team.setIdDoiTruong(LoginActivity.USER_ID_CURRENT);

        return team;
    }

    ArrayList<String> listTeamUsers = new ArrayList<>();
    public void insertFirebaseUserDoiTruong() {
//        final ProgressDialog progreDiaglogLoadding = new ProgressDialog(this);
//        progreDiaglogLoadding.setTitle("Tải dữ liệu lên trang chủ");
//        progreDiaglogLoadding.setMessage("Đang tải dữ liệu");
//        progreDiaglogLoadding.show();

        //  mDatabase = FirebaseDatabase.getInstance().getReference("Feeds").child(idDoi);
        mDatabase = FirebaseDatabase.getInstance().getReference("Team").child(IDLatter()+"").child("listThanhVien");
        mDatabase.child(LoginActivity.USER_ID_CURRENT).setValue("Admin");
//        listTeamUsers.add(listTeamUser);

//        mDatabase.setValue(listTeamUsers).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                progreDiaglogLoadding.dismiss();
//            }
//        });
    }
    public int IDLatter() {
        int idCuoi = 0;
        //lay uid cuoi cung
        for (int i = 0; i < listTeam.size(); i++) {
            idCuoi = listTeam.get(i).getIdDoi() + 1;
        }
        return idCuoi;
    }

    public void insertTeam(Team team, final ProgressDialog progreDiaglog) {
        //lay uid cuoi cung rồi tăng lên
//        for (int i = 0; i < listTeam.size(); i++) {
//            team.setIdDoi(listTeam.get(i).getIdDoi() + 1);
//        }
//        team.setIdDoi(IDLatter());
        mDatabase = FirebaseDatabase.getInstance().getReference("Team").child(IDLatter() + "");
        mDatabase.setValue(team).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                progreDiaglog.dismiss();
                // chuyển qua layout khác và dữ liệu
                Intent intent = new Intent(TaoDonActivity.this, DoiActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("TaoDoi_IDDoi", IDLatter() - 1 + "");
                bundle.putString("TaoDoi_IMGDoi", uri.toString());
                bundle.putString("TaoDoi_TenDoi", txtTenDoi.getText().toString());
                bundle.putString("TaoDoi_KhuVuc", spKhuVuc.getSelectedItem().toString());
                bundle.putString("TaoDoi_Email", txtEmail.getText().toString());
                bundle.putString("TaoDoi_SDT", txtSDT.getText().toString());
                bundle.putString("TaoDoi_GioiThieu", txtGioiThieu.getText().toString());
                bundle.putString("TaoDoi_TieuChi", txtTieuChi.getText().toString());
                bundle.putString("TaoDoi_Slogan", txtSlogan.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                finish();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progreDiaglog.dismiss();
                Toast.makeText(TaoDonActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void uploadImage(ImageView imageView, final ProgressDialog progreDiaglog, final Team team) {
        storage = FirebaseStorage.getInstance();
        storegaRef = storage.getReference();

        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        final StorageReference storageReference = storegaRef.child("imgTeam/" + "IDTeam_IMG: " + IDLatter() + "/" + UUID.randomUUID().toString());

        UploadTask uploadTask = storageReference.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                progreDiaglog.setMessage("Lỗi");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri downloadPhotoUrl) {

                        String uriIMG = downloadPhotoUrl.toString();
                        team.setHinhAnh(uriIMG);
                        insertTeam(team, progreDiaglog);
                        insertFirebaseUserDoiTruong();
                        //progreDiaglog.dismiss();

                    }
                });
            }
        });


    }


    private void updataTeam(Team team) {

        mDatabase = FirebaseDatabase.getInstance().getReference("Team").child(123 + "");
        //----------cach 1 update
        // mDatabase.updateChildren(team.toMap());

        //----------cach 2 update có hiển thị
        mDatabase.setValue(team).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(TaoDonActivity.this, "update thành công", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TaoDonActivity.this, "update thất bại", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void deleteTeam(int uid) {

        mDatabase = FirebaseDatabase.getInstance().getReference("Team").child(uid + "");
        //--------cach 1
        //mDatabase.removeValue();
        //----------cach 2 có hiện thông báo
        mDatabase.setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(TaoDonActivity.this, "xóa thành công", Toast.LENGTH_SHORT).show();

            }
        });

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
//                Picasso.get()
//                        .load(uri)
//                        .into(imgTaoDoi);
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgTaoDoi.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 1 && resultCode == RESULT_OK) {
//            Picasso.with(this)
//                    .load(uri)
//                    .into(imgTaoDoi);
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgTaoDoi.setImageBitmap(bitmap);

        }
        super.onActivityResult(requestCode, resultCode, data);
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