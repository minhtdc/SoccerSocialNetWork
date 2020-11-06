package com.example.soccersocialnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soccersocialnetwork.data_models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private support_func support_func;
    Animation topAnimation, bottomAnimation;
    Button btnRegister, btnLogin;
    TextView txtFogetPassword, txtLogo;
    ImageView imgLogo;
    EditText edtLoginEmail, edtLoginPassword;
    private FirebaseAuth fAuth;
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
                Toast.makeText(getBaseContext(), getDate("yyyy") + ";" + getDate("MM") + ";" + getDate("dd"), Toast.LENGTH_SHORT).show();
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

        final AlertDialog alert = new AlertDialog.Builder(this)
                .setView(alertLayout)
                .setTitle("Đăng ký")
                .setPositiveButton("Tiếp", null)
                .setNegativeButton("Hủy", null)
                .create();
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button btnHuy = ((AlertDialog) alert).getButton(AlertDialog.BUTTON_NEGATIVE);
                Button btnTiep = ((AlertDialog) alert).getButton(AlertDialog.BUTTON_POSITIVE);
                //xử lý ấn trên dialog
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert.dismiss();
                    }
                });
                btnTiep.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String userMail = edtDialogMail.getText().toString();
                        final String userName = edtDialogName.getText().toString();
                        if(userMail.equalsIgnoreCase("") == false && userName.equalsIgnoreCase("") == false) {
                            if(support_func.isValidEmailId(userMail)) {
                                user.setUserEmail(userMail);
                                user.setUserName(userName);
                                alert.dismiss();
                                dislayDialogLogin2();
                            }
                            else {
                                Toast.makeText(getBaseContext(), "Sai định dạng email!", Toast.LENGTH_SHORT).show();

                            }
                        }
                        else {
                            Toast.makeText(getBaseContext(), "Vui lòng nhập đủ dữ liệu!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
        alert.show();
    }

    //hiển thị dialog 2
    private void dislayDialogLogin2() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_login_2_layout, null);
        final EditText edtDialogBirthday = (EditText) alertLayout.findViewById(R.id.edtDialogBirthday);
        final EditText edtDialogAria = (EditText) alertLayout.findViewById(R.id.edtDialogAria);

        edtDialogBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Date Select Listener.
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        edtDialogBirthday.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                };

                // Create DatePickerDialog (Spinner Mode):
                DatePickerDialog datePickerDialog = new DatePickerDialog(LoginActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                        dateSetListener, getDate("yyyy"), getDate("MM"), getDate("dd"));

                // Show
                datePickerDialog.show();
            }
        });

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
        fAuth = FirebaseAuth.getInstance();
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_login_3_layout, null);
        final EditText edtDialogPass = (EditText) alertLayout.findViewById(R.id.edtDialogPass);
        final EditText edtDialogRePass = (EditText) alertLayout.findViewById(R.id.edtDialogRePass);

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
                final String pass = edtDialogPass.getText().toString();
                final String Repass = edtDialogRePass.getText().toString();
                if (pass.equals(Repass)) {
                    fAuth.createUserWithEmailAndPassword(user.getUserEmail(), pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull final Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                try {
                                    addUserInformation(fAuth.getUid(), user);//Add information of new user sign up
                                    Toast.makeText(getBaseContext(), "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            } else {
                                Toast.makeText(getBaseContext(), "Tạo tài khoản thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getBaseContext(), "Mật khẩu không trùng khớp!", Toast.LENGTH_SHORT).show();
                    dislayDialogLogin3();
                }
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    //ham anh xa
    private void anhXa() {
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
        json.put("userAria", user.getUserAria());
        return json;
    }

    //lấy ngày giờ
    public static int getDate(String data) {
        //lấy ngày giờ hệ thống
        SimpleDateFormat sdf = new SimpleDateFormat(data, Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return Integer.parseInt(currentDateandTime);
    }
}