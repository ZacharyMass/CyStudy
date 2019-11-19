package com.example.cystudy.ui.fragments.StudentFragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cystudy.LoginActivity;
import com.example.cystudy.MainActivity;
import com.example.cystudy.R;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static com.example.cystudy.MainActivity.navController;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentSettingsFragment extends Fragment {


    public StudentSettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    public void logout(){
        MainActivity.user = null;
        MainActivity.userRole = null;
        NavController navController = Navigation.findNavController(getActivity(), R.id.conditionalNavigation);
        navController.navigate(R.id.action_settingsFragment_to_loginActivity);
    }

}
