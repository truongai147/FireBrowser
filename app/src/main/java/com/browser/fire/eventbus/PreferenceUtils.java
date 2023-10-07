package com.browser.fire.eventbus;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.browser.fire.App;

public class PreferenceUtils {

    private static final String CHOOSED_THEMES = "CHOOSED_THEMES";


    private static SharedPreferences preferences;

    public static synchronized void init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
    }

}
