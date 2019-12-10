package com.example.cystudy.ui.fragments.StudentFragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cystudy.MainActivity;
import com.example.cystudy.R;
import com.example.cystudy.RecyclerViewAdapaters.RecyclerViewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import static androidx.navigation.ui.NavigationUI.setupWithNavController;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

public class StudyViewFragment extends Fragment {

    private static ArrayList<String> terms;
    private static ArrayList<String> defs;
    private static Boolean termShowing;
    private static int index;
    private static TextView cardText;
    private static TextView leftArrow;
    private static TextView rightArrow;
    private static TextView flipCard;
    private static TextView progressText;
    private static CountDownTimer timer;
    private static int totalTime;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_studyview, container, false);

        totalTime = 0; // Start as 0

        // Initialize timer
        timer = new CountDownTimer(36000000, 1000) { // Set this starting at 10 hours to avoid timing out
            @Override
            public void onTick(long millisUntilFinished) {
                totalTime += 1000;
            }

            @Override
            public void onFinish() {
                timer.cancel();
            }
        };

        timer.start();

        terms = StudentClassHomeFragment.studyViewTerms;
        defs = StudentClassHomeFragment.studyViewDefs;
        termShowing = true;
        index = 0;
        cardText = v.findViewById(R.id.studyViewFlashcardText);
        leftArrow = v.findViewById(R.id.studyViewPrevCardArrow);
        rightArrow = v.findViewById(R.id.studyViewNextCardArrow);
        flipCard = v.findViewById(R.id.studyViewFlipCard);
        progressText = v.findViewById(R.id.studyViewCardProgress);

        leftArrow.setVisibility(View.INVISIBLE);
        progressText.setText((index + 1) + "/" + terms.size());
        cardText.setText(terms.get(index));

        TextView pageTitle = v.findViewById(R.id.studyViewPageTitle);
        pageTitle.setText(RecyclerViewAdapter.studentClass + " Study View");

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextCard();
            }
        });

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevCard();
            }
        });

        flipCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flip();
            }
        });

        return v;
    }

    @Override
    public void onStop() {
        super.onStop();
        totalTime /= 1000;

        addTime();
    }

    private void nextCard() {

        index++; // Update index
        leftArrow.setVisibility(View.VISIBLE); // Show the left arrow

        if (index == terms.size() - 1) rightArrow.setVisibility(View.INVISIBLE);

        cardText.setText(terms.get(index));
        progressText.setText((index + 1) + "/" + terms.size());
    }

    private void prevCard() {

        index--; // Update index
        rightArrow.setVisibility(View.VISIBLE); // Show the left arrow

        if (index == 0) leftArrow.setVisibility(View.INVISIBLE);

        cardText.setText(terms.get(index));
        progressText.setText((index + 1) + "/" + terms.size());
    }

    private void flip() {

        if (termShowing) {
            cardText.setText(defs.get(index));
        } else {
            cardText.setText(terms.get(index));
        }

        termShowing = !termShowing; // Negate the boolean
    }

    private void addTime() {
        String URL = "http://coms-309-jr-7.misc.iastate.edu:8080/add-time?username=";
        URL += MainActivity.user + "&className=" + RecyclerViewAdapter.studentClass + "&time=" + totalTime; // This is the user that logged in and now needs conditional navigation

        try {
            JSONObject jsonBody = new JSONObject();
            RequestQueue addTimeRequestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));

            jsonBody.put("username", MainActivity.user);
            jsonBody.put("className", RecyclerViewAdapter.studentClass);
            jsonBody.put("time", totalTime);

            JsonObjectRequest addTimeRequest = new JsonObjectRequest(Request.Method.PUT, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // Log.d("addTime Response", response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("addTime Error", error.toString());
                    Log.d("New Time", totalTime + "");
                }
            });

            addTimeRequestQueue.add(addTimeRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
