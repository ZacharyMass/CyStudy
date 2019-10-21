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

        if (navController.getCurrentDestination().getId() == R.id.blankConditionalNavFragment) {
            if (userRole.matches("student")) {
                navController.navigate(R.id.action_blankConditionalNavFragment_to_studentHomeFragment);
            }
            else { // userRole.matches("teacher")
                navController.navigate(R.id.action_blankConditionalNavFragment_to_teacherHomeFragment);
            }
        }
    }

}

