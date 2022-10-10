package com.example.cureya_chatbot.ui.quizpart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cureya_chatbot.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class QuizActivity extends AppCompatActivity {
    private EditText title,optn1,optn2,optn3;
    private Button save;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Quiz");
    private Button finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().hide();
        title = findViewById(R.id.title);
        optn1 = findViewById(R.id.optn1);
        optn2 = findViewById(R.id.optn2);
        optn3 = findViewById(R.id.optn3);
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = title.getText().toString();
                String option1 = optn1.getText().toString();
                String option2 =optn2.getText().toString();
                String option3 =optn3.getText().toString();

                HashMap<String , String> userMap = new HashMap<>();

                userMap.put("title" , question);
                userMap.put("optn1" , option1);
                userMap.put("optn2" , option2);
                userMap.put("optn3" , option3);

                root.push().setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(QuizActivity.this, "Data Uploaded", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(QuizActivity.this,ViewActivity.class));
                        finish();
                    }
                });
            }
        });

    }
}