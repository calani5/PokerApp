package com.example.cmala.pokerapp;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private ArrayList<Card> cards;
    private int score = 0;
    private int scoreWithAce = 0;
    Player(Card cardOne, Card cardTwo) {
        cards.add(cardOne);
        cards.add(cardTwo);
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int getScoreWithAce() {//
        return scoreWithAce;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setScoreWithAce(int scoreWithAce) {
        this.scoreWithAce = scoreWithAce;
    }
    public void addCard(Card card) {
        cards.add(card);
    }

    public void update() {
        for (int i = 0; i < getCards().size(); i++) {
            setScore(getCards().get(i).getValue());
            if (getCards().get(i).getValue() == 1) {
                setScoreWithAce(getScore() + 10);
            }
        }
        if (getScore() > 21 && (getScoreWithAce() == 0 || getScoreWithAce() > 21)) {
            //Game.gameOver(getCards(), Game.getNumberOfCpus());
            //
        }
        if (getScore() == 21 || getScoreWithAce() == 21) {
            //Game.gameOver(getCards(), Game.getNumberOfCpus());
        }


    }
}
