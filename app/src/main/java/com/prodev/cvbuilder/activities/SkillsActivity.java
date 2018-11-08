package com.prodev.cvbuilder.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.prodev.cvbuilder.R;
import com.prodev.cvbuilder.data.Constant;
import com.prodev.cvbuilder.data.PrefManager;
import com.prodev.cvbuilder.fragments.SkillsEditFragment;
import com.prodev.cvbuilder.fragments.SkillsViewFragment;

import java.util.ArrayList;
import java.util.List;
import com.prodev.cvbuilder.callbacks.OnSaveListener;

public class SkillsActivity extends AppCompatActivity
        implements OnSaveListener
        , SkillsViewFragment.OnChangePrefListener {
    FragmentManager fragmentManager;
    SkillsEditFragment skillsEditFragment;
    SkillsViewFragment skillsViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        fragmentManager = getSupportFragmentManager();
        skillsEditFragment = new SkillsEditFragment();
        skillsViewFragment = new SkillsViewFragment();
        List<String> skills;
        try {
            skills = PrefManager.getSkills(this, Constant.SKILLS_KEY);
        } catch (Exception ex) {
            skills = new ArrayList<>();
        }
        if (skills.size() > 0) {
            inflateFragment(skillsViewFragment);
        } else {
            inflateFragment(skillsEditFragment);
        }


    }

    @Override
    public void onSave() {
        inflateFragment(skillsViewFragment);
    }

    @Override
    public void onEdit(List<String> skill, List<String> level) {
        skillsEditFragment.setSkills(skill, level);
        inflateFragment(skillsEditFragment);
    }

    @Override
    public void onDelete() {
        PrefManager.clearPref(this, Constant.SKILLS_KEY);
        PrefManager.clearPref(this, Constant.LEVEL_KEY);
        inflateFragment(skillsEditFragment);
    }

    private void inflateFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.skills_frag_container, fragment)
                .commit();
    }
}
