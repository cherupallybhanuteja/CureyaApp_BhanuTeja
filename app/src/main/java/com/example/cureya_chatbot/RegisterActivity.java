package com.example.cureya_chatbot;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
private TextInputEditText email;
private TextInputEditText password;
    private TextView submit;
    private FirebaseAuth mAuth;
    private TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        submit=findViewById(R.id.submit);
        login=findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();
//        navigate to login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
//        register user
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtemail=email.getText().toString();
                String txtpassword=password.getText().toString();
                if (TextUtils.isEmpty(txtemail) || TextUtils.isEmpty(txtpassword)
                        || TextUtils.isEmpty(txtemail) || TextUtils.isEmpty(txtpassword)){
                    Toast.makeText(RegisterActivity.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                } else if (txtpassword.length() < 6){
                    Toast.makeText(RegisterActivity.this, "Password too short!", Toast.LENGTH_SHORT).show();
                } else {
                   registerUser(txtemail, txtpassword );
                }
            }

            private void registerUser(String txtemail, String txtpassword) {

                mAuth.createUserWithEmailAndPassword(txtemail, txtpassword).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "user registered", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                            finish();
                        }else{
                            Toast.makeText(RegisterActivity.this, "account already exists or invalid credentials ", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }


        });
    }
}