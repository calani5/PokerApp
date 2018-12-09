package com.example.cmala.pokerapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Deck {
    private String deckID;
    private Card newC;
    private ArrayList<Card> deck;

    public Deck() {
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
        } catch (JSONException ignored) { }
    }

    public void setDeckID(String dID) {
        deckID = dID;
    }

    public String getDeckID() {
        return deckID;
    }
}
