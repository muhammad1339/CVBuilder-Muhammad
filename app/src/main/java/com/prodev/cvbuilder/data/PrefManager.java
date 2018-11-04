package com.prodev.cvbuilder.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prodev.cvbuilder.R;

import java.lang.reflect.Type;

public class PrefManager {
    public static void savePref(Object obj, Context context,String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.PREF_FILE, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<GenericObject<Object>>() {
        }.getType();
        GenericObject<Object> genericEducation = new GenericObject<>();
        genericEducation.setObject(obj);
        String eduJson = gson.toJson(genericEducation, type);
        sharedPreferences.edit().putString(key, eduJson).apply();
        Toast.makeText(context, R.string.edu_saved_msg, Toast.LENGTH_SHORT).show();
    }

    public static Education getEducationObject(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.PREF_FILE, Context.MODE_PRIVATE);
        Education education;
        Type type = new TypeToken<GenericObject<Education>>() {
        }.getType();
        Gson gson = new Gson();
        String eduJson = sharedPreferences.getString(Constant.EDUCATION_KEY, "N/A");
        GenericObject<Education> genericEducation = gson.fromJson(eduJson, type);
        education = genericEducation.getObject();
        return education;
    }
}
