package com.prodev.cvbuilder.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.prodev.cvbuilder.R;
import com.prodev.cvbuilder.fragments.PersonalInfoEditFragment;
import com.prodev.cvbuilder.fragments.PersonalInfoViewFragment;

public class PersonalInfoActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    PersonalInfoEditFragment personalInfoEditFragment;
    PersonalInfoViewFragment personalInfoViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        personalInfoEditFragment = new PersonalInfoEditFragment();
        personalInfoViewFragment = new PersonalInfoViewFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.personal_info_frag_container, personalInfoEditFragment)
                .commit();
    }
}
