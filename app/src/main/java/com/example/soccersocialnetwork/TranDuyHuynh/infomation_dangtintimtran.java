package com.example.soccersocialnetwork.TranDuyHuynh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.Set_Football_Pitches.model.SetTeam;
import com.example.soccersocialnetwork.TranDuyHuynh.models.thongTinTranDau;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class infomation_dangtintimtran extends AppCompatActivity {
    LinearLayout lnBack;
    TextView txtTenDoi;
    EditText edtDiaDiem, edtThoiGian, edtNgay, edtThongTinThem;
    ImageView imgThemThanhVien;
    Button btnTimSan, btnDang;
    Spinner spinner;
    CircleImageView imageTeam;
    public List<SetTeam> listTeam = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
    ArrayAdapter adapter;
    private DatabaseReference mDatabaseReference;
    ArrayList<String> idTeam = new ArrayList<>();
    ArrayList<String> hinhAnh = new ArrayList<>();
    ArrayList<thongTinTranDau> thongTinTranDaus;
    private String idDoiDangTin;
    thongTinTranDau thongTinTranDau;
    private String anhDoi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangtintimdoi);

        lnBack = (LinearLayout) findViewById(R.id.lnBackHome);
        btnTimSan = (Button) findViewById(R.id.btnTimSan);
        spinner = (Spinner) findViewById(R.id.spnChonDoi);
        txtTenDoi = (TextView) findViewById(R.id.txtTenDoi);
        imageTeam = (CircleImageView) findViewById(R.id.ImageDoi);
        imgThemThanhVien = (ImageView) findViewById(R.id.imgMoiThanhVien);
        edtDiaDiem = (EditText) findViewById(R.id.edtDiaDiem);
        edtNgay = (EditText) findViewById(R.id.edtNgay);
        edtThoiGian = (EditText) findViewById(R.id.edtThoiGian);
        edtThongTinThem = (EditText) findViewById(R.id.edtThongTinThem);
        btnDang = (Button) findViewById(R.id.btnDang);
        loadData();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        lnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnTimSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(infomation_dangtintimtran.this, all_stadiums.class);
                startActivity(intent);
            }
        });

        // hiện thị danh sách các thành viên có trong đội khi click vào img mời thành viên
        imgThemThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnDang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taoThongTinTranDau();
//               Intent intent = new Intent(infomation_dangtintimtran.this, home_flagment.class);
//               intent.putExtra("thongTinTranDau",thongTinTranDau);
//               startActivity(intent);
            }
        });

    }


    public void loadData() {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("users").child(LoginActivity.USER_ID_CURRENT).child("listDoi");
        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull final DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue().equals("Admin")) {
                    final String idTea = snapshot.getKey();

                    mDatabaseReference = FirebaseDatabase.getInstance().getReference(String.format("Team/%s/tenDoi", idTea));
                    mDatabaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull final DataSnapshot snapshots) {
                            SetTeam setTeam = new SetTeam();
                            setTeam.setIdDoi(idTea);
                            setTeam.setTenDoi(snapshots.getValue().toString());
                            listTeam.add(setTeam);

                            adapter = new ArrayAdapter(infomation_dangtintimtran.this, android.R.layout.simple_list_item_1, listTeam);
                            spinner.setAdapter(adapter);
                            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    txtTenDoi.setText(adapterView.getItemAtPosition(i).toString());
                                    idDoiDangTin = listTeam.get(i).getIdDoi();
                                    mDatabaseReference = FirebaseDatabase.getInstance().getReference(String.format("Team/%s/hinhAnh", listTeam.get(i).getIdDoi()));
                                    mDatabaseReference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            Picasso.get().load(snapshot.getValue().toString()).into(imageTeam);
                                            anhDoi = snapshot.getValue().toString();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                            Picasso.get().load(snapshot.getValue().toString()).into(imageTeam);
                                        }
                                    });
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                    txtTenDoi.setText(adapterView.getItemAtPosition(0).toString());
                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void taoThongTinTranDau() {
         thongTinTranDau = new thongTinTranDau();
        thongTinTranDau.setDiaDiem(edtDiaDiem.getText().toString());
        thongTinTranDau.setNgay(edtNgay.getText().toString());
        thongTinTranDau.setThoiGian(edtThoiGian.getText().toString());
        thongTinTranDau.setThongTinThem(edtThongTinThem.getText().toString());
        thongTinTranDau.setIdDoiDangTin(idDoiDangTin);
        thongTinTranDau.setTenDoi(txtTenDoi.getText().toString());
        thongTinTranDau.setAnhDoi(anhDoi);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        String keyID = FirebaseDatabase.getInstance().getReference().push().getKey();
        thongTinTranDau.setIdTranDau(keyID);
        mDatabaseReference.child("ThongTinTranDau").child(keyID).setValue(thongTinTranDau);
    }
}

