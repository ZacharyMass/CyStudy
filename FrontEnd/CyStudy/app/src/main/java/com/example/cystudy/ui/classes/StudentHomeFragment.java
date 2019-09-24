package com.example.cystudy.ui.classes;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cystudy.MainActivity;
import com.example.cystudy.R;
import com.example.cystudy.StudentStatsActivity;

public class StudentHomeFragment extends Fragment {

    private ClassesViewModel mViewModel;

    public static StudentHomeFragment newInstance() {
        return new StudentHomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Button button = inflater.inflate(R.layout.student_home_fragment, container, false).findViewById(R.id.yourStatsButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StudentStatsActivity.class);
                startActivity(intent);
            }
        });

        return inflater.inflate(R.layout.student_home_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ClassesViewModel.class);
        // TODO: Use the ViewModel
    }

}
