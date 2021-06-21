package com.example.quizapphanan;

import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.nio.file.Path;
import java.util.regex.Pattern;


public class signup extends AppCompatActivity implements View.OnClickListener {

    private TextView register, registerUser;
    private EditText email, password, confirmPassword;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        register = (TextView) findViewById(R.id.btnLogin);
        register.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.btnSignUp);
        registerUser.setOnClickListener(this);

        email = (EditText) findViewById(R.id.etEmailAddress);
        password = (EditText) findViewById(R.id.etPassword);
        confirmPassword = (EditText) findViewById(R.id.etConfirmPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.btnSignUp:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String emailId = email.getText().toString().trim();
        String passwordId = password.getText().toString().trim();
        String cPassword = confirmPassword.getText().toString().trim();

        if (emailId.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }


        if (passwordId.isEmpty()) {
            password.setError("password is required");
            password.requestFocus();
            return;
        }

        if (passwordId.length() < 6) {
            password.setError("Correct password Length is required");
            password.requestFocus();
            return;

        }

        if (cPassword.isEmpty()) {
            confirmPassword.setError("Confirm password is required");
            confirmPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(emailId,passwordId).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //if task is successful verify page will open
                            User user = new User(email,password);
                            FirebaseDatabase.getInstance().getReference("Users")
                             .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                             .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull  Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(signup.this, "user has been registered successfully", Toast.LENGTH_LONG).show();
                                        finish();
                                    } else {
                                        Toast.makeText(signup.this, "Fail to register! Try Again", Toast.LENGTH_LONG).show();

                                    }
                                    progressBar.setVisibility(View.GONE);
                                }
                            });

                        }else{
                            Toast.makeText(signup.this, "Fail to register! Try Again", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                }
        );




    }
}