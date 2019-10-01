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
        EditText usernameSpace = findViewById(R.id.email_login);
        EditText passwordSpace = findViewById(R.id.password_login);
        final String username = usernameSpace.getText().toString();
        String password = passwordSpace.getText().toString();

        checkValidUsername(username);
    }

    private void checkValidUsername(final String username) {
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
                Log.d("Login Error:", Objects.requireNonNull(error.getMessage())); // For testing
            }
        });

        loginQueue.add(stringRequest);
    }
}