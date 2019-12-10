package com.example.cystudy.ui.fragments.StudentFragments;

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
import com.android.volley.toolbox.Volley;
import com.example.cystudy.MainActivity;
import com.example.cystudy.R;
import com.example.cystudy.RecyclerViewAdapaters.RecyclerViewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import static androidx.navigation.ui.NavigationUI.setupWithNavController;

public class StudentClassHomeFragment extends Fragment {

    public static ArrayList<String> studyViewTerms = new ArrayList<>();
    public static ArrayList<String> studyViewDefs = new ArrayList<>();
    public static String URL = "http://coms-309-jr-7.misc.iastate.edu:8080/get-terms-by-class?className=" + RecyclerViewAdapter.studentClass;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Clear ArrayLists for terms and definitions to prevent duplicates on backStack operation
        studyViewTerms.clear();
        studyViewDefs.clear();

        final View v = inflater.inflate(R.layout.fragment_student_class_home, container, false);

        TextView pageTitle = v.findViewById(R.id.studentClassPageTitle);
        pageTitle.setText(RecyclerViewAdapter.studentClass + " Home");

        Button viewFlashcardsBtn = v.findViewById(R.id.studentViewFlashcards);
        Button goToStudyViewBtn = v.findViewById(R.id.studentStudyView);

        viewFlashcardsBtn.setOnClickListener(v1 -> {
            BottomNavigationView bottomNavView = MainActivity.bottomNavigationView;
            NavController navController = MainActivity.navController;
            setupWithNavController(bottomNavView, navController);
            navController.navigate(R.id.action_studentClassHomeFragment_to_classFragment);
        });

        goToStudyViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pullFlashcards();
            }
        });

        return v;
    }

    public void pullFlashcards() {
        // JSONArray Request
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject flashcard = response.getJSONObject(i);

                            String className = flashcard.getString("className");
                            String topic = flashcard.getString("topic");
                            String term = flashcard.getString("term");
                            String answer = flashcard.getString("answer");
                            String timeSpent = flashcard.getString("timeSpent");

                            studyViewTerms.add(term);
                            studyViewDefs.add(answer);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    BottomNavigationView bottomNavView = MainActivity.bottomNavigationView;
                    NavController navController = MainActivity.navController;
                    setupWithNavController(bottomNavView, navController);
                    navController.navigate(R.id.action_studentClassHomeFragment_to_studyViewFragment);
                },
                error -> error.printStackTrace()
        );

        requestQueue.add(jsonArrayRequest);
    }
}
