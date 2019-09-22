package com.example.s326197mappe1mekvalheim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PreferanserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferanser);

        getCurrentLanguage();
        getCurrentGameLength();
    }

    public void selectPreference(View view){
        Button button_1 = findViewById(R.id.preferanse_button_1);
        Button button_2 = findViewById(R.id.preferanse_button_2);
        Button button_3 = findViewById(R.id.preferanse_button_3);
        int selectedDrawable = R.drawable.preferanse_buttons;
        int defaultDrawable = R.drawable.buttons;
        switch (view.getId()){
            case R.id.preferanse_button_1:
                button_1.setBackground(ContextCompat.getDrawable(this, selectedDrawable));
                button_2.setBackground(ContextCompat.getDrawable(this, defaultDrawable));
                button_3.setBackground(ContextCompat.getDrawable(this, defaultDrawable));
                PreferenceManager.setGameLength(this, 5);
                break;
            case R.id.preferanse_button_2:
                button_1.setBackground(ContextCompat.getDrawable(this, defaultDrawable));
                button_2.setBackground(ContextCompat.getDrawable(this, selectedDrawable));
                button_3.setBackground(ContextCompat.getDrawable(this, defaultDrawable));
                PreferenceManager.setGameLength(this, 10);
                break;
            case R.id.preferanse_button_3:
                button_1.setBackground(ContextCompat.getDrawable(this, defaultDrawable));
                button_2.setBackground(ContextCompat.getDrawable(this, defaultDrawable));
                button_3.setBackground(ContextCompat.getDrawable(this, selectedDrawable));
                PreferenceManager.setGameLength(this, 25);
                break;
        }
    }

    public void selectLanguage(View view){
        Button norsk_button = findViewById(R.id.norsk_button);
        Button tysk_button = findViewById(R.id.tysk_button);

        int selectedDrawable = R.drawable.preferanse_buttons;
        int defaultDrawable = R.drawable.buttons;

        switch (view.getId()){
            case R.id.norsk_button:
                norsk_button.setBackground(ContextCompat.getDrawable(this, selectedDrawable));
                tysk_button.setBackground(ContextCompat.getDrawable(this, defaultDrawable));
                PreferenceManager.setLanguage(this, "no");
                break;
            case R.id.tysk_button:
                tysk_button.setBackground(ContextCompat.getDrawable(this, selectedDrawable));
                norsk_button.setBackground(ContextCompat.getDrawable(this, defaultDrawable));
                PreferenceManager.setLanguage(this, "de");
                break;
        }
    }

    public void getCurrentLanguage(){
        String language = PreferenceManager.getLanguage(this);
        Button button1 = findViewById(R.id.norsk_button);
        Button button2 = findViewById(R.id.tysk_button);

        int defaultDrawable = R.drawable.buttons;
        int selectedDrawable = R.drawable.preferanse_buttons;

        if (language.equals("no")){
            button1.setBackground(ContextCompat.getDrawable(this, selectedDrawable));
            button2.setBackground(ContextCompat.getDrawable(this, defaultDrawable));
        } else {
            button1.setBackground(ContextCompat.getDrawable(this, defaultDrawable));
            button2.setBackground(ContextCompat.getDrawable(this, selectedDrawable));
        }
    }

    public void getCurrentGameLength(){
        int gameLength = PreferenceManager.getGameLength(this);
        Button button1 = findViewById(R.id.preferanse_button_1);
        Button button2 = findViewById(R.id.preferanse_button_2);
        Button button3 = findViewById(R.id.preferanse_button_3);

        int defaultDrawable = R.drawable.buttons;
        int selectedDrawable = R.drawable.preferanse_buttons;
        /* todo: Kanskje fjerne dette og sette default valgt knapp (5) */
        if(gameLength == 5){
            button1.setBackground(ContextCompat.getDrawable(this, selectedDrawable));
            button2.setBackground(ContextCompat.getDrawable(this, defaultDrawable));
            button3.setBackground(ContextCompat.getDrawable(this, defaultDrawable));
        } else if (gameLength == 10){
            button1.setBackground(ContextCompat.getDrawable(this, defaultDrawable));
            button2.setBackground(ContextCompat.getDrawable(this, selectedDrawable));
            button3.setBackground(ContextCompat.getDrawable(this, defaultDrawable));
        } else {
            button1.setBackground(ContextCompat.getDrawable(this, defaultDrawable));
            button2.setBackground(ContextCompat.getDrawable(this, defaultDrawable));
            button3.setBackground(ContextCompat.getDrawable(this, selectedDrawable));
        }
    }
    public void goBack(View view){
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
