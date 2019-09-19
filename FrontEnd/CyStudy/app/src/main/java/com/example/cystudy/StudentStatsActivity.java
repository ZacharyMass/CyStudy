package com.example.cystudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class StudentStatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_stats);

        // Hide action bar
        getSupportActionBar().hide();

        // Populate Spinner for classes
        // Will be pulled from database
        String[] classes = {"CPRE 310", "STAT 330", "COM S 309", "COM S 321"};
        setContentView(R.layout.activity_student_stats);
        Spinner spin = (Spinner) findViewById(R.id.classesDropdown);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        int minutesSpent = 238; // Total time spent studying for class (will be pulled from server/database

        setTimeSpentStudying(minutesSpent);
    }

    /**
     * Function to convert time to hours, minutes and set this text in Student Stats page
     * @param minutes the total minutes spent on class
     */
    public void setTimeSpentStudying(int minutes) {

    }
}
