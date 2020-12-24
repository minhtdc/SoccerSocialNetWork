package com.example.soccersocialnetwork.TranDuyHuynh;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.data_models.Users;
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
import java.security.DigestInputStream;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class edit_profile_user extends AppCompatActivity {
    Dialog dialog;
    Button btnEditProfile;
    EditText edtHoTen, edtNamSinh, edtQueQuan, edtViTri, edtCanNang, edtChieuCao, edtSlogan;
    TextView txtTenTaiKhoan;
    CircleImageView imgUserPage;
    //Firebase
    FirebaseStorage storage;
    StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_user);
        btnEditProfile = (Button) findViewById(R.id.btnEditProfile);
        txtTenTaiKhoan = findViewById(R.id.txtTenTaiKhoan);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtSlogan = findViewById(R.id.edtSologan);
        edtNamSinh = findViewById(R.id.edtNamSinh);
        edtQueQuan = findViewById(R.id.edtQueQuan);
        edtViTri = findViewById(R.id.edtViTri);
        edtCanNang = findViewById(R.id.edtCanNang);
        edtChieuCao = findViewById(R.id.edtChieuCao);
        imgUserPage = findViewById(R.id.imgUserPage);

        txtTenTaiKhoan.setText(LoginActivity.USER_NAME_CURRENT);


        //lay thong tin nguoi dung
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(String.format("/users/%s/", LoginActivity.USER_ID_CURRENT));
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);

                txtTenTaiKhoan.setText(users.getUserEmail());
                edtHoTen.setText(users.getUserName());
                edtSlogan.setText(users.getUserSologan());
                edtNamSinh.setText(users.getUserBirth());
                edtQueQuan.setText(users.getUserQueQuan());
                edtViTri.setText(users.getUserViTri());
                edtCanNang.setText(users.getUserCanNang());
                edtChieuCao.setText(users.getUserChieuCao());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        imgUserPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);
                btnEditProfile.setText("Lưu");
                edtHoTen.setEnabled(true);
            }
        });

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtHoTen.isEnabled() == false) {
                    btnEditProfile.setText("Lưu");
                    edtHoTen.setEnabled(true);
                    edtSlogan.setEnabled(true);
                    edtNamSinh.setEnabled(true);
                    edtQueQuan.setEnabled(true);
                    edtViTri.setEnabled(true);
                    edtCanNang.setEnabled(true);
                    edtChieuCao.setEnabled(true);

                } else {
                    btnEditProfile.setText("Chỉnh sửa");
                    edtHoTen.setEnabled(false);
                    edtSlogan.setEnabled(false);
                    edtNamSinh.setEnabled(false);
                    edtQueQuan.setEnabled(false);
                    edtViTri.setEnabled(false);
                    edtCanNang.setEnabled(false);
                    edtChieuCao.setEnabled(false);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference(String.format("/users/%s/", LoginActivity.USER_ID_CURRENT));
                    myRef.child("userName").setValue(edtHoTen.getText().toString());
                    myRef.child("userBirth").setValue(edtNamSinh.getText().toString());
                    myRef.child("userSologan").setValue(edtSlogan.getText().toString());
                    myRef.child("userQueQuan").setValue(edtQueQuan.getText().toString());
                    myRef.child("userViTri").setValue(edtViTri.getText().toString());
                    myRef.child("userCanNang").setValue(edtCanNang.getText().toString());
                    myRef.child("userChieuCao").setValue(edtChieuCao.getText().toString());
                    uploadImage(imgUserPage);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    final Uri selectedImage = imageReturnedIntent.getData();
                    imgUserPage.setImageURI(selectedImage);
                    //uploadImage(imgUserPage);
                }
                break;
        }
    }

    public void uploadImage(ImageView imageView) {
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        final StorageReference storageReference = storageRef.child("imgUser/" + LoginActivity.USER_ID_CURRENT + "/" + UUID.randomUUID().toString());

        UploadTask uploadTask = storageReference.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

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
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference(String.format("/users/%s/userImage", LoginActivity.USER_ID_CURRENT));
                        myRef.setValue(uriIMG);
                    }
                });
            }
        });


    }


}



