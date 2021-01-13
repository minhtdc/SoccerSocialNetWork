package com.example.soccersocialnetwork.DoanThanhTung.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.DoiActivity;
import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.ThemThanhVien;
import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_ThanhVien extends BaseAdapter implements Filterable {
    Context context;
    ArrayList<Users> data;
    ArrayList<Users> datafull;
    ValueFilter valueFilter;
    String adminOrUser;
    private LayoutInflater inflater;
    int resource;
    DatabaseReference mDatabase;
    private ValueEventListener mListener;

    public Adapter_ThanhVien(@NonNull Context context, int resource, ArrayList<Users> data) {

        this.context = context;
        this.data = data;
        this.resource = resource;
        this.datafull = data;
        readUserOrAdmin();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class Holder {

        TextView tvTenThanhVienTrongDoi, tvEmailThanhVienTrongDoi, tvAdmin;
        ImageView imgAvatar;
        LinearLayout llThanhVienDoi;

//
//
////        TextView tv_DangBai;
////        ImageView img_DangBai;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Adapter_ThanhVien.Holder holder = new Adapter_ThanhVien.Holder();
        if (view == null) {
            holder = new Adapter_ThanhVien.Holder();
            view = LayoutInflater.from(context).inflate(resource, null);

            holder.tvTenThanhVienTrongDoi = view.findViewById(R.id.tvTenThanhVienTrongDoi);
            holder.tvEmailThanhVienTrongDoi = view.findViewById(R.id.tvEmailThanhVienTrongDoi);
            holder.imgAvatar = view.findViewById(R.id.imgAvatar);
            holder.llThanhVienDoi = view.findViewById(R.id.llThanhVienDoi);
            holder.tvAdmin = view.findViewById(R.id.tvAdmin);


            // holder.txtNgay = view.findViewById(R.id.tv_Gio);

            view.setTag(holder);
        } else
            holder = (Adapter_ThanhVien.Holder) view.getTag();

        final Users user = data.get(position);
        holder.tvAdmin.setVisibility(View.GONE);

        DatabaseReference userADmin = FirebaseDatabase.getInstance().getReference();
        final Holder finalHolder = holder;
        userADmin.child("users").child(user.getUserID()).child("listDoi").child(DoiActivity.idDoi).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.getValue().equals("Admin")){
                    finalHolder.tvAdmin.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if (user.getUserImage().equals("")) {

        } else {
            Picasso.get().load(user.getUserImage()).into(holder.imgAvatar);
        }
        // getUser(user.getUserEmail());

        holder.tvTenThanhVienTrongDoi.setText(user.getUserName());

        holder.tvEmailThanhVienTrongDoi.setText(user.getUserEmail());
        holder.llThanhVienDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogThongTinThanhVien(user);
            }
        });


        return view;

    }

    @NonNull
    @Override
    public Filter getFilter() {

        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

//    Filter filterrrr = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            ArrayList<Users> strings = new ArrayList<>();
//            if (constraint.toString().isEmpty()) {
//                strings.addAll(datafull);
//            } else {
//                for (Users movie : datafull) {
//                    if (movie.getUserName().toLowerCase().contains(constraint.toString().toLowerCase().trim())) {
//                        strings.add(movie);
//                    }
//                }
//            }
//
//            FilterResults filterResults = new FilterResults();
//            filterResults.values = strings;
//
//            return filterResults;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            data.clear();
//            data.add((Users) results.values);
//            notifyDataSetChanged();
//        }
//    };

    //---------search
    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Users> strings = new ArrayList<>();
            if (constraint.toString().isEmpty()) {
                strings.addAll(datafull);
            } else {
                for (Users movie : datafull) {
                    if (movie.getUserName().toLowerCase().contains(constraint.toString().toLowerCase().trim())) {
                        strings.add(movie);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = strings;

            return filterResults;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data = (ArrayList<Users>) results.values;
            notifyDataSetChanged();
        }
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
        if (adminOrUser.equals("Admin")) {
            btnKichThanhVien.setVisibility(View.VISIBLE);
        } else {
            btnKichThanhVien.setVisibility(View.GONE);
        }
        btnKichThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Loại bỏ thành viên");
                builder.setMessage("Bạn có chách muốn kích  ->>" + users.getUserName() + "<<-  ?");
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        kichThanhVien(users.getUserEmail());
                        dialogThongTinThanhVien.dismiss();
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Hủy Bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        dialogThongTinThanhVien.show();
        return dialogThongTinThanhVien;


    }

    private void kichThanhVien(final String key) {
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        //   Toast.makeText(ThemThanhVien.this, users.getUserEmail()+"", Toast.LENGTH_SHORT).show();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        mListener = mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dt :
                        snapshot.getChildren()) {

                    Users users = dt.getValue(Users.class);

                    if (key.equals(users.getUserEmail())) {
                        // Toast.makeText(ThemThanhVien.this, key+"", Toast.LENGTH_SHORT).show();
                        for (DataSnapshot dtt :
                                dt.child("listDoi").getChildren()) {
                            if (DoiActivity.idDoi.equals(dtt.getKey())) {
                                // dtt.child(idDoi).getValue();
                                if (dtt.getValue().equals("Admin")) {
                                    Toast.makeText(context, "Bạn không thể kích chính mình", Toast.LENGTH_SHORT).show();
                                    mDatabase.removeEventListener(mListener);
                                } else {
                                    databaseReference.child(dt.getKey()).child("listDoi").child(DoiActivity.idDoi).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            mDatabase.removeEventListener(mListener);
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void readUserOrAdmin() {
        adminOrUser = "khongnull";
        mDatabase = FirebaseDatabase.getInstance().getReference("users").child(LoginActivity.USER_ID_CURRENT).child("listDoi").child(DoiActivity.idDoi);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adminOrUser = snapshot.getValue(String.class);

                // Toast.makeText(Doi_ThongTinCaNhan.this,snapshot.getValue()+ "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
