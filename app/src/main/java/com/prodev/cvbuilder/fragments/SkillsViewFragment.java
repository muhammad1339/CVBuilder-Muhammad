package com.prodev.cvbuilder.fragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.prodev.cvbuilder.R;
import com.prodev.cvbuilder.data.Constant;
import com.prodev.cvbuilder.data.PrefManager;

import java.util.List;

public class SkillsViewFragment extends Fragment {
    private final String TAG = "SkillsViewFragment";

    public interface OnChangePrefListener {
        void onEdit(List<String> skill, List<String> level);

        void onDelete();
    }

    List<String> skill;
    List<String> level;

    private OnChangePrefListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnChangePrefListener) context;

    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skills_view, container, false);
        skill = PrefManager.getSkills(getContext(), Constant.SKILLS_KEY);
        level = PrefManager.getSkills(getContext(), Constant.LEVEL_KEY);

        LinearLayout linearLayout = view.findViewById(R.id.skills_frag_view);
        if (skill.size() > 0 && level.size() > 0) {
            for (int i = 0; i < level.size(); i++) {
                linearLayout.addView(getTextView(skill.get(i)));
                linearLayout.addView(getProgressBar(level.get(i)));
            }
        }
        Button editButton = view.findViewById(R.id.btn_edit_skill);
        Button deleteButton = view.findViewById(R.id.btn_delete_skill);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onEdit(skill, level);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onDelete();
            }
        });
        return view;
    }


    private TextView getTextView(String text) {
        TextView textView = new TextView(getContext());
        textView.setLayoutParams(getLayoutParams());
        textView.setText(text);
        return textView;
    }

    private ProgressBar getProgressBar(String level) {
        ProgressBar progressBar = new ProgressBar(getContext(), null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setLayoutParams(getLayoutParams());
        progressBar.setMax(99);

        if (level.equals(getResources().getString(R.string.beginner))) {
            progressBar.setProgress(33);
        } else if (level.equals(getResources().getString(R.string.intermediate))) {
            progressBar.setProgress(66);
        } else {
            progressBar.setProgress(99);
        }
        progressBar.setEnabled(false);
        return progressBar;
    }

    private ViewGroup.LayoutParams getLayoutParams() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = (int) getResources().getDimension(R.dimen.eight_dp_margin);
        params.setMargins(margin, margin, margin, margin);
        return params;
    }
}
