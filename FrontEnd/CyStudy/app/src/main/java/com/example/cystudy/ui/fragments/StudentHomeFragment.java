package com.example.cystudy.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cystudy.LoginActivity;
import com.example.cystudy.R;
import com.example.cystudy.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Homepage for a student user. Displays the classes they are enrolled in.
 * @author Zach Mass
 */
public class StudentHomeFragment extends Fragment {

    String username = LoginActivity.user; // Username from LoginActivity is global here
    String url = "http://coms-309-jr-7.misc.iastate.edu:8080/get-users-classes?username=" + username;
    private View v;

    ArrayList<String> classesL = new ArrayList<>();

    /**
     * Sets View object to appropriate XML document, calls pull request from server
     * @param inflater LayoutInflater object
     * @param container ViewGroup object
     * @param savedInstanceState Bundle object
     * @return v the current View object
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Inflate view
        v = inflater.inflate(R.layout.fragment_student_home, container, false);

        //Pull list of classes from server
        pullClasses();

        return v;
    }

    /**
     * Pulls classes via stringRequest, formats the text appropriately. Organizes into RecyclerView.
     */
    public void pullClasses() {
        RequestQueue studentHomeQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] classes = response.split(",");

                for (int i = 0; i < classes.length; i++)
                {
                    classesL.add(classes[i]);
                }

                // Initialize Recycler
                RecyclerView r = v.findViewById(R.id.classes_recycler_view);
                RecyclerViewAdapter a = new RecyclerViewAdapter(getContext(), classesL);
                r.setAdapter(a);
                r.setLayoutManager(new LinearLayoutManager(getContext()));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Error", error.getMessage());
            }
        });

        studentHomeQueue.add(stringRequest);
    }
}
