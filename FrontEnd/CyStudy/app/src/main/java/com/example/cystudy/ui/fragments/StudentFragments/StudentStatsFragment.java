package com.example.cystudy.ui.fragments.StudentFragments;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cystudy.MainActivity;
import com.example.cystudy.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * This class will be fully implemented after Demo 4.
 *
 * @author Zach Mass
 */
public class StudentStatsFragment extends Fragment {

    private static String time;
    private static String term1;
    private static String term2;
    private static String term3;
    private static int term1accuracy;
    private static int term2accuracy;
    private static int term3accuracy;
    private static int pulledTime;
    private static TextView timeString;

    public StudentStatsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Populate Spinner for classes
        // Will be pulled from database
        final View v = inflater.inflate(R.layout.fragment_student_stats, container, false);
        String[] classes = {"COM S 309"};

        final Spinner spinner = v.findViewById(R.id.classesDropdown);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, StudentHomeFragment.unformattedStudentClasses);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        timeString = v.findViewById(R.id.timeSpentValue); // Access empty time slot on page


        // Set values based on spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Find current value of spinner
                String selectedClass = spinner.getSelectedItem().toString(); // Figure out which class is selected

                // Total time spent studying for classes (will be pulled from server/database)
                int accuracy = 0; // Will be the overall percent correct in a class and will be pulled from server

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

                for (int i = 0; i < StudentHomeFragment.unformattedStudentClasses.size(); i++) {
                    if (selectedClass.matches(StudentHomeFragment.unformattedStudentClasses.get(i))) {
                        accuracy = 0;
                        term1 = "term1";
                        term2 = "term2";
                        term3 = "term3";
                        term1accuracy = 0;
                        term2accuracy = 0;
                        term3accuracy = 0;
                        pullTime(StudentHomeFragment.unformattedStudentClasses.get(i));
                    }
                }

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
                } else {
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
            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }

    /**
     * Function to convert seconds to hours, minutes and return the String
     *
     * @param seconds the total minutes spent on class
     */
    private void setTimeSpentStudying(int seconds) {
        // TODO: write a function to convert seconds to hh:mm:ss format
        String hh = "";
        String mm = "";
        String ss = "";
        int dividedValue = 0;
        int remainder = 0; // Initialize to 0 just to start

        dividedValue = seconds / 3600; // Hours
        remainder = seconds % 3600; // Remainder is minutes

        if (dividedValue < 10) {
            hh = "0" + dividedValue;
        } else {
            hh = dividedValue + "";
        }

        seconds = remainder;

        dividedValue = seconds / 60; // Minutes
        remainder = seconds % 60; // Remainder is seconds

        if (dividedValue < 10) {
            mm = "0" + dividedValue;
        } else {
            mm = dividedValue + "";
        }

        seconds = remainder;

        if (seconds < 10) {
            ss = "0" + seconds;
        } else {
            ss = seconds + "";
        }

        time = hh + "h " + mm + "m " + ss + "s";
        timeString.setText(time); // Set time spent studying overall text
    }

    private void pullTime(String className) {
        // JSONArray Request
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));

        String URL = "http://coms-309-jr-7.misc.iastate.edu:8080/get-users-stats?username=";
        URL += MainActivity.user + "&className=" + className;

        JsonObjectRequest pullTimeRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    pulledTime = response.getInt("timeSpent");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                setTimeSpentStudying(pulledTime);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(pullTimeRequest);
    }
}
