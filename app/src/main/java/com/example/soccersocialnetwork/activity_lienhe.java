    package com.example.soccersocialnetwork;

    import android.Manifest;
    import android.annotation.SuppressLint;
    import android.content.Intent;
    import android.content.pm.PackageManager;
    import android.net.Uri;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.LinearLayout;
    import android.widget.Toast;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.app.ActivityCompat;

    public class activity_lienhe extends AppCompatActivity {
        LinearLayout linearLayoutHuynh, linearLayoutVu, linearLayoutTung, linearLayoutMinh, linearLayoutSang;
        String NumberPhone = "0906100493";
        private static final String LOG_TAG = "Call fail";
        private static final int MY_PERMISSION_REQUEST_CODE_CALL_PHONE = 555;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lienhe);
            linearLayoutHuynh = findViewById(R.id.lnHuynh);
            linearLayoutTung = findViewById(R.id.lnHuynh);
            linearLayoutVu = findViewById(R.id.lnHuynh);
            linearLayoutMinh = findViewById(R.id.lnHuynh);
            linearLayoutTung = findViewById(R.id.lnHuynh);

            linearLayoutHuynh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    askPermissionAndCall();
                }
            });

            linearLayoutTung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    askPermissionAndCall();
                }
            });
        }

        private void askPermissionAndCall() {

            // With Android Level >= 23, you have to ask the user
            // for permission to Call.
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) { // 23

                // Check if we have Call permission
                int sendSmsPermisson = ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE);

                if (sendSmsPermisson != PackageManager.PERMISSION_GRANTED) {
                    // If don't have permission so prompt the user.
                    this.requestPermissions(
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSION_REQUEST_CODE_CALL_PHONE
                    );
                    return;
                }
            }
            this.callNow();
        }

        @SuppressLint("MissingPermission")
        private void callNow() {


            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + NumberPhone));
            try {
                this.startActivity(callIntent);
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Your call failed... " + ex.getMessage(),
                        Toast.LENGTH_LONG).show();
                ex.printStackTrace();
            }
        }

        // When you have the request results
        @Override
        public void onRequestPermissionsResult(int requestCode,
                                               String permissions[], int[] grantResults) {

            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            //
            switch (requestCode) {
                case MY_PERMISSION_REQUEST_CODE_CALL_PHONE: {

                    // Note: If request is cancelled, the result arrays are empty.
                    // Permissions granted (CALL_PHONE).
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        Log.i( LOG_TAG,"Permission granted!");
                        Toast.makeText(this, "Permission granted!", Toast.LENGTH_LONG).show();

                        this.callNow();
                    }
                    // Cancelled or denied.
                    else {
                        Log.i( LOG_TAG,"Permission denied!");
                        Toast.makeText(this, "Permission denied!", Toast.LENGTH_LONG).show();
                    }
                    break;
                }
            }
        }

        // When results returned
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == MY_PERMISSION_REQUEST_CODE_CALL_PHONE) {
                if (resultCode == RESULT_OK) {
                    // Do something with data (Result returned).
                    Toast.makeText(this, "Action OK", Toast.LENGTH_LONG).show();
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Action Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Action Failed", Toast.LENGTH_LONG).show();
                }
            }
        }
    }