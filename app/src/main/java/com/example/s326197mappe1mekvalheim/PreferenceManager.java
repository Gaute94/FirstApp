package com.example.s326197mappe1mekvalheim;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    public static String getLanguage(Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("preferences_file", Context.MODE_PRIVATE);

        return sharedPreferences.getString("language", "no");

    }

    public static void setLanguage(Context context, String lang){
        SharedPreferences sharedPreferences = context.getSharedPreferences("preferences_file", Context.MODE_PRIVATE);

        sharedPreferences.edit().putString("language", lang).apply();
    }

    public static int getGameLength(Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("preferences_file", Context.MODE_PRIVATE);

        return sharedPreferences.getInt("gameLength", 5);

    }

    public static void setGameLength(Context context, int gameLength){
        SharedPreferences sharedPreferences = context.getSharedPreferences("preferences_file", Context.MODE_PRIVATE);

        sharedPreferences.edit().putInt("gameLength", gameLength).apply();
    }

}
