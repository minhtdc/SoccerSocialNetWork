package com.example.soccersocialnetwork.DoanThanhTung.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccersocialnetwork.DoanThanhTung.Models.Feed;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Feeds;
import com.example.soccersocialnetwork.DoanThanhTung.Models.ListCMT;
import com.example.soccersocialnetwork.DoanThanhTung.Models.ThongBao;
import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.DoiActivity;
import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.android.gms.tasks.OnSuccessListener;
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
        holder.img_admin.setVisibility(View.GONE);

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
        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("users");
        databaseReference1.child(LoginActivity.USER_ID_CURRENT).child("listDoi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dt :
                snapshot.getChildren()){
                    if(dt.getKey().equals(DoiActivity.idDoi) && dt.getValue().equals("Admin")){
                        holder.img_admin.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.img_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog(feed.getId());
            }
        });
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
        ImageView imgAvatar,img_admin;
        Button btnBinhLuan;

        public Adapter_FeedsDoi2_ViewHoder(@NonNull View itemView) {
            super(itemView);
            tvNameUser = itemView.findViewById(R.id.tvNameUser);
            tvSTT = itemView.findViewById(R.id.tvSTT);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            img_admin = itemView.findViewById(R.id.img_admin);
            btnBinhLuan = itemView.findViewById(R.id.btnBinhLuan);

        }
    }

    ArrayAdapter adapter_binhLuan;

    private void dialogBinhLuan(final String idBinhLuan) {
        final ArrayList<ListCMT> listCMTS = new ArrayList<>();

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.dialog_doi_binhluan);

        final TextView tvChuaBinhLuan = bottomSheetDialog.findViewById(R.id.tvChuaBinhLuan);
        final ListView lvBinhLuan = bottomSheetDialog.findViewById(R.id.lvBinhLuan);
        final EditText txtBinhLuan = bottomSheetDialog.findViewById(R.id.txtBinhLuan);
        final Button btnGui = bottomSheetDialog.findViewById(R.id.btnGui);
        final Button btnSua = bottomSheetDialog.findViewById(R.id.btnSua);


//        adapter_binhLuan = new Adapter_BinhLuan(context,R.layout.adapter_binhluan, listCMTS);
//        lvBinhLuan.setAdapter(adapter_binhLuan);
//        adapter_binhLuan.notifyDataSetChanged();

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Team");
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

        lvBinhLuan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final CharSequence[] items = {"Xóa", "Sửa"};

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        if (which == 0) {
                            if (LoginActivity.USER_ID_CURRENT.equals(listCMTS.get(position).getUid())) {
                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();
                                databaseReference1.child("Team").child(DoiActivity.idDoi).child("listFeeds").child(idBinhLuan).child("listCMT").child(listCMTS.get(position).getIdCMT()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(context, "Đã xóa", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            } else {
                                Toast.makeText(context, "Bạn không có quyền", Toast.LENGTH_SHORT).show();
                            }
                        } else if (which == 1) {
                            if (LoginActivity.USER_ID_CURRENT.equals(listCMTS.get(position).getUid())) {
                                btnGui.setVisibility(View.GONE);
                                btnSua.setVisibility(View.VISIBLE);


                                txtBinhLuan.setText(listCMTS.get(position).getCmt());
                                btnSua.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();
                                        databaseReference1.child("Team").child(DoiActivity.idDoi).child("listFeeds").child(idBinhLuan).child("listCMT").child(listCMTS.get(position).getIdCMT()).child("cmt").setValue(txtBinhLuan.getText()+"").addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                btnSua.setVisibility(View.GONE);
                                                btnGui.setVisibility(View.VISIBLE);
                                                txtBinhLuan.setText("");
                                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                });


                            } else {
                                Toast.makeText(context, "Bạn không có quyền", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
                builder.show();
//                if(LoginActivity.USER_ID_CURRENT.equals(listCMTS.get(position).getUid())){
//                    Toast.makeText(context, listCMTS.get(position).getCmt()+"Đã xóa", Toast.LENGTH_SHORT).show();
//                }else
//                {
//                    Toast.makeText(context, "123123", Toast.LENGTH_SHORT).show();
//                }
                return false;
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
    private void bottomDialog(final String idSTT){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.adapter_doi_bottom_dialog);
        LinearLayout llXoa = bottomSheetDialog.findViewById(R.id.llXoa);
        llXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Team");
                databaseReference.child(DoiActivity.idDoi).child("listFeeds").child(idSTT).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Đã xóa", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });
        bottomSheetDialog.show();
    }

    private void insertBinhLuan(final String idBinhLuan, String noiDungBL) {
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Team").child(DoiActivity.idDoi).child("listFeeds").child(idBinhLuan).child("listCMT");
        ListCMT listCMT = new ListCMT();
        String idCMT = databaseReference.push().getKey();
        listCMT.setCmt(noiDungBL);
        listCMT.setIdCMT(idCMT);
        listCMT.setUid(LoginActivity.USER_ID_CURRENT);
        databaseReference.child(idCMT).setValue(listCMT).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
//                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("ThongBao");
//                String idThongBao = databaseReference1.push().getKey();
//                ThongBao thongBao = new ThongBao();
//                thongBao.setNoiDung(LoginActivity.USER_NAME_CURRENT + " đã bình luận bài viết của bạn");
//                thongBao.setIdThongBao(idThongBao);
//                thongBao.setUid(LoginActivity.USER_ID_CURRENT);
//
//                databaseReference1.child("ThongBao").child(idThongBao).setValue(thongBao);
            }
        });
        // databaseReference.child(DoiActivity.idDoi).child("listFeeds").child(idBinhLuan).child("listCMT").push().setValue(listCMT);


    }
}
