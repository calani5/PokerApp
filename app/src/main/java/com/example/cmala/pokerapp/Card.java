package com.example.cmala.pokerapp;

import java.net.URL;

public class Card {
    private int value;
    private URL img;
    private String suit;
    private String code;

    Card(String cSuit, int val, URL url, String cCode) {
        suit = cSuit;
        value = val;
        img = url;
        code = cCode;
    }

    Card() {}

    public String getSuit() {
        return suit;
    }
    public int getValue(){ return value; }
    public URL getImg(){ return img; }
    public String getCode() {return code; }

}
