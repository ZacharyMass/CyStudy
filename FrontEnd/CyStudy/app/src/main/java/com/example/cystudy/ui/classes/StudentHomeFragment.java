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
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cystudy.App.AppController;
import com.example.cystudy.R;

import org.json.JSONObject;

import static com.example.cystudy.App.AppController.TAG;

public class StudentHomeFragment extends Fragment {

    // public static String url = "http://coms-309-jr-7.misc.iastate.edu:8080/get-users";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Bind button to navigate to StudentStatsFragment
        final View v = inflater.inflate(R.layout.fragment_student_home, container, false);
        Button button = v.findViewById(R.id.yourStatsButton);
        button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.studentStatsFragment, null));
        final TextView classList = v.findViewById(R.id.classesText); // Will be replaced by class text

        // Tag used to cancel the request
        String tag_json_obj ="json_obj_req";
        String url ="https://api.androidhive.info/volley/person_object.json";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url,null,
                new Response.Listener<JSONObject>() {
            @Override
                    public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG,"Error: "+ error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

        return v;
    }
}