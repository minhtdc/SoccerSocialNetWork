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

import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class infomation_dangtintimtran extends AppCompatActivity {
    LinearLayout lnBack;
    TextView txtDang;
    Button btnTimSan;
    Spinner spinner;
    public List<Team> listTeam = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
    ArrayAdapter adapter;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangtintimdoi);

        lnBack = (LinearLayout) findViewById(R.id.lnBackHome);
        txtDang = (TextView) findViewById(R.id.txtDang);
        btnTimSan = (Button) findViewById(R.id.btnTimSan);
        spinner = (Spinner) findViewById(R.id.spnChonDoi);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Team").child("listThanhVien");
        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Team team = snapshot.getValue(Team.class);
                Toast.makeText(infomation_dangtintimtran.this,team.idDoi,Toast.LENGTH_LONG).show();
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

        //lấy dữ liệu đội về list
//        DatabaseReference mDatabase;
//        mDatabase = FirebaseDatabase.getInstance().getReference("Team");
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dt :
//                        snapshot.getChildren()) {
//
//                    Team team = dt.getValue(Team.class);
//
//                    listTeam.add(team);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.w(null, "loadPost:onCancelled", error.toException());
//            }
//        });
//
//        for (Team team : listTeam) {
//            if (team.idDoiTruong.equalsIgnoreCase(LoginActivity.USER_ID_CURRENT)) {
//                data.add(team.getTenDoi());
//            }
//
//        }


        // cấu hình spinner
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        spinner.setAdapter(adapter);


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


}

