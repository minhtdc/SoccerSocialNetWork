package com.example.soccersocialnetwork.TranDuyHuynh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
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
    TextView txtDang, txtTenDoi;
    Button btnTimSan;
    Spinner spinner;
    CircleImageView imageTeam;
    public List<String> listTeam = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
    ArrayAdapter adapter;
    private DatabaseReference mDatabaseReference;
    ArrayList<String> idTeam = new ArrayList<>();
    ArrayList<String> hinhAnh = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangtintimdoi);

        lnBack = (LinearLayout) findViewById(R.id.lnBackHome);
        txtDang = (TextView) findViewById(R.id.txtDang);
        btnTimSan = (Button) findViewById(R.id.btnTimSan);
        spinner = (Spinner) findViewById(R.id.spnChonDoi);
        txtTenDoi = (TextView) findViewById(R.id.txtTenDoi);
        imageTeam = (CircleImageView) findViewById(R.id.ImageDoi);

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
        txtDang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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
    }
//        txtDang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(infomation_dangtintimtran.this, home_flagment.class);
//                startActivity(intent);
//            }
//        });;

    public void loadData() {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("users").child(LoginActivity.USER_ID_CURRENT).child("listDoi");
        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue().equals("Admin")) {
                     final String idTea = snapshot.getKey();

                    mDatabaseReference = FirebaseDatabase.getInstance().getReference(String.format("Team/%s/tenDoi", idTea));
                    mDatabaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull final DataSnapshot snapshot) {
                            listTeam.add(snapshot.getValue().toString());

                            adapter = new ArrayAdapter<String>(infomation_dangtintimtran.this, android.R.layout.simple_list_item_1, listTeam);
                            spinner.setAdapter(adapter);
                            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    txtTenDoi.setText(adapterView.getItemAtPosition(i).toString());
                                    
                                    mDatabaseReference = FirebaseDatabase.getInstance().getReference(String.format("Team/%s/hinhAnh",idTea));
                                    mDatabaseReference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            Toast.makeText(infomation_dangtintimtran.this,idTea,Toast.LENGTH_LONG).show();
                                            Picasso.get().load(snapshot.getValue().toString()).into(imageTeam);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            Toast.makeText(infomation_dangtintimtran.this,idTea.toString(),Toast.LENGTH_LONG).show();

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

}

