package com.example.cystudy;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import static androidx.navigation.ui.NavigationUI.setupWithNavController;

public class MainActivity extends AppCompatActivity {

    public static String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide action bar
        getSupportActionBar().hide();

        /*
        Make GET request to find role of user based on their login from LoginActivity
         */
        String URL = "http://coms-309-jr-7.misc.iastate.edu:8080/get-role?username=";
        URL += LoginActivity.user; // Adds string to end
        RequestQueue roleQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Role Response", response); // For testing

                role = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Role Error:", Objects.requireNonNull(error.getMessage())); // For testing
            }
        });

        roleQueue.add(stringRequest);

        if (role == "student") {
            // Handle Navigation between fragments with Bottom Nav Bar
            final BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
            final NavController navController = Navigation.findNavController(this, R.id.fragment);
            setupWithNavController(bottomNavigationView, navController);
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                            if (navController.getCurrentDestination().getId() == R.id.studentStatsFragment) {
                                switch (menuItem.getItemId()) {
                                    case R.id.action_home:
                                        navController.navigate(R.id.action_studentStatsFragment_to_studentHomeFragment);
                                        break;
                                    case R.id.action_settings:
                                        navController.navigate(R.id.action_studentStatsFragment_to_settingsFragment);
                                        break;
                                }
                            } else if (navController.getCurrentDestination().getId() == R.id.studentHomeFragment) {
                                switch (menuItem.getItemId()) {
                                    case R.id.action_stats:
                                        navController.navigate(R.id.action_studentHomeFragment_to_studentStatsFragment);
                                        break;
                                    case R.id.action_settings:
                                        navController.navigate(R.id.action_studentHomeFragment_to_settingsFragment);
                                        break;
                                }
                            } else if (navController.getCurrentDestination().getId() == R.id.settingsFragment) {
                                switch (menuItem.getItemId()) {
                                    case R.id.action_stats:
                                        navController.navigate(R.id.action_settingsFragment_to_studentStatsFragment);
                                        break;
                                    case R.id.action_home:
                                        navController.navigate(R.id.action_settingsFragment_to_studentHomeFragment);
                                        break;
                                }
                            }
                            return true;
                        }
                    }
            );
        }
        else { // User is teacher

            Log.d("Error?", "In Else-Block");

            // Handle Navigation between fragments with Bottom Nav Bar
            final BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
            final NavController navController = Navigation.findNavController(this, R.id.fragment);
            setupWithNavController(bottomNavigationView, navController);
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                            if (navController.getCurrentDestination().getId() == R.id.teacherStatsFragment) {
                                switch (menuItem.getItemId()) {
                                    case R.id.action_home:
                                        navController.navigate(R.id.action_teacherStatsFragment_to_teacherHomeFragment);
                                        break;
                                    case R.id.action_settings:
                                        navController.navigate(R.id.action_teacherStatsFragment_to_teacherSettingsFragment);
                                        break;
                                }
                            } else if (navController.getCurrentDestination().getId() == R.id.teacherHomeFragment) {
                                switch (menuItem.getItemId()) {
                                    case R.id.action_stats:
                                        navController.navigate(R.id.action_teacherHomeFragment_to_teacherStatsFragment);
                                        break;
                                    case R.id.action_settings:
                                        navController.navigate(R.id.action_teacherStatsFragment_to_teacherSettingsFragment);
                                        break;
                                }
                            } else if (navController.getCurrentDestination().getId() == R.id.teacherSettingsFragment) {
                                switch (menuItem.getItemId()) {
                                    case R.id.action_stats:
                                        navController.navigate(R.id.action_teacherSettingsFragment_to_teacherStatsFragment);
                                        break;
                                    case R.id.action_home:
                                        navController.navigate(R.id.action_teacherSettingsFragment_to_teacherHomeFragment);
                                        break;
                                }
                            }
                            return true;
                        }
                    }
            );
        }
    }

}
