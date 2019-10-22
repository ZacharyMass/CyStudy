package com.example.cystudy.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cystudy.MainActivity;
import com.example.cystudy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static androidx.navigation.ui.NavigationUI.setupWithNavController;

public class TeacherFlashcardFragment extends Fragment {

    public static String URL = "http://coms-309-jr-7.misc.iastate.edu:8080/get-terms-by-class?className=COMS309";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // URL += TeacherHomeFragment.class1name;

        final View v = inflater.inflate(R.layout.fragment_teacher_flashcards, container, false);

        Button addFlashcardBtn = v.findViewById(R.id.floatingAddFlashcardButton);
        final TextView flashToFill = v.findViewById(R.id.flashcard);

        addFlashcardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavigationView bottomNavView = MainActivity.bottomNavigationView;
                NavController navController = MainActivity.navController;
                setupWithNavController(bottomNavView, navController);
                navController.navigate(R.id.action_teacherFlashcardFragment_to_addFlashcardFragment);
            }
        });

        flashToFill.setText("");

        // JSONArray Request
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

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
                        try{
                            // Loop through the array elements
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject flashcard = response.getJSONObject(i);

                                // Get the current student (json object) data
                                String className = flashcard.getString("className");
                                String topic = flashcard.getString("topic");
                                String term = flashcard.getString("term");
                                String answer = flashcard.getString("answer");
                                String timeSpent = flashcard.getString("timeSpent");

                                // Display the formatted json data in text view
                                flashToFill.append(term + ": " + answer);
                                flashToFill.append("\n\n");
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        error.printStackTrace();
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);

        return v;
    }
}
