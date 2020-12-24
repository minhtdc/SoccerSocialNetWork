package com.example.soccersocialnetwork.DoanThanhTung;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FireBaseTeam {
    private DatabaseReference mDatabase;

    private FirebaseStorage storage;
    public List<Team> listTeam = new ArrayList<>();
    private StorageReference storegaRef;

    public List<Team> getListTeam() {
        return listTeam;
    }

    public void setListTeam(List<Team> listTeam) {
        this.listTeam = listTeam;
    }


    public FireBaseTeam() {
        ;

    }

    public int UIDLatter() {
        int idCuoi = 0;
        //lay uid cuoi cung
        for (int i = 0; i < listTeam.size(); i++) {

            idCuoi = listTeam.get(i).getIdDoi() + 1;
        }
        return idCuoi;
    }

    public void insertTeam(Team team) {
        //lay uid cuoi cung rồi tăng lên
//        for (int i = 0; i < listTeam.size(); i++) {
//            team.setIdDoi(listTeam.get(i).getIdDoi() + 1);
//        }
        team.setIdDoi(UIDLatter());
        mDatabase = FirebaseDatabase.getInstance().getReference("Team").child(team.getIdDoi() + "");
        mDatabase.setValue(team);
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

    //    public ArrayList<Team> readTeam_TWO() {
//        readTeam();
//        return listTeam;
//    }
    public List<Team> readTeam_ONE() {

        mDatabase = FirebaseDatabase.getInstance().getReference("Team");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Team team = snapshot.getValue(Team.class);
                listTeam.add(team);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Team team = snapshot.getValue(Team.class);
                listTeam.add(team);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Team team = snapshot.getValue(Team.class);
                listTeam.add(team);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Team team = snapshot.getValue(Team.class);
                listTeam.add(team);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return listTeam;
    }


    public void uploadImage(Uri uri, final ProgressDialog progreDiaglog) {
        int idCuoi = 0;

        storage = FirebaseStorage.getInstance();
        storegaRef = storage.getReference();
        if (uri != null) {
            //--Dialog

            progreDiaglog.setTitle("Đang trong quá trình tải");
            progreDiaglog.show();
            StorageReference storageReference = storegaRef.child("imgTeam/" + "IDTeam_IMG: " + UIDLatter() + "/" + UUID.randomUUID().toString());

            storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri uri = taskSnapshot.getUploadSessionUri();
                    progreDiaglog.dismiss();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();

                    //displaying percentage in progress dialog
                    progreDiaglog.setMessage("Uploaded " + ((int) progress) + "%...");

                }
            });
        }
    }


    private String uriIMG;

    //up ảnh và thêm dữ liệu firebase
    public void uploadImage(ImageView imageView, final ProgressDialog progreDiaglog, final Team team) {


        storage = FirebaseStorage.getInstance();
        storegaRef = storage.getReference();

        //dialog
        progreDiaglog.setTitle("Đang trong quá trình tải");
        progreDiaglog.show();

        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        final StorageReference storageReference = storegaRef.child("imgTeam/" + "IDTeam_IMG: " + UIDLatter() + "/" + UUID.randomUUID().toString());

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
                        //Now play with downloadPhotoUrl
                        //Store data into Firebase Realtime Database
                        uriIMG = downloadPhotoUrl.toString();
                        team.setHinhAnh(uriIMG);
                        insertTeam(team);

                        progreDiaglog.dismiss();
                    }
                });
            }
        });


    }
}





