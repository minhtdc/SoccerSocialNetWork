package com.example.soccersocialnetwork.TranDuyHuynh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.data_models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TimKiemUserActivity extends AppCompatActivity {
    ImageButton btnSearch;
    EditText edtSearch;
    ListView listSearch;
    DatabaseReference myRef;
    ArrayList<String> usersArrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tim_kiem_user_layout);

        btnSearch = findViewById(R.id.imgBtnSearch);
        edtSearch = findViewById(R.id.edtSearchUser);
        listSearch = findViewById(R.id.listViewSearch);

        arrayAdapter = new ArrayAdapter<>(TimKiemUserActivity.this, android.R.layout.simple_list_item_1, usersArrayList);
        listSearch.setAdapter(arrayAdapter);

        myRef = FirebaseDatabase.getInstance().getReference(String.format("users/"));
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);
                usersArrayList.add(users.getUserName());
                arrayAdapter.add(users.getUserName());
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayAdapter.clear();
                for (String usersName: usersArrayList){
                    if(edtSearch.getText().toString().equalsIgnoreCase(usersName)){
                        arrayAdapter.add(usersName);
                        arrayAdapter.notifyDataSetChanged();

                    }
                }


            }
        });


    }
}