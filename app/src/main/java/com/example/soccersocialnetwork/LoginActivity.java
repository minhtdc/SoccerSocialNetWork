package com.example.soccersocialnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soccersocialnetwork.data_models.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Animation topAnimation, bottomAnimation;
    Button btnRegister, btnLogin;
    TextView txtFogetPassword, txtLogo;
    ImageView imgLogo;
    EditText edtLoginEmail, edtLoginPassword;
    private FirebaseAuth auth;
    Users user = new Users();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_layout);
        //hide action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //Ánh xạ
        anhXa();

        //animation
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        imgLogo.setAnimation(topAnimation);
        txtLogo.setAnimation(topAnimation);
        btnLogin.setAnimation(bottomAnimation);
        btnRegister.setAnimation(bottomAnimation);
        txtFogetPassword.setAnimation(bottomAnimation);
        edtLoginEmail.setAnimation(bottomAnimation);
        edtLoginPassword.setAnimation(bottomAnimation);


        //action
        //login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dislayDialogLogin1();
            }
        });

        txtFogetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

    }

    //Hiển thị dialog login 1
    private void dislayDialogLogin1() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_login_1_layout, null);
        final EditText edtDialogMail = (EditText) alertLayout.findViewById(R.id.edtDialogMail);
        final EditText edtDialogName = (EditText) alertLayout.findViewById(R.id.edtDialogName);

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
                user.setUserEmail(edtDialogMail.getText().toString());
                user.setUserName(edtDialogName.getText().toString());
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
        final EditText edtDialogBirthday = (EditText) alertLayout.findViewById(R.id.edtDialogBirthday);
        final EditText edtDialogAria = (EditText) alertLayout.findViewById(R.id.edtDialogAria);

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
                user.setUserBirth(edtDialogBirthday.getText().toString());
                user.setUserAria(edtDialogAria.getText().toString());
                dislayDialogLogin3();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    //hiển thị dialog login 3
    private void dislayDialogLogin3() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_login_3_layout, null);
        final EditText edtDialogPass = (EditText) alertLayout.findViewById(R.id.edtDialogPass);
        EditText edtDialogRePass = (EditText) alertLayout.findViewById(R.id.edtDialogRePass);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Đăng ký");
        alert.setView(alertLayout);
        alert.setCancelable(false);

        //cancel
        alert.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dislayDialogLogin2();
            }
        });

        //next
        alert.setPositiveButton("Xong", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // code for matching password
                //final String pass = edtDialogPass.getText().toString();
                try {
                    addUserInformation("123", user);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    //ham anh xa
    private void anhXa(){
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        txtFogetPassword = findViewById(R.id.txtFogetPassword);
        txtLogo = findViewById(R.id.txtLogo);
        imgLogo = findViewById(R.id.imgLogo);
        edtLoginEmail = findViewById(R.id.edtLoginMail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);

    }

    //adduser
    private void addUserInformation(String UserID, Users user) throws JSONException {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> postValues = null;

        try {
            postValues = support_func.toMap(toJSON(user));
        } catch (JSONException e) {
        }

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/users/" + UserID, postValues);

        mDatabase.updateChildren(childUpdates);
    }

    //put user to firebase
    public JSONObject toJSON(Users user) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("userEmail", user.getUserEmail());
        json.put("userName", user.getUserName());
        json.put("userBirth", user.getUserBirth());
        json.put("userAria",  user.getUserAria());
        return json;
    }
}