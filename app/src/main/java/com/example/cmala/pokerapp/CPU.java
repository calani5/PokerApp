package com.example.cmala.pokerapp;

import java.util.ArrayList;

public class CPU extends Player {
    private int score = 0;
    private int scoreWithAce = 0;

    CPU(Card cardOne, Card cardTwo) {
        super(cardOne, cardTwo);
    }

    public boolean cpuUpdate() {
        boolean aceFlag = false;
        boolean shouldDraw = true;
        for (int i = 0; i < getCards().size(); i++) {
            setScore(getCards().get(i).getValue());
            if (getCards().get(i).getValue() == 1 && !aceFlag) {
                setScoreWithAce(getScore() + 10);
                aceFlag = true;
            }
        }

        if ((getScore() > 21 && (getScoreWithAce() == 0 || getScoreWithAce() > 21)) && shouldDraw)  {
            /*if (Game.getNumberOfCpus() == 1) {
                gameOver
            } */
            shouldDraw = false;
        }
        if ((getScore() == 21 || getScoreWithAce() == 21) && shouldDraw) {
            /*if (Game.getNumberOfCpus() == 1) {
                gameOver
            } */
            shouldDraw = false;
        }

        if ((getScore() < 17  && (getScoreWithAce() == 0 || getScoreWithAce() > 21)) && shouldDraw) {
            //getCards().add(Game.deck.draw());
        }
        if ((getScoreWithAce() > 17 || getScore() > 17) && shouldDraw) {
            shouldDraw = false;
        }
        return shouldDraw;
    }
}
