package com.example.cmala.pokerapp;

import java.net.URL;

public class Card {
    private int value;
    private int valueTwoIfAce;
    private URL img;
    private String suit;
    private String code;

    Card(String cSuit, String tovalue, String url, String code) {
        suit = cSuit;
        if (tovalue.equalsIgnoreCase("ACE")) {
            value = 1;
            valueTwoIfAce = 11;
        } else if (tovalue.equalsIgnoreCase("KING") || tovalue.equalsIgnoreCase("QUEEN") || tovalue.equalsIgnoreCase("JACK")) {
            value = 10;
        } else {
            value = Integer.parseInt(tovalue);
        }

    }

    Card() {}

    public int getValue (){
        return value;
    }


}
