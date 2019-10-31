package com.example.cystudy;

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

import java.util.ArrayList;

public class SetsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static RecyclerViewAdapter.ViewHolder viewHolder;

    ArrayList<String> setsList;
    private Context mContext;

    public SetsRecyclerViewAdapter(Context context, ArrayList<String> list)
    {
        setsList = list;
        mContext = context;
        Log.d("mContext", mContext.toString());
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_class, parent, false);
        viewHolder = new RecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Log.d("In OnBindViewHolder", "true");
        holder.classNameTextView.setText(setsList.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Navigation.findNavController(view).navigate(R.id.action_teacherSetsFragment_to_teacherFlashcardFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return setsList.size();
    }
}
