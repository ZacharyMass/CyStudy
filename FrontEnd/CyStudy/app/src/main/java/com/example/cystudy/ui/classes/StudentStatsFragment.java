package com.example.cystudy.ui.classes;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cystudy.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StudentStatsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StudentStatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentStatsFragment extends Fragment {

    public StudentStatsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Populate Spinner for classes
        // Will be pulled from database
        View v = inflater.inflate(R.layout.fragment_student_stats, container, false);
        String[] classes = {"CPRE 310", "STAT 330", "COM S 309", "COM S 321"};
        Spinner spin = (Spinner) v.findViewById(R.id.classesDropdown);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, classes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        String selectedClass = "";
        if(spin.getSelectedItem() != null) {
            Log.println(Log.ERROR, "Error", "Null spin.getSelectedItem()");
            selectedClass = spin.getSelectedItem().toString(); // Figure out which class is selected
        }

        String time;
        String term1;
        String term2;
        String term3;
        int term1accuracy;
        int term2accuracy;
        int term3accuracy;
        // Total time spent studying for classes (will be pulled from server/database)
        int accuracy = 0; // Will be the overall percent correct in a class and will be pulled from server

        TextView timeString = v.findViewById(R.id.timeSpentValue); // Access empty time slot on page
        View overallGreenBar = v.findViewById(R.id.overallGreen); // Access green percentage bar for overall accuracy
        TextView overallPercentText = v.findViewById(R.id.overallPercent); // Access TextView for white percentage text on overall green bar
        TextView toStudyHeader = v.findViewById(R.id.termsToStudyHeader); // Access yellow text to divide page, this needs to be underlined
        toStudyHeader.setPaintFlags(toStudyHeader.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        TextView term1Percent = v.findViewById(R.id.term1Percent);
        TextView term2Percent = v.findViewById(R.id.term2Percent);
        TextView term3Percent = v.findViewById(R.id.term3Percent);
        View term1GreenBar = v.findViewById(R.id.term1green);
        View term2GreenBar = v.findViewById(R.id.term2green);
        View term3GreenBar = v.findViewById(R.id.term3green);
        TextView term1header = v.findViewById(R.id.term1header);
        TextView term2header = v.findViewById(R.id.term2header);
        TextView term3header = v.findViewById(R.id.term3header);

        // Apply minutes variable to appropriate class value
        if (selectedClass.matches("CPRE 310")) {
            int CPRE_minutesSpent = 238;
            accuracy = 76;
            term1 = "Rules of Inference";
            term2 = "Proofs";
            term3 = "Graphs";
            term1accuracy = 21;
            term2accuracy = 18;
            term3accuracy = 29;
            time = setTimeSpentStudying(CPRE_minutesSpent);

        } else if (selectedClass.matches("STAT 330")) {
            int STAT_minutesSpent = 149;
            accuracy = 75;
            term1 = "Conditional Probability";
            term2 = "Bayes' Rule";
            term3 = "Series vs. Parallelism";
            term1accuracy = 16;
            term2accuracy = 19;
            term3accuracy = 25;
            time = setTimeSpentStudying(STAT_minutesSpent);
        } else if (selectedClass.matches("COM S 309")) {
            int COMS309_minutesSpent = 300;
            accuracy = 89;
            term1 = "Git Basics";
            term2 = "Android Studio";
            term3 = "Project Management";
            term1accuracy = 12;
            term2accuracy = 9;
            term3accuracy = 36;
            time = setTimeSpentStudying(COMS309_minutesSpent);
        } else {
            int COMS321_minutesSpent = 101;
            accuracy = 92;
            term1 = "Processor Speeds";
            term2 = "Conversion of Units";
            term3 = "GPU Architecture";
            term1accuracy = 26;
            term2accuracy = 21;
            term3accuracy = 31;
            time = setTimeSpentStudying(COMS321_minutesSpent);
        }

        timeString.setText(time); // Set time spent studying overall text
        term1header.setText(term1);
        term2header.setText(term2);
        term3header.setText(term3);

        if (accuracy == 0) { // Can reason that the 3 recommended terms are also 0
            overallGreenBar.setVisibility(View.GONE);
            term1GreenBar.setVisibility(View.GONE);
            term2GreenBar.setVisibility(View.GONE);
            term3GreenBar.setVisibility(View.GONE);
            overallPercentText.setText("0%");
            term1Percent.setText("0%");
            term2Percent.setText("0%");
            term3Percent.setText("0%");
        }
        else {
            String overallPercentString = accuracy + "%";
            String term1PercentString = term1accuracy + "%";
            String term2PercentString = term2accuracy + "%";
            String term3PercentString = term3accuracy + "%";
            overallGreenBar.setVisibility(View.VISIBLE); // To account for it being invisible if 0 accuracy value
            term1GreenBar.setVisibility(View.VISIBLE);
            term2GreenBar.setVisibility(View.VISIBLE);
            term3GreenBar.setVisibility(View.VISIBLE);
            overallGreenBar.getLayoutParams().width = (accuracy * 750) / 100;
            term1GreenBar.getLayoutParams().width = (term1accuracy * 750) / 100;
            term2GreenBar.getLayoutParams().width = (term2accuracy * 750) / 100;
            term3GreenBar.getLayoutParams().width = (term3accuracy * 750) / 100;
            overallPercentText.setText(overallPercentString); // Set text in overall green bar
            term1Percent.setText(term1PercentString);
            term2Percent.setText(term2PercentString);
            term3Percent.setText(term3PercentString);
        }

        return v;
    }

    /**
     * Function to convert time to hours, minutes and return the String
     * @param minutes the total minutes spent on class
     */
    public String setTimeSpentStudying ( int minutes) {
        int hours = minutes / 60; // Get hours
        minutes = minutes - (hours * 60); // Get remainder of minutes
        return hours + "h" + " " + minutes + "m";
    }
}
