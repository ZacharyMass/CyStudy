package com.example.cystudy.ui.classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cystudy.LoginActivity;
import com.example.cystudy.R;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.GroupieViewHolder;
import com.xwray.groupie.Item;

public class StudentHomeFragment extends Fragment {

    String username = LoginActivity.user; // Username from LoginActivity is global here
    String url = "http://coms-309-jr-7.misc.iastate.edu:8080/get-users-classes?username=" + username;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Declare v as fragment view
        final View v = inflater.inflate(R.layout.fragment_student_home, container, false);

        // use a linear layout manager
        RecyclerView recyclerView = v.findViewById(R.id.classes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // specify an adapter (see also next example)
        String[] myDataset = {"Class 1", "Class2"};
        GroupAdapter adapter = new GroupAdapter<>();

        //Pull list of classes from server
        //TODO

        //Create new class object for each item pulled from server
        adapter.add(new ClassItem());
        adapter.add(new ClassItem());
        adapter.add(new ClassItem());

        // Set the adapter for the recycler view
        recyclerView.setAdapter(adapter);

        return v;
    }
}

class ClassItem extends Item{
    String className;

    @Override
    public void bind(@NonNull GroupieViewHolder viewHolder, int position) {
        //will be called in our list for each object later on
    }

    @Override
    public int getLayout() {
        return R.layout.class_row;
    }
}