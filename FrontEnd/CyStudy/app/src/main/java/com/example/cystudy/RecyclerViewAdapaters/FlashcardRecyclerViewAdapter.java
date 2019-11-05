package com.example.cystudy.RecyclerViewAdapaters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cystudy.LoginActivity;
import com.example.cystudy.R;
import java.util.ArrayList;

import static com.example.cystudy.ui.fragments.TeacherFragments.TeacherClassFragment.className;

public class FlashcardRecyclerViewAdapter extends RecyclerView.Adapter<FlashcardRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private static ViewHolder viewHolder;

    ArrayList<String> classesList;
    private Context mContext;

    public FlashcardRecyclerViewAdapter(Context context, ArrayList<String> classNames)
    {
        classesList = classNames;
        mContext = context;
        Log.d("mContext", mContext.toString());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_class, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("In OnBindViewHolder", "true");
        holder.classNameTextView.setText(classesList.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (LoginActivity.role.matches("student")) {
                    Navigation.findNavController(view).navigate(R.id.action_studentHomeFragment_to_classFragment);
                }
                else {
                    // int position = viewHolder.getAdapterPosition();
                    className = classesList.get(0); // Hardcoded this just to show something on next page
                    Log.d("Class Name", className);
                    Navigation.findNavController(view).navigate(R.id.action_teacherHomeFragment_to_teacherClassFragment);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return classesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView classNameTextView;
        LinearLayout parentLayout;


        public ViewHolder(View itemView)
        {
            super(itemView);

            classNameTextView = itemView.findViewById(R.id.class_name_text_view);
            parentLayout = itemView.findViewById(R.id.item_layout);
        }
    }
}
