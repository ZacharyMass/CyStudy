package com.example.cystudy;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static androidx.navigation.ui.NavigationUI.setupWithNavController;

/**
 * Basis for all conditional navigation in app. Directs user to appropriate set of fragments to navigate within.
 *
 * @author Zach Mass
 */
public class MainActivity extends AppCompatActivity {

    public static String url = "coms-309-jr-7.misc.iastate.edu";
    public static String user = LoginActivity.user;
    public static String currentClass = "COMS309";
    public static String userRole = LoginActivity.role; // Will be initialized via a String Request to server
    public static BottomNavigationView bottomNavigationView;
    public static NavController navController;

    /**
     * Initializes bottom navigation bar, directs user to appropriate set of pages based on user role.
     *
     * @param savedInstanceState Bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainAct Role", userRole);

        // Hide action bar
        getSupportActionBar().hide();

        // Handle Navigation between fragments with Bottom Nav Bar
        bottomNavigationView = findViewById(R.id.navigation);
        navController = Navigation.findNavController(this, R.id.conditionalNavigation);
        setupWithNavController(bottomNavigationView, navController);

        if (navController.getCurrentDestination().getId() == R.id.blankConditionalNavFragment) {
            if (userRole.matches("student")) {
                navController.navigate(R.id.action_blankConditionalNavFragment_to_studentHomeFragment);
            } else { // userRole.matches("teacher")
                navController.navigate(R.id.action_blankConditionalNavFragment_to_teacherHomeFragment);
            }
        }

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
                        } else if (navController.getCurrentDestination().getId() == R.id.teacherHomeFragment) {
                            switch (menuItem.getItemId()) {
                                case R.id.action_stats:
                                    navController.navigate(R.id.action_teacherHomeFragment_to_teacherStatsFragment);
                                    break;
                                case R.id.action_settings:
                                    navController.navigate(R.id.action_teacherHomeFragment_to_teacherSettingsFragment);
                                    break;
                            }
                        } else if (navController.getCurrentDestination().getId() == R.id.teacherStatsFragment) {
                            switch (menuItem.getItemId()) {
                                case R.id.action_home:
                                    navController.navigate(R.id.action_teacherStatsFragment_to_teacherHomeFragment);
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
                        } else if (navController.getCurrentDestination().getId() == R.id.teacherClassFragment) {
                            switch (menuItem.getItemId()) {
                                case R.id.action_stats:
                                    navController.navigate(R.id.action_teacherClassFragment_to_teacherStatsFragment);
                                    break;
                                case R.id.action_home:
                                    navController.navigate(R.id.action_teacherClassFragment_to_teacherHomeFragment);
                                    break;
                                case R.id.action_settings:
                                    navController.navigate(R.id.action_teacherClassFragment_to_teacherSettingsFragment);
                                    break;
                            }
                        } else if (navController.getCurrentDestination().getId() == R.id.teacherSetsFragment) {
                            switch (menuItem.getItemId()) {
                                case R.id.action_stats:
                                    navController.navigate(R.id.action_teacherSetsFragment_to_teacherStatsFragment);
                                    break;
                                case R.id.action_home:
                                    navController.navigate(R.id.action_teacherSetsFragment_to_teacherHomeFragment);
                                    break;
                                case R.id.action_settings:
                                    navController.navigate(R.id.action_teacherSetsFragment_to_teacherSettingsFragment);
                                    break;
                            }
                        } else if (navController.getCurrentDestination().getId() == R.id.gameFragment) {
                            switch (menuItem.getItemId()) {
                                case R.id.action_settings:
                                    navController.navigate(R.id.action_gameFragment_to_settingsFragment);
                                    break;
                                case R.id.action_stats:
                                    navController.navigate(R.id.action_gameFragment_to_studentStatsFragment);
                                    break;
                                case R.id.action_home:
                                    navController.navigate(R.id.action_gameFragment_to_studentHomeFragment);
                                    break;
                            }
                        }
                        return true;
                    }
                }
        );

    }
}