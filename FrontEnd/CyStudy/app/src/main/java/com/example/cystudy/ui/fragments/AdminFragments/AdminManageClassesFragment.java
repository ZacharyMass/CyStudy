package com.example.cystudy.ui.fragments.AdminFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cystudy.MainActivity;
import com.example.cystudy.R;
import com.example.cystudy.RecyclerViewAdapaters.RecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class AdminManageClassesFragment extends Fragment {

    private View v;
    ArrayList<String> classesL = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        classesL.clear(); // Clear on creation to prevent duplication of RecyclerView objects

        v = inflater.inflate(R.layout.fragment_admin_manage_classes, container, false);

        FloatingActionButton goToAddClasses = v.findViewById(R.id.adminAddClassFloat);

        goToAddClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.navController.navigate(R.id.action_adminManageClassesFragment_to_adminCreateClassFragment);
            }
        });

        pullClasses(); // This can run every time because ArrayList will be empty

        return v;
    }

    public void pullClasses() {
        String URL = "http://coms-309-jr-7.misc.iastate.edu:8080/get-classes";

        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try {
                            // Loop through the array elements
                            for (int i = 0; i < response.length(); i++) {
                                // Get current json object
                                JSONObject classObj = response.getJSONObject(i);

                                // Get the current student (json object) data
                                String className = classObj.getString("className");

                                // Add terms in desired format to array
                                classesL.add("\n" + className + "\n");
                                Log.d("Flashcard", classesL.get(i));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Initialize Recycler
                        RecyclerView r = v.findViewById(R.id.admin_classes_recycler_view);
                        RecyclerViewAdapter a = new RecyclerViewAdapter(getContext(), classesL);
                        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                        Log.d("Current context", getContext().toString());
                        r.setAdapter(a);
                        r.setLayoutManager(manager);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
    }
}

