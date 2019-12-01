package com.example.cystudy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Objects;

/**
 * Login page for every user. Contains two text entry fields (username, password), as well as link to 'Register' page
 * @author Zach Mass
 */
public class LoginActivity extends AppCompatActivity {

    public static String user; // Will be initialized fully if response from server is "true"
    public static String role; // Will be initialized after valid user confirmed

    /**
     * Links view to appropriate XML file, hides the action bar.
     * @param savedInstanceState Bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Hide Action Bar
        getSupportActionBar().hide();
    }

    /**
     * Moves to Register activity
     * @param view the current View object
     */
    public boolean navigateToRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        return true;
    }

    /**
     * Makes stringRequest to server to verify existence of username and validate password
     * @param view the current View object
     */
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
                String stringResponse = response + "";
                stringResponse = stringResponse.toLowerCase();

                Log.d("Login Response", stringResponse);

                if (stringResponse.matches("true")) {
                    user = username; // Initialize global variable
                    getRole(user);
                    // Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    // startActivity(intent);
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

    /**
     * Gets role of user via stringRequest, which is used in conditional navigation
     * @param username the username of current user
     */
    public void getRole(String username) {
        String URL = "http://coms-309-jr-7.misc.iastate.edu:8080/get-role?username=";
        URL += username; // This is the user that logged in and now needs conditional navigation
        RequestQueue roleQueue = Volley.newRequestQueue(this);

        StringRequest roleRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Role Response", response); // For testing
                role = response;
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Role Request Error", Objects.requireNonNull(error.getMessage())); // For testing
            }
        });

        roleQueue.add(roleRequest);
    }
}