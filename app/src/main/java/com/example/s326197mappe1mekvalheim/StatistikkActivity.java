package com.example.s326197mappe1mekvalheim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        correctText.setText(correct);
        incorrectText.setText(incorrect);
    }
}
