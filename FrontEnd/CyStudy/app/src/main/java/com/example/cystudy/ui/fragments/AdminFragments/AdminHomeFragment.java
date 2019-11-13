package com.example.cystudy.ui.fragments.AdminFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.example.cystudy.MainActivity;
import com.example.cystudy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static androidx.navigation.ui.NavigationUI.setupWithNavController;

public class AdminHomeFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_admin_home, container, false);

        Button adminEditClasses = v.findViewById(R.id.adminClassesBtn);
        Button adminEditTeachers = v.findViewById(R.id.adminTeachersBtn);
        Button adminEditStudents = v.findViewById(R.id.adminStudentsBtn);

        adminEditClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavigationView bottomNavView = MainActivity.bottomNavigationView;
                NavController navController = MainActivity.navController;
                setupWithNavController(bottomNavView, navController);
                navController.navigate(R.id.action_adminHomeFragment_to_adminManageClassesFragment);
            }
        });
        adminEditTeachers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavigationView bottomNavView = MainActivity.bottomNavigationView;
                NavController navController = MainActivity.navController;
                setupWithNavController(bottomNavView, navController);
                navController.navigate(R.id.action_adminHomeFragment_to_adminManageTeachersFragment);
            }
        });
        adminEditStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavigationView bottomNavView = MainActivity.bottomNavigationView;
                NavController navController = MainActivity.navController;
                setupWithNavController(bottomNavView, navController);
                navController.navigate(R.id.action_adminHomeFragment_to_adminManageClassesFragment);
            }
        });

        return v;
    }
}
