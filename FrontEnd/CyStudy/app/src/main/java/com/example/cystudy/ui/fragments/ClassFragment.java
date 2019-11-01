package com.example.cystudy.ui.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import com.example.cystudy.MainActivity;
import com.example.cystudy.R;
import com.example.cystudy.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends Fragment {

    ArrayList<String> setsL = new ArrayList<>();

    public ClassFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Inflate view
        View v = inflater.inflate(R.layout.fragment_class, container, false);

        TextView t = v.findViewById(R.id.classNameTitle);
        t.setText(MainActivity.currentClass);

        //Pull list of classes from server
        pullSets();

        // Initialize Recycler
        RecyclerView r = v.findViewById(R.id.sets_recycler_view);
        RecyclerViewAdapter a = new RecyclerViewAdapter(this.getContext(), setsL);
        r.setAdapter(a);
        r.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return v;
    }

    // Make string request to populate the text in the buttons and work with formatting string into usable form
    public void pullSets() {
        String className = "COMS309";
        String url = "http://coms-309-jr-7.misc.iastate.edu:8080/get-class-topics?className=" + className;

        RequestQueue studentHomeQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] sets = response.split(",");

                for (int i = 0; i < sets.length; i++) {
                    setsL.add(sets[i]);
                }
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
