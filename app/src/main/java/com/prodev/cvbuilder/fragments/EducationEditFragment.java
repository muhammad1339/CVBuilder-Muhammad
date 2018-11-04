package com.prodev.cvbuilder.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.prodev.cvbuilder.R;
import com.prodev.cvbuilder.data.Constant;
import com.prodev.cvbuilder.data.Education;
import com.prodev.cvbuilder.data.PrefManager;


public class EducationEditFragment extends Fragment {
    String uniStage;
    String fromDate;
    String toDate;
    String degree;
    String[] degreeArray;
    Education mEducation;
    private static final String TAG = "EducationEditFragment-";

    public void setEducationObject(Education education) {
        mEducation = education;
    }

    public interface OnSaveListener {
        void onSave();
    }

    OnSaveListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnSaveListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        degreeArray = getResources().getStringArray(R.array.spinner_degree);
    }

    TextInputEditText uniStageText;
    TextInputEditText fromDateText;
    TextInputEditText toDateText;
    Spinner degreeSpinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_education_edit, container, false);
        Button btnSaveEducation = view.findViewById(R.id.btn_save_education);
        configureViews(view);
        fillFields();
        btnSaveEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveEducation();
            }
        });

        return view;
    }

    private void fillFields() {
        if (mEducation != null) {
            uniStageText.setText(mEducation.getUniStage());
            fromDateText.setText(mEducation.getFromDate());
            toDateText.setText(mEducation.getToDate());
            degreeSpinner.setSelection(getArrayIndex(mEducation.getDegree()));
        }
    }


    private void configureViews(View view) {
        uniStageText = view.findViewById(R.id.uni_stage_text);
        fromDateText = view.findViewById(R.id.from_date_text);
        toDateText = view.findViewById(R.id.to_date_text);
        degreeSpinner = view.findViewById(R.id.spinner_degree);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.spinner_degree, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        degreeSpinner.setAdapter(adapter);
        if (degree == null) {
            degree = degreeArray[0];
        }
    }

    private void collectData() {
        uniStage = uniStageText.getText().toString();
        fromDate = fromDateText.getText().toString();
        toDate = toDateText.getText().toString();
        degree = degreeArray[degreeSpinner.getSelectedItemPosition()];
    }

    private void saveEducation() {
        collectData();
        if (TextUtils.isEmpty(uniStage) || TextUtils.isEmpty(fromDate)
                || TextUtils.isEmpty(degree) || TextUtils.isEmpty(toDate)) {
            Toast.makeText(getContext(), R.string.empty_message, Toast.LENGTH_SHORT).show();
        } else {
            Education education = new Education(uniStage, fromDate, toDate, degree);
            PrefManager.savePref(education, getContext(),Constant.EDUCATION_KEY);
            mListener.onSave();
        }
    }


    private int getArrayIndex(String value) {
        for (int i = 0; i < degreeArray.length; i++) {
            if (value.equals(degreeArray[i])) {
                return i;
            }
        }
        return 0;
    }
}

