package com.example.cystudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Hide Action Bar
        getSupportActionBar().hide();
    }

    public void navigateToRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        // Data validation (check if not empty) and get string from EditText values
        EditText usernameSpace = findViewById(R.id.email_login);
        EditText passwordSpace = findViewById(R.id.password_login);
        String username = usernameSpace.getText().toString();
        String password = passwordSpace.getText().toString();


        // if (!username.matches("") && !password.matches("")) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        // }
    }
}

