package com.aresrd.android.beatrp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Ramadan on 27.12.2016.
 */

public class SessionManager {

    private SharedPreferences prefs;

    public SessionManager(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void SetValue(String key, String value) {
        prefs.edit().putString(key, value).commit();
    }

    public String GetValue(String key) {
        String value = prefs.getString(key,"");
        return value;
    }
}
