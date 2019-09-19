package com.example.cystudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

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
        Spinner spin = findViewById(R.id.classesDropdown);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setOnItemSelectedListener(onItemSelectedListener1);

    }

    AdapterView.OnItemSelectedListener onItemSelectedListener1 =
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Spinner classSpinner = findViewById(R.id.classesDropdown);
                    String selectedClass = classSpinner.getSelectedItem().toString(); // Figure out which class is selected

                    String time;
                    // Total time spent studying for classes (will be pulled from server/database)
                    double accuracy; // Will be 

                    TextView timeString = findViewById(R.id.timeSpentValue); // Access empty time slot on page

                    // Apply minutes variable to appropriate class value
                    if (selectedClass.matches("CPRE 310")) {
                        int CPRE_minutesSpent = 238;
                        time = setTimeSpentStudying(CPRE_minutesSpent);

                    } else if (selectedClass.matches("STAT 330")) {
                        int STAT_minutesSpent = 149;
                        time = setTimeSpentStudying(STAT_minutesSpent);
                    } else if (selectedClass.matches("COM S 309")) {
                        int COMS309_minutesSpent = 300;
                        time = setTimeSpentStudying(COMS309_minutesSpent);
                    } else {
                        int COMS321_minutesSpent = 101;
                        time = setTimeSpentStudying(COMS321_minutesSpent);
                    }

                    timeString.setText(time);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            };

    /**
     * Function to convert time to hours, minutes and return the String
     * @param minutes the total minutes spent on class
     */
    public String setTimeSpentStudying ( int minutes){
        int hours = minutes / 60; // Get hours
        minutes = minutes - (hours * 60); // Get remainder of minutes
        return hours + "h" + " " + minutes + "m";
    }
}
