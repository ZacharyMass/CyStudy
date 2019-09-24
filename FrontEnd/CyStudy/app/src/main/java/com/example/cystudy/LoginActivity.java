package com.example.cystudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Hide Action Bar
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    public void navigateToRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        // DO DATA VALIDATION, if good, login and navigate (see in navigateToStudentStats function)
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void navigateToStudentStats(View view) {
        // Data validation (check if not empty) and get string from EditText values
        EditText usernameSpace = findViewById(R.id.email_login);
        EditText passwordSpace = findViewById(R.id.password_login);
        String username = usernameSpace.getText().toString();
        String password = passwordSpace.getText().toString();

        // Proceed after checking (if valid info)
        // The verification here will have to parse SQL database for username
        // If username found, will have to match password to it
        // if (!username.matches("") && !password.matches("")) {
            Intent intent = new Intent(this, StudentStatsActivity.class);
            startActivity(intent);
        // }
    }
}
