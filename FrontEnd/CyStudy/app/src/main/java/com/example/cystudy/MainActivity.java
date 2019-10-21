package com.example.cystudy;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
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

    public static String userRole = LoginActivity.role; // Will be initialized via a String Request to server

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainAct Role", userRole);

        // Hide action bar
        getSupportActionBar().hide();

        // Handle Navigation between fragments with Bottom Nav Bar
        final BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        final NavController navController = Navigation.findNavController(this, R.id.conditionalNavigation);
        setupWithNavController(bottomNavigationView, navController);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {

                    /**
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
                    **/

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        if (navController.getCurrentDestination().getId() == R.id.blankConditionalNavFragment) {
                            if (userRole.matches("student")) {
                                navController.navigate(R.id.action_blankConditionalNavFragment_to_studentHomeFragment);
                            }
                            else { // userRole.matches("teacher")
                                navController.navigate(R.id.action_blankConditionalNavFragment_to_teacherHomeFragment);
                            }
                        }

                        return true;
                    }
                }
        );
    }

}

