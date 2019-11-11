package com.example.cystudy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cystudy.ui.fragments.TeacherFragments.TeacherClassFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.security.AccessController.getContext;

/**
 * Register page for users not currently in database
 * @author Zach Mass
 */
public class RegisterActivity extends AppCompatActivity {

    String userRole;

    /**
     * Populate role spinner on creation of page
     * @param savedInstanceState Bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Hide Action Bar
        getSupportActionBar().hide();

        // Populate Role Spinner
        String[] roles = {"Student", "Teacher", "Administrator"};
        setContentView(R.layout.activity_register);
        Spinner spin = (Spinner) findViewById(R.id.role_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    /**
     * Navigates back to login page on click
     * @param view View object
     */
    public void navigateToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Takes in text fields filled in by user, verifies some Strings, makes request to server to create user
     * @param view View object
     */
    public void register(View view) {
        // Define Views
        final EditText email = findViewById(R.id.email_register);
        final EditText pass = findViewById(R.id.password_register);
        final EditText passConf = findViewById(R.id.password_confirm_register);
        final Spinner role = findViewById(R.id.role_spinner);

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
        else if(!pass.getText().toString().equals(passConf.getText().toString()))
        {
            Log.d("Passwords don't match", "pass = "+pass.getText()+" passConf = "+passConf.getText());
            Toast.makeText(this, "Passwords Do Not Match", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            String URL = "http://coms-309-jr-7.misc.iastate.edu:8080/add-user";
            JSONObject jsonBody = new JSONObject();
            RequestQueue addUserQueue = Volley.newRequestQueue(getApplicationContext());

            jsonBody.put("username", email.getText());
            jsonBody.put("password", pass.getText());
            jsonBody.put("role", role.getSelectedItem().toString());
            userRole = role.getSelectedItem().toString();

            JsonObjectRequest addUserRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // Need some form of verification here - ran into errors, but moving on to complete more work

                    // Toast User Creation
                    Toast toast = Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_SHORT);
                    toast.show();

                    // Navigate Home
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("addUser Error", error.toString());
                }
            });

            addUserQueue.add(addUserRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*
        // Establish Queue
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://coms-309-jr-7.misc.iastate.edu:8080/add-user";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);

                        // Toast User Creation
                        Toast toast = Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_SHORT);
                        toast.show();

                        // Navigate Home
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Show Error
                        String response = "Error Occurred: " + error.getMessage();
                        Log.d("Error.Response", response);
                        Toast toast = Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG);
                        toast.show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", email.getText().toString());
                params.put("pass", pass.getText().toString());
                params.put("role", role.getSelectedItem().toString().toLowerCase());

                Log.d("Tag", params.toString());

                return params;
            }
        };

        // Send to Server
        queue.add(postRequest);
         */
    }
}
