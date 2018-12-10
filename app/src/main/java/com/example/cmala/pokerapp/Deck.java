package com.example.cmala.pokerapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Deck {
    private String deckID;
    private Card newC;
    private ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<>();
    }

    public Card draw() {
        Card toR = deck.get(0);
        deck.remove(0);
        return toR;
    }

    public void createDeck(final JSONObject response) {
        try {
            JSONArray cards = response.getJSONArray("cards");
            for (int i = 0; i < cards.length(); i++) {
                String suit = cards.getJSONObject(i).getString("suit");
                String code = cards.getJSONObject(i).getString("code");
                String value = cards.getJSONObject(i).getString("value");
                String url = cards.getJSONObject(i).getString("image");
                Card newCard = new Card(suit, value, url, code);
                deck.add(newCard);
            }
            Game.setUp();
        } catch (JSONException ignored) { }
    }

    public void setDeckID(String dID) {
        deckID = dID;
    }

    public String getDeckID() {
        return deckID;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
}
