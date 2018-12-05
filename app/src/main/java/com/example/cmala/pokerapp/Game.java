package com.example.cmala.pokerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Game  extends AppCompatActivity {
    private int numberOfCpus = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);

        Intent intent = getIntent();
         numberOfCpus = intent.getIntExtra("numberCpus", 1);
    }
}
