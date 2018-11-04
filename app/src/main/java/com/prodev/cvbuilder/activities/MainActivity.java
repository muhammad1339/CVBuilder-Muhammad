package com.prodev.cvbuilder.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.prodev.cvbuilder.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goPersonalInfoActivity(View view) {
        Intent intent = new Intent(this, PersonalInfoActivity.class);
        startActivity(intent);
    }

    public void goEducationActivity(View view) {
        Intent intent = new Intent(this, EducationActivity.class);
        startActivity(intent);
    }

    public void goSkillsActivity(View view) {
        Intent intent = new Intent(this, SkillsActivity.class);
        startActivity(intent);
    }
}
