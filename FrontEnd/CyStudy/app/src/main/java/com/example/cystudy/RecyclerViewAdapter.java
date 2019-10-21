package com.example.cystudy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    ArrayList<String> classesList;
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> classNames)
    {
        classesList = classNames;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_class, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.classNameTextView.setText(classesList.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Navigation.findNavController(view).navigate(R.id.action_studentHomeFragment_to_classFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return classesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
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
