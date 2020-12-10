package com.example.soccersocialnetwork.TranDuyHuynh;

<<<<<<< Updated upstream
=======
import androidx.appcompat.app.AppCompatActivity;

>>>>>>> Stashed changes
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
<<<<<<< Updated upstream

import androidx.appcompat.app.AppCompatActivity;
=======
>>>>>>> Stashed changes

import com.example.soccersocialnetwork.R;

public class edit_profile_user extends AppCompatActivity {
    Dialog dialog;
<<<<<<< Updated upstream
Button btnChinhSua;
=======
    Button btnChinhSua;

>>>>>>> Stashed changes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_user);
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
        btnChinhSua = (Button) findViewById(R.id.btnChinhSua);
        btnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< Updated upstream
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

=======

                showDialogEditProfile();
            }
        });
    }

    public void showDialogEditProfile() {
        dialog = new Dialog(edit_profile_user.this);
        dialog.setTitle("Chỉnh sửa thông tin");
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_dialog_edit_profile);
        Button btnCapNhat = dialog.findViewById(R.id.btnCapnhat);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
         btnHuy.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 AlertDialog.Builder builder = new AlertDialog.Builder(edit_profile_user.this);
                 builder.setTitle("Hủy chỉnh sửa ?");
                 builder.setMessage("Bạn muốn hủy chỉnh sửa ?");
                 builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
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
>>>>>>> Stashed changes
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