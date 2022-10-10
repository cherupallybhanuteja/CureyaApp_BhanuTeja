package com.example.cureya_chatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Locale;

public class ChangeLanguage extends AppCompatActivity {
ImageView imgBack;
CardView cardhindi,cardeng;
TextView txthindi,txteng;
    Context context;
    Resources resources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);
        getSupportActionBar().hide();

    cardhindi=findViewById(R.id.cardhindi);
    cardeng=findViewById(R.id.cardeng);
        txthindi=findViewById(R.id.txthindi);
        txteng=findViewById(R.id.txteng);
    cardhindi.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setLocale("hi");

            Toast.makeText(ChangeLanguage.this, "Switched to Hindi", Toast.LENGTH_SHORT).show();
            recreate();
        }
    });
        cardeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("en");

                Toast.makeText(ChangeLanguage.this, "Switched to English", Toast.LENGTH_SHORT).show();
                recreate();
            }
        });
    imgBack=findViewById(R.id.imgBackArrowLang);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ChangeLanguage.this,ChatNavigation.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setLocale(String lang){
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("settings",MODE_PRIVATE).edit();
        editor.apply();


    }
    public void loadLocale(){
        SharedPreferences preferences=getSharedPreferences("settings", Activity.MODE_PRIVATE);
        String language=preferences.getString("My_lang","");
        setLocale(language);
    }
}