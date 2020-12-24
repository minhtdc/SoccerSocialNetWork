package com.example.soccersocialnetwork.DoanThanhTung.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.Fragment_Doi_Menu;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Adapter_ThemThanhVien_2 extends BaseAdapter {

    Context context;
    ArrayList<Users> data;
    ArrayList<Users> datafull;

    private LayoutInflater inflater;
    int resource;
    DatabaseReference mDatabase;


    public Adapter_ThemThanhVien_2(@NonNull Context context, int resource, ArrayList<Users> data) {

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

        TextView tvTenThanhVien, tvEmailThanhVien,tvDemSoThanhVienDuocThem;
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
        Adapter_ThemThanhVien_2.Holder holder = new Adapter_ThemThanhVien_2.Holder();
        if (view == null) {
            holder = new Adapter_ThemThanhVien_2.Holder();
            view = LayoutInflater.from(context).inflate(resource, null);


            holder.tvTenThanhVien = view.findViewById(R.id.tvTenThanhVien);
            holder.tvEmailThanhVien = view.findViewById(R.id.tvEmailThanhVien);
            holder.llThemThanhVienVaoDoi = view.findViewById(R.id.llThemThanhVienVaoDoi);

            // holder.txtNgay = view.findViewById(R.id.tv_Gio);

            view.setTag(holder);
        } else
            holder = (Adapter_ThemThanhVien_2.Holder) view.getTag();

        final Users user = data.get(position);



        holder.tvTenThanhVien.setText(user.getUserName());

        holder.tvEmailThanhVien.setText(user.getUserEmail());



        holder.llThemThanhVienVaoDoi.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác thực");
                builder.setMessage("Bạn có thực sự muốn xóa thành viên này?");
                builder.setNegativeButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        getUser(user.getUserEmail());
                        Fragment_Doi_Menu.strings.remove(position);
                        Fragment_Doi_Menu.listUser.add(user);
                        notifyDataSetChanged();
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                return false;
            }

        });
        holder.llThemThanhVienVaoDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, user.getUserID()+"", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    //getUser
    ArrayList<Users> listUser2 = new ArrayList<>();
     ArrayList<String> keyUser = new ArrayList<>();

    String keyy;

    private void getUser(final String email){
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Users users =snapshot.getValue(Users.class);
                if(users.getUserEmail().equals(email)){
                    //keyUser.add(snapshot.getKey());
                    keyy = snapshot.getKey();
//                    Toast.makeText(context, keyy+"", Toast.LENGTH_SHORT).show();
                    return;
                }


                //listUser2.add(users);
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
