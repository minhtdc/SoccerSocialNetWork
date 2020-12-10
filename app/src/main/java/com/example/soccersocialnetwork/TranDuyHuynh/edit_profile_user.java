package com.example.soccersocialnetwork.TranDuyHuynh;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soccersocialnetwork.R;

public class edit_profile_user extends AppCompatActivity {
    Dialog dialog;
Button btnChinhSua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_user);
        btnChinhSua = (Button) findViewById(R.id.btnChinhSua);
        btnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogEdit();
            }
        });
    }
    public void showDialogEdit(){
        dialog = new Dialog(edit_profile_user.this);
        dialog.setTitle("Chỉnh sửa thông tin");
        dialog.setCancelable(true); // khi bấm ra ngoài dialog thì dialog sẽ tự tắt đi
        dialog.setContentView(R.layout.dialog_edit_profile);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        Button btnCapNhat = dialog.findViewById(R.id.btnCapNhat);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(edit_profile_user.this);
                builder.setTitle("Hủy chỉnh sửa");
                builder.setCancelable(true);
                builder.setPositiveButton("Xác nhận hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(edit_profile_user.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}