package com.example.cystudy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    public static String user; // Will be initialized fully if response from server is "true"

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
        EditText email = findViewById(R.id.email_login);
        EditText pass = findViewById(R.id.password_login);
        final String username = email.getText().toString();
        String password = pass.getText().toString();

        // DO DATA VALIDATION, if good, login and navigate, else Toast and return
        if(email.getText().length() == 0)
        {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass.getText().length() == 0)
        {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        String URL = "http://coms-309-jr-7.misc.iastate.edu:8080/user-exists?username=";
        URL += username; // Adds string to end
        RequestQueue loginQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Login Response", response); // For testing

                if (response.matches("True")) {
                    user = username; // Initialize global variable
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Show Error
                String response = "Error Occurred: " + error.getMessage();
                Log.d("Error.Response", response);
                Toast toast = Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG);
                toast.show();
            }
        });

        loginQueue.add(stringRequest);
    }
}