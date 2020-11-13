package com.example.soccersocialnetwork;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {
    protected FirebaseAuth mFirebaseAuth;
    Button btnRegisterXacNhan, btnBackLogin;
    EditText edtForgetMail;

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
                finish();
            }
        });

        btnRegisterXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtForgetMail.getText().toString();
                if (email.equalsIgnoreCase("")) {
                    Toast.makeText(getBaseContext(), "Vui lòng nhập email.", Toast.LENGTH_SHORT).show();
                } else {
                    if(!support_func.isValidEmailId(email)){
                        Toast.makeText(getBaseContext(), "Sai định dạng email", Toast.LENGTH_SHORT).show();
                    }else {
                        mFirebaseAuth = FirebaseAuth.getInstance();
                        mFirebaseAuth.sendPasswordResetEmail(email)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        dislayDialogForgetPW();
                                    }
                                });
                    }
                }
            }
        });

    }

    //show dialog
    private void dislayDialogForgetPW() {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Đặt lại mật khẩu").setMessage("Chúng tôi đã gửi cho bạn một email vào hộp thư "
                + edtForgetMail.getText().toString() + ". Vui lòng kiểm tra lại để đặt lại mật khẩu!");
        alert.setCancelable(false);

        //ok
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }


    //ánh xạ
    private void anhXa() {
        btnRegisterXacNhan = findViewById(R.id.btnRegisterXacNhan);
        btnBackLogin = findViewById(R.id.btnBackLogin);
        edtForgetMail = findViewById(R.id.edtForgetMail);
    }

}