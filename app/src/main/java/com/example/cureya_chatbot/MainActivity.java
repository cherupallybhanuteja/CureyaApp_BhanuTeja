package com.example.cureya_chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button navButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
navButton = findViewById(R.id.nav_Buttton);
navButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        goToNav();
    }
});
    }


    public void goToNav() {

        Intent goTo = new Intent(MainActivity.this , ChatNavigation.class);
        startActivity(goTo);

    }
}