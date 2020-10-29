package com.example.soccersocialnetwork;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPasswordActivity extends AppCompatActivity {
    Button btnForgetPassword, btnBackLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password_layout);
        anhXa();

        //hide action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //action
        btnBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        btnForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dislayDialogForgetPW();
            }
        });

    }

    //show dialog
    private void dislayDialogForgetPW() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_login_3_layout, null);
        EditText edtRegisterPW = (EditText) alertLayout.findViewById(R.id.edtForgetPassword);
        EditText edtRegisterRePW = (EditText) alertLayout.findViewById(R.id.edtReForgetPassword);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Quên mật khẩu");
        alert.setView(alertLayout);
        alert.setCancelable(false);

        //cancel
        alert.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //next
        alert.setPositiveButton("Xong", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // code for matching password
                Toast.makeText(getBaseContext(), "Xong", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }


    //ánh xạ
    private void anhXa() {
        btnForgetPassword = findViewById(R.id.btnLogin);
        btnBackLogin = findViewById(R.id.btnBackLogin);
    }
}