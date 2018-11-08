package com.prodev.cvbuilder.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.prodev.cvbuilder.R;
import com.prodev.cvbuilder.data.Constant;
import com.prodev.cvbuilder.data.PrefManager;

import java.util.ArrayList;
import java.util.List;
import com.prodev.cvbuilder.callbacks.OnSaveListener;

public class SkillsEditFragment extends Fragment {


    OnSaveListener mListener;

    LinearLayout linearLayout;
    List<EditText> editTextList;
    List<RadioGroup> radioGroupList;
    List<String> skill;
    List<String> level;
    private int dynamicID = 100;
    private final String TAG = "SkillsEditFragment";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dynamicID = 100;
        mListener = (OnSaveListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setSkills(List<String> skill, List<String> level) {
        this.skill = skill;
        this.level = level;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skills_edit, container, false);
        editTextList = new ArrayList<>();
        radioGroupList = new ArrayList<>();

        linearLayout = view.findViewById(R.id.linear_skills);
        if (skill == null || level == null) {
            skill = new ArrayList<>();
            level = new ArrayList<>();
            EditText editTextFirstSkill = getEditText();
            editTextList.add(editTextFirstSkill);
            linearLayout.addView(editTextFirstSkill);

            RadioGroup firstRadioGroup = getRadioGroup();
            linearLayout.addView(firstRadioGroup);
            radioGroupList.add(firstRadioGroup);
            Log.d(TAG, "radioGroupList: " + radioGroupList.size());
        } else {
            fillData();
            Log.d(TAG, "level: " + level.size());
        }


        FloatingActionButton fabAddEditText = view.findViewById(R.id.fab_add_skill);
        fabAddEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = getEditText();
                linearLayout.addView(editText);
                editTextList.add(editText);
                RadioGroup radioGroup = getRadioGroup();
                linearLayout.addView(radioGroup);
                radioGroupList.add(radioGroup);
            }
        });

        Button saveButton = view.findViewById(R.id.btn_save_skills);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collectData();
            }
        });
        return view;
    }

    private EditText getEditText() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = (int) getResources().getDimension(R.dimen.eight_dp_margin);
        params.setMargins(margin, margin, margin, margin);
        EditText editText = new EditText(getContext());
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setLayoutParams(params);
        editText.setHint(R.string.new_skill);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            editText.setHintTextColor(getResources().getColor(android.R.color.darker_gray, null));
        } else {
            editText.setHintTextColor(getResources().getColor(android.R.color.darker_gray));
        }
        return editText;
    }

    private RadioGroup getRadioGroup() {
        RadioGroup radioGroup = new RadioGroup(getContext());
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        radioGroup.setLayoutParams(params);


        radioGroup.addView(getRadioButton(R.string.beginner));
        radioGroup.addView(getRadioButton(R.string.intermediate));
        radioGroup.addView(getRadioButton(R.string.advanced));
        return radioGroup;
    }

    private RadioButton getRadioButton(int stringRes) {
        RadioButton radioButton = new RadioButton(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        radioButton.setText(stringRes);
        radioButton.setLayoutParams(params);
        radioButton.setId(dynamicID);
        dynamicID++;
        return radioButton;
    }

    private void collectData() {
        PrefManager.clearPref(getContext(), Constant.SKILLS_KEY);
        PrefManager.clearPref(getContext(), Constant.LEVEL_KEY);
        skill.clear();
        level.clear();
        int currentID = 100;
        for (RadioGroup radioGroup : radioGroupList) {
            int id = radioGroup.getCheckedRadioButtonId();
            String currentChoiceText = "";
            if (id == currentID) {
                currentChoiceText = getResources().getString(R.string.beginner);
            } else if (id == currentID + 1) {
                currentChoiceText = getResources().getString(R.string.intermediate);
            } else if (id == currentID + 2) {
                currentChoiceText = getResources().getString(R.string.advanced);
            }
            if (TextUtils.isEmpty(currentChoiceText)) {
                Toast.makeText(getContext(), R.string.empty_message, Toast.LENGTH_SHORT).show();
                return;
            } else {
                level.add(currentChoiceText);
            }
            currentID += 3;
        }
        for (EditText editText : editTextList) {
            String currentSkill = editText.getText().toString();
            if (TextUtils.isEmpty(currentSkill)) {
                Toast.makeText(getContext(), R.string.empty_message, Toast.LENGTH_SHORT).show();
                return;
            } else {
                skill.add(currentSkill);
            }
        }
        if (skill.size() > 0 && level.size() > 0) {
            PrefManager.savePref(skill, getContext(), Constant.SKILLS_KEY);
            PrefManager.savePref(level, getContext(), Constant.LEVEL_KEY);
            mListener.onSave();
        } else {
            Toast.makeText(getContext(), R.string.empty_message, Toast.LENGTH_SHORT).show();
        }
    }

    private void fillData() {
        int currentChoiceID = 100;

        for (int i = 0; i < level.size(); i++) {
            Log.d(TAG, "currentChoiceID: " + currentChoiceID);
            Log.d(TAG, "dynamicID: " + dynamicID);
            EditText editText = getEditText();
            editText.setText(skill.get(i));
            linearLayout.addView(editText);
            editTextList.add(editText);
            RadioGroup radioGroup = getRadioGroup();
            if (level.get(i).equals(getResources().getString(R.string.beginner))) {
                radioGroup.check(currentChoiceID);
            } else if (level.get(i).equals(getResources().getString(R.string.intermediate))) {
                radioGroup.check(currentChoiceID + 1);
            } else if (level.get(i).equals(getResources().getString(R.string.advanced))) {
                radioGroup.check(currentChoiceID + 2);
            }
            currentChoiceID += 3;
            linearLayout.addView(radioGroup);
            radioGroupList.add(radioGroup);
        }
    }
}
