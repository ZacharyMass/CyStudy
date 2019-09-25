package com.example.cystudy.ui.classes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cystudy.R;

public class StudentHomeFragment extends Fragment {

    private ClassesViewModel mViewModel;

    public static StudentHomeFragment newInstance() {
        return new StudentHomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Bind button to navigate to StudentStatsFragment
        Button button = inflater.inflate(R.layout.fragment_student_home, container, false).findViewById(R.id.yourStatsButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_studentHomeFragment_to_studentStatsFragment);
            }
        });

        return inflater.inflate(R.layout.fragment_student_home, container, false);
    }

}
