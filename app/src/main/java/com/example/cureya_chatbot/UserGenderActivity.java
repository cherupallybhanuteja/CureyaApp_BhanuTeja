package com.example.cureya_chatbot;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UserGenderActivity extends AppCompatActivity {
    private ImageView imgBack,imgMale,imgFemale;
    private TextView txtNext,txtFemale,txtMale;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_gender);
        getSupportActionBar().hide();

        imgBack=findViewById(R.id.imgBackArrowGender);
        imgFemale=findViewById(R.id.imgFemaleLogo);
        imgMale=findViewById(R.id.imgMaleLogo);
        txtNext=findViewById(R.id.txtGenderNext);
        txtFemale=findViewById(R.id.txtFemale);
        txtMale=findViewById(R.id.txtMale);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UserGenderActivity.this,UserNameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imgFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgFemale.setImageDrawable(getDrawable(R.drawable.female_selected));
                imgMale.setImageDrawable(getDrawable(R.drawable.male_unselected));
                txtFemale.setTextColor(getResources().getColor(R.color.colorSecondaryGreen));
                txtMale.setTextColor(getResources().getColor(R.color.colorSecondaryAlphaGreen));

            }
        });

        imgMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgMale.setImageDrawable(getDrawable(R.drawable.male_selected));
                imgFemale.setImageDrawable(getDrawable(R.drawable.female_unselected));
                txtMale.setTextColor(getResources().getColor(R.color.colorSecondaryGreen));
                txtFemale.setTextColor(getResources().getColor(R.color.colorSecondaryAlphaGreen));
            }
        });

        txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UserGenderActivity.this,UserAgeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}