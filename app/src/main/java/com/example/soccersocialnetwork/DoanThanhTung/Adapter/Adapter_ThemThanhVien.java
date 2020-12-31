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

import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.DoiActivity;
import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.Fragment_Doi_Menu;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.adapter.Adapter_TestCLickTeam;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Adapter_ThemThanhVien extends BaseAdapter implements Filterable {

    Context context;
    ArrayList<Users> data;
    ArrayList<Users> datafull;
    ValueFilter valueFilter;
    private LayoutInflater inflater;
    int resource;
    DatabaseReference mDatabase;
    private ValueEventListener mListener;

    public Adapter_ThemThanhVien(@NonNull Context context, int resource, ArrayList<Users> data) {

        this.context = context;
        this.data = data;
        this.resource = resource;

        this.datafull = data;

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

        TextView tvTenThanhVien, tvEmailThanhVien;
        ImageView imgUserDoi;
        LinearLayout llThemThanhVienVaoDoi;
//
//
////        TextView tv_DangBai;
////        ImageView img_DangBai;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Adapter_ThemThanhVien.Holder holder = new Adapter_ThemThanhVien.Holder();
        if (view == null) {
            holder = new Adapter_ThemThanhVien.Holder();
            view = LayoutInflater.from(context).inflate(resource, null);

            holder.tvTenThanhVien = view.findViewById(R.id.tvTenThanhVien);
            holder.tvEmailThanhVien = view.findViewById(R.id.tvEmailThanhVien);
            holder.imgUserDoi = view.findViewById(R.id.imgUserDoi);
            holder.llThemThanhVienVaoDoi = view.findViewById(R.id.llThemThanhVienVaoDoi);

            // holder.txtNgay = view.findViewById(R.id.tv_Gio);

            view.setTag(holder);
        } else
            holder = (Adapter_ThemThanhVien.Holder) view.getTag();

        final Users user = data.get(position);

        if (user.getUserImage().equals("")) {

        } else {
            Picasso.get().load(user.getUserImage()).into(holder.imgUserDoi);
        }
        // getUser(user.getUserEmail());

        holder.tvTenThanhVien.setText(user.getUserName());

        holder.tvEmailThanhVien.setText(user.getUserEmail());
        holder.llThemThanhVienVaoDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogThemTinThanhVien(user);
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


    private void insertUser(final String key) {

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        mListener = mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dt :
                        snapshot.getChildren()) {
                    //    boolean kiemTra = true;
                    Users allUsers = dt.getValue(Users.class);
                    if (key.equals(allUsers.getUserEmail())) {
                        databaseReference.child(dt.getKey()).child("listDoi").child(DoiActivity.idDoi).setValue("User").addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                mDatabase.removeEventListener(mListener);
                                notifyDataSetChanged();
                            }
                        });

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private Dialog dialogThemTinThanhVien(final Users users) {
        //  mDatabase.getDatabase().goOnline();

        final Dialog dialogThemTinThanhVien = new Dialog(context);
        dialogThemTinThanhVien.getWindow().setBackgroundDrawableResource(R.color.colorWhite);
        dialogThemTinThanhVien.setContentView(R.layout.dialog_thongtinthanhvien);
        dialogThemTinThanhVien.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //ánh xạ
        Button btnKichThanhVien = dialogThemTinThanhVien.findViewById(R.id.btnKichThanhVien);
        btnKichThanhVien.setText("Thêm");
        final Button btnEXIT = dialogThemTinThanhVien.findViewById(R.id.btnEXIT);

        ImageView imgAvatar = dialogThemTinThanhVien.findViewById(R.id.imgAvatar);
        TextView tvTenUser = dialogThemTinThanhVien.findViewById(R.id.tvTenUser);
        TextView tvViTri = dialogThemTinThanhVien.findViewById(R.id.tvViTri);
        TextView tvChieuCao = dialogThemTinThanhVien.findViewById(R.id.tvChieuCao);
        TextView tvCanNang = dialogThemTinThanhVien.findViewById(R.id.tvChieuCao);
        TextView tvSlogan = dialogThemTinThanhVien.findViewById(R.id.tvSlogan);
        TextView tvSinhNhat = dialogThemTinThanhVien.findViewById(R.id.tvSinhNhat);
        TextView tvEmail = dialogThemTinThanhVien.findViewById(R.id.tvEmail);
        TextView tvKhuVuc = dialogThemTinThanhVien.findViewById(R.id.tvKhuVuc);
        TextView tvQueQuan = dialogThemTinThanhVien.findViewById(R.id.tvQueQuan);

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
        if (users.getUserQueQuan().equals("")) {

        } else {
            tvQueQuan.setText(users.getUserQueQuan());
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
                dialogThemTinThanhVien.dismiss();
            }
        });
        btnKichThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Loại bỏ thành viên");
                builder.setMessage("Bạn có chắc không? \n ->>" + users.getUserName() + "<<-  ?");
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // kichThanhVien(users.getUserEmail());
                        insertUser(users.getUserEmail());
                        dialogThemTinThanhVien.dismiss();
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

        dialogThemTinThanhVien.show();
        return dialogThemTinThanhVien;


    }
}
