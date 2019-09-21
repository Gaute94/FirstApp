package com.example.s326197mappe1mekvalheim;

import android.content.Context;
import android.content.SharedPreferences;

public class StatisticManager {

    public static int getCorrect(Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("statistics_file", Context.MODE_PRIVATE);

        return sharedPreferences.getInt("correct", 0);

    }

    public static void setCorrect(Context context, int correct){
        SharedPreferences sharedPreferences = context.getSharedPreferences("statistics_file", Context.MODE_PRIVATE);

        sharedPreferences.edit().putInt("correct", correct).apply();
    }

    public static int getIncorrect(Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("statistics_file", Context.MODE_PRIVATE);

        return sharedPreferences.getInt("incorrect", 0);

    }

    public static void setIncorrect(Context context, int incorrect){
        SharedPreferences sharedPreferences = context.getSharedPreferences("statistics_file", Context.MODE_PRIVATE);

        sharedPreferences.edit().putInt("incorrect", incorrect).apply();
    }
}
