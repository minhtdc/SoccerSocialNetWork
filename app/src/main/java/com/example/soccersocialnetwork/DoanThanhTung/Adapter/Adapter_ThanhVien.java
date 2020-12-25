package com.example.soccersocialnetwork.DoanThanhTung.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_ThanhVien extends ArrayAdapter {
    Context context;
    ArrayList<Users> data;
    int resource;
    DatabaseReference mDatabase;

    public Adapter_ThanhVien(@NonNull Context context, int resource, ArrayList<Users> data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
        this.resource = resource;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    static class Holder {

        TextView tvTenThanhVienTrongDoi, tvEmailThanhVienTrongDoi;

//
////        TextView tv_DangBai;
////        ImageView img_DangBai;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Holder holder = new Holder();
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);

            holder.tvTenThanhVienTrongDoi = view.findViewById(R.id.tvTenThanhVienTrongDoi);
            holder.tvEmailThanhVienTrongDoi = view.findViewById(R.id.tvEmailThanhVienTrongDoi);

            // holder.txtNgay = view.findViewById(R.id.tv_Gio);

            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final Users team = data.get(position);



        holder.tvTenThanhVienTrongDoi.setText(team.getUserName());
        holder.tvEmailThanhVienTrongDoi.setText(team.getUserEmail());


//        holder.llDanhSachDoi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
////
//                for(int i = 0; i<listTeamUsers.size();i++){
//                    if(LoginActivity.USER_ID_CURRENT.equals(listTeamUsers.get(i)) ){
//                        Intent intent = new Intent(getContext(), DoiActivity.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("TaoDoi_IDDoi",team.idDoi+"");
//                        intent.putExtras(bundle);
//
//                        context.startActivity(intent);
////                        break;
////                    }else{
////                        Intent intent = new Intent(getContext(), Doi_ThongTinCaNhan.class);
////                        Bundle bundle = new Bundle();
////                        bundle.putString("Doi_ID",team.idDoi+"");
////                        intent.putExtras(bundle);
////                        context.startActivity(intent);
////
//                    }
//                }
//            }
//        });
//        holder.imgDoiAdapter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, team.getTenDoi(), Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }


}
