package com.example.cureya_chatbot;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserNameActivity extends AppCompatActivity {
    private TextInputLayout edtUserName;
    private TextView txtNext;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    //UserInfo userInfo;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name);
        getSupportActionBar().hide();
        edtUserName=findViewById(R.id.edtNameDetail);
        txtNext=findViewById(R.id.txtNameNext);
        username=edtUserName.getEditText().toString();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        // userInfo=new UserInfo();

        txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(username)) {

                    Toast.makeText(UserNameActivity.this, "Please Enter name.", Toast.LENGTH_SHORT).show();
                } else {

                    addDatatoFirebase(edtUserName.getEditText().toString());
                    Intent intent= new Intent(UserNameActivity.this,ChatNavigation.class);
                    startActivity(intent);
                    finish();
                }



            }
        });
    }

    private void addDatatoFirebase(String name) {
        // below 3 lines of code is used to set
        // data in our object class.
        //userInfo.setuserName(name);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(edtUserName.getEditText().toString());

                // after adding this data we are showing toast message.
                Toast.makeText(UserNameActivity.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserNameActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();

            }



        });
    }

}