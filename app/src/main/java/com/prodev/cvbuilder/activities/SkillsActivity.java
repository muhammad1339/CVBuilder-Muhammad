package com.prodev.cvbuilder.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.prodev.cvbuilder.R;

public class SkillsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
