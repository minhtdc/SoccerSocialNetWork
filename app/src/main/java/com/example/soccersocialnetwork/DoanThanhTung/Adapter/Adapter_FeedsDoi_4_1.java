package com.example.soccersocialnetwork.DoanThanhTung.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccersocialnetwork.DoanThanhTung.Models.Feed;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Feeds;
import com.example.soccersocialnetwork.DoanThanhTung.Models.ListCMT;
import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.DoiActivity;
import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter_FeedsDoi_4_1 extends RecyclerView.Adapter<Adapter_FeedsDoi_4_1.Adapter_FeedsDoi2_ViewHoder> {

    Context context;
    private ArrayList<Feed> data;

    public Adapter_FeedsDoi_4_1(Context context, ArrayList<Feed> data) {
        this.context = context;
        this.data = data;

    }
//    public Adapter_FeedsDoi2(Context context,ArrayList<Feeds> data) {
//        this.context = context;
//        this.data = data;
//    }


    @NonNull
    @Override
    public Adapter_FeedsDoi2_ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doi_feeds_view_4_1, parent, false);
        return new Adapter_FeedsDoi2_ViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter_FeedsDoi2_ViewHoder holder, int position) {

        final Feed feed = data.get(position);
        if (feed == null) {
            return;
        }
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(feed.getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users user = snapshot.getValue(Users.class);
                holder.tvNameUser.setText(user.getUserName());
                if (!user.getUserImage().equals("")) {
                    Picasso.get().load(user.getUserImage()).into(holder.imgAvatar);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.tvSTT.setText(feed.getSTT());
        holder.btnBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogBinhLuan(feed.getId());

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class Adapter_FeedsDoi2_ViewHoder extends RecyclerView.ViewHolder {
        TextView tvNameUser, tvSTT;
        ImageView imgAvatar;
        Button btnBinhLuan;

        public Adapter_FeedsDoi2_ViewHoder(@NonNull View itemView) {
            super(itemView);
            tvNameUser = itemView.findViewById(R.id.tvNameUser);
            tvSTT = itemView.findViewById(R.id.tvSTT);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            btnBinhLuan = itemView.findViewById(R.id.btnBinhLuan);

        }
    }

    ArrayAdapter adapter_binhLuan;

    private void dialogBinhLuan(final String idBinhLuan) {
        final ArrayList<ListCMT> listCMTS = new ArrayList<>();

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.dialog_doi_binhluan);

        final TextView tvChuaBinhLuan = bottomSheetDialog.findViewById(R.id.tvChuaBinhLuan);
        final ListView lvBinhLuan = bottomSheetDialog.findViewById(R.id.lvBinhLuan);
        final EditText txtBinhLuan = bottomSheetDialog.findViewById(R.id.txtBinhLuan);
        Button btnGui = bottomSheetDialog.findViewById(R.id.btnGui);



//        adapter_binhLuan = new Adapter_BinhLuan(context,R.layout.adapter_binhluan, listCMTS);
//        lvBinhLuan.setAdapter(adapter_binhLuan);
//        adapter_binhLuan.notifyDataSetChanged();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Team");
        databaseReference.child(DoiActivity.idDoi).child("listFeeds").child(idBinhLuan).child("listCMT").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listCMTS.clear();
                for (DataSnapshot dt :
                        snapshot.getChildren()) {
                    //   Toast.makeText(context, dt.getKey() + "", Toast.LENGTH_SHORT).show();

                    ListCMT listCMT = dt.getValue(ListCMT.class);
                    listCMTS.add(listCMT);
                }
                if (listCMTS.size() != 0) {
                    tvChuaBinhLuan.setVisibility(View.GONE);
                }
                adapter_binhLuan = new Adapter_BinhLuan(context, R.layout.adapter_binhluan, listCMTS);
                lvBinhLuan.setAdapter(adapter_binhLuan);
                adapter_binhLuan.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertBinhLuan(idBinhLuan, txtBinhLuan.getText() + "");
                txtBinhLuan.setText("");

            }
        });
        bottomSheetDialog.show();

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

    private void insertBinhLuan(String idBinhLuan, String noiDungBL) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Team");
        ListCMT listCMT = new ListCMT();
        listCMT.setCmt(noiDungBL);
        listCMT.setUid(LoginActivity.USER_ID_CURRENT);
        databaseReference.child(DoiActivity.idDoi).child("listFeeds").child(idBinhLuan).child("listCMT").push().setValue(listCMT);


    }
}
