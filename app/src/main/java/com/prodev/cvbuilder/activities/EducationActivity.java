package com.prodev.cvbuilder.activities;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.prodev.cvbuilder.R;
import com.prodev.cvbuilder.data.Constant;
import com.prodev.cvbuilder.data.Education;
import com.prodev.cvbuilder.data.PrefManager;
import com.prodev.cvbuilder.fragments.EducationEditFragment;
import com.prodev.cvbuilder.fragments.EducationViewFragment;

public class EducationActivity extends AppCompatActivity
        implements EducationEditFragment.OnSaveListener
        , EducationViewFragment.OnChangePrefListener {
    FragmentManager fragmentManager;
    EducationEditFragment educationEditFragment;
    EducationViewFragment educationViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        educationEditFragment = new EducationEditFragment();
        educationViewFragment = new EducationViewFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (isSharedPrefEmpty()) {
            transaction.replace(R.id.education_frag_container, educationEditFragment).commit();
        } else {
            transaction.replace(R.id.education_frag_container, educationViewFragment).commit();
        }


    }

    private boolean isSharedPrefEmpty() {
        return TextUtils.isEmpty(getSharedPreferences(Constant.PREF_FILE, MODE_PRIVATE)
                .getString(Constant.EDUCATION_KEY, ""));
    }

    @Override
    public void onSave() {
        fragmentManager.beginTransaction().replace(R.id.education_frag_container, educationViewFragment).commit();
    }

    @Override
    public void onEdit(Education education) {
        educationEditFragment.setEducationObject(education);
        fragmentManager.beginTransaction().replace(R.id.education_frag_container, educationEditFragment).commit();
    }

    @Override
    public void onDelete() {
        educationEditFragment.setEducationObject(null);
        PrefManager.clearPref(getApplicationContext(), Constant.EDUCATION_KEY);
        fragmentManager.beginTransaction().replace(R.id.education_frag_container, educationEditFragment).commit();
    }
}
