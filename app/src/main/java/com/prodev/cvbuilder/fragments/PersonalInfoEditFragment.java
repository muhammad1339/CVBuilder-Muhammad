package com.prodev.cvbuilder.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.prodev.cvbuilder.R;
import com.prodev.cvbuilder.callbacks.OnSaveListener;
import com.prodev.cvbuilder.data.Constant;
import com.prodev.cvbuilder.data.PersonalInfo;
import com.prodev.cvbuilder.data.PrefManager;

public class PersonalInfoEditFragment extends Fragment {


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

    OnSaveListener mListener;
    EditText nameEditText;
    EditText emailEditText;
    EditText phoneEditText;
    EditText addressEditText;
    EditText dateEditText;
    Button btnSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_info_edit, container, false);
        configureViews(view);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getFragmentManager(), "");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collectData();
            }
        });
        return view;
    }

    private void configureViews(View view) {
        nameEditText = view.findViewById(R.id.et_name);
        emailEditText = view.findViewById(R.id.et_email);
        addressEditText = view.findViewById(R.id.et_address);
        phoneEditText = view.findViewById(R.id.et_phone);
        dateEditText = view.findViewById(R.id.et_dat_of_birth);
        btnSave = view.findViewById(R.id.btn_save_personal_info);
    }

    private void collectData() {
        String name = nameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String date = dateEditText.getText().toString();
        if (TextUtils.isEmpty(name) ||
                TextUtils.isEmpty(phone) ||
                TextUtils.isEmpty(address) ||
                TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(date)) {
            Toast.makeText(getContext(), R.string.empty_message, Toast.LENGTH_SHORT).show();
        } else {
            PersonalInfo personalInfo = new PersonalInfo(nameEditText.getText().toString()
                    , phoneEditText.getText().toString()
                    , emailEditText.getText().toString()
                    , addressEditText.getText().toString()
                    , dateEditText.getText().toString());
            PrefManager.savePref(personalInfo, getContext(), Constant.PERSONAL_INFO_KEY);
            mListener.onSave();
        }
    }

    public void setDateOfBirth(int y, int m, int d) {
        dateEditText.setText(y + "-" + m + "-" + d);
    }
}
