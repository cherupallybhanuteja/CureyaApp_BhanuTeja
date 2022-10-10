package com.example.cureya_chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserLocationActivity extends AppCompatActivity {
    ImageView imgLocation,imgBack;
    TextView txtLocation;
    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_location);
        getSupportActionBar().hide();

        imgLocation = findViewById(R.id.imgLocation);
        txtLocation=findViewById(R.id.txtLocationCityState);
        btnDone=findViewById(R.id.btnLocationDone);
        imgBack=findViewById(R.id.imgBackArrowLocation);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UserLocationActivity.this,UserProfessionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UserLocationActivity.this,ChatNavigation.class);
                startActivity(intent);
                finish();
            }
        });
    }
}