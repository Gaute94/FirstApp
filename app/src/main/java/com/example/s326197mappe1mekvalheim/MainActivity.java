package com.example.s326197mappe1mekvalheim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void play(View view){
        startActivity(new Intent(this, PlayActivity.class));
    }

    public void preferanser(View view){
        startActivity(new Intent(this, PreferanserActivity.class));
    }

    public void statistics(View view){
        startActivity(new Intent(this, StatistikkActivity.class));
    }
}
