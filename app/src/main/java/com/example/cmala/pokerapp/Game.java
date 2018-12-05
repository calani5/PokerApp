package com.example.cmala.pokerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Game  extends AppCompatActivity {
    private int numberOfCpus = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        numberOfCpus = intent.getIntExtra("numberCpus", 1);
        if (numberOfCpus == 1) {
            setContentView(R.layout.one_cpugame_screen);
        } else if (numberOfCpus == 2) {
            setContentView(R.layout.two_cpugame_screen);
        } else {
            setContentView(R.layout.three_cpugame_screen);
        }
    }
}
