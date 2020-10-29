package com.example.soccersocialnetwork;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        //hide action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //Ánh xạ
        anhXa();

        //action
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dislayDialogLogin1();
            }
        });

    }

    //Hiển thị dialog login 1
    private void dislayDialogLogin1() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_login_1_layout, null);
        EditText edtRegisterEmail = (EditText) alertLayout.findViewById(R.id.edtRegisterBirthday);
        EditText edtRegisterName = (EditText) alertLayout.findViewById(R.id.edtRegisterAria);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Đăng ký");
        alert.setView(alertLayout);
        alert.setCancelable(false);

        //cancel
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //next
        alert.setPositiveButton("Next", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // code for matching password
                dislayDialogLogin2();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    //hiển thị dialog 2
    private void dislayDialogLogin2() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_login_2_layout, null);
        EditText edtRegisterBirthday = (EditText) alertLayout.findViewById(R.id.edtRegisterBirthday);
        EditText edtRegisterAria = (EditText) alertLayout.findViewById(R.id.edtRegisterAria);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Đăng ký");
        alert.setView(alertLayout);
        alert.setCancelable(false);

        //cancel
        alert.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dislayDialogLogin1();
            }
        });

        //next
        alert.setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // code for matching password
                Toast.makeText(getBaseContext(), "Next", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }


    //ham anh xa
    private void anhXa(){
        btnRegister = findViewById(R.id.btnRegister);
    }
}