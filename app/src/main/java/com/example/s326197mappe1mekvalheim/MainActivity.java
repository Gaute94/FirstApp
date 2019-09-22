package com.example.s326197mappe1mekvalheim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleManager.setLocale(MainActivity.this, PreferenceManager.getLanguage(this).toLowerCase());
        //It is required to recreate the activity to reflect the change in UI.
        setContentView(R.layout.activity_main);



    }

    public void play(View view){
        if(QuestionManager.getInstance(this).hasMoreQuestions()) {
            startActivity(new Intent(this, PlayActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }else {
            noMoreQuestionsAlert();
        }
    }

    public void preferanser(View view){
        startActivity(new Intent(this, PreferanserActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void statistics(View view){
        startActivity(new Intent(this, StatistikkActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void noMoreQuestionsAlert(){
        new AlertDialog.Builder(this)
                .setTitle("Ferdig!")
                .setMessage("Det er ingen flere oppgaver. Du må starte appen på nytt om du vil spille igjen.")
                .setNeutralButton("Ok", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id){
                        dialogInterface.cancel();
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                      dialogInterface.cancel();
                    }
                }).create().show();
    }
}
