package com.prodev.cvbuilder.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    public interface OnDateOfBirthSet {
        void onDateSet(int y, int m, int d);
    }

    private OnDateOfBirthSet mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnDateOfBirthSet) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DatePickerDialog datePickerDialog;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(getContext(), this, year, month, day);
        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int y, int m, int d) {

        mListener.onDateSet(y, m, d);
    }
}
