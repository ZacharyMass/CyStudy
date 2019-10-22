package com.example.cystudy;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import static androidx.navigation.ui.NavigationUI.setupWithNavController;

public class MainActivity extends AppCompatActivity {

    public static RequestQueue queue;
    public static String url = "coms-309-jr-7.misc.iastate.edu";
    public static String user = LoginActivity.user;
    public static String currentClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = LoginActivity.user;

        // Hide action bar
        getSupportActionBar().hide();

        //Initiate RequestQueue
        queue = Volley.newRequestQueue(this);

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

}
