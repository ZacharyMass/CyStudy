package com.example.cystudy.ui.classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.cystudy.LoginActivity;
import com.example.cystudy.R;

public class StudentHomeFragment extends Fragment {

    String username = LoginActivity.user; // Username from LoginActivity is global here

    String url = "http://coms-309-jr-7.misc.iastate.edu:8080/get-users-classes?username=" + username;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Bind button to navigate to StudentStatsFragment
        final View v = inflater.inflate(R.layout.fragment_student_home, container, false);
        Button button = v.findViewById(R.id.yourStatsButton);
        button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.studentStatsFragment, null));
        TextView studentName = v.findViewById(R.id.studentName);
        studentName.setText("Username: " + username); // Just for reference to show correct user and communication from server

        return v;
    }
}