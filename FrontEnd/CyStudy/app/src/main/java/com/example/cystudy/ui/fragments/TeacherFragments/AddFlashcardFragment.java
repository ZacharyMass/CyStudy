package com.example.cystudy.ui.fragments.TeacherFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cystudy.MainActivity;
import com.example.cystudy.R;
import com.example.cystudy.ui.fragments.TeacherFragments.TeacherClassFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import static androidx.navigation.ui.NavigationUI.setupWithNavController;

public class AddFlashcardFragment extends Fragment {

    Button discard;
    Button create;
    EditText term;
    EditText definition;

    public static String URL = "http://coms-309-jr-7.misc.iastate.edu:8080/add-card";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_add_flashcard, container, false);

        discard = v.findViewById(R.id.discardButton);
        create = v.findViewById(R.id.createButton);
        term = v.findViewById(R.id.enterTermBox);
        definition = v.findViewById(R.id.enterDefinitionBox);

        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Discarded!", Toast.LENGTH_SHORT).show();
                BottomNavigationView bottomNavView = MainActivity.bottomNavigationView;
                NavController navController = MainActivity.navController;
                setupWithNavController(bottomNavView, navController);
                navController.navigate(R.id.action_addFlashcardFragment_to_teacherFlashcardFragment);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFlashcard();
                Toast.makeText(getContext(), "Added!", Toast.LENGTH_SHORT).show();
                BottomNavigationView bottomNavView = MainActivity.bottomNavigationView;
                NavController navController = MainActivity.navController;
                setupWithNavController(bottomNavView, navController);
                navController.navigate(R.id.action_addFlashcardFragment_to_teacherFlashcardFragment);
            }
        });

        return v;
    }

    private void createFlashcard() {

        try {
            JSONObject jsonBody = new JSONObject();
            RequestQueue createFlashcard = Volley.newRequestQueue(Objects.requireNonNull(getContext()));

            jsonBody.put("className", TeacherClassFragment.className);
            jsonBody.put("topic", "test");
            jsonBody.put("term", term.getText().toString());
            jsonBody.put("answer", definition.getText().toString());

            JsonObjectRequest addFlashcardRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Toast.makeText(getContext(), "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("Flashcard Error", error.toString());
                }
            });

            createFlashcard.add(addFlashcardRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
