package com.example.cmala.pokerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class Game  extends AppCompatActivity {
    private static int numberOfCpus = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Deck deck = new Deck();
        System.out.println(deck.getID());

        Intent intent = getIntent();
        numberOfCpus = intent.getIntExtra("numberCpus", 1);
        Player player = new Player();
        if (numberOfCpus == 1) {
            setContentView(R.layout.one_cpugame_screen);
            CPU bot1 = new CPU();
        } else if (numberOfCpus == 2) {
            setContentView(R.layout.two_cpugame_screen);
            CPU bot1 = new CPU();
            CPU bot2 = new CPU();
        } else {
            setContentView(R.layout.three_cpugame_screen);
            CPU bot1 = new CPU();
            CPU bot2 = new CPU();
            CPU bot3 = new CPU();
        }

    }
    public static int getNumberOfCpus() {
        return numberOfCpus;
    }
    public static void gameOver(ArrayList<Card> playerHand, int numberOfBots) {

    }

}
