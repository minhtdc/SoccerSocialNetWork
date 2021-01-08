package com.example.soccersocialnetwork.DoanThanhTung.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccersocialnetwork.DoanThanhTung.Models.Feed;
import com.example.soccersocialnetwork.DoanThanhTung.Models.ListCMT;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.DoiActivity;
import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.adapter.Adapter_TestCLickTeam;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_BinhLuan extends ArrayAdapter {

    Context context;
    int resource;
    private ArrayList<ListCMT> data;

    public Adapter_BinhLuan(Context context, int resource, ArrayList<ListCMT> data) {
        super(context, resource, data);
        this.resource = resource;
        this.context = context;
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

//    static class Holder {
//        TextView tvNameUser, tvBinhLuan;
//        ImageView imgAvatar;
//
//    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource,null);

        final TextView   tvNameUser = view.findViewById(R.id.tvNameUser);
        TextView   tvBinhLuan = view.findViewById(R.id.tvBinhLuan);
        final ImageView  imgAvatar = view.findViewById(R.id.imgAvatar);



        final ListCMT listCMT = data.get(position);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");


        databaseReference.child(listCMT.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users user = snapshot.getValue(Users.class);
//                Toast.makeText(context, user.getUserName()+"", Toast.LENGTH_SHORT).show();
                tvNameUser.setText(user.getUserName()+"");
                if(!user.getUserImage().equals("")){
                    Picasso.get().load(user.getUserImage()).into(imgAvatar);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        tvBinhLuan.setText(listCMT.getCmt());

        return view;
    }


    private void getBinhLuan(String idBinhLuan) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Team");
        databaseReference.child(DoiActivity.idDoi).child("listFeeds").child(idBinhLuan).child("listCMT").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dt :
                        snapshot.getChildren()) {
                    //   Toast.makeText(context, dt.getKey() + "", Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, dt.getValue() + "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
