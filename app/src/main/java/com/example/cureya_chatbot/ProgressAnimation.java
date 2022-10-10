package com.example.cureya_chatbot;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class ProgressAnimation extends Animation {
    private Context context;
    private ProgressBar progressLoading;
    private float from;
    private float to;

    public ProgressAnimation(Context context,ProgressBar progressLoading,float from, float to){
        this.context=context;
        this.progressLoading=progressLoading;
        this.from=from;
        this.to=to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from +(to-from)* interpolatedTime;
        progressLoading.setProgress((int)value);

        if(value == to){
            if(FirebaseAuth.getInstance().getCurrentUser()!=null)
            {
                context.startActivity(new Intent(context,ChatNavigation.class));
            }
            else
            {
                context.startActivity(new Intent(context,LoginActivity.class));
            }


        }

    }
}
