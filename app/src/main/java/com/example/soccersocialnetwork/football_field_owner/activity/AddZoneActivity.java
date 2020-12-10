package com.example.soccersocialnetwork.football_field_owner.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.football_field_owner.database.DataBaseHelper;
import com.example.soccersocialnetwork.football_field_owner.model.City;
import com.example.soccersocialnetwork.football_field_owner.model.Zone;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class AddZoneActivity extends AppCompatActivity {
    private static final String TAG = AddZoneActivity.class.getSimpleName();
    EditText txtTenKhu, txtDiaChi;
    Spinner spnTP, spnQuan, spnGioMo, spnPhutMo, spnGioDong, spnPhutDong;
    ImageView imgAnh;
    Button btnThem;
    ArrayList<City> data_tp = new ArrayList<>();
    ArrayList<String> data_quan = new ArrayList<>();
    ArrayList<String> data_gio = new ArrayList<>();
    ArrayList<String> data_phut = new ArrayList<>();
    ArrayAdapter adapter_tp, adapter_quan, adapter_gio, adapter_phut;
    //    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final int REQUEST_CODE_IMAGE = 999;
    DatabaseReference mFirebaseDatabase;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseDatabase mFirebaseInstance;
    String userId;
    StorageReference storageRef = storage.getReference().child("anhKhu");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_zone);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        setControl();
        setEvent();
    }

    private void setEvent() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
        dataBaseHelper.createDataBase();
        dataBaseHelper.openDataBase();
        KhoiTao();
        data_tp = dataBaseHelper.getAllCity();
        setAdapterSpinner(data_tp, adapter_tp, spnTP);
        setAdapterSpinner(data_gio, adapter_gio, spnGioMo);
        setAdapterSpinner(data_gio, adapter_phut, spnGioDong);
        setAdapterSpinner(data_phut, adapter_gio, spnPhutMo);
        setAdapterSpinner(data_phut, adapter_phut, spnPhutDong);
        spnTP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
                City items = data_tp.get(i);
                data_quan = dataBaseHelper.getAllDistrict(items.getId());
                setAdapterSpinner(data_quan, adapter_quan, spnQuan);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        imgAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(AddZoneActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_IMAGE);

            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                final StorageReference mountainsRef = storageRef.child("image" + calendar.getTimeInMillis() + ".png");

                // Get the data from an ImageView as bytes

                imgAnh.setDrawingCacheEnabled(true);
                imgAnh.buildDrawingCache();
                Bitmap bitmap = ((BitmapDrawable) imgAnh.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] data = baos.toByteArray();

                UploadTask uploadTask = mountainsRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(AddZoneActivity.this, "Lỗi!!!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                        // ...
                        mountainsRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri downloadPhotoUrl) {
                                //Now play with downloadPhotoUrl
                                //Store data into Firebase Realtime Database
                                String id = mFirebaseDatabase.child("Khu").push().getKey();
                                Zone zone = getZone(downloadPhotoUrl.toString(),id);
                                mFirebaseDatabase.child("Khu").child(id).setValue(zone);
                                Toast.makeText(AddZoneActivity.this, "thanhcong", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
        });
    }

    private byte[] imageViewToByte(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_IMAGE) {
            if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            } else {
                Toast.makeText(this, "Chưa cấp quyền truy cập!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgAnh.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private Zone getZone(String url, String id) {
        Zone zone = new Zone();
        zone.setTenKhu(txtTenKhu.getText().toString());
        zone.setThanhPho(spnTP.getSelectedItem().toString());
        zone.setQuan(spnQuan.getSelectedItem().toString());
        zone.setDiaChi(txtDiaChi.getText().toString());
        zone.setGioMo(spnGioMo.getSelectedItem().toString());
        zone.setGioDong(spnPhutMo.getSelectedItem().toString());
        zone.setPhutMo(spnGioDong.getSelectedItem().toString());
        zone.setPhutDong(spnPhutDong.getSelectedItem().toString());
        zone.setAnh(url);
        zone.setPushId(id);
        return zone;
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

    private void setControl() {
        txtTenKhu = findViewById(R.id.txtTenKhu);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        spnTP = findViewById(R.id.spnTP);
        spnQuan = findViewById(R.id.spnQuan);
        spnGioMo = findViewById(R.id.spnGioMo);
        spnPhutMo = findViewById(R.id.spnPhutMo);
        spnGioDong = findViewById(R.id.spnGioDong);
        spnPhutDong = findViewById(R.id.spnPhutDong);
        imgAnh = findViewById(R.id.imgAnhKhu);
        btnThem = findViewById(R.id.btnThem);

    }

    void KhoiTao() {
        String a,b;
        for (int i = 1; i < 25; i++) {
            if(i<10){
                a = String.valueOf(i);
                b = "0" + a;
            }else {
                b = String.valueOf(i);
            }
            data_gio.add(b);
        }
        data_phut.add("00");
        data_phut.add("30");
    }
}