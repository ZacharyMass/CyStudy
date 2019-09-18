package com.example.cystudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
        // DO DATA VALIDATION, if good, login and navigate
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void navigateToStudentStats(View view) {
        Intent intent = new Intent(this, StudentStatsActivity.class);
        startActivity(intent);
    }
}
