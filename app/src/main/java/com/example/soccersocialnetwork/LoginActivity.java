package com.example.soccersocialnetwork;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.soccersocialnetwork.TranDuyHuynh.home_layout;
import com.example.soccersocialnetwork.data_models.Users;
import com.example.soccersocialnetwork.football_field_owner.database.DataBaseHelper;
import com.example.soccersocialnetwork.football_field_owner.model.City;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private String aloo;
    private support_func support_func;
    Animation topAnimation, bottomAnimation;
    Button btnRegister, btnLogin;
    TextView txtFogetPassword, txtLogo;
    ImageView imgLogo;
    EditText edtLoginEmail, edtLoginPassword;
    public static FirebaseAuth fAuth = FirebaseAuth.getInstance();
    Users user = new Users();
    public static String USER_ID_CURRENT;
    public static String USER_NAME_CURRENT;
    public static String USER_IMG_CURRENT;
    public static boolean IS_LOGIN;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    ArrayList<City> data_tp = new ArrayList<>();
    ArrayList<String> data_quan = new ArrayList<>();
    ArrayAdapter adapter_tp, adapter_quan;


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

        //kiểm tra trạng thái đăng nhập
        initPreferences();
        if (sharedPreferences.getBoolean("IS_LOGIN", IS_LOGIN) == true) {
            USER_ID_CURRENT = sharedPreferences.getString("USER_ID_CURRENT", USER_ID_CURRENT);
            USER_NAME_CURRENT = sharedPreferences.getString("USER_NAME_CURRENT", USER_NAME_CURRENT);
            Intent intent = new Intent(LoginActivity.this, home_layout.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish();
        }


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
                if (isOnline()) {
                    String userName = edtLoginEmail.getText().toString();
                    String userPass = edtLoginPassword.getText().toString();
                    if (!userName.equalsIgnoreCase("") || !userPass.equalsIgnoreCase("")) {
                        fAuth.signInWithEmailAndPassword(userName, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull final Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    //lấy thông tin người dùng
                                    USER_ID_CURRENT = fAuth.getCurrentUser().getUid();
                                    IS_LOGIN = true;
                                    editor.putString("USER_ID_CURRENT", USER_ID_CURRENT);
                                    editor.putBoolean("IS_LOGIN", IS_LOGIN);
//                              editor.putString("USER_NAME_CURRENT", USER_NAME_CURRENT);
                                    editor.commit();
                                    // Toast.makeText(LoginActivity.this, USER_NAME_CURRENT, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, home_layout.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(LoginActivity.this, "Lỗi! Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                    } else {
                        Toast.makeText(LoginActivity.this, "Vui lòng nhập đủ dữ liệu!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Không có kết nối internet", Toast.LENGTH_SHORT).show();
                }
            }

        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOnline()) {
                    dislayDialogLogin1();
                } else {
                    Toast.makeText(LoginActivity.this, "Không có kết nối internet", Toast.LENGTH_SHORT).show();

                }
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

    //Hiển thị dialog signup 1
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
                        if (userMail.equalsIgnoreCase("") == false || userName.equalsIgnoreCase("") == false) {
                            if (support_func.isValidEmailId(userMail)) {
                                user.setUserEmail(userMail);
                                user.setUserName(userName);
                                alert.dismiss();
                                dislayDialogLogin2();
                            } else {
                                Toast.makeText(getBaseContext(), "Sai định dạng email!", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(getBaseContext(), "Vui lòng nhập đủ dữ liệu!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
        alert.show();
    }

    //hiển thị dialog signup 2
    private void dislayDialogLogin2() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_login_2_layout, null);
        final EditText edtDialogBirthday = (EditText) alertLayout.findViewById(R.id.edtDialogBirthday);
        final Spinner spnDialogThanhPho = (Spinner) alertLayout.findViewById(R.id.spnDialogThanhPho);
        final Spinner spnDialogQuan = (Spinner) alertLayout.findViewById(R.id.spnDialogQuan);


        //enable
        edtDialogBirthday.setInputType(InputType.TYPE_NULL);

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

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
        dataBaseHelper.createDataBase();
        dataBaseHelper.openDataBase();

        data_tp = dataBaseHelper.getAllCity();
        setAdapterSpinner(data_tp, adapter_tp, spnDialogThanhPho);
        spnDialogThanhPho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
                City items = data_tp.get(i);
                data_quan = dataBaseHelper.getAllDistrict(items.getId());
                setAdapterSpinner(data_quan, adapter_quan, spnDialogQuan);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        final AlertDialog alert = new AlertDialog.Builder(this)
                .setView(alertLayout)
                .setTitle("Đăng ký")
                .setPositiveButton("Tiếp", null)
                .setNegativeButton("Quay lại", null)
                .create();

        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button btnQuayLai = ((AlertDialog) alert).getButton(AlertDialog.BUTTON_NEGATIVE);
                Button btnTiep = ((AlertDialog) alert).getButton(AlertDialog.BUTTON_POSITIVE);

                btnQuayLai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert.dismiss();
                        dislayDialogLogin1();
                    }
                });

                btnTiep.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String birthDay = edtDialogBirthday.getText().toString();
                        String userAria = spnDialogThanhPho.getSelectedItem().toString() + ", " + spnDialogQuan.getSelectedItem().toString();
                        if (birthDay.equalsIgnoreCase("") || userAria.equalsIgnoreCase("")) {
                            Toast.makeText(getBaseContext(), "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                        } else {
                            user.setUserBirth(birthDay);
                            user.setUserAria(userAria);
                            alert.dismiss();
                            dislayDialogLogin3();
                        }
                    }
                });

            }
        });
        alert.show();
    }

    //hiển thị dialog signup 3
    private void dislayDialogLogin3() {
        fAuth = FirebaseAuth.getInstance();
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_login_3_layout, null);
        final EditText edtDialogPass = (EditText) alertLayout.findViewById(R.id.edtDialogPass);
        final EditText edtDialogRePass = (EditText) alertLayout.findViewById(R.id.edtDialogRePass);

        final AlertDialog alert = new AlertDialog.Builder(this)
                .setView(alertLayout)
                .setTitle("Đăng ký")
                .setPositiveButton("Xong", null)
                .setNegativeButton("Quay lại", null)
                .create();

        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button btnQuayLai = ((AlertDialog) alert).getButton(AlertDialog.BUTTON_NEGATIVE);
                Button btnXong = ((AlertDialog) alert).getButton(AlertDialog.BUTTON_POSITIVE);

                btnQuayLai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert.dismiss();
                        dislayDialogLogin2();
                    }
                });

                btnXong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String pass = edtDialogPass.getText().toString();
                        final String Repass = edtDialogRePass.getText().toString();

                        if (pass.equals(Repass)) {
                            fAuth.createUserWithEmailAndPassword(user.getUserEmail(), pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull final Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        try {
                                            addUserInformation(fAuth.getUid(), user);//Add information of new user sign up
                                            alert.dismiss();
                                            Toast.makeText(getBaseContext(), "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    } else {
                                        Toast.makeText(getBaseContext(), "Lỗi: Tạo tài khoản thất bại!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(getBaseContext(), "Mật khẩu không trùng khớp!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        alert.show();
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
        json.put("userImage", "https://firebasestorage.googleapis.com/v0/b/soccersocialnetwork-733b3.appspot.com/o/imgUser%2Fimg_user.jpg?alt=media&token=5c0b05eb-1bf7-4ae3-8449-32cc7c3c162f");
        return json;
    }

    //lấy ngày giờ
    public static int getDate(String data) {
        //lấy ngày giờ hệ thống
        SimpleDateFormat sdf = new SimpleDateFormat(data, Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return Integer.parseInt(currentDateandTime);
    }

    //lưu thông tin vô bộ nhớ
    public void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        editor = sharedPreferences.edit();
    }

    //check internet
    public Boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnected()) {
            return true;
        }
        return false;
    }

    private void setAdapterSpinner(ArrayList data, ArrayAdapter adapter, Spinner spinner) {
        if (adapter == null) {
            adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data);
            spinner.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            adapter.notifyDataSetChanged();
            spinner.setSelection(adapter.getCount() - 1);
        }
    }

}