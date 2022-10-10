package com.example.cureya_chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class UserAgeActivity extends AppCompatActivity {
    private TextView txtNext;
    private ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_age);
        getSupportActionBar().hide();

        txtNext=findViewById(R.id.txtAgeNext);
        imgBack=findViewById(R.id.imgBackArrowAge);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UserAgeActivity.this,UserGenderActivity.class);
                startActivity(intent);
                finish();
            }
        });
        txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UserAgeActivity.this,UserProfessionActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}