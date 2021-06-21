package com.example.quizapphanan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class Login extends AppCompatActivity implements View.OnClickListener {

    private TextView register;
    private EditText email, password;
    private Button signIn;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = (TextView) findViewById(R.id.btnSignUp);
        register.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.btnLogin);
        signIn.setOnClickListener(this);

        email = (EditText) findViewById(R.id.etEmailAddress);
        password = (EditText) findViewById(R.id.etPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        mAuth =FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignUp:
                startActivity(new Intent( this, signup.class));
                finish();
                break;
            case R.id.btnLogin:
                userLogin();
                break;
        }

    }

    private void userLogin() {
        String emailId = email.getText().toString().trim();
        String passwordId = password.getText().toString().trim();

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

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(emailId,passwordId).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull  Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(Login.this, MainActivity.class));
                    Toast.makeText(Login.this, "Successfully LoggedIn!", Toast.LENGTH_LONG).show();
                    finish();

                } else {
                    Toast.makeText(Login.this, "Failed to Login! Try Again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
