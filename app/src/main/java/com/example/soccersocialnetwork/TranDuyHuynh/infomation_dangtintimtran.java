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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.Set_Football_Pitches.flagment.SetListFreeTimeFragment;
import com.example.soccersocialnetwork.Set_Football_Pitches.model.SetTeam;
import com.example.soccersocialnetwork.TranDuyHuynh.adapter.Adapter_TimSan;
import com.example.soccersocialnetwork.TranDuyHuynh.adapter.adapter_MoiThanhVien;
import com.example.soccersocialnetwork.TranDuyHuynh.models.information_TimSan;
import com.example.soccersocialnetwork.TranDuyHuynh.fragments.home_flagment;
import com.example.soccersocialnetwork.TranDuyHuynh.models.thongTinTranDau;
import com.example.soccersocialnetwork.activity_moithanhvien;
import com.example.soccersocialnetwork.data_models.Users;
import com.example.soccersocialnetwork.football_field_owner.database.DataBaseHelper;
import com.example.soccersocialnetwork.football_field_owner.model.City;
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
    EditText edtThoiGian, edtNgay, edtThongTinThem;
    ImageView imgThemThanhVien;
    Button btnTimSan, btnDang;
    ListView lvDanhSach;
    Spinner spinner, dangTinSpnTinh, dangTinSpnQuan;
    CircleImageView imageTeam;
    public List<SetTeam> listTeam = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
    ArrayAdapter adapter;
    private DatabaseReference mDatabaseReference;
    ArrayList<String> idTeam = new ArrayList<>();
    ArrayList<String> hinhAnh = new ArrayList<>();
    ArrayList<thongTinTranDau> thongTinTranDaus;
    public static String idDoiDangTin;
    public static String tenDoi;
    public static String timSan = "";
    public static String dongActivity = "";

    thongTinTranDau thongTinTranDau;
    private String anhDoi;
    ListView listViewThanhVienThamGia;
    ArrayList<Users> DanhThanhVienThamGia = adapter_MoiThanhVien.thanhVienThamgia;
    ArrayList<information_TimSan> data_timSan = SetListFreeTimeFragment.data_timSan;

    ArrayAdapter arrayAdapter;
    ArrayAdapter adapter_TimSan;
    ArrayList<City> data_tp = new ArrayList<>();
    ArrayList<String> data_quan = new ArrayList<>();
    ArrayAdapter adapter_tp, adapter_quan;

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
        edtNgay = (EditText) findViewById(R.id.edtNgay);
        edtThoiGian = (EditText) findViewById(R.id.edtThoiGian);
        edtThongTinThem = (EditText) findViewById(R.id.edtThongTinThem);
        btnDang = (Button) findViewById(R.id.btnDang);
        listViewThanhVienThamGia = (ListView) findViewById(R.id.lstThanhVienDuocMoi);
        lvDanhSach = findViewById(R.id.lvSan);
        dangTinSpnTinh = findViewById(R.id.dangTinSpnTinh);
        dangTinSpnQuan = findViewById(R.id.dangTinSpnQuan);

        arrayAdapter = new ArrayAdapter<Users>(this, android.R.layout.simple_list_item_1,DanhThanhVienThamGia);
        loadData();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
        dataBaseHelper.createDataBase();
        dataBaseHelper.openDataBase();

        data_tp = dataBaseHelper.getAllCity();
        setAdapterSpinner(data_tp, adapter_tp, dangTinSpnTinh);
        dangTinSpnTinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
                City items = data_tp.get(i);
                data_quan = dataBaseHelper.getAllDistrict(items.getId());
                setAdapterSpinner(data_quan, adapter_quan, dangTinSpnQuan);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                timSan = "abc";
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        // hiện thị danh sách các thành viên có trong đội khi click vào img mời thành viên
        imgThemThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter_MoiThanhVien.thanhVienThamgia.clear();
                Intent intent = new Intent(infomation_dangtintimtran.this, activity_moithanhvien.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
//              Toast.makeText(infomation_dangtintimtran.this,idDoiDangTin.toString(),Toast.LENGTH_LONG).show();
            }
        });

        btnDang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtThoiGian.getText().toString().equals("") || edtNgay.getText().toString().equals("") || spinner.getChildAt(0) == null){
                    Toast.makeText(infomation_dangtintimtran.this, "Không thể đăng bài, vui lòng kiểm tra thông tin!", Toast.LENGTH_SHORT).show();
                }
                else {
                    taoThongTinTranDau();
                    Intent intent = new Intent(infomation_dangtintimtran.this, home_layout.class);
                    startActivity(intent);
                    finish();
                }
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
                                    arrayAdapter.clear();
                                    txtTenDoi.setText(adapterView.getItemAtPosition(i).toString());
                                    tenDoi = txtTenDoi.getText().toString();
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
                if(snapshot.getValue() == null){
                    btnDang.setEnabled(false);
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

    private void setAdapterSpinner(ArrayList data, ArrayAdapter adapter, Spinner spinner) {
        if (adapter == null) {
            adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data);
            spinner.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            adapter.notifyDataSetChanged();
            spinner.setSelection(adapter.getCount() - 1);
        }
    }

    public void taoThongTinTranDau() {
        thongTinTranDau = new thongTinTranDau();
        thongTinTranDau.setDiaDiem(dangTinSpnTinh.getSelectedItem().toString() + ", " + dangTinSpnQuan.getSelectedItem().toString());
        thongTinTranDau.setNgay(edtNgay.getText().toString());
        thongTinTranDau.setThoiGian(edtThoiGian.getText().toString());
        thongTinTranDau.setThongTinThem(edtThongTinThem.getText().toString());
        thongTinTranDau.setIdDoiDangTin(idDoiDangTin);
        thongTinTranDau.setTenDoi(txtTenDoi.getText().toString());
        thongTinTranDau.setAnhDoi(anhDoi);
        thongTinTranDau.setThanhVienThamGia(DanhThanhVienThamGia);
        //kiểm tra có sân hay không
        if(lvDanhSach.getChildAt(0) == null)
        {

        }
        else {
            String san = SetListFreeTimeFragment.data_timSan.get(0).getTenSan() + "( " + SetListFreeTimeFragment.data_timSan.get(0).getGioDat() + " )";
            thongTinTranDau.setSan(san);
        }


        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        String keyID = FirebaseDatabase.getInstance().getReference().push().getKey();
        thongTinTranDau.setIdTranDau(keyID);
        mDatabaseReference.child("ThongTinTranDau").child(keyID).setValue(thongTinTranDau);
        mDatabaseReference.child("ThongTinTranDau").child(keyID).child("idNguoiDangTin").setValue(LoginActivity.USER_ID_CURRENT);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            listViewThanhVienThamGia.setAdapter(arrayAdapter);
        }catch (Exception e){

        }
        try {
            adapter_TimSan = new Adapter_TimSan(this, R.layout.item_listview_timsan, SetListFreeTimeFragment.data_timSan);
            adapter_TimSan.clear();
            lvDanhSach.setAdapter(adapter_TimSan);
            adapter_TimSan.notifyDataSetChanged();
        }catch (Exception e){

        }

    }
}

