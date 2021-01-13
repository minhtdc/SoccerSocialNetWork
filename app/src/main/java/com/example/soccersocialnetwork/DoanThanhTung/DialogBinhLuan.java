package com.example.soccersocialnetwork.DoanThanhTung;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.soccersocialnetwork.DoanThanhTung.Adapter.Adapter_BinhLuan;
import com.example.soccersocialnetwork.DoanThanhTung.Models.ListCMT;
import com.example.soccersocialnetwork.DoanThanhTung.Models.ThongBao;
import com.example.soccersocialnetwork.DoanThanhTung.ViewThanhTung.DoiActivity;
import com.example.soccersocialnetwork.LoginActivity;
import com.example.soccersocialnetwork.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DialogBinhLuan extends BottomSheetDialog {

    TextView tvChuaBinhLuan;
    ListView lvBinhLuan;
    EditText txtBinhLuan;
    Button btnGui, btnSua;
    Bundle bundle;
    ArrayList<ListCMT> listCMTS = new ArrayList<>();
    ArrayAdapter adapter_binhLuan;

    public DialogBinhLuan(@NonNull Context context, Bundle bundle) {
        super(context);
        this.bundle = bundle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_doi_binhluan);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        tvChuaBinhLuan = findViewById(R.id.tvChuaBinhLuan);
        lvBinhLuan = findViewById(R.id.lvBinhLuan);
        txtBinhLuan = findViewById(R.id.txtBinhLuan);
        btnGui = findViewById(R.id.btnGui);
        btnSua = findViewById(R.id.btnSua);


        final String idBinhLuan = bundle.getString("id_Feed");
        final String uid = bundle.getString("id_User");
        final String idDoi = bundle.getString("id_DoiBL");
        if (DoiActivity.idDoi == null) {
            DoiActivity.idDoi = idDoi;
        }

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
                adapter_binhLuan = new Adapter_BinhLuan(getContext(), R.layout.adapter_binhluan, listCMTS);
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

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        if (which == 0) {
                            if (LoginActivity.USER_ID_CURRENT.equals(listCMTS.get(position).getUid())) {
                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();
                                databaseReference1.child("Team").child(DoiActivity.idDoi).child("listFeeds").child(idBinhLuan).child("listCMT").child(listCMTS.get(position).getIdCMT()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getContext(), "Đã xóa", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            } else {
                                Toast.makeText(getContext(), "Bạn không có quyền", Toast.LENGTH_SHORT).show();
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
                                        databaseReference1.child("Team").child(DoiActivity.idDoi).child("listFeeds").child(idBinhLuan).child("listCMT").child(listCMTS.get(position).getIdCMT()).child("cmt").setValue(txtBinhLuan.getText() + "").addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                btnSua.setVisibility(View.GONE);
                                                btnGui.setVisibility(View.VISIBLE);
                                                txtBinhLuan.setText("");
                                                Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                });


                            } else {
                                Toast.makeText(getContext(), "Bạn không có quyền", Toast.LENGTH_SHORT).show();
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
                insertBinhLuan(idBinhLuan, txtBinhLuan.getText() + "", uid);
                txtBinhLuan.setText("");

            }
        });
    }

    private void insertBinhLuan(final String idBinhLuan, String noiDungBL, final String uid) {
        final Calendar calendar = Calendar.getInstance();
        int idate = calendar.get(Calendar.DATE);

        int imonth = calendar.get(Calendar.MONTH);
        int iyear = calendar.get(Calendar.YEAR);
        int ihours = calendar.get(Calendar.HOUR);
        int iminute = calendar.get(Calendar.MINUTE);
        int isecond = calendar.get(Calendar.SECOND);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Team").child(DoiActivity.idDoi).child("listFeeds").child(idBinhLuan).child("listCMT");
        ListCMT listCMT = new ListCMT();
        String idCMT = databaseReference.push().getKey();
        listCMT.setCmt(noiDungBL);
        listCMT.setIdCMT(idCMT);
        listCMT.setUid(LoginActivity.USER_ID_CURRENT);
        listCMT.setThoiGian(idate + "/" + imonth + "/" + iyear + "\n" + ihours + ":" + iminute + ":" + isecond);
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
                thongBao.setIdBinhLuan(idBinhLuan);
                DatabaseReference userBL = FirebaseDatabase.getInstance().getReference();
                if (!LoginActivity.USER_ID_CURRENT.equals(uid)) {
                    userBL.child("users").child(uid).child("listThongBao").child(idThongBao).setValue("Có người bình luận bài viết");
                    databaseReference1.child("ThongBao").child(idThongBao).setValue(thongBao);
                }

            }
        });
        // databaseReference.child().child("listFeeds").child(idBinhLuan).child("listCMT").push().setValue(listCMT);


    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
