package com.example.cystudy;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import static androidx.navigation.ui.NavigationUI.setupWithNavController;

public class MainActivity extends AppCompatActivity {

    public static RequestQueue queue;
    public static String url = "coms-309-jr-7.misc.iastate.edu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide action bar
        getSupportActionBar().hide();

        //Initiate RequestQueue
        queue = Volley.newRequestQueue(this);

        /** NOTE: in content_main.xml, I changed the width and height of the student fragment to 0dp to hide them and work on teacher pages

        // Handle Navigation between fragments with Bottom Nav Bar
        final BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        final NavController navController = Navigation.findNavController(this, R.id.studentFragment);
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

         **/

        // Handle Navigation between fragments with Bottom Nav Bar
        final BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        final NavController navController = Navigation.findNavController(this, R.id.teacherFragment);
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
                                    navController.navigate(R.id.action_teacherHomeFragment_to_teacherSettingsFragment);
                                    break;
                            }
                        } else if (navController.getCurrentDestination().getId() == R.id.teacherSettingsFragment) {
                            switch (menuItem.getItemId()) {
                                case R.id.action_stats:
                                    navController.navigate(R.id.action_teacherSettingsFragment_to_teacherStatsFragment);
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

}

