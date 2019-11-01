package com.example.cystudy.ui.fragments.TeacherFragments;

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
import com.example.cystudy.R;
import com.example.cystudy.RecyclerViewAdapaters.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class TeacherSetsFragment extends Fragment {

    public static String URL = "http://coms-309-jr-7.misc.iastate.edu:8080/get-class-topics?className=";

    ArrayList<String> topicsL = new ArrayList<>();

    private View v;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        URL += TeacherClassFragment.className; // Should be COMS309 currently for testing purposes

        v = inflater.inflate(R.layout.fragment_teacher_sets, container, false);

        pullTopicsForClass();

        return v;
    }

    private void pullTopicsForClass() {
        RequestQueue topicsByClassQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] topics = response.split(",");

                for (int i = 0; i < topics.length; i++)
                {
                    topicsL.add(topics[i]);
                    Log.d("Topic", topics[i]);
                }

                // Initialize Recycler
                RecyclerView r = v.findViewById(R.id.teacher_sets_recycler_view);
                RecyclerViewAdapter a = new RecyclerViewAdapter(Objects.requireNonNull(getContext()), topicsL);
                Log.d("Current context", getContext().toString());
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

        topicsByClassQueue.add(stringRequest);
    }
}
