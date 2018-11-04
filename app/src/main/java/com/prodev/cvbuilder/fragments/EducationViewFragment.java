package com.prodev.cvbuilder.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.prodev.cvbuilder.R;
import com.prodev.cvbuilder.data.Education;
import com.prodev.cvbuilder.data.PrefManager;


public class EducationViewFragment extends Fragment {
    public interface OnChangePrefListener {
        void onEdit(Education education);

        void onDelete();
    }

    private static final String TAG = "EducationViewFragment-";
    private TextView eduTextView;
    private TextView degreeTextView;
    private TextView fromDateTextView;
    private TextView toDateTextView;
    private Button btnEdit;
    private Button btnDelete;
    private OnChangePrefListener mListener;
    private Education mEducation;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEducation = PrefManager.getEducationObject(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_education_view, container, false);
        Log.d(TAG, "onCreateView: " + mEducation.toString());
        configureViews(view);
        if (mEducation != null) {
            showData(mEducation);
        }
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onEdit(mEducation);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onDelete();
            }
        });
        return view;
    }

    private void showData(Education education) {
        eduTextView.setText(education.getUniStage());
        degreeTextView.setText(education.getDegree());
        fromDateTextView.setText(education.getFromDate());
        toDateTextView.setText(education.getToDate());
    }

    private void configureViews(View view) {
        eduTextView = view.findViewById(R.id.text_view_edu_stage);
        degreeTextView = view.findViewById(R.id.text_view_degree);
        fromDateTextView = view.findViewById(R.id.text_view_from_date);
        toDateTextView = view.findViewById(R.id.text_view_to_date);
        btnEdit = view.findViewById(R.id.btn_edit);
        btnDelete = view.findViewById(R.id.btn_delete);
    }


}
