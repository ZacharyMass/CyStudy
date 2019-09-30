package com.example.cystudy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

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

    public void navigateToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void register(View view) {
        // DO DATA VALIDATION, if good, login and navigate

        // Establish Queue
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://coms-309-jr-7.misc.iastate.edu:8080/add-user?username=insertUsername&pass=insertPassword&role=studentOrProfessor";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        Toast toast = Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT);
                        toast.show();
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String response = "Connected, Error Occured";
                        Log.d("Error.Response", response);
                        Toast toast = Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG);
                        toast.show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", "Brad");
                params.put("password", "password123");
                params.put("role", "student");

                return params;
            }
        };
        queue.add(postRequest);
    }
}
