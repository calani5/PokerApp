package com.example.cmala.pokerapp;

import java.net.URL;

public class Card {
    private int value;
    private URL img;
    private String suit;
    private String code;

    Card(String cSuit) {
        suit = cSuit;
    }

    Card() {}

}
