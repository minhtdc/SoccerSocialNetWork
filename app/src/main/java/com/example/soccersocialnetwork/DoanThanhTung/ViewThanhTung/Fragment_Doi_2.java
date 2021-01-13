package com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_FeedsDoi;
import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_TranDau;
import com.example.soccersocialnetwork.DoanThanhTung.Models.Feeds;
import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.models.thongTinTranDau;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

public class Fragment_Doi_2 extends Fragment {

    ListView lvTranDau;

    ArrayList<thongTinTranDau> listTranDau = new ArrayList<>();
    ArrayAdapter adapter_TranDau;
    //  Adapter_FeedsDoi adapterFeedsDoi;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = (ViewGroup) inflater.inflate((R.layout.fragment_trandau), container, false);
         lvTranDau = rootView.findViewById(R.id.lvTranDau);

        setEvent();
        return rootView;
    }


    private void setEvent() {

        DatabaseReference listThongTinTranDau = FirebaseDatabase.getInstance().getReference();
        listThongTinTranDau.child("ThongTinTranDau").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listTranDau.clear();
                for (DataSnapshot dt :
                        snapshot.getChildren()) {
                    thongTinTranDau trandau = dt.getValue(thongTinTranDau.class);
                    if(DoiActivity.idDoi.equals(trandau.getIdDoiDangTin())){
                        listTranDau.add(trandau);
                    }

                }

                adapter_TranDau = new Adapter_TranDau(getContext(),R.layout.doi_match_view,listTranDau);
                lvTranDau.setAdapter(adapter_TranDau);
                adapter_TranDau.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lvTranDau.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialogThongTinThanhVien(listTranDau.get(position).getIdTranDau()).show();
//                Toast.makeText(getContext(),listTranDau.get(position).getDiaDiem()+ "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Dialog dialogThongTinThanhVien(String idThongTinTranDau) {
        //  mDatabase.getDatabase().goOnline();

        final Dialog dialogTranDau = new Dialog(getContext());
        dialogTranDau.getWindow().setBackgroundDrawableResource(R.color.colorWhite);
        dialogTranDau.setContentView(R.layout.dialog_trandau_thongtin);
        dialogTranDau.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //ánh xạ
        Button btnEXIT = dialogTranDau.findViewById(R.id.btnEXIT);
        final TextView tvTrangThai = dialogTranDau.findViewById(R.id.tvTrangThai);
        final TextView tvNgay = dialogTranDau.findViewById(R.id.tvNgay);
        final TextView tvGio = dialogTranDau.findViewById(R.id.tvGio);
        final TextView tvDD = dialogTranDau.findViewById(R.id.tvDD);
        final TextView tvThongTin = dialogTranDau.findViewById(R.id.tvThongTin);
        final TextView tvTenTeam = dialogTranDau.findViewById(R.id.tvTenTeam);
        TextView tvTenDoiThu = dialogTranDau.findViewById(R.id.tvTenDoiThu);
        final ImageView imgTeam = dialogTranDau.findViewById(R.id.imgTeam);
        ImageView imgDoiThu = dialogTranDau.findViewById(R.id.imgDoiThu);

        DatabaseReference tranDauFirebase = FirebaseDatabase.getInstance().getReference();
        tranDauFirebase.child("ThongTinTranDau").child(idThongTinTranDau).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                thongTinTranDau thongTin = snapshot.getValue(thongTinTranDau.class);

                if (thongTin.getNgay() !=null){
                    tvNgay.setText(thongTin.getNgay()+"");
                }

                tvGio.setText(thongTin.getThoiGian()+"");
                tvDD.setText(thongTin.getDiaDiem()+"");
                tvThongTin.setText(thongTin.getThongTinThem()+"");
                tvTenTeam.setText(thongTin.getTenDoi()+"");
                Map<String, Object> data = (Map<String, Object>) snapshot.getValue();
                String abc = data.get("trangThaiDuyet") + "";
                if(abc.equals("0")){
                    tvTrangThai.setText("Chưa tìm được đối thủ");
                }else
                {
                    tvTrangThai.setText("Có đối thủ");
                    tvTrangThai.setTextColor(Color.GREEN);
                }
                //Toast.makeText(getContext(), dt.getKey()+"", Toast.LENGTH_SHORT).show();
//                    feeds.setQuan(data.get("quan") + "");
//                    feeds.setThongBao(data.get("thongBao") + "");
                Picasso.get().load(thongTin.getAnhDoi()).into(imgTeam);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnEXIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTranDau.dismiss();
            }
        });


        return dialogTranDau;


    }


}
