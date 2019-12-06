package com.example.cystudy.ui.fragments.StudentFragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cystudy.LoginActivity;
import com.example.cystudy.MainActivity;
import com.example.cystudy.R;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static com.example.cystudy.MainActivity.navController;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        Button logoutButton = v.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener((View v2) -> {
            logout();
        });

        return v;
    }

    public void logout(){
        MainActivity.user = null;
        MainActivity.userRole = null;
        getActivity().finish();
        NavController navController = Navigation.findNavController(getActivity(), R.id.conditionalNavigation);
        navController.navigate(R.id.action_settingsFragment_to_loginActivity);

        //Clear backstack
        FragmentManager fm = getActivity().getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); i++) {
            fm.popBackStack();
        }
    }

}
