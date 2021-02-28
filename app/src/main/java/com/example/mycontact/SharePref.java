package com.example.mycontact;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePref {
    private Context context;
    private SharedPreferences pref;
    private static final String PREF_NAME = "prefSlider";
    private static final String PREF_KEY = "startslider";

    public SharePref(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
    }
    public boolean StartSlider(){
        return pref.getBoolean(PREF_KEY,true);
    }
    public void setStart(boolean start){
        pref.edit().putBoolean(PREF_KEY,start).apply();
    }

}
