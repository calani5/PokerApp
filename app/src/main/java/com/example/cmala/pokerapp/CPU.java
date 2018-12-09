package com.example.cmala.pokerapp;

import java.util.ArrayList;

public class CPU extends Player {
    private int score = 0;
    private int scoreWithAce = 0;

    CPU(ArrayList<Card> startingHand) {
        super(startingHand);
    }

}
