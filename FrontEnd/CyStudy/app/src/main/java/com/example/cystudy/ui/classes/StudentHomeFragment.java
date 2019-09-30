package com.example.cystudy.ui.classes;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cystudy.App.AppController;
import com.example.cystudy.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.cystudy.App.AppController.TAG;

public class StudentHomeFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Bind button to navigate to StudentStatsFragment
        View v = inflater.inflate(R.layout.fragment_student_home, container, false);
        Button button = v.findViewById(R.id.yourStatsButton);
        button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.studentStatsFragment, null));
        final TextView classList = v.findViewById(R.id.classesText); // Will be replaced by class text

        String url = "http://coms-309-jr-7.misc.iastate.edu:3306/cystudy/user/get-users";

        Context mContext = getContext();

        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        JsonObjectRequest getJSONClasses = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        classList.setText(response.toString());
                    }
                },
                new Response.ErrorListener() {
            @Override
                    public void onErrorResponse(VolleyError error) {
                classList.setText(error.toString());
            }
                });

        requestQueue.add(getJSONClasses);

        return v;
    }

}

