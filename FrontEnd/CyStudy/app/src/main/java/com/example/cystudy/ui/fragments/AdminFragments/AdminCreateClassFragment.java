package com.example.cystudy.ui.fragments.AdminFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cystudy.R;

import java.util.Objects;

public class AdminCreateClassFragment extends Fragment {

    private View v;
    EditText className;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_admin_add_class, container, false);

        Button createClassButton = v.findViewById(R.id.adminCreateClassBtn);
        className = v.findViewById(R.id.enterClassNameBox);

        createClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createClass();
            }
        });

        return v;
    }

    public void createClass() {
        String URL = "http://coms-309-jr-7.misc.iastate.edu:8080/add-class?className=";
        URL += className.getText().toString();

        RequestQueue createClassQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));

        StringRequest createClassRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), "Class Added!", Toast.LENGTH_SHORT);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Add Class Error", Objects.requireNonNull(error.getMessage()));
            }
        });

        createClassQueue.add(createClassRequest);
    }
}
