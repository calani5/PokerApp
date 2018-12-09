package com.example.cmala.pokerapp;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private ArrayList<Card> cards;
    private int score = 0;
    private int scoreWithAce = 0;
    Player(ArrayList<Card> startingHand) {
        cards.addAll(cards);
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int getScoreWithAce() {
        return scoreWithAce;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setScoreWithAce(int scoreWithAce) {
        this.scoreWithAce = scoreWithAce;
    }

    public void update() {
        for (int i = 0; i < getCards().size(); i++) {
            setScore(getCards().get(i).getValue());
            if (getCards().get(i).getValue() == 1) {
                setScoreWithAce(getScore() + 10);
            }
        }
        if (getScore() > 21 && (getScoreWithAce() == 0 || getScoreWithAce() > 21)) {
            Game.gameOver(getCards(), Game.getNumberOfCpus());
        }

    }
}
