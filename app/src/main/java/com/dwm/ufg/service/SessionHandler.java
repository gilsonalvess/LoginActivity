package com.dwm.ufg.service;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionHandler {
    public static final String FOLDER = "SESSION";
    private static final String PROPERTY_TOKEN = "TOKEN";


    public static void saveToken(String inputToken, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(
                FOLDER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PROPERTY_TOKEN, inputToken);
        editor.apply();
    }


    public static String getToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(
                FOLDER, Context.MODE_PRIVATE);
        String token = preferences.getString(PROPERTY_TOKEN, "");
        return token;
    }
}
