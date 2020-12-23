package com.example.soccersocialnetwork.DoanThanhTung.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.Fragment_Doi_Menu;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.adapter.Adapter_TestCLickTeam;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
            holder.llThemThanhVienVaoDoi = view.findViewById(R.id.llThemThanhVienVaoDoi);

            // holder.txtNgay = view.findViewById(R.id.tv_Gio);

            view.setTag(holder);
        } else
            holder = (Adapter_ThemThanhVien.Holder) view.getTag();

        final  Users user = data.get(position);

     //   getUser(user.getUserEmail());

        holder.tvTenThanhVien.setText(user.getUserName());
        holder.tvEmailThanhVien.setText(user.getUserEmail());
        holder.llThemThanhVienVaoDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference("users");
                mDatabase.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Users users =snapshot.getValue(Users.class);
                        if(users.getUserEmail().equals(user.getUserEmail())){
                            //keyUser.add(snapshot.getKey());
                            user.setUserID(snapshot.getKey());
                            Fragment_Doi_Menu.strings.add(user);
                            Fragment_Doi_Menu.listUser.remove(position);
                            notifyDataSetChanged();
                            keyy = snapshot.getKey();
//                    Toast.makeText(context, keyy+"", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) { }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) { }
                });

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
            if(constraint.toString().isEmpty()){
                strings.addAll(datafull);
            }else{
                for(Users movie: datafull){
                    if(movie.getUserName().toLowerCase().contains(constraint.toString().toLowerCase().trim())){
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
                //    Toast.makeText(context, keyy+"", Toast.LENGTH_SHORT).show();
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
