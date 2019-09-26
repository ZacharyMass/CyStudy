package com.example.cystudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cystudy.ui.classes.StudentHomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.AccessController;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.security.AccessController.getContext;

public class LoginActivity extends AppCompatActivity {

    private String url = "https://google.com"; // This will need to be filled in once server guys let us know

    private EditText usernameSpace = findViewById(R.id.email_login);
    private EditText passwordSpace = findViewById(R.id.password_login);
    private Button btn = findViewById(R.id.login_button);
    public static String class1, class2;

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
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser(){

        final String username = usernameSpace.getText().toString().trim();
        final String password = passwordSpace.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        parseData(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",username);
                params.put("password",password);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void parseData(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {
                JSONArray dataArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {

                    JSONObject dataobj = dataArray.getJSONObject(i);
                    class1 = dataobj.getString("class1");
                    class2 = dataobj.getString("class2");
                }

                Intent intent = new Intent(getApplicationContext(), StudentHomeFragment.class);
                startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
