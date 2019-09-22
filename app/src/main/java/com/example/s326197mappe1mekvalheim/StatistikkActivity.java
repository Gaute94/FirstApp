package com.example.s326197mappe1mekvalheim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StatistikkActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistikk);
        getCurrentStatistics();
    }

    @Override
    protected void onResume(){
        super.onResume();
        getCurrentStatistics();
    }

    public void getCurrentStatistics(){
        int correct = StatisticManager.getCorrect(this);
        int incorrect = StatisticManager.getIncorrect(this);

        TextView correctText = findViewById(R.id.statistikk_correct);
        TextView incorrectText = findViewById(R.id.statistikk_incorrect);

        correctText.setText(String.valueOf(correct));
        incorrectText.setText(String.valueOf(incorrect));
    }


    public void deleteStatistics(View view){
        StatisticManager.setCorrect(this, 0);
        StatisticManager.setIncorrect(this, 0);

        TextView correctText = findViewById(R.id.statistikk_correct);
        TextView incorrectText = findViewById(R.id.statistikk_incorrect);

        correctText.setText(String.valueOf(getResources().getInteger(R.integer.stat_correct_default)));
        incorrectText.setText(String.valueOf(getResources().getInteger(R.integer.stat_incorrect_default)));
    }
    public void goBack(View view){
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
