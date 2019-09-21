package com.example.s326197mappe1mekvalheim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class PlayActivity extends AppCompatActivity {

    private QuestionManager questionManager;
    private int gameLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        this.questionManager = new QuestionManager(this);
        TextView textView = findViewById(R.id.question);
        textView.setText(questionManager.nextQuestion());
        this.gameLength = PreferenceManager.getGameLength(this);
    }

    @Override
    protected void onResume(){
        this.gameLength = PreferenceManager.getGameLength(this);
        super.onResume();
    }

    public void enterNumber(View view){
        EditText editText = findViewById(R.id.answer);
        Button button = (Button)view;
        if(editText.getText().length() < 4) {
            editText.append(button.getText());
        }
    }

    public void delete(View view){
        EditText editText = findViewById(R.id.answer);
        int length = editText.getText().length();
        if (length > 0) {
            editText.getText().delete(length - 1, length);
        }
    }

    public void deleteAll(View view){
        EditText editText = findViewById(R.id.answer);
        editText.setText("");
    }

    public void submit(View view){
        EditText editText = findViewById(R.id.answer);
        TextView textView = findViewById(R.id.response);
        TextView textView1 = findViewById(R.id.question);
        Log.d("EditText text", editText.getText().toString());
        int answer = Integer.parseInt(editText.getText().toString());

        if(questionManager.checkIfCorrect(answer)){
            textView.setText(R.string.correct);
            textView.setTextColor(ContextCompat.getColor(this, R.color.primary));
            StatisticManager.setCorrect(this, StatisticManager.getCorrect(this) + 1);
        } else {
            textView.setText(R.string.incorrect);
            textView.setTextColor(ContextCompat.getColor(this, R.color.delete));
            StatisticManager.setIncorrect(this, StatisticManager.getIncorrect(this) + 1);
        }
        if(questionManager.hasMoreQuestions()) {
            Log.d("HasMore", "Has more questions");
            textView1.setText(questionManager.nextQuestion());
            editText.setText("");
            this.gameLength = this.gameLength - 1;
            if(this.gameLength == 0){
                finish();
            }
        } else {
            Log.d("NoMore", "DOES NOT HAVE MORE QUESTIONS");
            new AlertDialog.Builder(this)
                    .setTitle("Ferdig!")
                    .setMessage("Det er ingen flere oppgaver.")
                    .setNeutralButton("Ok", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int id){
                            finish();
                            dialogInterface.cancel();
                        }
                    })
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            finish();
                        }
                    }).create().show();

        }
    }

//    public int checkGameLength(){
//        int gameLength = PreferenceManager.getGameLength(this);
//
//    }

    public void exitGame(View view){
        finish();
    }
}
