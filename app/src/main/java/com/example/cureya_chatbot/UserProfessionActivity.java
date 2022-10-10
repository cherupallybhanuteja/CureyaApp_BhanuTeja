package com.example.cureya_chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfessionActivity extends AppCompatActivity {
    ImageView imgBack;
    TextView txtNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profession);
        getSupportActionBar().hide();

        imgBack=findViewById(R.id.imgBackArrowProfession);
        txtNext=findViewById(R.id.txtProfessionNext);

        txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UserProfessionActivity.this,UserLocationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UserProfessionActivity.this,UserAgeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}