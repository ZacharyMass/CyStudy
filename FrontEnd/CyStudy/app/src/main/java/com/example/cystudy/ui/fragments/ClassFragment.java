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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends Fragment {

    public static String URL = "http://coms-309-jr-7.misc.iastate.edu:8080/get-terms-by-class?className=";
    ArrayList<String> flashcardsL = new ArrayList<>();
    private View v;


    public ClassFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Inflate view
        v = inflater.inflate(R.layout.fragment_class, container, false);

        Button b = v.findViewById(R.id.GameButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomNavigationView bottomNavView = MainActivity.bottomNavigationView;
                NavController navController = MainActivity.navController;
                setupWithNavController(bottomNavView, navController);
                navController.navigate(R.id.action_classFragment_to_gameFragment);
            }
        }
        );

        URL += MainActivity.currentClass;

        TextView t = v.findViewById(R.id.studentClassNameTitle);
        t.setText(MainActivity.currentClass);

        //Pull list of classes from server
        pullFlashcards();

        return v;
    }

    // Make string request to populate the text in the buttons and work with formatting string into usable form
    public void pullFlashcards() {
        // JSONArray Request
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
                                JSONObject flashcard = response.getJSONObject(i);

                                // Get the current student (json object) data
                                String className = flashcard.getString("className");
                                String topic = flashcard.getString("topic");
                                String term = flashcard.getString("term");
                                String answer = flashcard.getString("answer");
                                String timeSpent = flashcard.getString("timeSpent");

                                // Add terms in desired format to array
                                flashcardsL.add(term + ": " + answer);
                                Log.d("Flashcard", flashcardsL.get(i));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Initialize Recycler
                        RecyclerView r = v.findViewById(R.id.student_flashcards_recycler_view);
                        RecyclerViewAdapter a = new RecyclerViewAdapter(getContext(), flashcardsL);
                        Log.d("Current context", getContext().toString());
                        r.setAdapter(a);
                        r.setLayoutManager(new LinearLayoutManager(getContext()));
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
