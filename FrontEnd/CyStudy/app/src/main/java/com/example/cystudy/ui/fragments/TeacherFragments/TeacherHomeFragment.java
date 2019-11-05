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
import com.example.cystudy.LoginActivity;
import com.example.cystudy.R;
import com.example.cystudy.RecyclerViewAdapaters.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Objects;

import static androidx.navigation.ui.NavigationUI.setupWithNavController;

/**
 * This is the first page that the teacher user will see upon logging in. Displays a list of their classes as buttons
 * @author Brad Gannon
 */
public class TeacherHomeFragment extends Fragment {

    String username = LoginActivity.user; // Username from LoginActivity is global here
    String url = "http://coms-309-jr-7.misc.iastate.edu:8080/get-users-classes?username=" + username;
    private View v;

    ArrayList<String> classesL = new ArrayList<>();

    /**
     * Initializes current View object, calls a GET request to pull list of classes for teacher user from DB
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return v the current View object
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_teacher_home, container, false);

        //Pull list of classes from server
        pullClasses();

        return v;
    }

    /**
     * StringRequest to DB to get current list of classes for teacher, some quick String operations to convert to usable form
     */
    public void pullClasses() {
        RequestQueue teacherHomeQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] classes = response.split(",");

                for (int i = 0; i < classes.length; i++)
                {
                    classesL.add(classes[i]);
                }

                // Convert data to RecyclerView
                RecyclerView r = v.findViewById(R.id.teacher_classes_recycler_view);
                RecyclerViewAdapter a = new RecyclerViewAdapter(getContext(), classesL);
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

        teacherHomeQueue.add(stringRequest);
    }
}


