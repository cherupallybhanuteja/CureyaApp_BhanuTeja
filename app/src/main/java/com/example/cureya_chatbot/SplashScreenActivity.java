package com.example.cureya_chatbot;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;


import com.sasank.roundedhorizontalprogress.ProgressBarAnimation;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;


import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {
    private ProgressBar progressLoading;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.colorBlack));

        progressLoading =findViewById(R.id.progLoading);
        progressLoading.setMax(100);
        progressLoading.setScaleY(2f);
        progressAnimation();

    }

    private void progressAnimation(){
        ProgressAnimation animation=new ProgressAnimation(this,progressLoading,0f,100f);
        animation.setDuration(3000);
        progressLoading.setAnimation(animation);

    }
}