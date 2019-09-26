package com.example.cystudy.ui.classes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cystudy.R;

import org.json.JSONObject;

public class StudentHomeFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Bind button to navigate to StudentStatsFragment
        View v = inflater.inflate(R.layout.fragment_student_home, container, false);
        Button button = v.findViewById(R.id.yourStatsButton);
        button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.studentStatsFragment, null));

        return v;
    }

}
