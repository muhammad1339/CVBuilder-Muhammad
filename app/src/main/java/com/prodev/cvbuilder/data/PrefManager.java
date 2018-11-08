package com.prodev.cvbuilder.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prodev.cvbuilder.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PrefManager {
    public static void savePref(Object obj, Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.PREF_FILE, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<GenericObject<Object>>() {
        }.getType();
        GenericObject<Object> genericEducation = new GenericObject<>();
        genericEducation.setObject(obj);
        String eduJson = gson.toJson(genericEducation, type);
        sharedPreferences.edit().putString(key, eduJson).apply();
        Toast.makeText(context, R.string.saved_msg, Toast.LENGTH_SHORT).show();
    }

    public static Education getEducationObject(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.PREF_FILE, Context.MODE_PRIVATE);
        Type type = new TypeToken<GenericObject<Education>>() {
        }.getType();
        Gson gson = new Gson();
        String eduJson = sharedPreferences.getString(key, "");
        GenericObject<Education> genericEducation = gson.fromJson(eduJson, type);
        return genericEducation.getObject();
    }

    public static PersonalInfo getPersonalInfoObject(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.PREF_FILE, Context.MODE_PRIVATE);
        Type type = new TypeToken<GenericObject<PersonalInfo>>() {
        }.getType();
        Gson gson = new Gson();
        String personalInfoJson = sharedPreferences.getString(key, "");
        GenericObject<PersonalInfo> personalInfoGenericObject = gson.fromJson(personalInfoJson, type);
        return personalInfoGenericObject.getObject();
    }

    public static List<String> getSkills(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.PREF_FILE, Context.MODE_PRIVATE);
        Type type = new TypeToken<GenericObject<List<String>>>() {
        }.getType();
        Gson gson = new Gson();
        String skillJson = sharedPreferences.getString(key, "");
        GenericObject<List<String>> listGenericObject = gson.fromJson(skillJson, type);

        return listGenericObject.getObject();
    }

    public static void clearPref(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.PREF_FILE, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(key).apply();
    }
}
