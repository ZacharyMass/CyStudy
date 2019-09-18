package com.example.cystudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cystudy.ui.classes.ClassesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ClassesFragment.newInstance())
                    .commitNow();
        }
    }
}
