package com.example.quizapphanan;

import android.widget.EditText;

public class User {

    public String fullEmail, pwd;

    public User(EditText email, EditText password) {

    }

    public User(String fullEmail, String pwd) {
        this.fullEmail = fullEmail;
        this.pwd = pwd;
    }
}
