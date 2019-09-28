package com.example.cystudy.ui.classes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cystudy.MainActivity;
import com.example.cystudy.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StudentHomeFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Bind button to navigate to StudentStatsFragment
        View v = inflater.inflate(R.layout.fragment_student_home, container, false);
        Button button = v.findViewById(R.id.yourStatsButton);
        button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.studentStatsFragment, null));
        final TextView classList = v.findViewById(R.id.classesText); // Will be replaced by class text

        // Establish Queue
        RequestQueue queue = MainActivity.queue;
        String url = "coms-309-jr-7.misc.iastate.edu:3306/cystudy/user/add";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        classList.setText("Test");
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String response = "Error Occured";
                        classList.setText(response);
                        Log.d("Error.Response", response);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                params.put("first_name", "Brad");
                params.put("last_name", "Gannon");
                params.put("role", "Student");
                params.put("password", "Password");

                return params;
            }
        };
        queue.add(postRequest);

        return v;
    }

}
