package com.example.soccersocialnetwork.DoanThanhTung.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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

import com.example.soccersocialnetwork.DoanThanhTung.DialogBinhLuan;
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
                final Users user = snapshot.getValue(Users.class);
                holder.tvNameUser.setText(user.getUserName());
                if (!user.getUserImage().equals("")) {
                    Picasso.get().load(user.getUserImage()).into(holder.imgAvatar);
                }

                holder.tvNameUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogThongTinThanhVien(user).show();
                    }
                });
                holder.imgAvatar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogThongTinThanhVien(user).show();
                    }
                });
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
                Bundle bundle = new Bundle();
                bundle.putString("id_Feed",feed.getId());
                bundle.putString("id_User",feed.getUid());
                DialogBinhLuan dialogBinhLuan = new DialogBinhLuan(context,bundle);


                dialogBinhLuan.show();

            //   dialogBinhLuan(feed.getId(),feed.getUid());

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

    private void dialogBinhLuan(final String idBinhLuan, final String uid) {
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
                insertBinhLuan(idBinhLuan, txtBinhLuan.getText() + "",uid);
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

    private void insertBinhLuan(final String idBinhLuan, String noiDungBL, final String uid) {
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Team").child(DoiActivity.idDoi).child("listFeeds").child(idBinhLuan).child("listCMT");
        ListCMT listCMT = new ListCMT();
        String idCMT = databaseReference.push().getKey();
        listCMT.setCmt(noiDungBL);
        listCMT.setIdCMT(idCMT);
        listCMT.setUid(LoginActivity.USER_ID_CURRENT);
        databaseReference.child(idCMT).setValue(listCMT).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();
                String idThongBao = databaseReference1.push().getKey();
                ThongBao thongBao = new ThongBao();
                thongBao.setNoiDung(LoginActivity.USER_NAME_CURRENT + " đã bình luận bài viết của bạn");
                thongBao.setIdThongBao(idThongBao);
                thongBao.setIdDoi(DoiActivity.idDoi);
                thongBao.setUid(LoginActivity.USER_ID_CURRENT);
                thongBao.setImg("https://firebasestorage.googleapis.com/v0/b/soccersocialnetwork-733b3.appspot.com/o/imgTeam%2FIDTeam_IMG%3A%205%2Ffb631f43-09f2-4300-9349-4c066deb032e?alt=media&token=3062a8e5-49eb-458e-be1c-1bab84c644d6");

                DatabaseReference userBL = FirebaseDatabase.getInstance().getReference();
                if(!LoginActivity.USER_ID_CURRENT.equals(uid)){
                    userBL.child("users").child(uid).child("listThongBao").child(idThongBao).setValue("Có người bình luận bài viết của bạn");
                    databaseReference1.child("ThongBao").child(idThongBao).setValue(thongBao);
                }

            }
        });
        // databaseReference.child().child("listFeeds").child(idBinhLuan).child("listCMT").push().setValue(listCMT);
    }

    private Dialog dialogThongTinThanhVien(final Users users) {
        //  mDatabase.getDatabase().goOnline();

        final Dialog dialogThongTinThanhVien = new Dialog(context);
        dialogThongTinThanhVien.getWindow().setBackgroundDrawableResource(R.color.colorWhite);
        dialogThongTinThanhVien.setContentView(R.layout.dialog_thongtinthanhvien);
        dialogThongTinThanhVien.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //ánh xạ
        Button btnKichThanhVien = dialogThongTinThanhVien.findViewById(R.id.btnKichThanhVien);
        final Button btnEXIT = dialogThongTinThanhVien.findViewById(R.id.btnEXIT);
        ImageView imgAvatar = dialogThongTinThanhVien.findViewById(R.id.imgAvatar);
        TextView tvTenUser = dialogThongTinThanhVien.findViewById(R.id.tvTenUser);
        TextView tvViTri = dialogThongTinThanhVien.findViewById(R.id.tvViTri);
        TextView tvChieuCao = dialogThongTinThanhVien.findViewById(R.id.tvChieuCao);
        TextView tvCanNang = dialogThongTinThanhVien.findViewById(R.id.tvChieuCao);
        TextView tvSlogan = dialogThongTinThanhVien.findViewById(R.id.tvSlogan);
        TextView tvSinhNhat = dialogThongTinThanhVien.findViewById(R.id.tvSinhNhat);
        TextView tvEmail = dialogThongTinThanhVien.findViewById(R.id.tvEmail);
        TextView tvKhuVuc = dialogThongTinThanhVien.findViewById(R.id.tvKhuVuc);

        btnKichThanhVien.setVisibility(View.GONE);
        //setevent
        if (users.getUserImage().equals("")) {

        } else {
            Picasso.get().load(users.getUserImage()).into(imgAvatar);
        }

        if (users.getUserName().equals("")) {

        } else {
            tvTenUser.setText(users.getUserName());
        }

        if (users.getUserCanNang().equals("")) {

        } else {
            tvCanNang.setText(users.getUserCanNang());
        }


        if (users.getUserAria().equals("")) {

        } else {
            tvKhuVuc.setText(users.getUserAria());
        }

        if (users.getUserEmail().equals("")) {

        } else {
            tvEmail.setText(users.getUserEmail());
        }

        if (users.getUserBirth().equals("")) {

        } else {
            tvSinhNhat.setText(users.getUserBirth());
        }

        if (users.getUserSologan().equals("")) {

        } else {
            tvSlogan.setText(users.getUserSologan());
        }
        if (users.getUserViTri().equals("")) {

        } else {
            tvViTri.setText(users.getUserViTri());
        }
        if (users.getUserChieuCao().equals("")) {

        } else {
            tvChieuCao.setText(users.getUserChieuCao());
        }
        btnEXIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogThongTinThanhVien.dismiss();
            }
        });

        dialogThongTinThanhVien.show();
        return dialogThongTinThanhVien;


    }
}
