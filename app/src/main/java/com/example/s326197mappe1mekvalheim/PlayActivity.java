package com.example.s326197mappe1mekvalheim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
    private int correct;
    private int incorrect;

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

    @Override
    public void onBackPressed(){
        exitGameEarlyAlert();
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
        TextView correctNum = findViewById(R.id.correctNum);
        TextView incorrectNum = findViewById(R.id.incorrectNum);

        if(questionManager.checkIfCorrect(answer)){
            textView.setText(R.string.correct);
            textView.setTextColor(ContextCompat.getColor(this, R.color.primary));
            this.correct++;
            correctNum.setText(String.valueOf(correct));

        } else {
            textView.setText(R.string.incorrect);
            textView.setTextColor(ContextCompat.getColor(this, R.color.delete));
            this.incorrect++;
            incorrectNum.setText(String.valueOf(incorrect));
        }
        if(questionManager.hasMoreQuestions()) {
            Log.d("HasMore", "Has more questions");
            textView1.setText(questionManager.nextQuestion());
            editText.setText("");

            this.gameLength = this.gameLength - 1;
            if(this.gameLength == 0){
                roundFinished();
                correctNum.setText(String.valueOf(0));
                incorrectNum.setText(String.valueOf(0));
            }
        } else {
           setScore();
            noMoreQuestionsAlert();
        }
    }

    private void setScore(){
        StatisticManager.setCorrect(this, StatisticManager.getCorrect(this) + correct);
        StatisticManager.setIncorrect(this, StatisticManager.getIncorrect(this) + incorrect);
    }

    private void noMoreQuestionsAlert(){
        new AlertDialog.Builder(this)
                .setTitle("Ferdig!")
                .setMessage("Det er ingen flere oppgaver.")
                .setNeutralButton("Ok", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id){
                        finish();
                        setScore();
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        dialogInterface.cancel();
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        finish();
                        setScore();
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    }
                }).create().show();
    }

    private void exitGameEarlyAlert(){
        new AlertDialog.Builder(this)
                .setTitle("Sikker?")
                .setMessage("Er du sikker på at du ønsker å avslutte før spillet er ferdig?")
                .setNegativeButton("Nei", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("PlayActivity", "clicked NEI");
                        dialogInterface.cancel();
                    }
                })
                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("PlayActivity", "clicked JA");
                        dialogInterface.cancel();
                        finish();
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    }
                }).create().show();
    }

    private void roundFinished(){
        new AlertDialog.Builder(this)
                .setTitle("Runden er ferdig")
                .setMessage("Du fikk " + correct + " rette og " + incorrect + " feil! Ønsker du å starte ny runde?")
                .setNegativeButton("Nei", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setScore();
                        correct = 0;
                        incorrect = 0;
                        dialogInterface.cancel();
                        finish();
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    }
                })
                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setScore();
                        correct = 0;
                        incorrect = 0;
                        dialogInterface.cancel();
                        gameLength = PreferenceManager.getGameLength(PlayActivity.this);
                    }
                }).create().show();
    }

    public void exitGame(View view){
        finish();
    }
}
