package com.example.cystudy.ui.fragments.AdminFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cystudy.MainActivity;
import com.example.cystudy.R;
import com.example.cystudy.RecyclerViewAdapaters.RecyclerViewAdapter;
import com.example.cystudy.ui.fragments.TeacherFragments.TeacherClassFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class AdminAddTeacherToClassFragment extends Fragment {

    View v;
    private ArrayList<String> classesL = new ArrayList<>();
    private Spinner classesSpinner;
    private Spinner teacherSpinner;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        classesL.clear();

        v = inflater.inflate(R.layout.fragment_admin_add_teacher_to_class, container, false);

        Button cancelAdd = v.findViewById(R.id.adminCancelAddTeacherBtn);
        Button addTeacherToClass = v.findViewById(R.id.adminAddTeacherBtn);

        teacherSpinner = v.findViewById(R.id.adminSelectTeacherDropdown);
        ArrayAdapter<String> teacherAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, AdminManageTeachersFragment.teachersL);
        teacherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teacherSpinner.setAdapter(teacherAdapter);

        pullClasses();

        cancelAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.navController.navigate(R.id.action_adminAddTeacherToClassFragment_to_adminManageTeachersFragment);
            }
        });

        addTeacherToClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTeacher();
            }
        });

        return v;
    }

    private void pullClasses() {
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
                                classesL.add(className);
                                Log.d("Flashcard", classesL.get(i));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        classesSpinner = v.findViewById(R.id.adminSelectClassDropdown);
                        ArrayAdapter<String> classAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, classesL);
                        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        classesSpinner.setAdapter(classAdapter);
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

    private void addTeacher() {

        String URL = "http://coms-309-jr-7.misc.iastate.edu:8080/add-to-class";

        try {
            JSONObject jsonBody = new JSONObject();
            RequestQueue addTeacherQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));

            jsonBody.put("username", teacherSpinner.getSelectedItem().toString());
            jsonBody.put("className", classesSpinner.getSelectedItem().toString());

            JsonObjectRequest addTeacherRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Toast.makeText(getContext(), "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("Add Teacher Error", error.toString());
                }
            });

            addTeacherQueue.add(addTeacherRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
