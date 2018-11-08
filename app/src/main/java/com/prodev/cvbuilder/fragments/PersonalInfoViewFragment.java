package com.prodev.cvbuilder.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.prodev.cvbuilder.R;
import com.prodev.cvbuilder.data.Constant;
import com.prodev.cvbuilder.data.PersonalInfo;
import com.prodev.cvbuilder.data.PrefManager;

public class PersonalInfoViewFragment extends Fragment {
    public interface OnChangePrefListener {
        void onEdit(PersonalInfo personalInfo);

        void onDelete();
    }

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

    TextView nameTextView;
    TextView phoneTextView;
    TextView emailTextView;
    TextView addressTextView;
    TextView dateTextView;
    Button btnEdit;
    Button btnDelete;
    PersonalInfo personalInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_info_view, container, false);
        configureViews(view);
        final PersonalInfo personalInfo = PrefManager.getPersonalInfoObject(getContext(), Constant.PERSONAL_INFO_KEY);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onEdit(personalInfo);
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

    private void configureViews(View view) {
        nameTextView = view.findViewById(R.id.tv_name);
        phoneTextView = view.findViewById(R.id.tv_phone);
        emailTextView = view.findViewById(R.id.tv_email);
        addressTextView = view.findViewById(R.id.tv_address);
        dateTextView = view.findViewById(R.id.tv_date_of_birth);
        btnDelete = view.findViewById(R.id.btn_delete_personal_info);
        btnEdit = view.findViewById(R.id.btn_edit_personal_info);
    }

}
