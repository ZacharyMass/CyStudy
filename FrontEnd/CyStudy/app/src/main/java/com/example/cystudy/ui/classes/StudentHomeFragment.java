package com.example.cystudy.ui.classes;

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
import androidx.navigation.Navigation;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cystudy.LoginActivity;
import com.example.cystudy.R;

import java.util.Objects;

public class StudentHomeFragment extends Fragment {

    String username = LoginActivity.user; // Username from LoginActivity is global here

    String url = "http://coms-309-jr-7.misc.iastate.edu:8080/get-users-classes?username=" + username;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Bind button to navigate to StudentStatsFragment
        final View v = inflater.inflate(R.layout.fragment_student_home, container, false);
        TextView studentName = v.findViewById(R.id.studentName);
        studentName.setText("Username: " + username); // Just for reference to show correct user and communication from server
        final Button class1 = v.findViewById(R.id.class1button);
        final Button class2 = v.findViewById(R.id.class2button);
        final Button class3 = v.findViewById(R.id.class3button);
        final Button class4 = v.findViewById(R.id.class4button);

        fillButtons(class1, class2, class3, class4);

        return v;
    }

    // Make string request to populate the text in the buttons and work with formatting string into usable form
    public void fillButtons(final Button class1, final Button class2, final Button class3, final Button class4) {

        RequestQueue studentHomeQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                populateButtonText(response, class1, class2, class3, class4);
                Log.d("Classes Response", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                class1.setText("Error: " + error.getMessage());
            }
        });

        studentHomeQueue.add(stringRequest);
    }

    public void populateButtonText(String text, final Button class1, final Button class2, final Button class3, final Button class4) {

        text = text.substring(0, text.length() - 1);
        String[] classes = text.split(" ");

        for (int i = 0; i < classes.length; i++) {
            String temp; // Will be used to remove comma on end

            switch (i) {
                case 0:
                    temp = classes[i];
                    temp = temp.substring(0, temp.length() - 1);
                    class1.setText(temp);
                    break;
                case 1:
                    temp = classes[i];
                    temp = temp.substring(0, temp.length() - 1);
                    class2.setText(temp);
                    break;
                case 2:
                    temp = classes[i];
                    temp = temp.substring(0, temp.length() - 1);
                    class3.setText(temp);
                    break;
                case 3:
                    temp = classes[i];
                    temp = temp.substring(0, temp.length() - 1);
                    class4.setText(temp);
                    break;
            }
        }
    }
}