package com.example.s326197mappe1mekvalheim;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QuestionManager {

    private static QuestionManager questionManagerInstance;



    public static QuestionManager getInstance(Context context){
        if(questionManagerInstance == null){
            questionManagerInstance = new QuestionManager(context);
        }
        return questionManagerInstance;
    }
    private Queue<String> questions;

    private int gameLength;
    private int correct;
    private int incorrect;

    private QuestionManager(Context context){
        List<String> questions = Arrays.asList(context.getResources().getStringArray(R.array.math_questions));
        Collections.shuffle(questions);
        this.gameLength = PreferenceManager.getGameLength(context);
        this.questions = new LinkedList<>(questions);
    }
    /*Lage liste av spørsmål*/
    public Queue<String> getQuestions(Context context){
//        List<String> questions = Arrays.asList(context.getResources().getStringArray(R.array.math_questions));
//        Collections.shuffle(questions);
        return this.questions;
    }

    public int getCorrect(){
        return this.correct;
    }

    public void setCorrect(int correct){
        this.correct = correct;
    }

    public int getIncorrect(){
        return this.incorrect;
    }

    public void setIncorrect(int incorrect){
        this.incorrect = incorrect;
    }

    public int getGameLength(){
        return this.gameLength;
    }

    public void setGameLength(int gameLength){
        this.gameLength = gameLength;
    }

    public String nextQuestion(){
        return questions.peek();
    }

    public boolean checkIfCorrect(int answer) {
        boolean correct = (answer == correctAnswer());
        if(correct){
            this.correct++;
        } else {
            this.incorrect++;
        }
        questions.remove();
        return correct;
    }

    private int correctAnswer(){
        String question = questions.peek();
        if(question != null) {
            String[] mathQuestion = question.split("[+]");
            return Integer.parseInt(mathQuestion[0]) + Integer.parseInt(mathQuestion[1]);
        } else {
            Log.e("QuestionManager", "Could not parse question is null");
            return -1;
        }
    }

    public boolean hasMoreQuestions(){
        return questions.peek() != null;
    }


   /* Sjekke om svaret er riktig*/

    /* */

}
