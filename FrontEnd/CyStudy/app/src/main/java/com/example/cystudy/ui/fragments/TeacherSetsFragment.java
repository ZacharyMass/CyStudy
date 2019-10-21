package com.example.cystudy.ui.fragments;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.example.cystudy.MainActivity;
import com.example.cystudy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import static androidx.navigation.ui.NavigationUI.setupWithNavController;

public class TeacherSetsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_teacher_sets, container, false);

        // Adding a button programmatically will be good here

        Button set1button = v.findViewById(R.id.sampleSet1Name);

        set1button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavigationView bottomNavView = MainActivity.bottomNavigationView;
                NavController navController = MainActivity.navController;
                setupWithNavController(bottomNavView, navController);
                navController.navigate(R.id.action_teacherSetsFragment_to_teacherFlashcardFragment);
            }
        });

        return v;
    }
}
