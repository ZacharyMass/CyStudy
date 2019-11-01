package com.example.cystudy.ui.fragments.TeacherFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.example.cystudy.MainActivity;
import com.example.cystudy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static androidx.navigation.ui.NavigationUI.setupWithNavController;

public class TeacherClassFragment extends Fragment {

    public static String className;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_teacher_class, container, false);

        TextView pageTitle = v.findViewById(R.id.teacherClassPageTitle);
        pageTitle.setText(className + " Home");

        Button viewSetsButton = v.findViewById(R.id.teacherViewFlashcards);

        viewSetsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavigationView bottomNavView = MainActivity.bottomNavigationView;
                NavController navController = MainActivity.navController;
                setupWithNavController(bottomNavView, navController);
                navController.navigate(R.id.action_teacherClassFragment_to_teacherFlashcardFragment);
            }
        });

        return v;
    }
}
