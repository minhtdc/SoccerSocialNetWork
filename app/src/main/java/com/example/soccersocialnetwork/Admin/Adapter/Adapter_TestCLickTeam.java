package com.example.soccersocialnetwork.Admin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
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

public class Adapter_TestCLickTeam extends BaseAdapter implements Filterable {
    Context context;
    ArrayList<Team> data;
    ArrayList<Team> datafull;

    ValueFilter valueFilter;
    int resource;
    DatabaseReference mDatabase;

    public Adapter_TestCLickTeam(@NonNull Context context, int resource, ArrayList<Team> data) {

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
        ImageView imageView, imgXoa;
        TextView txtTenDoi, txtKhuVuc, txtThongtinGioiHieu, txtThongTinSL_CauThu_lstTeam;

    }

    int soCauThu = 0;
    ValueEventListener valueEventListener;
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Holder holder = new Holder();
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);

            holder.imageView = view.findViewById(R.id.imgLogo_Teams_list_teams);
            holder.txtTenDoi = view.findViewById(R.id.txtTendoi_list_teams);
            holder.txtKhuVuc = view.findViewById(R.id.txtThongTinKhuVuc_lstTeams);
            holder.txtThongtinGioiHieu = view.findViewById(R.id.txtThongTinGioiThieu_lstTeams);
            holder.txtThongTinSL_CauThu_lstTeam = view.findViewById(R.id.txtThongTinSL_CauThu_lstTeam);
            holder.imgXoa = view.findViewById(R.id.imgXoa);

            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final Team team = data.get(position);


        //   readuser(team.getIdDoi() + "");
        Picasso.get().load(team.getHinhAnh()).into(holder.imageView);
        holder.txtTenDoi.setText(team.getTenDoi());
        holder.txtKhuVuc.setText(team.getKhuVuc());
        holder.txtThongtinGioiHieu.setText(team.getGioiThieu());
        holder.imgXoa.setImageResource(R.drawable.icon_delete);
        final DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();
        final Holder finalHolder = holder;

        valueEventListener = databaseReference1.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                soCauThu = 0;
                for (DataSnapshot dt :
                        snapshot.getChildren()) {
                    for (DataSnapshot dtt :
                            dt.child("listDoi").getChildren()) {
                        if (dtt.getKey().equals(team.getIdDoi() + "")) {
                            soCauThu++;
                        }
                    }
                }
                finalHolder.txtThongTinSL_CauThu_lstTeam.setText(soCauThu+"");
                databaseReference1.removeEventListener(valueEventListener);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTeam(team.getIdDoi() +"");

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
            ArrayList<Team> strings = new ArrayList<>();
            if (constraint.toString().isEmpty()) {
                strings.addAll(datafull);
            } else {
                for (Team movie : datafull) {
                    if (movie.getTenDoi().toLowerCase().contains(constraint.toString().toLowerCase().trim())) {
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

            data = (ArrayList<Team>) results.values;

            notifyDataSetChanged();
        }
    }

    private void deleteTeam(final String idTeam){
        DatabaseReference firebaseTeam = FirebaseDatabase.getInstance().getReference();
        firebaseTeam.child("Team").child(idTeam).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                DatabaseReference firebaseUsers = FirebaseDatabase.getInstance().getReference();
                firebaseUsers.child("users").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dt:
                        snapshot.getChildren()){
                            Users users = dt.getValue(Users.class);
                            for(DataSnapshot dtt:
                            dt.child("listDoi").getChildren()){
                                if(dtt.getKey().equals(idTeam)){
                                    DatabaseReference firebaseRemoveUser = FirebaseDatabase.getInstance().getReference();
                                    firebaseRemoveUser.child("users").child(dt.getKey()).child("listDoi").child(dtt.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(context, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

}
