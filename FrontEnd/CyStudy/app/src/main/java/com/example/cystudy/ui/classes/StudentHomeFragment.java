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
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cystudy.R;
import com.example.cystudy.app.AppController;

import org.json.JSONArray;

import static com.example.cystudy.app.AppController.TAG;

public class StudentHomeFragment extends Fragment {

    // public static String url = "http://coms-309-jr-7.misc.iastate.edu:8080/get-users";
    //String url = "https://api.androidhive.info/volley/string_response.html";
    String url = "http://echo.jsontest.com/key/value/one/two";
    TextView classList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Bind button to navigate to StudentStatsFragment
        final View v = inflater.inflate(R.layout.fragment_student_home, container, false);
        Button button = v.findViewById(R.id.yourStatsButton);
        button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.studentStatsFragment, null));
        classList = v.findViewById(R.id.classList); // Will be replaced by class text
        Button startRequest = v.findViewById(R.id.makeRequest);

        // classList.setText("Before method");

        final RequestQueue sampleQueue = Volley.newRequestQueue(this.getContext());

        startRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag_json_obj = "json_obj_req";

                Log.d(TAG, "Clicked button, before Request");

                StringRequest jsonObjReq = new StringRequest(Request.Method.GET,
                        url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("VOLLEY", response);
                        classList.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        classList.setText("Error");
                    }
                });

                Log.d("VOLLEY1", jsonObjReq.toString());
                sampleQueue.add(jsonObjReq);
                Log.d("VOLLEY2", jsonObjReq.toString());
            }
        });

        return v;
    }
}