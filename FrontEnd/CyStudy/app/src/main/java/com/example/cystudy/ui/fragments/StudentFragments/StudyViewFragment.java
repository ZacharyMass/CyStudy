package com.example.cystudy.ui.fragments.StudentFragments;

import android.os.Bundle;
import android.os.CountDownTimer;
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

import com.example.cystudy.MainActivity;
import com.example.cystudy.R;
import com.example.cystudy.RecyclerViewAdapaters.RecyclerViewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static androidx.navigation.ui.NavigationUI.setupWithNavController;

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
        Log.d("Time Spent", totalTime + " seconds");
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
}
