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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        this.questionManager = QuestionManager.getInstance(this);
        TextView textView = findViewById(R.id.question);
        textView.setText(questionManager.nextQuestion());
        Log.d("PlayActivity", "onCreate ble kjørt når skjermen snudde");

        TextView correctNum = findViewById(R.id.correctNum);
        TextView incorrectNum = findViewById(R.id.incorrectNum);
        correctNum.setText(String.valueOf(questionManager.getCorrect()));
        incorrectNum.setText(String.valueOf(questionManager.getIncorrect()));
    }

    @Override
    protected void onResume(){
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
        if(editText.getText().toString().trim().equals("")){
            return;
        }
        TextView textView1 = findViewById(R.id.question);
        int answer = Integer.parseInt(editText.getText().toString());
        TextView correctNum = findViewById(R.id.correctNum);
        TextView incorrectNum = findViewById(R.id.incorrectNum);

        if(questionManager.checkIfCorrect(answer)){
            correctNum.setText(String.valueOf(questionManager.getCorrect()));
            Log.d("PlayActivity", "Correct: " + questionManager.getCorrect());

        } else {
            Log.d("PlayActivity", "incorrect: " + questionManager.getIncorrect());
            incorrectNum.setText(String.valueOf(questionManager.getIncorrect()));
        }
        if(questionManager.hasMoreQuestions()) {
            textView1.setText(questionManager.nextQuestion());
            editText.setText("");

            questionManager.setGameLength(questionManager.getGameLength()-1);
            if(questionManager.getGameLength() == 0){
                roundFinished();
                correctNum.setText(String.valueOf(0));
                incorrectNum.setText(String.valueOf(0));
            }
        } else {
            noMoreQuestionsAlert();
        }
    }

    private void setScore(){
        StatisticManager.setCorrect(this, StatisticManager.getCorrect(this) + questionManager.getCorrect());
        StatisticManager.setIncorrect(this, StatisticManager.getIncorrect(this) + questionManager.getIncorrect());
    }

    private void noMoreQuestionsAlert(){
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.no_more_title))
                .setMessage(getResources().getString(R.string.no_more_message))
                .setNeutralButton(getResources().getString(R.string.click_ok), new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id){
                        finish();
                        setScore();
                        questionManager.setGameLength(PreferenceManager.getGameLength(PlayActivity.this));
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        dialogInterface.cancel();
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        finish();
                        setScore();
                        questionManager.setGameLength(PreferenceManager.getGameLength(PlayActivity.this));
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    }
                }).create().show();
    }

    private void exitGameEarlyAlert(){
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.exit_game_early_title))
                .setMessage(getResources().getString(R.string.exit_game_early_message))
                .setNegativeButton(getResources().getString(R.string.click_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("PlayActivity", "clicked NEI");
                        dialogInterface.cancel();
                    }
                })
                .setPositiveButton(getResources().getString(R.string.click_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("PlayActivity", "clicked JA");
                        dialogInterface.cancel();
                        questionManager.setGameLength(PreferenceManager.getGameLength(PlayActivity.this));
                        finish();
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    }
                }).create().show();
    }

    private void roundFinished(){
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.round_finished_title))
                .setMessage(String.format(getResources().getString(R.string.round_finished_message), questionManager.getCorrect(), questionManager.getIncorrect()))
                .setNegativeButton(getResources().getString(R.string.click_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setScore();
                        questionManager.setCorrect(0);
                        questionManager.setIncorrect(0);
                        questionManager.setGameLength(PreferenceManager.getGameLength(PlayActivity.this));
                        dialogInterface.cancel();
                        finish();
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    }
                })
                .setPositiveButton(getResources().getString(R.string.click_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setScore();
                        questionManager.setCorrect(0);
                        questionManager.setIncorrect(0);
                        questionManager.setGameLength(PreferenceManager.getGameLength(PlayActivity.this));
                        dialogInterface.cancel();
//                        gameLength = PreferenceManager.getGameLength(PlayActivity.this);
                    }
                }).create().show();
    }

    public void exitGame(View view){
        exitGameEarlyAlert();
    }
}
