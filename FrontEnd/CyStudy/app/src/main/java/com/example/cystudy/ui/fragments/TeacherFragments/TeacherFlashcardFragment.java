package com.example.cystudy.ui.fragments.TeacherFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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

import static android.widget.LinearLayout.VERTICAL;
import static androidx.navigation.ui.NavigationUI.setupWithNavController;

/**
 * This page shows the entire list of flashcards for a given class (for the teacher user)
 * @author Brad Gannon
 */
public class TeacherFlashcardFragment extends Fragment {

    public static String URL = "http://coms-309-jr-7.misc.iastate.edu:8080/get-terms-by-class?className=";
    ArrayList<String> flashcardsL = new ArrayList<>();
    private View v;

    /**
     * Initializes buttons, navigation bar on bottom
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return v the current View object
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        URL += TeacherClassFragment.className;

        v = inflater.inflate(R.layout.fragment_teacher_flashcards, container, false);

        TextView className = v.findViewById(R.id.teacherClassNameTitle);
        className.setText(TeacherClassFragment.className.split("\n")[1] + " Cards");

        FloatingActionButton fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.navController.navigate(R.id.action_teacherFlashcardFragment_to_addFlashcardFragment);
            }
        });

        pullFlashcards();

        return v;
    }

    /**
     * Simple JSON Array Request to pull flashcard data from DB, organize into usable form
     */
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
                                flashcardsL.add("\n" + term + "\n");
                                Log.d("Flashcard", flashcardsL.get(i));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Initialize Recycler
                        RecyclerView r = v.findViewById(R.id.teacher_flashcards_recycler_view);
                        RecyclerViewAdapter a = new RecyclerViewAdapter(getContext(), flashcardsL);
                        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                        Log.d("Width", manager.getWidth() + "");
                        Log.d("Height", manager.getHeight() + "");
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
