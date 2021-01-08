package com.example.soccersocialnetwork.TranDuyHuynh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.adapter.searchUserAdapter;
import com.example.soccersocialnetwork.data_models.Users;
import com.example.soccersocialnetwork.support_func;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TimKiemUserActivity extends AppCompatActivity {
    AutoCompleteTextView edtSearch;
    ListView listSearch;
    DatabaseReference myRef;
    ArrayList<Users> usersArrayList = new ArrayList<>();
    private com.example.soccersocialnetwork.TranDuyHuynh.adapter.searchUserAdapter searchUserAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tim_kiem_user_layout);
        setTitle("Tìm kiếm người dùng");

        edtSearch = findViewById(R.id.edtSearchUser);
        listSearch = findViewById(R.id.listViewSearch);


        searchUserAdapter = new searchUserAdapter(TimKiemUserActivity.this, R.layout.search_user_adapter_layout, usersArrayList);
        listSearch.setAdapter(searchUserAdapter);


        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchUserAdapter.clear();
                if (edtSearch.getText().toString().equals("")) {
                    searchUserAdapter.clear();
                }
                else {
                    myRef = FirebaseDatabase.getInstance().getReference(String.format("users/"));
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            searchUserAdapter.clear();
                            for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                                String key= dataSnapshot.getKey();
                                Users users = dataSnapshot.getValue(Users.class);
                                if (support_func.isStrLike(edtSearch.getText().toString(), users.getUserName())) {
                                    users.setUserID(key);
                                    usersArrayList.add(users);
                                    searchUserAdapter.notifyDataSetChanged();
                                }
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}