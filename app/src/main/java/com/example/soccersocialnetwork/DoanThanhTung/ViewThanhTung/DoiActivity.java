package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.soccersocialnetwork.DoanThanhTung.Adapter.ViewPagerAdapter;
import com.example.soccersocialnetwork.DoanThanhTung.DataBase.DBTeam;
import com.example.soccersocialnetwork.DoanThanhTung.FireBaseTeam;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DoiActivity extends AppCompatActivity {

    //tao thanh nang, luot qua luot lai
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DatabaseReference mDatabase;


    ArrayList<Team> listTeam = new ArrayList<>();

    ImageView imgDoi;
    TextView tvTenDoi;
    String idDoi,uriIMG,tenDoi,khuVuc,email,sdt,gioiThieu,tieuChi,slogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Nhóm");
        actionBar.setDisplayHomeAsUpEnabled(true);

        setControl();

        tabLayout.setupWithViewPager(viewPager);
        //chuyền dữ liệu vào string --- TEAM
        tenDoi = getIntent().getExtras().getString("TaoDoi_TenDoi");
        uriIMG = getIntent().getExtras().getString("TaoDoi_IMGDoi");
        idDoi = getIntent().getExtras().getString("TaoDoi_IDDoi");
        khuVuc = getIntent().getExtras().getString("TaoDoi_KhuVuc");
        email = getIntent().getExtras().getString("TaoDoi_Email");
        sdt = getIntent().getExtras().getString("TaoDoi_SDT");
        gioiThieu = getIntent().getExtras().getString("TaoDoi_GioiThieu");
        tieuChi = getIntent().getExtras().getString("TaoDoi_TieuChi");
        slogan = getIntent().getExtras().getString("TaoDoi_Slogan");

//        //chuyển đổi ảnh bằng uri
//        Picasso.get().load(uriIMG).fit().into(imgDoi);
//        tvTenDoi.setText(tenDoi);

        //chuyen trang qua lại fragment và icon
        setupViewPager();
        setIcon();


        //Toast.makeText(this, fireBaseTeam.getListTeam().size() +"", Toast.LENGTH_SHORT).show();
        setEvent();
    }

//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//
//    }



    private void setEvent() {
        DBTeam dbTeam = new DBTeam(this);
        readTeam();



        imgDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(DoiActivity.this, idDoi+"", Toast.LENGTH_SHORT).show();
            }
        });

//        llThongTinDoi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(DoiActivity.this,Doi_ThongTinCaNhan.class);
//                startActivity(intent);
//            }
//        });

        //vào firebase rồi add danh sách firebase vào sqlite
//        mDatabase = FirebaseDatabase.getInstance().getReference("Team");
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dt:
//                        snapshot.getChildren()) {
//                    Team team = dt.getValue(Team.class);
//                    listTeam.add(team);
//                  //  dbTeam.addTeam(team);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }
//    private ArrayList<Team> getData(){
//        ArrayList<Team> listTeam = new ArrayList<>();
//        ArrayList<Team> a = new ArrayList<>();
//        fireBaseTeam.readTeam( a );
//        listTeam = fireBaseTeam.getListTeam();
//        return listTeam;
//    }


    public void readTeam() {
        mDatabase = FirebaseDatabase.getInstance().getReference("Team");
        final List<String> keys = new ArrayList<>();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dt :
                        snapshot.getChildren()) {
                    keys.add(dt.getKey());
                    if(idDoi.equals(dt.getKey())){
                        Team team = dt.getValue(Team.class);
                       // listTeam.add(team);
                        Picasso.get().load(team.getHinhAnh()).into(imgDoi);
                        tvTenDoi.setText(team.getTenDoi());
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
        imgDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(DoiActivity.this, keys.size() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Fragment_Doi fragment_doi = new Fragment_Doi();
        Bundle bundle_Doi = new Bundle();
        bundle_Doi.putString("Doi_ID",idDoi);
        fragment_doi.setArguments(bundle_Doi);
        adapter.addFragment(fragment_doi, "");



        adapter.addFragment(new Fragment_Doi_2(), "");

        //chuyền dữ liệu qua fragment
        Fragment_Doi_Menu fragment_doi_menu = new Fragment_Doi_Menu();


        Bundle bundle_menu = new Bundle();
        bundle_menu.putString("Doi_ID",idDoi);
        bundle_menu.putString("Doi_uriIMG",uriIMG);
        bundle_menu.putString("Doi_TenDoi",tenDoi);
        bundle_menu.putString("Doi_KhuVuc",khuVuc);
        bundle_menu.putString("Doi_Email",email);
        bundle_menu.putString("Doi_SDT",sdt);
        bundle_menu.putString("Doi_GioiThieu",gioiThieu);
        bundle_menu.putString("Doi_TieuChi",tieuChi);
        bundle_menu.putString("Doi_Slogan",slogan);

        fragment_doi_menu.setArguments(bundle_menu);
//
        adapter.addFragment(fragment_doi_menu, "");

        viewPager.setAdapter(adapter);

    }

    private void setIcon() {
        tabLayout.getTabAt(0).setIcon(R.drawable.icon_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_delicious);
        tabLayout.getTabAt(2).setIcon(R.drawable.icon_bars);

    }

    private void setControl() {
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabs);

        imgDoi = findViewById(R.id.imgDoi);
        tvTenDoi = findViewById(R.id.tvTenDoi);




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